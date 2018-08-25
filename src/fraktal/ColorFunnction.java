package fraktal;

import java.awt.*;

public class ColorFunnction {
    public static Color farbVerlauf(final Color cStart, final Color cEnd, final int iterMax, final int iterCur) {
        final double dR = ((cEnd.getRed() - cStart.getRed()) / (double) iterMax);
        final double dG = ((cEnd.getGreen() - cStart.getGreen()) / (double) iterMax);
        final double dB = ((cEnd.getBlue() - cStart.getBlue()) / (double) iterMax);

        return new Color((int) (cStart.getRed() + dR * iterCur),
                (int) (cStart.getGreen() + dG * iterCur),
                (int) (cStart.getBlue() + dB * iterCur));
    }

    public static Color farbVerlauf(final Color cStart, final Color cEnd, final double percentage) {
        return farbVerlauf(cStart, cEnd, 100, (int) (percentage * 100));
    }

}
