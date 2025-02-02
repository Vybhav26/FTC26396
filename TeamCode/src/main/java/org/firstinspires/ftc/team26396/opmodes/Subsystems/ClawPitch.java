package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawPitch {
    private final Servo clawPitchServo = null;


    private boolean isClawOpen;
    private static final double PITCH_PICKUP_POSITION = 0.3;//0.5;   //0.6// Pickup position

    private static final double PITCH_HANG_POSITION = 0.7;//0.8; //0.7    // 90 degrees down
    private static final double PITCH_NEUTRAL_POSITION = 0.0;  // Neutral (straight)

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

    public double getPositions() {  return clawPitchServo.getPosition(); }

    public void controls(Gamepad gamepad){


        if ((gamepad.triangle)&& (getPositions()==PITCH_NEUTRAL_POSITION) ) {
            setPickupPosition();
        }
        if ((gamepad.triangle)&& (getPositions()==PITCH_PICKUP_POSITION)) {
            setNeutralPosition();
        }

        if(gamepad.cross){
            setHangPosition();
        }
    }

}
