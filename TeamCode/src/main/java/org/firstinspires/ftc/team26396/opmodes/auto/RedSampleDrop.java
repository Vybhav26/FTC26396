package org.firstinspires.ftc.team26396.opmodes.auto;


// RR-specific imports

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name="Red Sample Drop")
public class RedSampleDrop extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(20, -60, 0));
        // Define arm positions using the constants from the Arm class

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, -60, 0))
                        .waitSeconds(1)
                        .turnTo(Math.toRadians(90))
                        .lineToY(-36)
                        .waitSeconds(1)
                        .turnTo(Math.toRadians(0))
                        .waitSeconds(1)
                        .lineToX(38)
                        .turnTo(Math.toRadians(90))
                        .lineToY(-10)
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(44, -10))
                        .waitSeconds(1)
                        .lineToY(-58)
                        .lineToY(-10)
                        .strafeTo(new Vector2d(54, -10))
                        .waitSeconds(1)
                        .lineToY(-57)
                        .lineToY(-10)
                        .strafeTo(new Vector2d(61, -10))
                        .waitSeconds(1)
                        .lineToY(-57)
                        .build());
    }
}
