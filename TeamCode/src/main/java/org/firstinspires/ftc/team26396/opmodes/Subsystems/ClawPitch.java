package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class ClawPitch {
    private Servo clawPitchServo;

    private static final double PITCH_PICKUP_POSITION = 0.75;//0.62;//0.65// 0.3;//0.5;   //0.6// Pickup position

    private static final double PITCH_HANG_POSITION = 0.6
            ;//0.8; //0.7    // 90 degrees down
    private static final double PITCH_NEUTRAL_POSITION = 0.4;//0.22; //0.25// 0.0;  // Neutral (straight)

    public ClawPitch(Servo clawPitchServo) {
        this.clawPitchServo = clawPitchServo;
        resetPitch();
    }

    public void setPickupPosition() {
        clawPitchServo.setPosition(PITCH_PICKUP_POSITION);
    }

    public void setHangPosition() {
        clawPitchServo.setPosition(PITCH_HANG_POSITION);
    }

    public void setNeutralPosition() {
        clawPitchServo.setPosition(PITCH_NEUTRAL_POSITION);
    }

    public void resetPitch() {
        clawPitchServo.setPosition(PITCH_PICKUP_POSITION);
    }
}
