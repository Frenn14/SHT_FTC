package com.shtrobotice.ShtKit.hardware.DriveBase;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.Supplier;

public abstract strictfp class DriveBase {
    protected DcMotor lf, lb, rf, rb;
    protected Supplier<Float> inX, inY, inRot;
    public DriveBase(HardwareMap hardwareMap, String leftFront, String leftBack, String rightFront, String rightBack, Gamepad gamepad) {
        this(hardwareMap, leftFront, leftBack, rightFront, rightBack);
        initInput(gamepad);
    }
    public DriveBase(HardwareMap hardwareMap, String leftFront, String leftBack, String rightFront, String rightBack) {
        lf = hardwareMap.get(DcMotor.class, leftFront);
        lb = hardwareMap.get(DcMotor.class, leftBack);
        rf = hardwareMap.get(DcMotor.class, rightFront);
        rb = hardwareMap.get(DcMotor.class, rightBack);

        setBreak(DcMotor.ZeroPowerBehavior.BRAKE);

        setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public final void initInput(Gamepad gamepad) {
        inX     = ()->gamepad.left_stick_x;
        inY     = ()->gamepad.left_stick_y;
        inRot   = ()->gamepad.right_stick_x;
    }
    public void setBreak(DcMotor.ZeroPowerBehavior brake) {
        rf.setZeroPowerBehavior(brake);
        lb.setZeroPowerBehavior(brake);
        lf.setZeroPowerBehavior(brake);
        rb.setZeroPowerBehavior(brake);
    }
    public void setDirection(DcMotorSimple.Direction direction) {
        DcMotorSimple.Direction _r = (direction == DcMotorSimple.Direction.FORWARD) ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD;
        setDirection(MotorDirection.RIGHTFRONT, direction);
        setDirection(MotorDirection.RIGHTBACK, direction);
        setDirection(MotorDirection.LEFTFRONT, _r);
        setDirection(MotorDirection.LEFTBACK, _r);
    }
    public void setDirection(MotorDirection motorDirection , DcMotorSimple.Direction direction) {
        switch (motorDirection) {
            case RIGHTFRONT:    rf.setDirection(direction); break;
            case LEFTBACK:      lb.setDirection(direction); break;
            case LEFTFRONT:     lf.setDirection(direction); break;
            case RIGHTBACK:     rb.setDirection(direction); break;
            default: break;
        }
    }
    public final void setInX(Supplier<Float> key) { inX = key; }
    public final void setInY(Supplier<Float> key) { inY = key; }
    public final void setInRot(Supplier<Float> key) { inRot = key; }
    public abstract void update();
}
