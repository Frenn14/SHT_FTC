package com.shtrobotice.ShtKit.hardware.Camera.SimpleCamera.pipelines;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class GrayscalePipeline extends OpenCvPipeline {
    private final Mat gray = new Mat();

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, gray, Imgproc.COLOR_RGB2GRAY);
        return gray;
    }
}

