package org.firstinspires.ftc.team26396.opmodes.test.DriveTests;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp(name = “CRServoIntakeTest (Blocks to Java)”, group = “CRServo”)
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Servo Intake", group="TeleOp")

public class ServoIntakeTest extends LinearOpMode {

    private Servo con_servo;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        con_servo = hardwareMap.get(Servo.class, "intake");

  //      con_servo = hardwareMap.crservo.get(CRServo.class,“intake”);


        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                con_servo.setPosition(1);
            }
            if (gamepad1.b) {
                con_servo.setPosition(-1);
            }
            if (gamepad1.atRest()) {
                con_servo.setPosition(0);
            }
            telemetry.update();
        }
        con_servo.setPosition(0); // You have a potential to have a run after the loop, just shut it off when the opmode closes


    }
}