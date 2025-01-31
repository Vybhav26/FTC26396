package org.firstinspires.ftc.team26396.opmodes.auto.red;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.team26396.opmodes.auto.presets.Arm;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.XYaw;
import org.firstinspires.ftc.team26396.opmodes.auto.presets.YPitch;
import org.firstinspires.ftc.team26396.roadrunner.teamcode.MecanumDrive;



@Autonomous(name = "RedObservationPark", group = "Red Alliance", preselectTeleOp = "RobotCentricDrive")
@Disabled
public class RedObservationPark extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initialize the MecanumDrive with the hardware map
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, -60, Math.toRadians(0)));

        Arm arm = new Arm(hardwareMap);
        XYaw yaw = new XYaw(hardwareMap);
        YPitch pitch = new YPitch(hardwareMap);

        if (isStopRequested()) return;

        waitForStart();

        if (opModeIsActive()) {
            // Define the trajectory for the Blue Basket sequence with waits
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(12, -60, Math.toRadians(0)))
                            .strafeTo(new Vector2d(56,-60))
                            .build()
            );
        }
    }
}