package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClawCode {

    // Define the CRServo for the intake system
    private CRServo claw = null;

    // Store the current power level of the intake
    public double currentPower = 0.0;

    // Define constants for intake power values
    private static final double INTAKE_COLLECT = 1.0;  // Power for collecting
    private static final double INTAKE_DEPOSIT = -1.0; // Power for depositing
    private static final double INTAKE_OFF = 0.0;      // Power for stopping

    public ClawCode(CRServo claw) {
        this.claw = claw;
        // Ensure the servo is off at the start
        stopClaw();
    }

    /**
     * Controls the intake servo based on button inputs.
     *
     * @param collectButton   True if the collect button is pressed
     * @param depositButton   True if the deposit button is pressed
     */
    public void controlClaw(Gamepad gamepad) {
        if (gamepad.left_trigger > 0.1) {
            currentPower = INTAKE_COLLECT; // Set power to collect
        } else if (gamepad.right_trigger > 0.1) {
            currentPower = INTAKE_DEPOSIT; // Set power to deposit
        } else {
            currentPower = INTAKE_OFF; // Stop the intake
        }

        // Set the intake power
        if (claw != null) {
            claw.setPower(currentPower);
        }
    }

    /**
     * Stops the intake by setting power to zero.
     */
    public void stopClaw() {
        currentPower = INTAKE_OFF;
        if (claw != null) {
            claw.setPower(currentPower);
        }
    }
}
