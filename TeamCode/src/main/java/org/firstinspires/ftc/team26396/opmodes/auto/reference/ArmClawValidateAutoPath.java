package org.firstinspires.ftc.team26396.opmodes.auto.reference;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Claw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.XYaw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name = "Arm Claw Validate", group = "Initial Validation")
public class ArmClawValidateAutoPath extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initialize the MecanumDrive with the hardware map
        Pose2d initialPose = new Pose2d(-24, 60, Math.toRadians(-90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Arm arm = new Arm(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);
        YPitch pitch = new YPitch(hardwareMap);
        XYaw yaw = new XYaw(hardwareMap);
        Claw claw = new Claw(hardwareMap);

        TrajectoryActionBuilder waitTrajectory = drive.actionBuilder(initialPose)
                .waitSeconds(1);

        if (isStopRequested()) return;

        while (!isStopRequested() && !opModeIsActive()) {

            telemetry.addData("Position during Init", drive.updatePoseEstimate());
            telemetry.update();
        }

        Pose2d initPose = new Pose2d(-24, 60, Math.toRadians(-90));

        TrajectoryActionBuilder testTrajectory = drive.actionBuilder(initPose)
                .afterDisp(5, () -> {
                    arm.raiseArmForMoving();
                });

        // Wait for the start signal
        waitForStart();

        if (opModeIsActive()) {
            // Define the trajectory for the Blue Basket sequence with waits
            Actions.runBlocking(
                    new SequentialAction(testTrajectory.build(),

//                            new ParallelAction(testTrajectory.build()),
//                            arm.initializeArm(),
//                            waitTrajectory.build(),
//                            arm.raiseArmForLowerBasket(),
//                            linearSlide.initLinearSlide(),
//                            linearSlide.extendArmForward(),
//                            arm.raiseArmForNetzone(),
//                            waitTrajectory.build(),
//                            arm.raiseArmForSamplePickUpFromFloor(),
//                            waitTrajectory.build(),
//                            arm.raiseArmForLowerBasket(),
//                            arm.raiseArmForSamplePickUpFromFloor(),
//                            waitTrajectory.build(),
//                            arm.raiseArmForUpperBasket(),
//                            waitTrajectory.build(),
//                            arm.initializeArm(),
//                            linearSlide.retractArmBackward(),
//                            linearSlide.extendSlideForPickFromPool(),
                            yaw.moveWristCenter(),
                            pitch.moveWristMiddle(),
                            claw.openClaw(),
                            claw.closeClaw(),
                            pitch.moveWristDown(),
                            yaw.moveWristCenter(),
                            arm.deactivateArm()
                    )
            );
        }
    }
}