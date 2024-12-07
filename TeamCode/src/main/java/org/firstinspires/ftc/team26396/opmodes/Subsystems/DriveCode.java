package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveCode {
    // Define the drive motors
    private DcMotor FrontLeftMotor;
    private DcMotor BackLeftMotor;
    private DcMotor FrontRightMotor;
    private DcMotor BackRightMotor;

    // Gamepad for input control
    private Gamepad controller;

    // Constructor to initialize motors and gamepad
    public DriveCode(DcMotor FrontLeftM, DcMotor BackLeftM, DcMotor BackRightM, DcMotor FrontRightM, Gamepad c) {
        FrontLeftMotor = FrontLeftM;
        BackLeftMotor = BackLeftM;
        FrontRightMotor = FrontRightM;
        BackRightMotor = BackRightM;
        controller = c;
    }

    // Update method to handle teleoperation inputs
    public void update() {
        // Get joystick values
        double LeftJoyStickVal = controller.left_stick_y;
        double RightJoyStickVal = controller.right_stick_x;

        // Calculate power for left and right motors
        double powerLeft = LeftJoyStickVal - RightJoyStickVal;
        double powerRight = LeftJoyStickVal + RightJoyStickVal;

        // Set motor powers
        SetLeftMotors(-powerLeft);  // Invert to match forward/backward
        SetRightMotors(powerRight);
    }

    // Method to set power for left motors
    public void SetLeftMotors(double power) {
        FrontLeftMotor.setPower(power);
        BackLeftMotor.setPower(power);
    }

    // Method to set power for right motors
    public void SetRightMotors(double power) {
        FrontRightMotor.setPower(power);
        BackRightMotor.setPower(power);
    }

    // Method to get the average encoder value of the left motors
    public int GetLeftEncoders() {
        int frontVal = FrontLeftMotor.getCurrentPosition();
        int backVal = BackLeftMotor.getCurrentPosition();
        return (frontVal + backVal) / 2;
    }

    // Method to get the average encoder value of the right motors
    public int GetRightEncoders() {
        int frontVal = FrontRightMotor.getCurrentPosition();
        int backVal = BackRightMotor.getCurrentPosition();
        return (frontVal + backVal) / 2;
    }

    // Method to reset all motor encoders
    public void ResetEncoders() {
        FrontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Method to reset the drive system (stop motors and reset encoders)
    public static void reset(DriveCode drive) {
        drive.SetLeftMotors(0);
        drive.SetRightMotors(0);
        drive.ResetEncoders();
    }
}
