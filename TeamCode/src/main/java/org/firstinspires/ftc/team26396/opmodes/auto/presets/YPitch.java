package org.firstinspires.ftc.team26396.opmodes.auto.presets;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team26396.constants.Constants;

public class YPitch {

    private static final Servo pitch = (Servo) hardwareMap.get(Servo.class, "pitch");


    private static final double UP_POSITION = 0.3;     // Position to place into an high basket (70 degrees)

    private static final double DOWN_POSITION = 0.7;

    private static final double MIDDLE_POSITION = 0.0;

    public YPitch(HardwareMap hardwareMap) {
        pitch.setPosition(DOWN_POSITION);

    }

    public Action moveWristUp() {
        return new MoveWristUp();
    }

    public Action moveWristDown() {
        return new MoveWristDown();
    }

    public Action moveWristMiddle() {
        return new MoveWristToMiddle();
    }

    public static class MoveWristUp implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                pitch.setPosition(DOWN_POSITION);
                initialized = true;
            }

            // checks lift's current position
            return setPitchPosition(packet, UP_POSITION);

        }
    }

    public class MoveWristDown implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                pitch.setPosition(0);
                initialized = true;
            }

            // checks lift's current position
            return setPitchPosition(packet, DOWN_POSITION);

        }
    }

    public class MoveWristToMiddle implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                pitch.setPosition(0);
                initialized = true;
            }

            // checks lift's current position
            return setPitchPosition(packet, MIDDLE_POSITION);

        }
    }

    private static boolean setPitchPosition(TelemetryPacket packet, double position) {

        return position < pitch.getPosition();
    }
}