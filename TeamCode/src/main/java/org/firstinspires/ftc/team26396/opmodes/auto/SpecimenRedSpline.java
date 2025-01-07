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
public class SpecimenRedSpline extends LinearOpMode{
    public void runOpMode() {
        // I'm assuming you're at 0,0
        Pose2d initPose;
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(23.48, -69.05, Math.toRadians(90.00)));

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(20, -60, 0))
                        .splineTo(new Vector2d(20, -40), Math.toRadians(0))
                        .splineTo(new Vector2d(60, -40), Math.toRadians(0))
                        .splineTo(new Vector2d(0, -40), Math.toRadians(0))
                        .splineTo(new Vector2d(0, -33), Math.toRadians(90))
                        .build());
    }

    //TrajectorySequence trajectory0 = drive.trajectorySequenceBuilder(new Pose2d(23.48, -69.05, Math.toRadians(90.00)))
    //.lineTo(new Vector2d(48.62, -66.89))
    //.build();
}
