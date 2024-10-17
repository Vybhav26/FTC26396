package org.firstinspires.ftc.team26396.opmodes.test.DriveTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Full: Mecanum Right Joystick Control", group = "Linear OpMode")
public class BasicOmniOpMode_Linear extends LinearOpMode {

    // Declare OpMode members for the 4 motors and elapsed time
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables for the 4 mecanum motors
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Set motor directions: Reverse the motors on one side to ensure correct movement
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Display initialization status on Driver Station
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        runtime.reset();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;

            // Use the right joystick for all directional movement
            double axial = -gamepad1.right_stick_y;   // Forward/Backward
            double lateral = gamepad1.right_stick_x;  // Left/Right Strafing
            double yaw = 0;  // Set to 0 as we won't rotate for this setup

            // Calculate power for each wheel using the Mecanum formula
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            // Normalize the power values to ensure no value exceeds 1.0
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Set power to motors based on calculations
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            // Display telemetry information
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "Front L/R: (%.2f, %.2f), Back L/R: (%.2f, %.2f)",
                    leftFrontPower, rightFrontPower, leftBackPower, rightBackPower);
            telemetry.update();
        }
    }
}
