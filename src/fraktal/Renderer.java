package fraktal;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer {

    final int width, height;
    final int iThreads;
    final RenderThread[] activeThreads;
    final BufferedImage biImage;
    final Dimension threadRenderArea;

    final int xThreads, yThreads;

    private FraktalProgram fraktal;

    public void renderImage() {
        int x, y;
        for (int i = 0; i < iThreads; i++) {
            x = i % xThreads;
            y = i / xThreads;
            activeThreads[i] = new RenderThread(biImage, fraktal, x * threadRenderArea.width, y * threadRenderArea.height, threadRenderArea.width, threadRenderArea.height, fraktal.center);
            activeThreads[i].start();
        }

        int thrCount = iThreads;
        int anzThreads = xThreads * yThreads;
        while (thrCount < anzThreads) {
            for (int i = 0; i < iThreads; i++) {
                if (activeThreads[i].isAlive()) {
                    try {
                        Thread.sleep(0, 100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    x = thrCount % xThreads;
                    y = thrCount / xThreads;
                    activeThreads[i] = new RenderThread(biImage, fraktal, x * threadRenderArea.width, y * threadRenderArea.height, threadRenderArea.width, threadRenderArea.height, fraktal.center);
                    activeThreads[i].start();
                    thrCount++;
                    if (!(thrCount < anzThreads))
                        break;
                }
            }
        }

        for (RenderThread thread : activeThreads) {
            while (thread.isAlive()) {
                try {
                    Thread.sleep(0, 100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Renderer(FraktalProgram fraktal, BufferedImage biImage, int iThreads, Dimension renderArea) {
        width = biImage.getWidth();
        height = biImage.getHeight();
        this.biImage = biImage;
        threadRenderArea = renderArea;
        xThreads = (int) Math.ceil(width / (double) threadRenderArea.width);
        yThreads = (int) Math.ceil(height / (double) threadRenderArea.height);
        this.iThreads = Math.min(iThreads, xThreads * yThreads);
        activeThreads = new RenderThread[this.iThreads];
        this.fraktal = fraktal;
    }

    public void setFraktalProgram(FraktalProgram frak) {
        fraktal = frak;
    }

}
