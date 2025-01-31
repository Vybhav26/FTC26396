package org.firstinspires.ftc.team26396.opmodes.test.auto;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.LinearSlide;

@Autonomous(name = "Arm+Slide Auto test", group = "Auto")
public class ParallelTest extends LinearOpMode {
    private MecanumDrive drive;
    private Arm arm;
    private LinearSlide slide;

    @Override
    public void runOpMode() {
        // Initialize subsystems
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0)); // Starting position
        arm = new Arm(hardwareMap);
        slide = new LinearSlide(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        // Run the sequence
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0)) // Starting pose
                        .strafeTo(new Vector2d(20, 0))  // 1. Strafe forward 20 inches
                        .waitSeconds(0.5)

                        // 2. Lift the arm
                        .afterTime(0, arm.raiseArmForUpperBasket())
                        .waitSeconds(0.5)

                        // 3. Extend the linear slide
                        .afterTime(0, slide.extendSlideForPickFromPool())
                        .waitSeconds(0.5)

                        // 4. Lower the arm slightly
                        .afterTime(0, arm.raiseArmForSpecimenPickUpFromWall())
                        .waitSeconds(0.3)

                        // 5. Raise the arm back to its original position
                        .afterTime(0, arm.raiseArmForUpperBasket())
                        .waitSeconds(0.3)

                        // 6. Retract the linear slide
                        .afterTime(0, slide.retractArmBackward())
                        .waitSeconds(0.5)

                        .build()
        );
    }
}
