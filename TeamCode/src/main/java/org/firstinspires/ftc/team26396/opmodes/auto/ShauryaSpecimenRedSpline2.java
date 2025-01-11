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

@Autonomous(name = "Specimen Red Spline")
public class ShauryaSpecimenRedSpline2 extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize the MecanumDrive with the hardware map
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

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