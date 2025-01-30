package org.firstinspires.ftc.team26396.opmodes.auto.blue;

import static com.qualcomm.robotcore.util.ElapsedTime.Resolution.SECONDS;

import androidx.annotation.NonNull;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.team26396.constants.Constants;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.team26396.opmodes.auto.reference.FTCWIRES_AUTO;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;

@Autonomous(name="Blue Auto", group = "Blue Alliance", preselectTeleOp = "RobotCentricDrive")
public class Blue_Left extends LinearOpMode {

    private final Servo claw;

    private static final double OPEN_POSITION = 0.8;     // Position to place into an high basket (70 degrees)

    private static final double CLOSE_POSITION = 0.5;

    private static final double NEUTRAL_POSITION = 0.5;

    private static DcMotorEx armMotor = null;


    public static String TEAM_NAME = "Pack-A-Punch";
    public static int TEAM_NUMBER = 26396;

    public Blue_Left(Servo claw, DcMotorEx armMotor) {
        this.claw = claw;
        this.armMotor = armMotor;
    }

    //Define and declare Robot Starting Locations
    public enum START_POSITION{
        LEFT,
        RIGHT,
    }
    public static FTCWIRES_AUTO.START_POSITION startPosition;

        public void runOpMode() {
            //Key Pad input to selecting Starting Position of robot
            telemetry.setAutoClear(true);
            telemetry.clearAll();
            while(!isStopRequested()){
                telemetry.addData("Initializing FTC Wires (ftcwires.org) Autonomous adopted for Team:",
                        TEAM_NAME, " ", TEAM_NUMBER);
                telemetry.addData("---------------------------------------","");
                telemetry.addData("Select Starting Position  on gamepad 1:","");
                telemetry.addData("    Left   ", " = X ");
                telemetry.addData("    Right ", " = Y ");
                if(gamepad1.x){
                    startPosition = FTCWIRES_AUTO.START_POSITION.LEFT;
                    break;
                }
                if(gamepad1.y){
                    startPosition = FTCWIRES_AUTO.START_POSITION.RIGHT;
                    break;
                }
                telemetry.update();
            }
            telemetry.setAutoClear(false);
            telemetry.clearAll();

            telemetry.addData("Selected Starting Position", startPosition);
            telemetry.update();

            claw.setPosition(NEUTRAL_POSITION);
            new Arm.InitializeArm();

            waitForStart();

            //Game Play Button  is pressed
            if (opModeIsActive() && !isStopRequested()) {
                new Arm.DeactivateArm();
                //Build parking trajectory based on last detected target by vision
                runAutonoumousMode();
            }
        }

        public void runAutonoumousMode() {
//                                .splineTo(new Vector2d(48, 49), Math.toRadians(44)) //Moves in position to put Sample 1 in high basket
//                                .splineTo(new Vector2d(57, 58), Math.toRadians(44)) //Puts Sample 1 in high basket
//                                .splineTo(new Vector2d(59, 25), Math.toRadians(-90)) //Moves/picks up Sample 2
//                                .splineTo(new Vector2d(61, 49), Math.toRadians(62.49)) //Moves/puts  Sample 2 to/in high basket
//                                .splineTo(new Vector2d(69, 33), Math.toRadians(-59.14)) //Moves/picks up Sample 3
//                                .splineTo(new Vector2d(68, 52), Math.toRadians(83.38)) //Moves/puts  Sample 3 to/in high basket
//                                .splineTo(new Vector2d(27, 5), Math.toRadians(180.00)) //Moves to accomplish Level 1 Hang            //Initialize Pose2d as desired
            Pose2d initPose = new Pose2d(0, 70, Math.toRadians(90)); // Starting Pose
            Pose2d SpecimenHang = new Pose2d(0, 27, Math.toRadians(-90));
            Pose2d MoveSample1 = new Pose2d(49, 35, Math.toRadians(-90));
            Pose2d PickSample1 = new Pose2d(49, 24, Math.toRadians(-90));
            Pose2d PositionSample1 = new Pose2d(49, 49, Math.toRadians(44));
            Pose2d submersiblePark = new Pose2d(57, 58, 44);
            Pose2d PickSample2 = new Pose2d(59, 25, -90);
            Pose2d Sample2High = new Pose2d(61, 49, 62.49);
            Pose2d PickSample3 = new Pose2d(69, 33, -90);
            Pose2d Sample3High = new Pose2d(68, 52, 83.38);
            Pose2d Ascent1 = new Pose2d(27, 5, 180);


            double waitSecondsBeforeDrop = 0;
            MecanumDrive drive = new MecanumDrive(hardwareMap, initPose);

            if (startPosition == FTCWIRES_AUTO.START_POSITION.LEFT) {

                //Move Robot to Position to hand a specimen on the high chamber
                Actions.runBlocking(
                        drive.actionBuilder(drive.pose)
                                .splineTo(SpecimenHang.position, SpecimenHang.heading)
                                .build());
                safeWaitSeconds(0.1);
                telemetry.addLine("Move robot to submersible to place specimen on chamber!");
                telemetry.update();
                //Add Preset Code after speaking with Hema Aunty
                new Arm.RaiseArmForLowerBasket();
                claw.setPosition(OPEN_POSITION);
                safeWaitSeconds(0.1);
                telemetry.addLine("Place specimen on chamber!");
                telemetry.update();

                //Move to pick up Sample 1
                Actions.runBlocking(
                        drive.actionBuilder(drive.pose)
                                .strafeTo(MoveSample1.position)
                                .build());
                safeWaitSeconds(0.1);
                telemetry.addLine("Move Robot to pick up yellow sample 1!");
                telemetry.update();

                //Pick Up Sample 1
                Actions.runBlocking(
                        drive.actionBuilder(drive.pose)
                                .lineToY(PickSample1.position.y)
                                .build());
                safeWaitSeconds(0.1);
                telemetry.addLine("Pick up yellow Sample 1!");
                telemetry.update();
                new Arm.RaiseArmForSamplePickupFromFloor();
                claw.setPosition(CLOSE_POSITION);

            }

            else {
            }
        }
    //method to wait safely with stop button working if needed. Use this instead of sleep
    public void safeWaitSeconds(double time) {
        ElapsedTime timer = new ElapsedTime(SECONDS);
        timer.reset();
        while (!isStopRequested() && timer.time() < time) {
        }
    }
}