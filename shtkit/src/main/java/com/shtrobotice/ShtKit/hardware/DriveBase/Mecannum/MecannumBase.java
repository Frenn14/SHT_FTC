package com.shtrobotice.ShtKit.hardware.DriveBase.Mecannum;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.shtrobotice.ShtKit.hardware.DriveBase.DriveBase;

public class MecannumBase extends DriveBase {
    public MecannumBase(HardwareMap hardwareMap, String leftFront, String leftBack, String rightFront, String rightBack) {
        super(hardwareMap, leftFront, leftBack, rightFront, rightBack);
    }
    public MecannumBase(HardwareMap hardwareMap, String leftFront, String leftBack, String rightFront, String rightBack, Gamepad gamepad) {
        super(hardwareMap, leftFront, leftBack, rightFront, rightBack, gamepad);
    }
    public void setHeadless() { }

    @Override
    public void update() {
        double x = inX.get();
        double y = inY.get();
        double r = inRot.get();

        double lfp = y + x + r;
        double lbp = y - x + r;
        double rfp = y - x - r;
        double rbp = y + x - r;

        double max = Math.max(
                Math.max(Math.abs(lfp), Math.abs(lbp)),
                Math.max(Math.abs(rfp), Math.abs(rbp))
        );

        if (max > 1.0) {
            lfp /= max;
            lbp /= max;
            rfp /= max;
            rbp /= max;
        }

        lf.setPower(lfp);
        lb.setPower(lbp);
        rf.setPower(rfp);
        rb.setPower(rbp);
    }
}
