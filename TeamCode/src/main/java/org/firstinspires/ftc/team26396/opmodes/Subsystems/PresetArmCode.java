package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PresetArmCode {
    private DcMotor linearSlideMotor;
    private DcMotor armMotor;

    // Preset arm positions (in encoder ticks)
    private final int ARM_GROUND = 10; //Make sure it isn't touching actual ground
    private final int ARM_LOW = 500; //TODO: Edit to 1500 for Low Basket
    private final int ARM_HIGH = 1000; //TODO: Edit
    private final int ARM_MAX = 1500; //TODO: Edit (2000)

    private static final double LinearSlide_POWER = 0.8;


    // ArmMotor power settings
    private static final double ARM_POWER = 0.8;

    public PresetArmCode(DcMotor linearSlideMotor, DcMotor armMotor) {
        this.linearSlideMotor = linearSlideMotor;
        this.armMotor = armMotor;

        //REFER TO ORIGINAL ARM CODE
        // Configure the lift motor with encoders
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Brake
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void controlArmPreset(Gamepad gamepad) {

        //Preset to Ground Level (Doesn't touch the ground and pivot on the claw)
        setArmPosition(ARM_GROUND);

        // Arm Motor Control using preset positions
        if (gamepad.left_trigger > 0.1) {
            linearSlideMotor.setPower(-LinearSlide_POWER); // Move arm down
        } else if (gamepad.right_trigger > 0.1) {
            linearSlideMotor.setPower(LinearSlide_POWER); // Move arm up
        } else {
            linearSlideMotor.setPower(0); // Stop arm movement
        }


        // Lift Motor Control using preset positions
        if (gamepad.dpad_up) {
            setArmPosition(ARM_MAX); // Lift to max position, for hanging
        } else if (gamepad.dpad_down) {
            setArmPosition(ARM_GROUND); // Lift to Lowest position, pick up blocks from submersible
        } else if (gamepad.dpad_left) {
            setArmPosition(ARM_LOW); // Lift to low position, i.e Low Basket
        } else if (gamepad.dpad_right) {
            setArmPosition(ARM_HIGH); // Lift to High position, i.e High Basket
        }
    }

    // Set Lift position using encoder ticks
    private void setArmPosition(int targetPosition) {
        armMotor.setTargetPosition(targetPosition);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(ARM_POWER);
    }
}
