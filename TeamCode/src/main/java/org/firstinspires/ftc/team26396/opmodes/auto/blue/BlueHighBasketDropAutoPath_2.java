/*

package org.firstinspires.ftc.team26396.opmodes.auto.blue;


// RR-specific imports

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Claw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Roll;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.XYaw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Autonomous(name="Blue High Basket Drop Auto Path_2")
public class BlueHighBasketDropAutoPath_2 extends LinearOpMode {
    public void runOpMode() {
        // I'm assuming you're at 0, 60, facing the basket
        Pose2d initialPose = new Pose2d(24, 60, Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        // Define arm positions using the constants from the Arm class

        Arm arm = new Arm(hardwareMap);
        LinearSlide linearSlide = new LinearSlide(hardwareMap);

        XYaw yaw = new XYaw(hardwareMap);
        YPitch pitch = new YPitch(hardwareMap);
        Roll roll = new Roll(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        waitForStart();

        double xDestPositionDropSampleInHand = 52;
        double yDestPositionDropSampleInHand = 51;
        double headingDestPositionDropSampleInHand = Math.toRadians(0.0);

        TrajectoryActionBuilder goToBasketFromInitPosition = drive.actionBuilder(initialPose)
                // Lift the arm
                .stopAndAdd(arm.raiseArmForUpperBasket())
                .waitSeconds(1)
                .stopAndAdd(yaw.moveWristCenter())
//                .stopAndAdd(arm.raiseArmForUpperBasket())
//                // Turn the wrist outward with the loaded sample
//                .stopAndAdd(roll.initPosition())
                // Move to the basket area
                .strafeToLinearHeading(new Vector2d(xDestPositionDropSampleInHand, yDestPositionDropSampleInHand), Math.toRadians(45));


        Action sleepAction1Second = new SleepAction(1);
        Action sleepAction2Seconds = new SleepAction(2);
        Action sleepAction3Seconds = new SleepAction(3);

        TrajectoryActionBuilder basketToFirstSample =

                goToBasketFromInitPosition.endTrajectory().fresh()
                        .turnTo(Math.toRadians(-100)) //check angle
                        //.strafeTo(new Vector2d(46.5, 34.5));
//                        .lineToY(38);

        TrajectoryActionBuilder firstSampleToBasket =
                basketToFirstSample.endTrajectory().fresh()
                        .strafeToLinearHeading(new Vector2d(xDestPositionDropSampleInHand, yDestPositionDropSampleInHand), Math.toRadians(45));

        TrajectoryActionBuilder basketToSecondSample =
                firstSampleToBasket.endTrajectory().fresh()
                        .turnTo(Math.toRadians(-120))
//                        .strafeTo(new Vector2d(57, 34.5));
//                        .lineToY(38);

        TrajectoryActionBuilder secondSampleToBasket =
                basketToSecondSample.endTrajectory().fresh()
                        .strafeToLinearHeading(new Vector2d(xDestPositionDropSampleInHand, yDestPositionDropSampleInHand), Math.toRadians(45));

        TrajectoryActionBuilder basketToThirdSample =
                firstSampleToBasket.endTrajectory().fresh()
                        .turnTo(Math.toRadians(-150))
//                        .strafeTo(new Vector2d(57, 34.5));
//                        .lineToY(38);

        TrajectoryActionBuilder secondSampleToBasket =
                basketToThirdSample.endTrajectory().fresh()
                        .strafeToLinearHeading(new Vector2d(xDestPositionDropSampleInHand, yDestPositionDropSampleInHand), Math.toRadians(45));

        Action goToBasketFromInitPositionAction = goToBasketFromInitPosition.build();
        Action basketToFirstSampleAction = basketToFirstSample.build();
        Action firstSampleToBasketAction = firstSampleToBasket.build();
        Action basketToSecondSampleAction = basketToSecondSample.build();
        Action secondSampleToBasketAction = secondSampleToBasket.build();
        Action basketToThirdSampleAction = basketToThirdSample.build();
        Action thirdSampleToBasketAction = thirdSampleToBasket.build();

        Action fullAction = new SequentialAction(
                // Init position to basket with pre-loaded sample
                goToBasketFromInitPositionAction,
                // Drop the sample into the basket
                buildCommonActionForDroppingToBasket(goToBasketFromInitPosition, arm, linearSlide, claw, pitch),

                //-- Turn around to exact degreee of sample 1, and pick it up, then turn back
                // Go to deposit the first sample
                basketToFirstSampleAction,
                // Pick up first sample
                buildCommonActionToPickSample(basketToFirstSample, arm, linearSlide, claw, pitch),
                // First sample to basket
                firstSampleToBasketAction,
                // Drop the sample into the basket
                buildCommonActionForDroppingToBasket(goToBasketFromInitPosition, arm, linearSlide, claw, pitch),
                // Go from basket to the second sample
                basketToSecondSampleAction,
                // Drop the sample into the basket
                buildCommonActionToPickSample(basketToSecondSample, arm, linearSlide, claw, pitch),
                      secondSampleToBasketAction,
                buildCommonActionForDroppingToBasket(goToBasketFromInitPosition, arm, linearSlide, claw, pitch)),
                // Go from basket to the second sample

//THIRD
                basketToThirdSampleAction,
               // Pick up first sample
               buildCommonActionToPickSample(basketToThirdSample, arm, linearSlide, claw, pitch),
                // First sample to basket
                thirdSampleToBasketAction,
                // Drop the sample into the basket
                buildCommonActionForDroppingToBasket(goToBasketFromInitPosition, arm, linearSlide, claw, pitch),

                //Reset All Encoders to not mess up Tele-Op
               buildCommonActionToResetEncoders(arm, linearSlide);

        Actions.runBlocking(fullAction);

    }

    public Action buildCommonActionForDroppingToBasket(TrajectoryActionBuilder inputTrajectory, Arm arm, LinearSlide linearSlide, Claw claw, YPitch pitch) {

        Action dropSampleIntoHighBasketAction = inputTrajectory.endTrajectory().fresh()
//                .stopAndAdd(new SleepAction(1))
                // Raise the arm to upper basket height
                .stopAndAdd(arm.raiseArmForUpperBasket())
                .stopAndAdd(new SleepAction(.7))
                // Extend the slide
                .stopAndAdd(linearSlide.extendArmForward())
                .stopAndAdd(new SleepAction(1))
                // Move towards the basket
                .lineToX(58)
                // Turn the wrist towards the basket - wrist up method may be misleading
                .stopAndAdd(pitch.moveWristUp())
                .stopAndAdd(new SleepAction(.1))
                // Open the claw to drop the sample
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(new SleepAction(.2))
                // Move the wrist back - wrist down method may be misleading
                .stopAndAdd(pitch.moveWristDown())
//                .stopAndAdd(new SleepAction(.2))
                // Retract slide backward
                .lineToX(52)
                .stopAndAdd(new SleepAction(.5))
                .stopAndAdd(linearSlide.retractSlideBackward())
                .stopAndAdd(new SleepAction(1))
                // Bring the arm back down
                .stopAndAdd(arm.deactivateArm())
//                .stopAndAdd(new SleepAction(2))
                .build();

        return dropSampleIntoHighBasketAction;
    }
    public Action buildCommonActionToPickSample1(TrajectoryActionBuilder inputTrajectory, Arm arm, LinearSlide linearSlide, Claw claw, YPitch pitch) {
        Action pickSampleFromFloor = inputTrajectory.endTrajectory().fresh()
                .stopAndAdd(linearSlide.extendSlideForPickFromPool())
                .stopAndAdd(pitch.moveWristUp())
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(claw.closeClaw())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(linearSlide.resetLinearSlide())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(arm.raiseArmForUpperBasket())
                .build();

        return pickSampleFromFloor;
    }
    public Action buildCommonActionToPickSample2(TrajectoryActionBuilder inputTrajectory, Arm arm, LinearSlide linearSlide, Claw claw, YPitch pitch) {
        Action pickSampleFromFloor = inputTrajectory.endTrajectory().fresh()
                .stopAndAdd(linearSlide.extendSlideForPickFromPool()) //Increase
                .stopAndAdd(pitch.moveWristUp())
                .stopAndAdd(claw.openClaw())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(claw.closeClaw())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(linearSlide.resetLinearSlide())
                .stopAndAdd(new SleepAction(1))
                .stopAndAdd(arm.raiseArmForUpperBasket())
                .build();

        return pickSampleFromFloor;
    }
    public Action buildCommonActionToResetEncoders(Arm arm, LinearSlide linearSlide, Claw claw, YPitch pitch) {
        Action pickSampleFromFloor = inputTrajectory.endTrajectory().fresh()
                .stopAndAdd(linearSlide.resetLinearSlide()) //Increase
                .stopAndAdd(arm.resetArm())
                .build();

        return pickSampleFromFloor;
    }

}

*/