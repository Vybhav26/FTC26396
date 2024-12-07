package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeCode {

    // Define the CRServo for the intake system
    private CRServo intake = null;

    // Store the current power level of the intake
    public double currentPower = 0.0;

    // Define constants for intake power values
    private static final double INTAKE_COLLECT = 1.0;  // Power for collecting
    private static final double INTAKE_DEPOSIT = -1.0; // Power for depositing

    public IntakeCode(CRServo intake) {
        this.intake = intake;
        // Ensure the servo is off at the start
        stopIntake();
    }

    /**
     * Controls the intake servo based on the gamepad inputs.
     *
     * @param gamepad   The gamepad providing input
     * @param telemetry The telemetry object for feedback
     */
    public void controlIntake(Gamepad gamepad, Telemetry telemetry) {
        // Check for stick or button inputs
        if (gamepad.left_stick_y < -0.5) {
            currentPower = INTAKE_COLLECT; // Push stick up to collect
        } else if (gamepad.left_stick_y > 0.5) {
            currentPower = INTAKE_DEPOSIT; // Push stick down to deposit
        } else if (gamepad.cross) {
            currentPower = INTAKE_COLLECT; // X button for collect
        } else if (gamepad.circle) {
            currentPower = INTAKE_DEPOSIT; // O button for deposit
        } else {
            currentPower = 0.0; // Neutral/stop if no input
        }

        // Set the intake power
        intake.setPower(currentPower);

        // Send feedback to telemetry
        telemetry.addData("Intake Power", currentPower);
        telemetry.update();
    }

    /**
     * Stops the intake by setting power to zero.
     */
    public void stopIntake() {
        currentPower = 0.0;
        intake.setPower(currentPower);
    }
}
