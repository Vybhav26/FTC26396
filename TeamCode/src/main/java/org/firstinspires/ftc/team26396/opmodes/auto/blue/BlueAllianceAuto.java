package org.firstinspires.ftc.team26396.opmodes.auto.blue;


import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.SECONDS;

import com.acmerobotics.roadrunner.ParallelAction;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Claw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.LinearSlide;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Roll;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.XYaw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;
// ... (previous imports remain the same)

@Autonomous(name="Blue Auto", group = "Blue Alliance")

public class BlueAllianceAuto extends LinearOpMode {

    // Robot components
    private Arm arm;
    private LinearSlide linearSlide;
    private YPitch yPitch;
    private XYaw xYaw;
    private Roll rollMechanism;
   // private Claw clawMechanism;

    public static final String TEAM_NAME = "Pack-A-Punch";
    public static final int TEAM_NUMBER = 26396;

    public enum START_POSITION {
        LEFT,
        RIGHT,
    }


    private START_POSITION startPosition;


    @Override
    public void runOpMode() {
        initHardware();

        selectStartPosition();

        waitForStart();

        if (opModeIsActive() && !isStopRequested()) {
            arm.deactivateArm();
            runAutonomousMode();
        }
    }

    private void initHardware() {
        // Initialize hardware from config
        Servo claw = hardwareMap.get(Servo.class, "claw");
        DcMotorEx linearSlideMotor = hardwareMap.get(DcMotorEx.class, "liftMotor");
        Servo pitch = hardwareMap.get(Servo.class, "pitch");
       Servo yaw = hardwareMap.get(Servo.class, "yaw");
        Servo roll = hardwareMap.get(Servo.class, "roll");
        DcMotorEx armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
    }


    private void selectStartPosition() {
        telemetry.setAutoClear(true);
        telemetry.clearAll();

        while (!isStopRequested()) {
            telemetry.addData("Initializing FTC Wires (ftcwires.org) Autonomous adopted for Team:",
                    TEAM_NAME + " " + TEAM_NUMBER);
            telemetry.addData("---------------------------------------", "");
            telemetry.addData("Select Starting Position on gamepad 1:", "");
            telemetry.addData("    Left   ", " = X ");
            telemetry.addData("    Right ", " = Y ");

            if (gamepad1.x) {
                startPosition = START_POSITION.LEFT;
                break;
            }
            if (gamepad1.y) {
                startPosition = START_POSITION.RIGHT;
                runAutonomousMode();
                break;}
            telemetry.update();
        }

        telemetry.setAutoClear(false);
        telemetry.clearAll();
        telemetry.addData("Selected Starting Position", startPosition);
        telemetry.update();
    }

    private void runAutonomousMode() {
        if (startPosition == START_POSITION.LEFT) {
            runRightPath();
        } else {
            runLeftPath();
        }
    }
    public class OpenClaw{
        public void OpenClaw(){}
    }


    private void runLeftPath() {
        Pose2d initPose = new Pose2d(0, 70, Math.toRadians(90)); // Starting Pose
        Pose2d specimenHang = new Pose2d(0, 27, Math.toRadians(-90));
        Pose2d moveSample1 = new Pose2d(49, 35, Math.toRadians(-90));
        Pose2d pickSample1 = new Pose2d(49, 24, Math.toRadians(-90));
        Pose2d positionSample1 = new Pose2d(49, 49, Math.toRadians(44));
        Pose2d sample1High = new Pose2d(57, 58, 44);
        Pose2d pickSample2 = new Pose2d(59, 25, -90);
        Pose2d sample2High = new Pose2d(61, 49, 62.49);
        Pose2d pickSample3 = new Pose2d(69, 33, -90);
        Pose2d sample3High = new Pose2d(68, 52, 83.38);
        Pose2d ascent1 = new Pose2d(27, 5, 180);

        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);

