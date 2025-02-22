package org.firstinspires.ftc.team26396.opmodes.auto.blue;


// RR-specific imports

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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

@Autonomous(name="Blue Hang Specimen Simple Auto Path")
public class BlueHangSpecimenSimpleAutoPath extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, -60, Math.toRadians(90)));
        // Define arm positions using the constants from the Arm class

        Arm arm = new Arm(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);

        XYaw yaw = new XYaw(hardwareMap);
        YPitch pitch = new YPitch(hardwareMap);
        Roll roll = new Roll(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        waitForStart();

        TrajectoryActionBuilder tab1 = drive.actionBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                .waitSeconds(3);

        Action initializeRobot = tab1.build();

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
//                .waitSeconds(2)
                .strafeTo(new Vector2d(0, -20));
//                .waitSeconds(3);
//                .lineToY(-48.0);

        Action moveToPositionToHangSample = tab2.build();

        Action hangSample = new ParallelAction(
                arm.raiseArmForLowerBasket(),
                linearSlide.extendArmForward(),
                pitch.moveWristDown(),
                yaw.moveWristCenter(),
                roll.rotate90Clockwise()
        );

        TrajectoryActionBuilder tab3 = tab2.endTrajectory().fresh()
//                        .waitSeconds(1)
                        .lineToY(-58.0)
                .waitSeconds(3);

        Action completeHang = tab3.build();

        Action fullAction = drive.actionBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(claw.closeClaw())
                .stopAndAdd(arm.raiseArmForHighRungHang())
                .waitSeconds(0.5)
                .stopAndAdd(yaw.moveWristCenter())
//                                .stopAndAdd(roll.rotate90Clockwise())
//                                .lineToY(-50)
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(new SleepAction(0.5))
                                .stopAndAdd(linearSlide.moveSlideForHighRung())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(pitch.moveWristDown())
                                .stopAndAdd(new SleepAction(.5))
                                .lineToY(-34)
                                .stopAndAdd(new SleepAction(.5))
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(yaw.moveWristLeft())
//                                .stopAndAdd(roll.rotate90Clockwise())
//                              .stopAndAdd(claw.openClaw())
                                .stopAndAdd(pitch.moveWristUp())
                                .stopAndAdd(new SleepAction(.5))
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.retractSlideBackward())
                                .stopAndAdd(new SleepAction(1))
                                .lineToY(-44)
                                .stopAndAdd(claw.openClaw())
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(pitch.moveWristUp())
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(claw.closeClaw())
                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(arm.deactivateArm())
//                                .strafeTo(new Vector2d(0, -50))
                                .stopAndAdd(arm.raiseArmForMoving())
//                                .stopAndAdd(new SleepAction(1))
                                .strafeTo(new Vector2d(0,-48))
                                .stopAndAdd(arm.deactivateArm())
//                // Shaurya's path
//                .lineToX(-34)
//
//                .lineToY(-44)
//
//                .strafeTo(new Vector2d(0,-48))

                // Shauya's path
//                                new Pose2d(12, -60,Math.toRadians(0)))
//                                .afterDisp(.5,  arm.raiseArmForLowerBasket())
//                                .waitSeconds(.100)
//                                .afterDisp(.5, yaw.moveWristCenter())
                .strafeTo(new Vector2d(0,-36))
                .waitSeconds(.100)
//                        arm.initializeArm(),
//                        arm.raiseArmForSpecimenPickUpFromWall(),
//                        drive.actionBuilder(new Pose2d(0, -36, 0))
//                .lineToY
                .strafeTo(new Vector2d(36, -36))
                //.splineTo(new Vector2d(35, 0), Math.toRadians(90))
//                .turnTo(Math.toRadians(90))
                .strafeTo(new Vector2d(36, -8))
//                .lineToY(-2)
                .waitSeconds(.05)
                .strafeTo(new Vector2d(48, -6))
                .waitSeconds(.05)
                .lineToY(-48)
                .lineToY(-2)
                .strafeTo(new Vector2d(57, -6))
                .waitSeconds(.100)
                .lineToY(-48)
                .lineToY(-2)
                .strafeTo(new Vector2d(62, -6))
                .waitSeconds(.100)
                .lineToY(-2)
                .waitSeconds(.100)
                .lineToY(-15)
                .turnTo(Math.toRadians(270))
                .lineToY(-6)
                                .build();


        telemetry.addData("Linear Slide Position : ", tab2.endTrajectory().fresh());
        telemetry.update();

//        Actions.runBlocking(fullAction);

//        Actions.runBlocking(
//                new SequentialAction(initializeRobot,
//                        claw.closeClaw(),
////                        moveToPositionToHangSample,
//                        new ParallelAction(
//                                arm.raiseArmForLowerBasket(),
//                                linearSlide.extendArmHalfway(),
//                                pitch.moveWristDown()
////                                yaw.moveWristLeft(),
////                                roll.rotate90Clockwise(),
////                                claw.openClaw()
//                        ),
//                        moveToPositionToHangSample,
//                        linearSlide.retractArmBackward(),
//                        new SleepAction(2.0),
//                        arm.raiseArmForMoving(),
////                        arm.raiseArmForLowerBasket(),
////                        linearSlide.extendArmForward(),
//                        pitch.moveWristUp(),
////                        yaw.moveWristLeft(),
////                        roll.rotate90Clockwise(),
////                        claw.openClaw(),
////                        moveToPositionToHangSample,
////                        new SleepAction(2.0),
//                        completeHang
//
////                        .stopAndAdd(arm.raiseArmForLowerBasket())
////                        .stopAndAdd(linearSlide.extendArmForward())
////                        .stopAndAdd(pitch.moveWristDown())
////                        .stopAndAdd(yaw.moveWristCenter())
////                        .stopAndAdd(roll.rotate90Clockwise())
////                        .lineToY(-48.0)
////                        .stopAndAdd(linearSlide.retractArmBackward())
////                        .endTrajectory().fresh()
////                        .lineToY(-58.0)
////                        .build()
//        ));


//        Actions.runBlocking(initializeRobot);
//        Actions.runBlocking(arm.raiseArmForLowerBasket());
//        Actions.runBlocking(linearSlide.extendArmForward());
//        Actions.runBlocking(pitch.moveWristUp());
//        Actions.runBlocking(yaw.moveWristLeft());
//        Actions.runBlocking(roll.rotate90Clockwise());
//        Actions.runBlocking(moveToPositionToHangSample);
////        Actions.runBlocking(claw.openClaw());
//        Actions.runBlocking(linearSlide.retractArmBackward());
//        Actions.runBlocking(arm.raiseArmForSpecimenPickUpFromWall());
//        Actions.runBlocking(completeHang);

        Actions.runBlocking(fullAction);

    }
}