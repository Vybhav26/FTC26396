package org.firstinspires.ftc.team26396.opmodes.auto.presets;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.team26396.constants.Constants;
import org.firstinspires.ftc.team26396.opmodes.Subsystems.PresetSlideCode;

public class LinearSlide  {

    private static final DcMotorEx linearSlideMotor = (DcMotorEx) hardwareMap.get(DcMotor.class, "armMotor");
    private static final double LINEAR_SLIDE_POWER = 0.5;

    private static final double RETRACT_LINEAR_SLIDE_POWER = -0.5;
    // Positions in degrees (as doubles)
    private static final double INIT_DEGREES = 14.0;
    private static final double EXTEND_FULL_DEGREES = 85.0;     // Position to place into an high basket (70 degrees)

    private static final double RETRACT_FULL_DEGREES = 0.0;

    private static final double EXTEND_HALF_DEGREES = 50.0;

    private static final double EXTEND_SLIDE_FOR_PICKUP_FROM_FLOOR_DEGREES = 50.0;

    private static final double MAX_LENGTH = 1800;

    private static final double MIN_LENGTH = 5.0;

    // Formula to calculate ticks per degree
    static final double LINEAR_SLIDE_TICKS_PER_DEGREE = 19.2032086;
//            145.1 // encoder ticks per rotation of the bare RS-555 motor
//                    * 5.2 // gear ratio of the 5.2:1 Yellow Jacket gearbox
//                    * 5.0 // external gear reduction, a 20T pinion gear driving a 100T hub-mount gear (5:1 reduction)
//                    * 1 / 360.0 * 2; // we want ticks per degree, not per rotation


    // Pre-calculated arm positions in encoder ticks based on degrees
    private static final double INIT_POSITION_TICKS = INIT_DEGREES* LINEAR_SLIDE_TICKS_PER_DEGREE;
    private final double EXTEND_SLIDE_FOR_PICKUP_FROM_FLOOR_DEGREES_TICKS = EXTEND_SLIDE_FOR_PICKUP_FROM_FLOOR_DEGREES * LINEAR_SLIDE_TICKS_PER_DEGREE;
    private static final double EXTEND_FULL_DEGREES_TICKS = EXTEND_FULL_DEGREES * LINEAR_SLIDE_TICKS_PER_DEGREE;
    private static final double RETRACT_FULL_DEGREES_TICKS = RETRACT_FULL_DEGREES * LINEAR_SLIDE_TICKS_PER_DEGREE;
    private final double EXTEND_HALF_DEGREES_TICKS = EXTEND_HALF_DEGREES * LINEAR_SLIDE_TICKS_PER_DEGREE;

    // Fudge factor for fine control of arm adjustments
    //Larger FudgeFactor = More Jerky Movements
    private static final double FUDGE_FACTOR = 5.0;


    public LinearSlide(HardwareMap hardwareMap) {

        linearSlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public Action initLinearSlide() {
        return new InitLinearSlide();
    }

    public static Action extendArmForward() {
        return new ExtendLinearSlide();
    }

    public Action retractArmBackward() {
        return new RetractArmBackward();
    }

    public Action extendSlideForPickFromPool() {
        return new ExtendSlideForPickFromPool();
    }

    public class InitLinearSlide implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                initialized = true;
                linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                linearSlideMotor.setPower(LINEAR_SLIDE_POWER);
            }

            
            return setLinearSlidePosition(packet, INIT_POSITION_TICKS, LINEAR_SLIDE_POWER);

        }
    }

    public static class ExtendLinearSlide implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                initialized = true;
                linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                linearSlideMotor.setPower(LINEAR_SLIDE_POWER);
            }

            return setLinearSlidePosition(packet, EXTEND_FULL_DEGREES_TICKS, LINEAR_SLIDE_POWER);

        }
    }

    public static class RetractArmBackward implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                initialized = true;
                linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                linearSlideMotor.setPower(RETRACT_LINEAR_SLIDE_POWER);
            }

            return setLinearSlidePosition(packet, RETRACT_FULL_DEGREES_TICKS, RETRACT_LINEAR_SLIDE_POWER);

        }
    }

    public class ExtendSlideForPickFromPool implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                initialized = true;
                linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                linearSlideMotor.setPower(LINEAR_SLIDE_POWER);
            }

            return setLinearSlidePosition(packet, EXTEND_SLIDE_FOR_PICKUP_FROM_FLOOR_DEGREES_TICKS, LINEAR_SLIDE_POWER);

        }
    }

    public class ExtendSlideHalfLength implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                initialized = true;
                linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                linearSlideMotor.setPower(LINEAR_SLIDE_POWER);
            }

            
            return setLinearSlidePosition(packet, EXTEND_HALF_DEGREES_TICKS, LINEAR_SLIDE_POWER);

        }
    }

    private static boolean setLinearSlidePosition(TelemetryPacket packet, double targetPosition, double linearSlidePower) {

        packet.addLine("Target Position : " + targetPosition);

        if (targetPosition < INIT_POSITION_TICKS || targetPosition > (EXTEND_FULL_DEGREES_TICKS+5.0)) {
            targetPosition = INIT_POSITION_TICKS; // Set to low/ground position if out of range
        }

        // Convert target position in ticks (double) and set motor
        linearSlideMotor.setTargetPosition((int) targetPosition);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideMotor.setPower(linearSlidePower);

        double currentPosition = linearSlideMotor.getCurrentPosition();
        packet.put("Linear Slide Position", currentPosition);
        packet.addLine("Current Position : " + currentPosition);

        if (currentPosition < MAX_LENGTH) {

            packet.put("In else condition", "Linear Slide");

            // true causes the action to rerun
            linearSlideMotor.setTargetPosition((int) currentPosition);
            linearSlideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearSlideMotor.setPower(linearSlidePower);
            return true;
        } else {
            // false stops action rerun

            packet.put("In else condition", "Linear Slide");
            linearSlideMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            linearSlideMotor.setPower(0);
            return false;
        }
    }
}
