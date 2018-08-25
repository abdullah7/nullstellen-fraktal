package fraktal;

public abstract class FraktalProgram implements Renderable {

    protected Point2F center;
    protected int winWidth;
    protected int winHeight;

    public void setWindowSize(int width, int height) {
        winWidth = width;
        winHeight = height;
    }
}
