package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class IntakeCode {

    // Define the CRServo for the intake system
    private CRServo intake = null;

    // Store the current power level of the intake
     public double currentPower= 0.0;

    // Define constants for intake power values
    private static final double INTAKE_COLLECT = 1.0;  // Power for collecting
    private static final double INTAKE_DEPOSIT = -1.0; // Power for depositing

    public IntakeCode(CRServo intake) {
        this.intake = intake;
        // Ensure the servo is off at the start
        intake.setPower(currentPower);
    }

    /**
     * Controls the intake servo based on the bumper buttons pressed.
     *
     * @param leftBumper The state of the left bumper button on the gamepad
     * @param rightBumper The state of the right bumper button on the gamepad
     */
    public void controlIntake(boolean leftBumper, boolean rightBumper) {
        if (leftBumper) {
            currentPower = INTAKE_COLLECT; // Set current power to collect when left bumper is pressed
        }
        else if (rightBumper) {
            currentPower = INTAKE_DEPOSIT; // Set current power to deposit when right bumper is pressed
        }

        // Reset power of intake to 0.0 upon rest
        intake.setPower(currentPower);
    }

    /**
     * Stops the intake motor, but maintains the current power level.
     */
    public void stopIntake() {
        intake.setPower(currentPower); // Keeps the intake at its current power level rather than stopping it completely
    }
}
