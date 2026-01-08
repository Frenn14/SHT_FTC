package com.shtrobotice.ShtKit.hardware.Camera.SimpleCamera.pipelines;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class NormalViewPipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}
