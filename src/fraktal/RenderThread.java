package fraktal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class RenderThread extends Thread {

    Point2F center;
    final int x, y, renderWidth, renderHeight;
    final byte[] pixelArray;
    final int winWidth, winHeight;
    private final FraktalProgram fraktal;

    public RenderThread(BufferedImage biImage, FraktalProgram fraktal, int x, int y, int renderWidth, int renderHeight, Point2F center) {
        this.fraktal = fraktal;
        this.x = x;
        this.y = y;
        this.renderWidth = renderWidth;
        this.renderHeight = renderHeight;
        pixelArray = ((DataBufferByte) (biImage.getRaster().getDataBuffer())).getData();
        winWidth = biImage.getWidth();
        winHeight = biImage.getHeight();
        this.center = new Point2F(center);

    }

    @Override
    public void run() {
        renderImage();
    }

    private void renderImage() {
        int xMax = Math.min(x + renderWidth, winWidth);
        int yMax = Math.min(y + renderHeight, winHeight);
        Color col;
        Point2F renderPoint;
        for (int iX = x; iX < xMax; iX++) {
            for (int iY = y; iY < yMax; iY++) {
                renderPoint = calculatePixelRealCoordinates(iX, iY, winWidth, winHeight, center);
                col = fraktal.calcPixel(renderPoint);
                pixelArray[iY * winWidth * 3 + iX * 3] = (byte) col.getBlue();
                pixelArray[iY * winWidth * 3 + iX * 3 + 1] = (byte) col.getGreen();
                pixelArray[iY * winWidth * 3 + iX * 3 + 2] = (byte) col.getRed();
            }
        }

    }

    private Point2F calculatePixelRealCoordinates(int x, int y, int winWidth, int winHeight, Point2F center) {
        return new Point2F(center.x + (x / (double)winWidth - 0.5) , center.y + (y / (double)winHeight - 0.5));
    }


}
