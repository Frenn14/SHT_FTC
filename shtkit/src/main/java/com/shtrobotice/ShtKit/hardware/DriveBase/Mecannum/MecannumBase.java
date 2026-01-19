package com.shtrobotice.ShtKit.hardware.DriveBase.Mecannum;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.shtrobotice.ShtKit.hardware.DriveBase.DriveBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.function.Supplier;

public class MecannumBase extends DriveBase {

    Supplier<Double> getHeading;
    Boolean reversed;
    public MecannumBase(HardwareMap hardwareMap, String leftFront, String leftBack, String rightFront, String rightBack) {
        super(hardwareMap, leftFront, leftBack, rightFront, rightBack);
    }
    public MecannumBase(HardwareMap hardwareMap, Gamepad gamepad, String leftFront, String leftBack, String rightFront, String rightBack) {
        super(hardwareMap, gamepad, leftFront, leftBack, rightFront, rightBack);
    }

    public void setHeadless(Class<?> headlessClass, String name) {
        setHeadless(headlessClass, name, false);
    }
    public void setHeadless(Class<?> headlessClass, String name, Boolean reversed) {
        this.reversed = reversed;
        if (headlessClass.equals(GoBildaPinpointDriver.class)) {
            GoBildaPinpointDriver pp = hm.get(GoBildaPinpointDriver.class, name);
            pp.resetPosAndIMU();
            getHeading = ()->{ pp.update(GoBildaPinpointDriver.ReadData.ONLY_UPDATE_HEADING); return pp.getHeading(AngleUnit.RADIANS); };
        }
    }

    @Override
    public void update() {
        double x = inX.get();
        double y = inY.get();
        double r = inRot.get();

        if(getHeading != null) {
            double h = (reversed) ? getHeading.get() : -(getHeading.get());

            double tx = x * Math.cos(h) - y * Math.sin(h);
            double ty = x * Math.sin(h) + y * Math.cos(h);

            x = tx;
            y = ty;
        }

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
