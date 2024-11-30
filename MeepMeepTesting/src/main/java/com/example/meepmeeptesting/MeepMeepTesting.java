package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(90, 90, Math.toRadians(322.28875976108804), Math.toRadians(322.28875976108804), 14)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0.22, -71.00, Math.toRadians(90.00)))
                        .lineTo(new Vector2d(-0.07, -27.17))
                        .lineTo(new Vector2d(-48.71, -31.72))
                        .lineTo(new Vector2d(-59.99, -57.79))
                        .lineToSplineHeading(new Pose2d(47.98, -66.14, Math.toRadians(-4.42)))
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}