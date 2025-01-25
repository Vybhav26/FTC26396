package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class ClawRoll {
    private Servo clawRotationServo = null;

    private static final double ROTATION_POSITION_INIT = 0.1;  // Initial rotation
    private static final double ROTATION_POSITION_NORMAL = 1.0; // Normal rotation

    public ClawRoll(Servo clawRotationCRServo) {
        this.clawRotationServo = clawRotationServo;
        resetRoll();
    }

    public void rotateNormal() {
        clawRotationServo.setPosition(ROTATION_POSITION_NORMAL);
    }


    public void resetRoll() {
        if(clawRotationServo != null) {
        clawRotationServo.setPosition(ROTATION_POSITION_INIT);
        }
    }
}
