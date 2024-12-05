package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PresetArmCode {
    private DcMotor armMotor;
    private DcMotor liftMotor;

//ALL TAKEN FROM GOBILDA VANILLA
    // Preset arm positions (in encoder ticks)
    /*
    private final int ARM_COLLAPSED = 0;
    private final int ARM_COLLECT = 500;
    private final int ARM_CLEAR_BARRIER = 1000;
    private final int ARM_SCORE_SPECIMEN = 2000;
*/
    // Preset lift positions (in encoder ticks)
    private final int LIFT_GROUND = 0;
    private final int LIFT_LOW = 500;
    private final int LIFT_HIGH = 1000;
    private final int LIFT_MAX = 1500;

    private static final double ARM_POWER = 0.8;


    // Motor power settings
    private static final double LIFT_POWER = 0.8;

    public PresetArmCode(DcMotor armMotor, DcMotor liftMotor) {
        this.armMotor = armMotor;
        this.liftMotor = liftMotor;

        //REFER TO ORIGINAL ARM CODE
        // Configure the lift motor with encoders
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Brake
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void controlArmPreset(Gamepad gamepad) {
        // Arm Motor Control using preset positions
        if (gamepad.left_trigger > 0.1) {
            armMotor.setPower(-ARM_POWER); // Move arm down
        } else if (gamepad.right_trigger > 0.1) {
            armMotor.setPower(ARM_POWER); // Move arm up
        } else {
            armMotor.setPower(0); // Stop arm movement
        }


        // Lift Motor Control using preset positions
        if (gamepad.dpad_up) {
            setLiftPosition(LIFT_HIGH); // Lift to high position
        } else if (gamepad.dpad_down) {
            setLiftPosition(LIFT_GROUND); // Lift to ground position
        } else if (gamepad.dpad_left) {
            setLiftPosition(LIFT_LOW); // Lift to low position
        } else if (gamepad.dpad_right) {
            setLiftPosition(LIFT_MAX); // Lift to max position
        }
    }

    // Set Arm position using encoder ticks
    /*
    private void setArmPosition(int targetPosition) {
        armMotor.setTargetPosition(targetPosition);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armMotor.setPower(ARM_POWER);
    }
*/
    // Set Lift position using encoder ticks
    private void setLiftPosition(int targetPosition) {
        liftMotor.setTargetPosition(targetPosition);
        liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftMotor.setPower(LIFT_POWER);
    }
}
