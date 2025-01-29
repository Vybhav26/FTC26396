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
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name = "Roadrunner Basic Auto")
@Disabled
public class RoadRunner_Test extends LinearOpMode {

    @Override
    public void runOpMode() {
        // I'm assuming you're at 0,0
        Pose2d initPose = new Pose2d(0, 61.75, Math.toRadians(90.00));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(initPose)
                        .lineToY(45)
                        .lineToY(29.66)
                        .lineToY(0)
                        .lineToXSplineHeading(23,Math.toRadians(180.00))
                        .build());
    }

}