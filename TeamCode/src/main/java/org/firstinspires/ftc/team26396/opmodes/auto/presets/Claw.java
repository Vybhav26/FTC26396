package org.firstinspires.ftc.team26396.opmodes.auto.presets;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team26396.constants.Constants;

public class Claw {

    private static final Servo claw = hardwareMap.get(Servo.class, "claw");

    private static final double OPEN_POSITION = 0.8;     // Position to place into an high basket (70 degrees)

    private static final double CLOSE_POSITION = 0.5;

    private static final double NEUTRAL_POSITION = 0.5;

    public Claw(HardwareMap hardwareMap) {

        claw.setPosition(0);

    }

    public Action closeClaw() {
        return new CloseClaw();
    }

    public Action openClaw() {
        return new OpenClaw();
    }

    public static class CloseClaw implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                claw.setPosition(0);
                initialized = true;
            }

            // checks lift's current position
            return setClawPosition(packet, CLOSE_POSITION);

        }
    }

    public static class OpenClaw implements Action {
        // checks if the lift motor has been powered on
        private boolean initialized = false;

        // actions are formatted via telemetry packets as below
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            // powers on motor, if it is not on
            if (!initialized) {
                claw.setPosition(0);
                initialized = true;
            }

            // checks lift's current position
            return setClawPosition(packet, OPEN_POSITION);

        }
    }

    private static boolean setClawPosition(TelemetryPacket packet, double position) {

        return position < claw.getPosition();
    }
}