package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class ClawYaw {
    private Servo clawYawServo;

    private static final double YAW_INCREMENT = 0.005;  // Smaller increment for slower movement
    private static final double MAX_YAW = 1.0;         // Servo max position
    private static final double MIN_YAW = 0.0;         // Servo min position
    private static final double UPDATE_INTERVAL = 0.05; // Increased interval to 0.05 (20Hz)

    private double currentYaw = 0.5;  // Start at center position

    public ClawYaw(Servo clawYawServo) {
        this.clawYawServo = clawYawServo;
        clawYawServo.setPosition(currentYaw);
    }

    // Move the servo left, incrementing position by very small steps
    public void moveLeft() {
        if (currentYaw + YAW_INCREMENT <= MAX_YAW) {
            currentYaw += YAW_INCREMENT;
            clawYawServo.setPosition(currentYaw);
        }
    }

    // Move the servo right, decrementing position by very small steps
    public void moveRight() {
        if (currentYaw - YAW_INCREMENT >= MIN_YAW) {
            currentYaw -= YAW_INCREMENT;
            clawYawServo.setPosition(currentYaw);
        }
    }

    // Update function to check if D-Pad buttons are held
    public void updateYaw(boolean dpadLeft, boolean dpadRight) {
        if (dpadLeft) {
            moveLeft();
        }
        if (dpadRight) {
            moveRight();
        }
    }
}


/*
package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class ClawYaw {
    private Servo clawYawServo;

    private static final double YAW_SPEED = 0.03;
    private static final double YAW_CENTER = 0.5;
    private static final double MIN_POSITION = 0.0;
    private static final double MAX_POSITION = 1.0;

    private double currentPosition = YAW_CENTER;

    public ClawYaw(Servo clawYawServo) {
        this.clawYawServo = clawYawServo;
        resetYaw();
    }

    public void pointLeft() {
        currentPosition += YAW_SPEED;
        currentPosition = Math.max(currentPosition, MIN_POSITION);
        clawYawServo.setPosition(currentPosition);
    }

    public void pointRight() {
        currentPosition -= YAW_SPEED;
        currentPosition = Math.min(currentPosition, MAX_POSITION);
        clawYawServo.setPosition(currentPosition);
    }

    public void resetYaw() {
        currentPosition = YAW_CENTER;
        clawYawServo.setPosition(currentPosition);
    }
}
*/

/*
package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class ClawYaw {
    private CRServo clawYawServo;

    private static final double YAW_LEFT = 0.3;   // Face left
    private static final double YAW_RIGHT = -0.3;  // Face right
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
*/