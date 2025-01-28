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

    private boolean isClawOpen = false;  // Track claw state (open/closed/neutral)
    private boolean isRollNormal = true; // Track roll state (normal/reverse)
    private boolean isYawLeft = false;  // Track yaw state (left/right)
    private boolean isPitchPickup = false;  // Track pitch state (pickup/neutral)

    public ClawCode(Servo clawOpenCloseServo, Servo clawRotationServo, Servo clawYawServo, Servo clawPitchServo) {
        this.clawOpenCloseServo = clawOpenCloseServo;
        this.clawPitch = new ClawPitch(clawPitchServo);
        this.clawYaw = new ClawYaw(clawYawServo);
        this.clawRoll = new ClawRoll(clawRotationServo);
        clawRoll.rotateNormal(); // Set initial position for roll

        resetClaw(); // Set claw to default neutral state
    }

    public void controlClawLogitech(Gamepad logitechGamepad) {
        // Claw control on the Logitech controller (Button A)
        if (logitechGamepad.a) {
            // Toggle claw open/close/neutral on the same button
            if (!isClawOpen) {
                clawOpenCloseServo.setPosition(CLAW_OPEN_POSITION);  // Open claw
                isClawOpen = true;
            } else if (isClawOpen && !isClawNeutral()) {
                clawOpenCloseServo.setPosition(CLAW_CLOSED_POSITION);  // Close claw
                isClawOpen = false;
            } else {
                clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);  // Set claw to neutral
                isClawOpen = false;  // It's now neutral, so reset the flag
            }
        }
    }

    public void controlClawPS4(Gamepad ps4Gamepad) {

        // Pitch control on PS4 controller - Toggle between pickup/neutral positions
        if (ps4Gamepad.circle) {
            if (!isPitchPickup) {
                clawPitch.setPickupPosition();  // Set pitch to pickup position
                isPitchPickup = true;
            } else {
                clawPitch.setNeutralPosition();  // Set pitch to neutral position
                isPitchPickup = false;
            }
        }

        // Roll control on PS4 controller - Toggle between normal/reverse
        if (ps4Gamepad.right_bumper) {
            if (isRollNormal) {
                clawRoll.resetRoll();  // Reverse claw roll
                isRollNormal = false;
            } else {
                clawRoll.rotateNormal();  // Normal claw roll
                isRollNormal = true;
            }
        }

//        // Yaw control on PS4 controller
//        if (ps4Gamepad.square) {
//            if (!isYawLeft) {
//                clawYaw.pointLeft();  // Rotate yaw left
//                isYawLeft = true;
//            }
//        } else if (ps4Gamepad.circle) {
//            if (isYawLeft) {
//                clawYaw.pointRight();  // Rotate yaw right
//                isYawLeft = false;
//            }
//        }

        // Reset all positions using the "Share" button
        if (ps4Gamepad.cross) {
            resetClaw();  // Reset claw, pitch, yaw, and roll to neutral
        }
    }

    public void resetClaw() {
        clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);  // Set claw to neutral
        clawPitch.resetPitch();  // Reset pitch to neutral
        clawYaw.resetYaw();  // Reset yaw to neutral position
        clawRoll.resetRoll();  // Reset roll to neutral position
        isClawOpen = false;  // Reset claw state to neutral
        isRollNormal = true;  // Reset roll state to normal
        isYawLeft = false;  // Reset yaw state to neutral
        isPitchPickup = false;  // Reset pitch state to neutral
    }

    private boolean isClawNeutral() {
        return clawOpenCloseServo.getPosition() == CLAW_NEUTRAL_POSITION;
    }
}


//package org.firstinspires.ftc.team26396.opmodes.Subsystems;
//
//import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.CRServo;
//
//public class ClawCode {
//    private Servo clawOpenCloseServo;
//    private ClawPitch clawPitch;
//    private ClawYaw clawYaw;
//    private ClawRoll clawRoll;
//
//    private static final double CLAW_OPEN_POSITION = 0.8;   // Fully open
//    private static final double CLAW_CLOSED_POSITION = 0.5; // Fully closed
//    private static final double CLAW_NEUTRAL_POSITION = 0.5; // Neutral (half-open)
//
//    public ClawCode(Servo clawOpenCloseServo, Servo clawRotationServo, Servo clawYawServo, Servo clawPitchServo) {
//        this.clawOpenCloseServo = clawOpenCloseServo;
//        this.clawPitch = new ClawPitch(clawPitchServo);
//        this.clawYaw = new ClawYaw(clawYawServo);
//        this.clawRoll = new ClawRoll(clawRotationServo);
//        clawRoll.rotateNormal();
//
//        resetClaw();
//    }
//
//    public void controlClaw(Gamepad gamepad) {
//
//
//        if (gamepad.left_bumper) {
//            clawOpenCloseServo.setPosition(CLAW_OPEN_POSITION);
//            clawRoll.rotateNormal();
//        } else if (gamepad.right_bumper) {
//            clawOpenCloseServo.setPosition(CLAW_CLOSED_POSITION);
//            clawRoll.rotateNormal();
//        } else if (gamepad.share) {
//            clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);
//        }
//
//        if (gamepad.triangle) {
//            clawPitch.setPickupPosition();
//        } else if (gamepad.cross) {
//            clawPitch.setNeutralPosition();
//        } else if (gamepad.share) {
//            clawPitch.setNeutralPosition();
//        }
//
//        if (gamepad.square) {
//            clawYaw.pointLeft();
//        } else if (gamepad.circle) {
//            clawYaw.pointRight();
//        } else if (gamepad.share) {
//            clawYaw.resetYaw();
//        }
//
//
//        // Additional controls for pitch can be added as needed.
//    }
//
//    public void resetClaw() {
//        clawOpenCloseServo.setPosition(CLAW_NEUTRAL_POSITION);
//        clawPitch.resetPitch();
//        clawYaw.resetYaw();
//        clawRoll.resetRoll();
//    }
//}
