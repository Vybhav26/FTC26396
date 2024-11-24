package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class ArmCode {
    private DcMotor armMotor;
    private DcMotor liftMotor;

    // Motor power settings
    private static final double ARM_POWER = 0.8; // Adjust based on required speed
    private static final double LIFT_POWER = 0.8;

    public ArmCode(DcMotor armMotor, DcMotor liftMotor) {
        this.armMotor = armMotor;
        this.liftMotor = liftMotor;

        // Set zero power behavior to brake
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void controlArm(Gamepad gamepad) {
        // Linear Slide Motor Control (armMotor)
        if (gamepad.dpad_up) {
            armMotor.setPower(ARM_POWER);
        } else if (gamepad.dpad_down) {
            armMotor.setPower(-ARM_POWER);
        } else {
            armMotor.setPower(0);    // Stop the linear slide
        }

        // Lift Motor Control (liftMotor)
        if (gamepad.triangle) {
            liftMotor.setPower(LIFT_POWER);
        } else if (gamepad.cross) {
            liftMotor.setPower(-LIFT_POWER);
        } else {
            liftMotor.setPower(0);    // Stop the arm
        }
    }
}
