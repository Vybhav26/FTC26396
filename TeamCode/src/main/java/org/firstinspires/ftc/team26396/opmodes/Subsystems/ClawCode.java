package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class ClawCode {
    private Servo clawOpenCloseServo;
    private ClawPitch clawPitch;
    private ClawYaw clawYaw;
    private ClawRoll clawRoll;

    private static final double CLAW_OPEN_POSITION = 0.8;   // Fully open
    private static final double CLAW_CLOSED_POSITION = 0.5; // Fully closed
    private static final double CLAW_NEUTRAL_POSITION = 0.5; // Neutral (half-open)

    public ClawCode(Servo clawOpenCloseServo, Servo clawRotationServo, Servo clawYawServo, Servo clawPitchServo) {
        this.clawOpenCloseServo = clawOpenCloseServo;
        this.clawPitch = new ClawPitch(clawPitchServo);
        this.clawYaw = new ClawYaw(clawYawServo);
        this.clawRoll = new ClawRoll(clawRotationServo);
        resetClaw();
    }

    public void controlClaw(Gamepad gamepad) {


        if (gamepad.left_bumper) {
            clawOpenCloseServo.setPosition(CLAW_OPEN_POSITION);
        } else if (gamepad.right_bumper) {
            clawOpenCloseServo.setPosition(CLAW_CLOSED_POSITION);
        } else if (gamepad.share) {
            clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);
        }

        if (gamepad.triangle) {
            clawPitch.setPickupPosition();
        } else if (gamepad.cross) {
            clawPitch.setNeutralPosition();
        } else if (gamepad.share) {
            clawPitch.setNeutralPosition();
        }

        if (gamepad.square) {
            clawYaw.pointLeft();
        } else if (gamepad.circle) {
            clawYaw.pointRight();
        } else if (gamepad.share) {
            clawYaw.resetYaw();
        }

        // Additional controls for pitch can be added as needed.
    }

    public void resetClaw() {
        clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);
        clawPitch.resetPitch();
        clawYaw.resetYaw();
        clawRoll.resetRoll();
    }
}
