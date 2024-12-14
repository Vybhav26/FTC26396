package org.firstinspires.ftc.team26396.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Move Forward 10 Inches", group="Simple")
public class BasicPark extends LinearOpMode {

    // Declare motor variables
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    // Constants for encoder calculations
    private static final double COUNTS_PER_MOTOR_REV = 1440; // Adjust based on your motor specs
    private static final double DRIVE_GEAR_REDUCTION = 1.0; // No gear reduction
    private static final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter in inches
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode() {
        // Initialize motors
        leftFront = hardwareMap.dcMotor.get("frontLeftMotor");
        leftRear = hardwareMap.dcMotor.get("backLeftMotor");
        rightFront = hardwareMap.dcMotor.get("frontRightMotor");
        rightRear = hardwareMap.dcMotor.get("backRightMotor");


        // Set motor directions (reverse right motors for proper movement)
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.REVERSE);

        // Reset encoders and set to RUN_USING_ENCODER mode
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Wait for the game to start
        waitForStart();

        // Move forward 10 inches
        int targetPosition = (int) (10 * COUNTS_PER_INCH);

        leftFront.setTargetPosition(targetPosition);
        rightFront.setTargetPosition(targetPosition);
        leftRear.setTargetPosition(targetPosition);
        rightRear.setTargetPosition(targetPosition);

        // Set power to motors
        leftFront.setPower(0.5);
        rightFront.setPower(0.5);
        leftRear.setPower(0.5);
        rightRear.setPower(0.5);

        // Wait until the motors finish their movement
        while (opModeIsActive() &&
                (leftFront.isBusy() && rightFront.isBusy() && leftRear.isBusy() && rightRear.isBusy())) {
            telemetry.addData("Status", "Moving forward");
            telemetry.update();
        }

        // Stop all motion
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        rightRear.setPower(0);

        // Set motors to run without encoders
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Indicate completion
        telemetry.addData("Status", "Task Complete");
        telemetry.update();
    }
}
