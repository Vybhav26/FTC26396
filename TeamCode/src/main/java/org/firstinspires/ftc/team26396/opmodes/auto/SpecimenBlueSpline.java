package org.firstinspires.ftc.team26396.opmodes.auto;


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

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;
@Autonomous(name = "AryaBlueSpecimenSpline")
public class SpecimenBlueSpline extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-20, 60, -90))
                        .waitSeconds(0.5)
                        .splineTo(new Vector2d(-52, 57), Math.toRadians(90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(0, 35), Math.toRadians(-90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-49, 40), Math.toRadians(-90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-56, 57), Math.toRadians(90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-57, 40), Math.toRadians(-90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-57, 57), Math.toRadians(90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(1, 35), Math.toRadians(-90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-59, 40), Math.toRadians(-135))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(-59, 57), Math.toRadians(90))
                        .waitSeconds(0.5)

                        .splineTo(new Vector2d(2, 35), Math.toRadians(-90))
                        .waitSeconds(0.5)

                        .build());
    }
}
