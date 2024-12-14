package org.firstinspires.ftc.team26396.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Move Forward 10 Inches (No Encoders)", group="Simple")
public class BasicPark extends LinearOpMode {

    // Declare motor variables
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    // Constants
    private static final double POWER = 0.5; // Motor power
    private static final long DRIVE_TIME_MS = 1000; // Adjust based on trial runs for 10 inches

    @Override
    public void runOpMode() {
        // Initialize motors
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        // Set motor directions (reverse right motors for proper movement)
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Wait for the game to start
        waitForStart();

        // Move forward for a specified time
        frontLeftMotor.setPower(POWER);
        frontRightMotor.setPower(POWER);
        backLeftMotor.setPower(POWER);
        backRightMotor.setPower(POWER);

        // Sleep for the determined time to move forward 10 inches
        sleep(DRIVE_TIME_MS);

        // Stop all motion
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);

        // Indicate completion
        telemetry.addData("Status", "Task Complete");
        telemetry.update();
    }
}
