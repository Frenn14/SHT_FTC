package com.shtrobotice.ShtKit.hardware;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.shtrobotice.ShtKit.hardware.DriveBase.Mecannum.MecannumBase;

public class Hardware {

    static HardwareMap hm = null;
    static volatile Gamepad gamepad1 = null;
    static volatile Gamepad gamepad2 = null;

    public Hardware(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        this.hm = hardwareMap;
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public final static <T> T get(Class<? extends T> driveBaseClass, String leftFrontMotor,String leftBackMotor,String rightFrontMotor,String rightBackMotor) {
        T rs = null;
        try {
            if (driveBaseClass.equals(MecannumBase.class)) {rs = (T) new MecannumBase(hm, leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor, gamepad1);}
            return rs;
        } catch (Exception e) {
            throw new RuntimeException(String.format("베이스 '" + driveBaseClass.getSimpleName() + "'을/를 구성할 수 없습니다."));
        }
    }

    public final static <T> T get(Class<? extends T> classOrInterface, String deviceName) {
        T rs = null;
        try {
            rs = hm.get(classOrInterface, deviceName);
            return rs;
        }
        catch (Exception e) {
            throw new RuntimeException(String.format("이름이 '" + deviceName + "'인 '" + classOrInterface.getSimpleName() + "' 장치를 찾을 수 없습니다."));
        }
    }

}
