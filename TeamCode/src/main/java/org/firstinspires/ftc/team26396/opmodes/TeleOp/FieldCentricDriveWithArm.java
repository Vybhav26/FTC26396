package org.firstinspires.ftc.team26396.opmodes.TeleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

//SubsystemsCodeImports
//import org.firstinspires.ftc.team26396.opmodes.Subsystems.ArmCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.PresetArmCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.IntakeCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.WristCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.DriveCode;


@TeleOp(name="TeleOp", group="TeleOpFINAL")
public class FieldCentricDriveWithArm extends LinearOpMode {
    // private ArmCode armControl;  // Declare the ArmControl object
    private IntakeCode intakeControl;  // Declare the intakeControl object
    private WristCode wristControl;  // Declare the wristControl object
    private PresetArmCode armControl; //Declare the PresetArmControl object
    private DriveCode driveControl;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare the motors for the drive system
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Declare the arm motors
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor"); // aka LinearSlide
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor"); // aka ArmPivot

        //Declare intake servo
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");

        //Declare wrist servo
        Servo wristServo = hardwareMap.get(Servo.class, "wrist");

        // Reverse the right side motors (if necessary)
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set zero power behavior to BRAKE for both motors
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize the armControl object and pass the arm motors
        //armControl = new ArmCode(armMotor, liftMotor);

        // Initialize the PresetArmControl object and pass the arm motors
        armControl = new PresetArmCode(armMotor, liftMotor);

        // Initialize the intakeControl object and pass the intake CRServo
        intakeControl = new IntakeCode(intakeServo);

        // Initialize the wristControl object and pass the wrist Servo
        wristControl = new WristCode(wristServo);

        // Initialize the driveControl object and pass the 4 drive motors
        driveControl = new DriveCode(frontLeftMotor, backLeftMotor, backRightMotor, frontRightMotor, gamepad1);

        // Retrieve the IMU from the hardware map
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        waitForStart();

        while (opModeIsActive()) {
            //
            // DRIVE CODE -- Start --
            //
/*
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // Reset the robot's yaw when pressing the options button
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
  */
            //
            // DRIVE CODE -- End --
            //

            // Arm control using the ArmCode class
            //armControl.controlArm(gamepad2);

            // Arm control using the PresetArmCode class - works
            armControl.controlArmPreset(gamepad2);

            // Intake control using the IntakeCode class -- now including telemetry -- remove it, fix
            intakeControl.controlIntake(gamepad2, telemetry);

            // Wrist control using the WristCode class, fix
            wristControl.controlWrist(gamepad2.dpad_left, gamepad2.dpad_right);

            // Wrist control using the WristCode class, fix
            driveControl.update();

            // Telemetry for monitoring
//            telemetry.addData("Front Left Motor Power", frontLeftPower);
//            telemetry.addData("Back Left Motor Power", backLeftPower);
//            telemetry.addData("Front Right Motor Power", frontRightPower);
//            telemetry.addData("Back Right Motor Power", backRightPower);
            telemetry.addData("Arm Motor Power", armMotor.getPower());
            telemetry.addData("Lift Motor Power", liftMotor.getPower());
            telemetry.addData("Intake (CRServo) Power", intakeServo.getPower());
            telemetry.addData("Wrist (Servo) Power", wristServo.getPosition());

            telemetry.update();

        }
    }
}
