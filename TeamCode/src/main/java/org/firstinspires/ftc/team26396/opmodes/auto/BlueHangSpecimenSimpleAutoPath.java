package org.firstinspires.ftc.team26396.opmodes.auto;


// RR-specific imports

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Arm;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Claw;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.Roll;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.XYaw;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.auto.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name="Blue Hang Specimen Simple Auto Path")
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

        TrajectoryActionBuilder tab1 = drive.actionBuilder(new Pose2d(0, -60, Math.toRadians(90)));

        Action initializeRobot = tab1.build();

        TrajectoryActionBuilder tab2 = tab1.endTrajectory().fresh()
                .lineToY(-48.0);

        Action moveToPositionToHangSample = tab2.build();

        Action hangSample = new ParallelAction(
                arm.raiseArmForLowerBasket(),
                linearSlide.extendArmForward(),
                pitch.moveWristDown(),
                yaw.moveWristCenter(),
                roll.rotate90Clockwise()
        );

        TrajectoryActionBuilder tab3 = tab2.endTrajectory().fresh()
                        .lineToY(-58.0);

        Action completeHang = tab3.build();

        Actions.runBlocking(
                new SequentialAction(initializeRobot,
                        new ParallelAction(
                                arm.raiseArmForLowerBasket(),
                                linearSlide.extendArmForward(),
                                pitch.moveWristDown(),
                                yaw.moveWristCenter(),
                                roll.rotate90Clockwise(),
                                claw.openClaw()
                        ),
                        moveToPositionToHangSample,
                        completeHang

//                        .stopAndAdd(arm.raiseArmForLowerBasket())
//                        .stopAndAdd(linearSlide.extendArmForward())
//                        .stopAndAdd(pitch.moveWristDown())
//                        .stopAndAdd(yaw.moveWristCenter())
//                        .stopAndAdd(roll.rotate90Clockwise())
//                        .lineToY(-48.0)
//                        .stopAndAdd(linearSlide.retractArmBackward())
//                        .endTrajectory().fresh()
//                        .lineToY(-58.0)
//                        .build()
        ));




    }
}