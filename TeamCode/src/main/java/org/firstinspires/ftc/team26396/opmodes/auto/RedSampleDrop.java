package org.firstinspires.ftc.team26396.opmodes.auto;


// RR-specific imports

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Arm;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Claw;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.XYaw;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Arm;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.XYaw;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;
import javax.xml.xpath.XPath;

@Autonomous(name="Red Sample Drop")
public class RedSampleDrop extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, -60,0 ));
        // Define arm positions using the constants from the Arm class

//        Arm arm = new Arm(hardwareMap);
//
//        XYaw yaw = new XYaw(hardwareMap);
//        YPitch pitch=new YPitch(hardwareMap);
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(new Pose2d(12, -60,Math.toRadians(0)))
//                                .afterDisp(.5,  arm.raiseArmForLowerBasket())
//                                .waitSeconds(.100)
//                                .afterDisp(.5, yaw.moveWristCenter())
                                .strafeTo(new Vector2d(0,-33))
                                .waitSeconds(.100)
//                        arm.initializeArm(),
//                        arm.raiseArmForSpecimenPickUpFromWall(),
//                        drive.actionBuilder(new Pose2d(0, -36, 0))
                                .lineToX(35)
                                //.splineTo(new Vector2d(35, 0), Math.toRadians(90))
                                .turnTo(Math.toRadians(90))
                                .lineToY(5)
                                .waitSeconds(.100)
                                .strafeTo(new Vector2d(44, 5))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                .lineToY(5)
                                .strafeTo(new Vector2d(55, -6))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                .lineToY(-6)
                                .strafeTo(new Vector2d(64, -6))
                                .waitSeconds(.100)
                                .lineToY(-40)
                                // .strafeTo(new Vector2d(57, -52))
                                //.waitSeconds(.100)
                                //.turnTo(Math.toRadians(270))
//                        build()
                                // arm.initializeArm(),
                                //arm.raiseArmForSpecimenPickUpFromWall(),
//                        drive.actionBuilder(new Pose2d(54, -52, 0))
                                //.turnTo(Math.toRadians(90))
                                //.lineToY(-40)
                                //.waitSeconds(.100)
                                //.turnTo(Math.toRadians(180))
                                //.lineToX(2)
                                //.turnTo(Math.toRadians(90))
                                .build()));




    }
}