package com.shtrobotice.ShtKit.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.shtrobotice.ShtKit.hardware.Camera.LimeLight.Limelight;
import com.shtrobotice.ShtKit.hardware.DriveBase.Mecannum.MecannumBase;

public class hardware {

    volatile HardwareMap hm;
    volatile Gamepad gamepad1;
    volatile Gamepad gamepad2;

    public hardware(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        this.hm = hardwareMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public final MecannumBase get(Class<? extends MecannumBase> driveBaseClass, String leftFrontMotor,String leftBackMotor,String rightFrontMotor,String rightBackMotor) {
        try {
            return new MecannumBase(hm, gamepad1, leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor);
        } catch (Exception e) {
            throw new RuntimeException(String.format("베이스 '" + driveBaseClass.getSimpleName() + "'을/를 구성할 수 없습니다."));
        }
    }

    public final <T> T get(Class<? extends T> classOrInterface, String deviceName) {
        T rs;
        try {
            if (classOrInterface.equals(Limelight.class)) rs = (T) (new Limelight(hm, deviceName));
            else rs = hm.get(classOrInterface, deviceName);
            return rs;
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("이름이 '" + deviceName + "'인 '" + classOrInterface.getSimpleName() + "' 장치를 찾을 수 없습니다."));
        }
    }

}
