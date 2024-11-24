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
import org.firstinspires.ftc.team26396.opmodes.Subsystems.ArmCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.IntakeCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.WristCode;



@TeleOp(name="TeleOp", group="TeleOpFINAL")
public class FieldCentricDriveWithArm extends LinearOpMode {
    private ArmCode armControl;  // Declare the ArmControl object
    private IntakeCode intakeControl;  // Declare the intakeControl object
    private WristCode wristControl;  // Declare the wristControl object


    @Override
    public void runOpMode() throws InterruptedException {
        // Declare the motors for the drive system
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Declare the arm motors
        DcMotor armMotor = hardwareMap.dcMotor.get("arm-linearSlide"); // aka LinearSlide
        DcMotor liftMotor = hardwareMap.dcMotor.get("lift"); // aka ArmPivot

        //Declare intake servo
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");

        //Declare intake servo
        Servo wristServo = hardwareMap.get(Servo.class, "wrist");

        // Reverse the right side motors (if necessary)
        //NOTE: 3 motors are forward, 1 reverse, or vice versa
        //TODO: Update NOTE once confirmed
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        // Set zero power behavior to BRAKE for both motors to
        //make sure they don't go crazy upon start
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Intialize the armControl object and pass the arm motors
        armControl = new ArmCode(armMotor, liftMotor);

        // Intialize the intakeControl object and pass the intake CRServo
        intakeControl = new IntakeCode(intakeServo);

        // Intialize the wristControl object and pass the wrist Servo
        wristControl = new WristCode(wristServo);

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
            Utlize Field Centric Drive --
            Means that robot will move in relation to field
            Ex: Moves forward, then right, the front face of the robot will
            still be looking the same direction even after turning.
            */
            //gamepad1 left and right sticks are used for strafing
            //gamepad 1 right stick is used for turning
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // Reset the robot's yaw when pressing the options button (on a PS4 controller)
            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);
            //
            // DRIVE CODE -- End --
            //

            /*
             Arm control using the ArmCode class -- Uses Dpad Up and Down
             and PS4 controller buttons 'cross' and 'triangle'
              - Dpad Controls Linear Slide
              - Buttons control Arm Rotation
            */
            armControl.controlArm(gamepad2);


            //Intake control using the IntakeCode class -- uses bumper
            intakeControl.controlIntake(gamepad2.left_bumper, gamepad2.right_bumper);

            //Wrist control using the WristCode class -- Uses dpad
            wristControl.controlWrist(gamepad2.dpad_left, gamepad2.dpad_right);

            // Telemetry for monitoring
            telemetry.addData("Front Left Motor Power", frontLeftPower);
            telemetry.addData("Back Left Motor Power", backLeftPower);
            telemetry.addData("Front Right Motor Power", frontRightPower);
            telemetry.addData("Back Right Motor Power", backRightPower);
            telemetry.addData("Arm Motor Power", armMotor.getPower());
            telemetry.addData("Lift Motor Power", liftMotor.getPower());
            telemetry.addData("Intake (CRServo) Power", intakeServo.getPower());
            telemetry.addData("Wrist (Servo) Power", wristServo.getPosition());
            telemetry.update();

        }
    }
}
