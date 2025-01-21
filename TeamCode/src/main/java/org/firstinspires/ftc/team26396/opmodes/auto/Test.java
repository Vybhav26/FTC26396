package org.firstinspires.ftc.team26396.opmodes.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;

@Config
@Autonomous(name = "BLUE_TEST_AUTO_FSM", group = "Autonomous")
public class Test extends LinearOpMode {

    // Constants for motor settings
    private static final double LINEAR_SLIDE_POWER = 0.8;
    private static final double ARM_POWER = 1.0;

    // Positions in degrees
    private static final double INIT_DEGREES = 0.0;
    private static final double GROUND_DEGREES = 5.0;
    private static final double LOW_DEGREES = 15.0;
    private static final double HIGH_DEGREES = 71.0;
    private static final double MAX_DEGREES = 95.0;

    // Formula to calculate ticks per degree
    private static final double ARM_TICKS_PER_DEGREE =
            145.1 * 5.2 * 5.0 * 1 / 360.0 * 2;

    // Pre-calculated arm positions in encoder ticks
    private static final double INIT_POSITION_TICKS = INIT_DEGREES * ARM_TICKS_PER_DEGREE;
    private static final double GROUND_POSITION_TICKS = GROUND_DEGREES * ARM_TICKS_PER_DEGREE;
    private static final double LOW_POSITION_TICKS = LOW_DEGREES * ARM_TICKS_PER_DEGREE;
    private static final double HIGH_POSITION_TICKS = HIGH_DEGREES * ARM_TICKS_PER_DEGREE;
    private static final double MAX_POSITION_TICKS = MAX_DEGREES * ARM_TICKS_PER_DEGREE;

    private static final double FUDGE_FACTOR = 5.0;
    private double armTargetPosition = GROUND_POSITION_TICKS;

    public class Lift {
        private DcMotorEx lift;

        public Lift(HardwareMap hardwareMap) {
            lift = hardwareMap.get(DcMotorEx.class, "liftMotor");
            lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        public void liftUp() {
            lift.setPower(0.8);
        }

        public void liftDown() {
            lift.setPower(-0.8);
        }

        public void stopLift() {
            lift.setPower(0);
        }

        public int getLiftPosition() {
            return lift.getCurrentPosition();
        }
    }

    public class Arm {
        private DcMotorEx arm;

        public Arm(HardwareMap hardwareMap) {
            arm = hardwareMap.get(DcMotorEx.class, "armMotor");
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            arm.setDirection(DcMotorSimple.Direction.FORWARD);
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        public void setArmPosition(double targetPosition) {
            arm.setTargetPosition((int) targetPosition);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setPower(ARM_POWER);
        }
        public void raiseArm() {
            arm.setPower(0.8);
        }
        public void lowerArm() {
            arm.setPower(-0.8);
        }

        public void stopArm() {
            arm.setPower(0);
        }

        public int getArmPosition() {
            return arm.getCurrentPosition();
        }
    }

    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "claw");
        }

        public Action closeClaw() {
            claw.setPosition(0.55);
            return null;
        }

        public void openClaw() {
            claw.setPosition(1.0);
        }
    }

    private enum State {
        IDLE,
        MOVING_TO_POSITION,
        STRAFE_TO_POSITION,
        LIFT_UP,
        OPEN_CLAW,
        CLOSE_CLAW,
        STRAFE_BACK_TO_POSITION,
        LOWER_ARM,
        SPLINE_TO_POSITION,
        PICK_UP_GAMEPIECE,
        COMPLETE
    }

    @Override
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(0, 0, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Lift lift = new Lift(hardwareMap);
        Arm arm = new Arm(hardwareMap);
        Claw claw = new Claw(hardwareMap);

        State currentState = State.IDLE;

        Actions.runBlocking(claw.closeClaw());

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            switch (currentState) {
                case IDLE:
                    telemetry.addData("Status", "Idle");
                    telemetry.update();
                    currentState = State.MOVING_TO_POSITION;
                    break;

                case MOVING_TO_POSITION:
                    telemetry.addData("State", "Moving to (50, 10)");
                    telemetry.update();
                    drive.actionBuilder(initialPose).splineTo(new Vector2d(50, 10), Math.toRadians(90)).waitSeconds(2).build();
                    currentState = State.STRAFE_TO_POSITION;
                    break;
            }

            telemetry.addData("Current State", currentState);
            telemetry.update();
        }
    }
}