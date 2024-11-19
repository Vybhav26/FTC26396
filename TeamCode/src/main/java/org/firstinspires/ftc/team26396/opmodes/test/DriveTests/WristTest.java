//package org.firstinspires.ftc.team26396.opmodes.test.DriveTests;

//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.CRServo;

package org.firstinspires.ftc.team26396;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="WristServo Control", group="TeleOp")
public class WristTest extends OpMode {

    // Define the CRServo for the intake system
    private CRServo intake;

    // Define constants for intake servo power values
    private static final double INTAKE_COLLECT = 1.0;  // Power for collecting
    private static final double INTAKE_DEPOSIT = -1.0; // Power for depositing
    private static final double INTAKE_OFF = 0.0;     // Power for turning off the intake

    @Override
    public void init() {
        // Initialize the CRServo (intake system)
        intake = hardwareMap.get(CRServo.class, "wrist");

        // Ensure the servo is off at the start
        intake.setPower(INTAKE_OFF);
    }

    @Override
    public void loop() {
        // Intake control logiServoIntakeTestc based on gamepad buttons
        if (gamepad1.dpad_up) {
            intake.setPower(INTAKE_COLLECT); // Set power to collect when left bumper is pressed
        }
        else if (gamepad1.dpad_up) {
            intake.setPower(INTAKE_OFF); // Turn off intake when right bumper is pressed
        }
        else if (gamepad1.dpad_right) {
            intake.setPower(INTAKE_DEPOSIT); // Set power to deposit when Y button is pressed
        }
        else {
            intake.setPower(INTAKE_OFF); // Ensure intake is off when no buttons are pressed
        }

        // Telemetry for debugging (display power state of intake)
        telemetry.addData("Intake Power", intake.getPower());
        telemetry.update();
    }

    @Override
    public void stop() {
        // Turn off the intake servo when the OpMode stops
        intake.setPower(INTAKE_OFF);
    }
}
