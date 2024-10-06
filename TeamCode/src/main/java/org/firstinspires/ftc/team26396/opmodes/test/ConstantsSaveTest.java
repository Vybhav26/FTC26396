package org.firstinspires.ftc.team26396.opmodes.test;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.team26396.constants.ConstantsSaver;

@TeleOp(name = "Test - Constants Save", group = "Test")
public final class ConstantsSaveTest extends CommandOpMode {

    @Override public void initialize() {
       new ConstantsSaver(telemetry).save();
    }
}