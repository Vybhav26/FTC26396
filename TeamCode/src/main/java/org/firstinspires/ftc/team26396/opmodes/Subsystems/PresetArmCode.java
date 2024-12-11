package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PresetArmCode {
    private DcMotor linearSlideMotor;
    private DcMotor armMotor;

    // Preset arm positions (in encoder ticks)
    final double ARM_TICKS_PER_DEGREE =
            28 // number of encoder ticks per rotation of the bare motor
                    * 250047.0 / 4913.0 // This is the exact gear ratio of the 50.9:1 Yellow Jacket gearbox
                    * 100.0 / 20.0 // This is the external gear reduction, a 20T pinion gear that drives a 100T hub-mount gear
                    * 1/360.0; // we want ticks per degree, not per rotation



    private final int ARM_GROUND = 50; //Make sure it isn't touching actual ground
    private final int ARM_LOW = 500; //TODO: Edit to 1500 for Low Basket
    private final int ARM_HIGH = 1500; //TODO: LOW BASKET
    private final int ARM_MAX = 2000; //TODO: HIGH BASKET


//1500 = low basket.... 3000
    private static final double LinearSlide_POWER = 0.8;


    // ArmMotor power settings
    private static final double ARM_POWER = 1.0;

    public PresetArmCode(DcMotor linearSlideMotor, DcMotor armMotor) {
        this.linearSlideMotor = linearSlideMotor;
        this.armMotor = armMotor;

        //REFER TO ORIGINAL ARM CODE
        // Configure the lift motor with encoders
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        setArmPosition(ARM_GROUND);

        // Brake
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void controlArmPreset(Gamepad gamepad) {

        //Preset to Ground Level (Doesn't touch the ground and pivot on the claw)

        // LinearSlide Motor Control using preset positions
        if (gamepad.left_trigger > 0.1) {
            linearSlideMotor.setPower(-LinearSlide_POWER); // Move arm down
        } else if (gamepad.right_trigger > 0.1) {
            linearSlideMotor.setPower(LinearSlide_POWER); // Move arm up
        } else {
            linearSlideMotor.setPower(0); // Stop arm movement
        }


        // Arm Motor Control using preset positions
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
