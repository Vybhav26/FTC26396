package org.firstinspires.ftc.team26396.opmodes.auto.red;


// RR-specific imports

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
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

        TrajectoryActionBuilder tab1 = drive.actionBuilder(new Pose2d(0, -60, Math.toRadians(90)))
                .waitSeconds(3);

        Action initializeRobot = tab1.build();

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
//                .waitSeconds(2)
                .strafeTo(new Vector2d(0, -50));
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
        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        drive.actionBuilder(new Pose2d(0, -60,Math.toRadians(0)))
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(claw.closeClaw())
                                .stopAndAdd(arm.raiseArmForHighRungHang())
//                                .lineToY(-50)
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.extendArmHalfway())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
//                                .stopAndAdd(new SleepAction(2))
                                .lineToY(-40)
//                                .stopAndAdd(new SleepAction(2))
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
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.retractSlideBackward())
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
                                .stopAndAdd(arm.deactivateArm())
//                                .strafeTo(new Vector2d(0, -50))
                                .stopAndAdd(arm.raiseArmForMoving())
//                                .stopAndAdd(new SleepAction(1))
                                //Shaurya Program Below
                                .strafeTo(new Vector2d(-56,60))
                                .stopAndAdd(arm.deactivateArm())
                                .lineToX(38)
                                .turnTo(Math.toRadians(90))
                                .lineToY(5)
                                .waitSeconds(.100)
                                .strafeTo(new Vector2d(47, 5))
                                .waitSeconds(.100)
                                .lineToY(-58)
                                .lineToY(5)
                                .strafeTo(new Vector2d(57, 5))
                                .waitSeconds(.100)
                                .lineToY(-57)
                                .lineToY(-30)
                                .turnTo(Math.toRadians(270))
                                .lineToY(-55)
                                /////////////////////////////////
                                .stopAndAdd(claw.openClaw())
                                .stopAndAdd(arm.raiseArmForSpecimenPickUpFromWall())
                                .stopAndAdd(claw.closeClaw())
                                ////////////////////////////////
                                .strafeTo(new Vector2d(0, -41))
                                .turnTo(Math.toRadians(90))
                                /////////////////////////////
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(claw.closeClaw())
                                .stopAndAdd(arm.raiseArmForHighRungHang())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.extendArmHalfway())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(2))
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
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.retractSlideBackward())
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
                                .stopAndAdd(arm.deactivateArm())
//                                .strafeTo(new Vector2d(0, -50))
                                .stopAndAdd(arm.raiseArmForMoving())
//                                .stopAndAdd(new SleepAction(1))
                                ////////////////////////////////////////////
                                .strafeTo(new Vector2d(57, -55))
                                .turnTo(Math.toRadians(270))
                                ////////////////////////////////////////////
                                .stopAndAdd(claw.openClaw())
                                .stopAndAdd(arm.raiseArmForSpecimenPickUpFromWall())
                                .stopAndAdd(claw.closeClaw())
                                //////////////////////////////////////////
                                .strafeTo(new Vector2d(0, -41))
                                .turnTo(Math.toRadians(90))
                                //////////////////////////////////////////////
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(claw.closeClaw())
                                .stopAndAdd(arm.raiseArmForHighRungHang())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.extendArmHalfway())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(2))
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
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.retractSlideBackward())
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
                                .stopAndAdd(arm.deactivateArm())
//                                .strafeTo(new Vector2d(0, -50))
                                .stopAndAdd(arm.raiseArmForMoving())
//                                .stopAndAdd(new SleepAction(1))
                                ////////////////////////////////////////////
                                .strafeTo(new Vector2d(57, -55))
                                .turnTo(Math.toRadians(270))
                                ////////////////////////////////////////////
                                .stopAndAdd(claw.openClaw())
                                .stopAndAdd(arm.raiseArmForSpecimenPickUpFromWall())
                                .stopAndAdd(claw.closeClaw())
                                ////////////////////////////////////////////
                                .strafeTo(new Vector2d(0, -41))
                                .turnTo(Math.toRadians(90))
                                /////////////////////////////////////////////
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                .stopAndAdd(claw.closeClaw())
                                .stopAndAdd(arm.raiseArmForHighRungHang())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.extendArmHalfway())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(2))
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
//                                .stopAndAdd(new SleepAction(2))
//                                .stopAndAdd(new SleepAction(1))
                                .stopAndAdd(linearSlide.retractSlideBackward())
                                .stopAndAdd(claw.openClaw())
//                                .stopAndAdd(new SleepAction(2))
                                .stopAndAdd(pitch.moveWristDown())
                                .stopAndAdd((telemetryPacket) -> {
                                    telemetry.addLine("Arm position : " + arm.armMotor.getCurrentPosition());
                                    telemetry.addLine("Slide position : " + linearSlide.linearSlideMotor.getCurrentPosition());
                                    telemetry.update();
                                    return false;
                                })
                                //////////////////////////////////////////////
                                .strafeTo(new Vector2d(57, -55))
                                .build()));
    }
}
