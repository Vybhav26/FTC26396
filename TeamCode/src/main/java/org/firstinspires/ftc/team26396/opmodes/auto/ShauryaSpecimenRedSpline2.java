package org.firstinspires.ftc.team26396.opmodes.auto;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name = "Specimen Red Spline")
public class ShauryaSpecimenRedSpline2 extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize the MecanumDrive with the hardware map
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        DcMotorEx linearSlideMotor = (DcMotorEx)hardwareMap.get(DcMotor.class, "armMotor");

        DcMotor armMotor = hardwareMap.dcMotor.get("liftMotor");
        CRServo clawServo = hardwareMap.get(CRServo.class, "claw");
        Servo wristServo = hardwareMap.get(Servo.class, "wrist");
        DcMotor HangMotor1 = hardwareMap.dcMotor.get("HM1");
        DcMotor HangMotor2 = hardwareMap.dcMotor.get("HM2");


        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        //backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        if (isStopRequested()) return;

        // Initialize and configure IMU
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
        imu.initialize(imuParameters);

        // Set zero power behavior
        linearSlideMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setPositionPIDFCoefficients(10.0);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        HangMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        HangMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Wait for the start signal
        waitForStart();

        // Check if the op mode is active
        if (opModeIsActive()) {
            // Execute the trajectory
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(-32, -69, 0))
                            .splineTo(new Vector2d(-54, -55), Math.toRadians(230))
                            .splineTo(new Vector2d(-47, -37), Math.toRadians(-270))
                            .splineTo(new Vector2d(-52, -50), Math.toRadians(230))
                            .splineTo(new Vector2d(-52, -37), Math.toRadians(-245))
                            .splineTo(new Vector2d(-52, -38), Math.toRadians(240))
                            .splineTo(new Vector2d(-57, -50), Math.toRadians(230))
                            .splineTo(new Vector2d(-56, -30), Math.toRadians(-220))
                            .splineTo(new Vector2d(-52, -51), Math.toRadians(230))
                            .splineTo(new Vector2d(-27, 0), Math.toRadians(-330))
                            .build()
            );
        }
    }
}