package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class ClawYaw {
    private CRServo clawYawServo;

    private static final double YAW_LEFT = -0.2;   // Face left
    private static final double YAW_RIGHT = 0.2;  // Face right
    private static final double YAW_CENTER = 0.0; // Neutral (forward)

    public ClawYaw(CRServo clawYawServo) {
        this.clawYawServo = clawYawServo;
        resetYaw();
    }

    public void pointLeft() {
        clawYawServo.setPower(YAW_LEFT);
    }

    public void pointRight() {
        clawYawServo.setPower(YAW_RIGHT);
    }

    public void resetYaw() {
        clawYawServo.setPower(0.0);
    }
}
