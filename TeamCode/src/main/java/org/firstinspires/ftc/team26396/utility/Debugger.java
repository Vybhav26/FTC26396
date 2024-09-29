package org.firstinspires.ftc.team26396.utility;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.team26396.constants.exceptions.MalformedPropertyException;

import java.util.ArrayList;

public final class Debugger {
    private final ArrayList<String> output;

    @Nullable
    private final Telemetry telemetry;

    public Debugger(@Nullable Telemetry telemetry) {
        this.output    = new ArrayList<>();
        this.telemetry = telemetry;
    }

    public void addMalformedPropertyException(@NonNull MalformedPropertyException exception) {
        String issue = "Field " + exception.name + " Cannot Be Loaded"
                + "\nReason: Value [" + exception.value + "] Is Malformed | "
                + exception.reason;
        output.add(issue);
    }

    public void addMessage(@NonNull String message) {
        output.add(message);
    }

    public void displayAll() {
        if (telemetry == null) return;

        telemetry.addData("Number Of Issues", output.size());

        for (String message: output) { telemetry.addLine("\n" + message); }

        telemetry.update();
    }
}