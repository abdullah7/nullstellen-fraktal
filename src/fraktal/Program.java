package fraktal;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Program extends JPanel {


    private static final long serialVersionUID = 1L;
    private final BufferedImage mbImage;
    private int width, height;
    private Renderer renderer;

    FraktalProgram currentFraktal;

    public Program(final int winWidth, final int winHeight, final int iThreads) {
        width = winWidth;
        height = winHeight;
        mbImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        currentFraktal = new NullstellenFraktal();
        currentFraktal.setWindowSize(width, height);
        changeFraktal();
    }

    @Override
    public void paint(Graphics g) {
        renderer.setFraktalProgram(currentFraktal);
        renderer.renderImage();
        g.drawImage(mbImage, 0, 0, null);
        g.setColor(Color.white);
        repaint();
    }

    public static void main(String[] args) {
            int width = 1024;
            int height = 780;
            Program mbProgram = new Program(width, height, 12);
            final JFrame frame = new JFrame("Null Fraktal");
            frame.add(mbProgram);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            Insets winInsets = frame.getInsets();
            frame.setSize(width + winInsets.left + winInsets.right, height + winInsets.top + winInsets.bottom);
    }

    private void changeFraktal() {
        int iThreads = 8;
        renderer = new Renderer(currentFraktal, mbImage, iThreads, new Dimension(160, 160));
    }

}
