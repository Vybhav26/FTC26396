package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(90, 90, Math.toRadians(322.28875976108804), Math.toRadians(322.28875976108804), 14)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0.22, -71.00, Math.toRadians(90.00)))
                        .lineTo(new Vector2d(0.37, -28.35))
                        .lineTo(new Vector2d(-48.56, -30.69))
                        .lineToSplineHeading(new Pose2d(-59.99, -57.79, Math.toRadians(247.46)))
                        .lineToSplineHeading(new Pose2d(-59.69, -30.40, Math.toRadians(85.00))) // Adjusted to change orientation
                        .lineToSplineHeading(new Pose2d(-59.84, -57.94, Math.toRadians(254.57)))
                        .lineToSplineHeading(new Pose2d(-68.92, -31.13, Math.toRadians(86.00)))
                        .lineToSplineHeading(new Pose2d(-59.84, -57.94, Math.toRadians(254.57)))
                        .lineToSplineHeading(new Pose2d(-40.94, -0.66, Math.toRadians(70.45)))
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}