package com.shtrobotice.ShtKit.hardware.OpMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.shtrobotice.ShtKit.hardware.Hardware;

public abstract class SHTOpMode extends LinearOpMode {
    public volatile Hardware hardware;

    @Override
    public final void runOpMode() {
        hardware = new Hardware(hardwareMap,gamepad1,gamepad2);
        Init();
        waitForStart();
        Start();
        while (opModeIsActive()) {
            Loop();
        }
    }
    public void Init() {}
    public void Start() {}
    public void Loop() {}
}
