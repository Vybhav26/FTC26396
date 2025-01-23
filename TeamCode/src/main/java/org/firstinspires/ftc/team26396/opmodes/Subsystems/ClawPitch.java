package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ClawPitch {
    private Servo clawPitchServo; // This is the servo that controls the claw's pitch.

    // Constants you might need to adjust for your robot:
    private static final double CLAW_PARALLEL = 0.5;
    // The default servo position when the claw is parallel to the ground.
    // Tweak this value depending on how the claw is mounted on your robot.

    private static final double ARM_REST_ANGLE = 35.0;
    // The angle of the arm (in degrees) when it’s resting and the claw is parallel to the ground.
    // Update this based on how your arm sits at rest.

    private static final double MAX_ADJUSTMENT = 0.3;
    // The maximum amount the claw can tilt up or down.
    // Adjust this to limit how far the claw moves to stay safe and within range.

    private static final double MIN_SERVO_POSITION = 0.0;
    // The lowest position the servo can go.
    // Check your servo specs and physical setup to make sure this is correct.

    private static final double MAX_SERVO_POSITION = 1.0;
    // The highest position the servo can go.
    // Double-check your servo limits to avoid damaging anything.

    /**
     * Constructor to set up the claw.
     *
     * @param clawPitchServo the servo object that controls the claw's pitch
     */
    public ClawPitch(Servo clawPitchServo) {
        this.clawPitchServo = clawPitchServo;
    }

    /**
     * Adjusts the claw's pitch automatically based on the arm's current angle.
     *
     * @param armAngle the angle of the arm (in degrees) relative to the ground
     */
    public void updateClawPitch(double armAngle) {
        // Figure out the best claw position to keep it level
        double pitchAdjustment = calculateClawPitch(armAngle);

        // Move the servo to the calculated position
        clawPitchServo.setPosition(pitchAdjustment);
    }

    /**
     * Figures out where the servo should move to keep the claw level.
     *
     * @param armAngle the current angle of the arm (in degrees)
     * @return the servo position that will keep the claw level
     */
    private double calculateClawPitch(double armAngle) {
        // How far is the arm from its resting angle?
        double angleDeviation = armAngle - ARM_REST_ANGLE;

        // Turn that deviation into a proportional servo adjustment
        // Negative values tilt the claw down; positive values tilt it up
        double proportionalAdjustment = angleDeviation * (MAX_ADJUSTMENT / 90.0);

        // Add or subtract from the neutral position to calculate the new claw position
        double newPitchPosition = CLAW_PARALLEL - proportionalAdjustment;

        // Make sure we don't send the servo outside its safe range
        newPitchPosition = Math.max(MIN_SERVO_POSITION, Math.min(MAX_SERVO_POSITION, newPitchPosition));

        return newPitchPosition;
    }

    /**
     * Resets the claw to its default "parallel to the ground" position.
     */
    public void resetClawToNeutral() {
        clawPitchServo.setPosition(CLAW_PARALLEL);
    }
}