        // Move to specimen hang position
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(specimenHang.position, specimenHang.heading)
                        .build()
        );
        safeWaitSeconds(0.1);
        telemetry.addLine("Move robot to submersible to place specimen on chamber!");
        telemetry.update();

        // Initial specimen placement
        new ParallelAction(
                arm.raiseArmForUpperBasket(),
                linearSlide.extendSlideForPickFromPool(),
                yPitch.moveWristUp(),
                xYaw.moveWristLeft(),
                rollMechanism.rotate90Clockwise()
              //  clawMechanism.openClaw()
        );

        safeWaitSeconds(0.1);
        telemetry.addLine("Place specimen on chamber!");
        telemetry.update();

        // Move to pick up Sample 1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(moveSample1.position)
                        .build()
        );
        safeWaitSeconds(0.1);
        telemetry.addLine("Move Robot to pick up yellow Sample 1!");
        telemetry.update();

        // Pick Up Sample 1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pickSample1.position.y)
                        .build()
        );
        safeWaitSeconds(0.1);
        arm.raiseArmForSamplePickUpFromFloor();
     //   clawMechanism.closeClaw();
        telemetry.addLine("Pick up yellow Sample 1!");
        telemetry.update();

        // Move to position for dropping Sample 1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(positionSample1.position, positionSample1.heading)
                        .build()
        );
        safeWaitSeconds(0.1);
        arm.raiseArmForUpperBasket();
        telemetry.addLine("Move to drop yellow Sample 1!");
        telemetry.update();

        // Drop Sample 1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(sample1High.position, sample1High.heading)
                        .build()
        );
        safeWaitSeconds(0.1);
        linearSlide.extendSlideForPickFromPool();
        new OpenClaw();
        telemetry.addLine("Drop yellow Sample 1");
        telemetry.update();



        // Complete the sequence with Ascent1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(ascent1.position, ascent1.heading)
                        .build()
        );
        safeWaitSeconds(0.1);
        arm.raiseArmForLowerBasket();
        telemetry.addLine("Level 1 Ascent Completed!");
        telemetry.addLine("Blue Left Autonomous Complete!");
        telemetry.update();
    }

    public class GrabFromWall {
        // Instance variables if needed

        public void raiseArmForSpecimenPickupFromWall() {
            // Implementation code here
        }
        public void OpenClaw(){}
    }

    public class raiseArmforHighBasket {
        // Instance variables if needed

        public void RaiseArmForHighBasket() {
            // Implementation code here
        }
        public void OpenClaw(){}
    }

    public class HangSpecimen {
        // Instance variables if needed
        public void CloseClaw(){}
    }



    private void runRightPath() {
        Pose2d initPose1 = new Pose2d(0, 42, 90.00);
        Pose2d pose1 = new Pose2d(0, 33, 90.00);
        Pose2d pose2 = new Pose2d(-35, 33, 90.00);
        Pose2d pose3 = new Pose2d(-35, -5, 90);
        Pose2d pose4 = new Pose2d(-44, -5, 90);
        Pose2d pose5 = new Pose2d(-44, 40, 90);
        Pose2d pose6 = new Pose2d(-44, -5, 90);
        Pose2d pose9 = new Pose2d(-55, 6, 90);
        Pose2d pose10 = new Pose2d(-55, 40, 90);
        Pose2d pose11 = new Pose2d(-55, 6, 90);
        Pose2d pose12 = new Pose2d(-72, 67.5, 180.00);
        Pose2d pose13 = new Pose2d(0, 48, 90);
        Pose2d pose14 = new Pose2d(0, 45, 90.00);
        //Linetosplineheading back to pose12 and repeat

        MecanumDrive drive = new MecanumDrive(hardwareMap, initPose1);

        // Move to pose1
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose1.position.y)
                        .build()
        );
        safeWaitSeconds(0.1);

        // Move to pose2 and turn
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToX(pose2.position.x)
                        .turnTo(Math.toRadians(90))
                        .build()
        );
        safeWaitSeconds(0.1);

        // Move to pose3
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose3.position.y)
                        .build()
        );
        safeWaitSeconds(0.1);

        // Strafe to pose4
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(pose4.position)
                        .build()
        );
        safeWaitSeconds(0.1);

        // Move to pose5
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose5.position.y)
                        .build()
        );

        // Move to pose6
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose6.position.y)
                        .build()
        );

        // Strafe to pose9
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .strafeTo(pose9.position)
                        .build()
        );
        safeWaitSeconds(0.1);

        // Move to pose10
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose10.position.y)
                        .build()
        );

        // Move to pose11
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose11.position.y)
                        .build()
        );

        //Move the pose12
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .splineTo(pose12.position, pose12.heading)
                        .build());
        new GrabFromWall();

        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToYSplineHeading(pose13.position.y, pose13.heading)
                        .build());
        new raiseArmforHighBasket();

        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                        .lineToY(pose14.position.y)
                        .build());
        new HangSpecimen();








    }

    public void safeWaitSeconds(double time) {
        ElapsedTime timer = new ElapsedTime(SECONDS);
        timer.reset();
        while (!isStopRequested() && timer.time() < time) {
        }
    }
}
