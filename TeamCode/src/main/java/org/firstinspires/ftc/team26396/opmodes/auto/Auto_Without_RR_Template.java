package org.firstinspires.ftc.team26396.opmodes.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name="Blue Right Auto",group="LinearOpMode")
@Disabled
public class Auto_Without_RR_Template extends LinearOpMode {
    // Initialize the hardware variables for all motors
    private final DcMotor leftFrontDrive = hardwareMap.get(DcMotorEx.class, "frontLeftMotor");
    private final DcMotor leftBackDrive = hardwareMap.get(DcMotorEx.class, "backLeftMotor");
    private final DcMotor rightFrontDrive = hardwareMap.get(DcMotorEx.class, "frontRightMotor");
    private final DcMotor rightBackDrive = hardwareMap.get(DcMotorEx.class, "backRightMotor");
    private final DcMotor armMotor = hardwareMap.get(DcMotor.class, "armMotor");
    private final DcMotor liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
    private final CRServo intake = hardwareMap.get(CRServo.class, "intake");
    private final ElapsedTime runtime = new ElapsedTime();

    // Encoder counts per inch (tune this value based on your robot’s configuration)

    private static final double COUNTS_PER_MOTOR_REV = 537.6; // Adjust for your motor
    private static final double DRIVE_GEAR_REDUCTION = 19.2; // No gear reduction
    private static final double WHEEL_DIAMETER_INCHES = 3.7796; // Wheel diameter
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
    private static final double WHEELBASE_WIDTH = 14.0; // Example width in inches
    private static final double TURNING_RADIUS = WHEELBASE_WIDTH / 2;
    private static final double COUNTS_PER_RADIAN = (COUNTS_PER_MOTOR_REV / (Math.PI * WHEEL_DIAMETER_INCHES)) * (TURNING_RADIUS);


    private void Telemetry() {
        //Initializes telemetry on driver station
        telemetry.addData("Status", "Initialized");
        telemetry.addData("Front Left Power", leftFrontDrive.getPower());
        telemetry.addData("Front Right Power", rightFrontDrive.getPower());
        telemetry.addData("Back Left Power", leftBackDrive.getPower());
        telemetry.addData("Back Right Power", rightBackDrive.getPower());
        telemetry.addData("Linear Arm Horizontal Extension Power", armMotor.getPower());
        telemetry.addData("Linear Arm Rotation Power", liftMotor.getPower());
        //If else statements for displaying telemetry for Intake
        if (intake.getPower() == 1) {
            telemetry.addData("Status:", "Intake");
        } else if (intake.getPower() == -1) {
            telemetry.addData("Status:", "Outtake");
        } else if (intake.getPower() == 0) {
            telemetry.addData("Status:", "Rest");
        }
    }

    private void Direction() {
        // Set motor directions: Reverse the motors on one side to ensure correct movement
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void ExtendArm(double power, long time) {
        //set armMotor(Horizontal) Power
        armMotor.setPower(power);
    }

    public void RotateArm(double power, long time) {
        //set liftMotor(Rotation) Power
        liftMotor.setPower(power);
    }

    public void Intake(double power, long time) {
        //set intake Power
        intake.setPower(1.0);
    }

    public void Outtake(double power, long time) {
        //set Outtake Power
        intake.setPower(1.0);
    }

    @Override
    public void runOpMode() {
        Direction();
        Telemetry();
        // Reset encoders
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motors to run using encoders
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            //Put all code



        }


    }

    public void MotorPower(double speed) {
        //set MotorPower for the OpMode
        leftFrontDrive.setPower(speed);
        leftBackDrive.setPower(speed);
        rightFrontDrive.setPower(speed);
        rightBackDrive.setPower(speed);
    }

    private void driveInches(String direction, double speed, double value) {
        int tp = (int) (value * COUNTS_PER_RADIAN);
        int targetPosition = (int) (value * COUNTS_PER_INCH);

        switch (direction.toLowerCase()) {
            case "forward":  // Move forward
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + targetPosition);
                break;

            case "backward": // Move backward
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() - targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() - targetPosition);
                break;

            case "right": // Strafing right
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() - targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + targetPosition);
                break;

            case "left": // Strafing left
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() - targetPosition);
                break;
            case "turn": //Changing the orientation of the robot
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - tp);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - tp);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + tp);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + tp);

            break;

            default:
                telemetry.addData("Error", "Invalid direction: " + direction);
                telemetry.update();
                return;
        }

    }
}
