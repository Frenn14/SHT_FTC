# SHT_FTC

[![Java](https://img.shields.io/badge/Java-18-ED8B00.svg?logo=openjdk)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.20-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/Gradle-8.7.3-02303A.svg?logo=gradle)](https://gradle.org)
[![FTCRobotController](https://img.shields.io/badge/FtcRobotController-11.0-ED3F27.svg)](https://github.com/FIRST-Tech-Challenge/FtcRobotController)
[![FTCDashboard](https://img.shields.io/badge/FTCDashboard-0.5.0-6E8CFB.svg)]([https://acmerobotics.github.io/ftc-dashboard/)
[![LearnRoadRunner](https://img.shields.io/badge/LearnRoadRunner-1.0.1-3C467B.svg)](https://learnroadrunner.com/)

## FTC Software Working Kit

* #### Features
  * 사용 기능 통합화
  * 기능별 사용성 개선

* #### Class
  * SHTOpMode    
  * MecannumBase
  * SimpleCamera
  * LimeLight

# Update List

## Beta v1.0 (2026-1-3)
#### SHTOpMode 클래스를 구축 및 하드웨어 객체 MecannumBase 클래스 추가

1. SHTOpMode 클래스를 기반으로 하는 @TeleOp 및 @Autonomous 개발 환경 구축
   * `@TeleOp` 및 `@Autonomous` 개발 환경 구축 시에 `SHTOpMode`를 상속하여 사용 가능
   * `LinearOpMode`와 다르게 `hardwareMap` 대신 `hardware` 동적 변수를 활용하여 사용 가능

2. 하드웨어 객체 MecannumBase 클래스 추가
   * `hardware.get();` 메서드를 사용하여 선언하고 사용 가능