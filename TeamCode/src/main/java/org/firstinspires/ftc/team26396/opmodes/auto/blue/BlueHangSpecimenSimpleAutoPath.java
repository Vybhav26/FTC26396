package org.firstinspires.ftc.team26396.opmodes.auto.blue;


// RR-specific imports

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
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
@Disabled
public class BlueHangSpecimenSimpleAutoPath extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0,0
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, -58, Math.toRadians(90)));
        // Define arm positions using the constants from the Arm class

        Arm arm = new Arm(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);

        XYaw yaw = new XYaw(hardwareMap);
        YPitch pitch=new YPitch(hardwareMap);
        Roll roll = new Roll(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        waitForStart();

        TrajectoryActionBuilder tab1 = drive.actionBuilder(new Pose2d(0, -60, Math.toRadians(90)))
                .waitSeconds(3);

        Action initializeRobot = tab1.build();

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
//                .waitSeconds(2)
                .strafeTo(new Vector2d(0, -50))
                .waitSeconds(3);
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

        Action fullAction = drive.actionBuilder(new Pose2d(0, -60, Math.toRadians(90)))
                        .stopAndAdd(arm.raiseArmForLowerBasket())
                                .stopAndAdd(linearSlide.extendArmForward())
                .waitSeconds(2)
//                                        .stopAndAdd(pitch.moveWristUp())
//                                                .stopAndAdd(yaw.moveWristLeft())
//                                                        .stopAndAdd(roll.rotate90Clockwise())
//                                                                .stopAndAdd(claw.openClaw())
                                                                    .stopAndAdd(linearSlide.retractArmBackward())
                                                                        .waitSeconds(2)
                                                                                .strafeTo(new Vector2d(0, -50))
                                                                                        .build();


        telemetry.addData("Linear Slide Position : ", tab2.endTrajectory().fresh());
        telemetry.update();

//        Actions.runBlocking(fullAction);

//        Actions.runBlocking(
//                new SequentialAction(initializeRobot,
////                        moveToPositionToHangSample,
//                        new ParallelAction(
//                                arm.raiseArmForLowerBasket(),
//                                linearSlide.extendArmForward(),
//                                pitch.moveWristUp(),
//                                yaw.moveWristLeft(),
//                                roll.rotate90Clockwise(),
//                                claw.openClaw()
//                        ),
////                        linearSlide.retractArmBackward(),
////                        new SleepAction(2.0),
////                        arm.raiseArmForLowerBasket(),
////                        linearSlide.extendArmForward(),
////                        pitch.moveWristUp(),
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

        Actions.runBlocking(initializeRobot);
        Actions.runBlocking(arm.raiseArmForLowerBasket());
        Actions.runBlocking(linearSlide.extendArmForward());
        Actions.runBlocking(pitch.moveWristUp());
        Actions.runBlocking(yaw.moveWristLeft());
        Actions.runBlocking(roll.rotate90Clockwise());
        Actions.runBlocking(moveToPositionToHangSample);
//        Actions.runBlocking(claw.openClaw());
        Actions.runBlocking(linearSlide.retractArmBackward());
        Actions.runBlocking(arm.raiseArmForSpecimenPickUpFromWall());
        Actions.runBlocking(completeHang);

    }
}