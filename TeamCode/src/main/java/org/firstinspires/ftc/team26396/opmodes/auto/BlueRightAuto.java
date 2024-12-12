package org.firstinspires.ftc.team26396.opmodes.auto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="Blue Right Auto",group="LinearOpMode")
@Disabled
public class BlueRightAuto extends LinearOpMode {
    // Initialize the hardware variables
    private  DcMotor leftFrontDrive;
    private  DcMotor leftBackDrive;
    private  DcMotor rightFrontDrive;
    private  DcMotor rightBackDrive;
    private  DcMotor armMotor;
    private  DcMotor liftMotor;
    private  CRServo intake;
    private  ElapsedTime runtime = new ElapsedTime();
    private  CRServo wristServo;


    // Initialize all drive constants
    private static final double COUNTS_PER_MOTOR_REV = 537.6; // Adjust for your motor
    private static final double DRIVE_GEAR_REDUCTION = 19.2; // No gear reduction
    private static final double WHEEL_DIAMETER_INCHES = 3.7796; // Wheel diameter
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
    private static final double WHEELBASE_WIDTH = 14.0; // Example width in inches
    private static final double TURNING_RADIUS = WHEELBASE_WIDTH / 2;
    private static final double COUNTS_PER_RADIAN = (COUNTS_PER_MOTOR_REV / (Math.PI * WHEEL_DIAMETER_INCHES)) * (TURNING_RADIUS);




    // Motor power settings
    private static final double ARM_POWER = 0.8;
    private static final double LIFT_POWER = 0.8;

    final double ARM_TICKS_PER_DEGREE =
            28 // number of encoder ticks per rotation of the bare motor
                    * 19.2  // This is the exact gear ratio of the 50.9:1 Yellow Jacket gearbox
                    * 100.0 / 20.0 // This is the external gear reduction, a 20T pinion gear that drives a 100T hub-mount gear
                    * 1/360.0;// we want ticks per degree, not per rotation

    final double ARM_COLLAPSED_INTO_ROBOT  = 0;
    final double ARM_CLEAR_BARRIER         = 15 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SPECIMEN        = 90 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SAMPLE_IN_LOW   = 90 * ARM_TICKS_PER_DEGREE;
    final double ARM_SCORE_SAMPLE_IN_HIGH = 110 * ARM_TICKS_PER_DEGREE;


    @Override
    public void runOpMode() {

        leftFrontDrive = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        leftBackDrive = hardwareMap.get(DcMotor.class, "backLeftMotor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontRightMotor");
        rightBackDrive = hardwareMap.get(DcMotor.class, "backRightMotor");
        armMotor = hardwareMap.get(DcMotor.class, "armMotor");
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");
        intake = hardwareMap.get(CRServo.class, "intake");
        wristServo = hardwareMap.get(CRServo.class, "wrist");
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

    // Set Lift position using encoder ticks
    public void setLiftPosition(int targetPosition) {
        liftMotor.setTargetPosition(targetPosition);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(LIFT_POWER);
    }

    // Set Arm position using encoder ticks
    private void setArmPosition(int targetPosition) {
        armMotor.setTargetPosition(targetPosition);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(ARM_POWER);
    }

    private void MotorPower(double speed) {
        //set MotorPower for the OpMode
        leftFrontDrive.setPower(speed);
        leftBackDrive.setPower(speed);
        rightFrontDrive.setPower(speed);
        rightBackDrive.setPower(speed);
    }

    private void driveInches(String direction, double speed, double value) {
        int targetPosition;
        // Determine the target position or radians based on the direction
        if (direction.equalsIgnoreCase("turn")) {
            targetPosition = (int) (value * COUNTS_PER_RADIAN); // 'value' is radians
        } else {
            targetPosition = (int) (value * COUNTS_PER_INCH); // 'value' is inches
        }

        switch (direction.toLowerCase()) {
            case "forward":  // Move forward
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + targetPosition);
                telemetry.addData("Status", "Moving forward");
                telemetry.update();

                break;

            case "backward": // Move backward
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() - targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() - targetPosition);
                telemetry.addData("Status", "Moving backward");
                telemetry.update();

                break;

            case "right": // Strafing right
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() + targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() - targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + targetPosition);
                telemetry.addData("Status", "Strafing right");
                telemetry.update();

                break;

            case "left": // Strafing left
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() + targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() - targetPosition);
                telemetry.addData("Status", "Strafing left");
                telemetry.update();

                break;
            case "turn": //Changing the orientation of the robot[For the OpMode code, do Math.toRadians
                leftFrontDrive.setTargetPosition(leftFrontDrive.getCurrentPosition() - targetPosition);
                leftBackDrive.setTargetPosition(leftBackDrive.getCurrentPosition() - targetPosition);
                rightFrontDrive.setTargetPosition(rightFrontDrive.getCurrentPosition() + targetPosition);
                rightBackDrive.setTargetPosition(rightBackDrive.getCurrentPosition() + targetPosition);
                telemetry.addData("Status", "Turning " + value + " degrees");
                telemetry.update();

                break;
        }


    }

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
        telemetry.update();
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
        intake.setPower(-1.0);
    }
}
