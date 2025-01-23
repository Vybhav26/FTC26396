package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class ClawCode {

    // This servo opens and closes the claw
    private Servo clawOpenCloseServo;

    // This CRServo spins the claw (rotates it continuously)
    private CRServo clawRotationCRServo;

    // This servo changes the direction the claw faces (yaw)
    private Servo clawYawServo;

    // Positions for opening and closing the claw
    private static final double CLAW_OPEN_POSITION = 0.8;   // Fully open
    private static final double CLAW_CLOSED_POSITION = 0.1; // Fully closed
    private static final double CLAW_NEUTRAL_POSITION = 0.5; // Half-open (neutral)

    // Power levels to rotate the claw left and right
    private static final double ROTATION_POWER_LEFT = -1.0;  // Spin the claw left
    private static final double ROTATION_POWER_RIGHT = 1.0;  // Spin the claw right
    private static final double ROTATION_POWER_STOP = 0.0;   // Stop spinning

    // Positions for yawing the claw (pointing it left, right, or straight)
    private static final double YAW_LEFT = 0.1;   // Face left
    private static final double YAW_RIGHT = 0.8;  // Face right
    private static final double YAW_CENTER = 0.5; // Face forward (neutral)

    /**
     * Sets up the claw system with all its servos.
     *
     * @param clawOpenCloseServo The servo that opens and closes the claw
     * @param clawRotationCRServo The CRServo that spins the claw
     * @param clawYawServo The servo that adjusts the claw's direction (yaw)
     */
    public ClawCode(Servo clawOpenCloseServo, CRServo clawRotationCRServo, Servo clawYawServo) {
        this.clawOpenCloseServo = clawOpenCloseServo;
        this.clawRotationCRServo = clawRotationCRServo;
        this.clawYawServo = clawYawServo;

        // Make sure everything starts in the neutral position
        resetClaw();
    }

    /**
     * Handles all the controls for the claw. Just press the buttons, and the claw does its thing.
     *
     * @param gamepad The PS4 controller you're using to control the claw
     */
    public void controlClaw(Gamepad gamepad) {
        // Open and close the claw with  (X) and (O)
        if (gamepad.cross) { // Cross button for opening
            clawOpenCloseServo.setPosition(CLAW_OPEN_POSITION); // Open the claw all the way
        } else if (gamepad.circle) { // Circle button for closing
            clawOpenCloseServo.setPosition(CLAW_CLOSED_POSITION); // Close the claw tightly
        } else {
            clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION); // Keep it halfway open
        }

        // Spin the claw using the LeftTrigger and RightTrigger
        if (gamepad.left_trigger > 0.1) {
            clawRotationCRServo.setPower(ROTATION_POWER_LEFT * gamepad.left_trigger); // Spin left (proportional to how hard you press)
        } else if (gamepad.right_trigger > 0.1) {
            clawRotationCRServo.setPower(ROTATION_POWER_RIGHT * gamepad.right_trigger); // Spin right (proportional too)
        } else {
            clawRotationCRServo.setPower(ROTATION_POWER_STOP); // Stop spinning
        }

        // Change where the claw is pointing (yaw) with L1 and R1 bumpers
        if (gamepad.left_bumper) { // Press LeftBumper to point left
            clawYawServo.setPosition(YAW_LEFT);
        } else if (gamepad.right_bumper) { // Press RightBumper to point right
            clawYawServo.setPosition(YAW_RIGHT);
        } else {
            clawYawServo.setPosition(YAW_CENTER); // If neither is pressed, keep it forward
        }
    }

    //Resets Everything to Neutral
    public void resetClaw() {
        clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION); // Claw halfway open
        clawRotationCRServo.setPower(ROTATION_POWER_STOP);     // No spinning
        clawYawServo.setPosition(YAW_CENTER);                 // Point forward
    }
}
