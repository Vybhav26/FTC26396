package org.firstinspires.ftc.team26396.roadrunner;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team26396.roadrunner.MecanumDrive;

@Autonomous(name = "RRTest Auto")
public class RRTest extends LinearOpMode {


    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,71,0));

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0,71,0))
                        .lineToX(48)
                        .build());
    }
}
