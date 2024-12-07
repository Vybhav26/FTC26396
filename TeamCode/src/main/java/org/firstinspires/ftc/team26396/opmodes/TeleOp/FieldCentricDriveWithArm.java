package org.firstinspires.ftc.team26396.opmodes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

// Subsystems Imports
import org.firstinspires.ftc.team26396.opmodes.Subsystems.PresetArmCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.IntakeCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.WristCode;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.DriveCode;

@TeleOp(name = "TeleOp", group = "TeleOpFINAL")
public class FieldCentricDriveWithArm extends LinearOpMode {

    // Subsystems
    private PresetArmCode armControl;
    private IntakeCode intakeControl;
    private WristCode wristControl;
    private DriveCode driveControl;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare the motors and servos
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor armMotor = hardwareMap.dcMotor.get("armMotor");
        DcMotor liftMotor = hardwareMap.dcMotor.get("liftMotor");
        CRServo intakeServo = hardwareMap.get(CRServo.class, "intake");
        Servo wristServo = hardwareMap.get(Servo.class, "wrist");

        // Initialize and configure IMU
        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters imuParameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(imuParameters);

        // Reverse the necessary motors - Right = Forward, Left = Reverse
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE); //Forward
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction./*REVERSE*/FORWARD);


        // Set zero power behavior
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize subsystems
        armControl = new PresetArmCode(armMotor, liftMotor);
        intakeControl = new IntakeCode(intakeServo);
        wristControl = new WristCode(wristServo);
        driveControl = new DriveCode(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor, imu);

        waitForStart();

        while (opModeIsActive()) {

            /*
            Overall, Gamepad 1 Controls Drive, Wrist, Intake.
                     Gamepad 2 Controls ArmPrests and LinearSlide
             */

            // DRIVE CODE
            driveControl.drive(gamepad1);
            /*
            Gamepad1 Joysticks:
            a) LeftStick, when moved in Y direction controls up and down movement
            b) LeftStick, when moved in X direction controls left and right (strafing) movement
            c) RightStick, when moved in the +X direction right, and left in the -X direction
             */

            // ARM CONTROL
            armControl.controlArmPreset(gamepad2);
            /*
            LINEAR SLIDE
            Gamepad2 Trigger:
            a) Using Left Trigger - move linear slide backward
            b) Using Right Trigger - move linear slide forward

            ARM
            Gamepad2 Dpad:
            c) Using Dpad UP - Lift to highest position, for hanging
            d) Using Dpad DOWN - Lift to Lowest position, pick up blocks from submersible
            e) Using Dpad LEFT - Lift to low position, i.e Low Basket
            f) Using Dpad RIGHT - Lift to high position, i.e Upper Basket
             */

            // INTAKE CONTROL
            intakeControl.controlIntake(gamepad1);
            /*
            Gamepad1 Trigger:
            a) Using Left Trigger - pushes block OUTWARD
            b) Using Right Trigger - take block IN
             */

            // WRIST CONTROL
            wristControl.controlWrist(gamepad2.square, gamepad2.circle);
            /*
            Gamepad2 Buttons (PS4 Controller):
            a) Using Square Button - Bends Wrist to the Left
            b) Using Circle Trigger - Makes Wrist straight
             */



            // TELEMETRY
            telemetry.addData("Front Left Power", frontLeftMotor.getPower());
            telemetry.addData("Back Left Power", backLeftMotor.getPower());
            telemetry.addData("Front Right Power", frontRightMotor.getPower());
            telemetry.addData("Back Right Power", backRightMotor.getPower());
            telemetry.addData("Arm Motor Power", armMotor.getPower());
            telemetry.addData("Lift Motor Power", liftMotor.getPower());
            telemetry.addData("Intake Power", intakeServo.getPower());
            telemetry.addData("Wrist Position", wristServo.getPosition());
            telemetry.update();
        }
    }
}
