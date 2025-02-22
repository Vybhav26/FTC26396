package org.firstinspires.ftc.team26396.opmodes.auto.red;


// RR-specific imports

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Claw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Roll;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.XYaw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name="Red Sample Drop", group = "Red Alliance", preselectTeleOp = "RobotCentricDrive")

public class RedSampleDrop extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, -60,0 ));
        // Define arm positions using the constants from the Arm class

//        Arm arm = new Arm(hardwareMap);
//
//        XYaw yaw = new XYaw(hardwareMap);
//        YPitch pitch=new YPitch(hardwareMap);
        // Define arm positions using the constants from the Arm class

        Arm arm = new Arm(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);

        XYaw yaw = new XYaw(hardwareMap);
        YPitch pitch = new YPitch(hardwareMap);
        Roll roll = new Roll(hardwareMap);
        Claw claw = new Claw(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(new Pose2d(12, -60,Math.toRadians(0)))
                                .turnTo(Math.toRadians(90))
                                .strafeTo(new Vector2d(0,-33))
                                .waitSeconds(.100)
                                .strafeTo(new Vector2d(35,0))
                                .lineToY(5)
                                .waitSeconds(.100)
                                .strafeTo(new Vector2d(44, 5))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                .lineToY(5)
                                .strafeTo(new Vector2d(55, 5))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                .lineToY(-20)
                                .turnTo(Math.toRadians(180))
                                .lineToY(-38)
                                .strafeTo(new Vector2d(0, 25))
                                .turnTo(Math.toRadians(180))
                                .strafeTo(new Vector2d(64, -6))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                .build()));
    }
}