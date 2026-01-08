package com.shtrobotice.ShtKit.hardware.Camera.SimpleCamera.pipelines;

import org.openftc.easyopencv.OpenCvPipeline;

public enum Pipelines {
    NORMAL{ @Override public OpenCvPipeline get() { return new NormalViewPipeline(); } },
    INVERTED{ @Override public OpenCvPipeline get() { return new InvertedColorPipeline(); } },
    FILTER_RED{ @Override public OpenCvPipeline get() { return new RedFilterPipeline(); } },
    FILTER_BLUE{ @Override public OpenCvPipeline get() { return new BlueFilterPipeline(); } },
    FILTER_YELLOW{ @Override public OpenCvPipeline get() { return new YellowFilterPipeline(); } },
    GRAY_SCALE{ @Override public OpenCvPipeline get() { return new GrayscalePipeline(); } },
    APRIL_TAG{ @Override public OpenCvPipeline get() { return new AprilTagDetectionPipeline(April_TagSize, April_FX, April_FY, April_CX, April_CY); } };

    public static double April_FX = 578.272;
    public static double April_FY = 578.272;
    public static double April_CX = 402.145;
    public static double April_CY = 221.506;
    public static double April_TagSize = 0.166;

    public abstract OpenCvPipeline get();
}
