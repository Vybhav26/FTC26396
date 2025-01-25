package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class ClawRoll {
//    private Servo clawRotationServo = null;
    public Servo clawRotationServo ;

    private static final double ROTATION_POSITION_INIT = 0.0;  // Initial rotation
    private static final double ROTATION_POSITION_NORMAL = 0.5; // Normal rotation

    public ClawRoll(Servo clawRotationCRServo) {
        this.clawRotationServo = clawRotationServo;
        resetRoll();
    }

    public void rotateNormal() {
        if(clawRotationServo != null) {

            clawRotationServo.setPosition(ROTATION_POSITION_NORMAL);
        }
    }


    public void resetRoll() {
        if(clawRotationServo != null) {
        clawRotationServo.setPosition(ROTATION_POSITION_INIT);
        }
    }
}
