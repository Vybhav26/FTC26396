package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedBasket {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)


                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder
                                        (new Pose2d(-20, -65, Math.toRadians(90)))


                                //Path for Red Basket Drop, but all coordinates are multiplied by 1, and all readians have 180+ added
                                .lineToSplineHeading(new Pose2d(-37, -37, Math.toRadians(180+-45)))
                                .waitSeconds(0.5)
                                .lineToSplineHeading(new Pose2d(-53, -57, Math.toRadians(180+45)))
                                .waitSeconds(1)
                                .lineToSplineHeading(new Pose2d(-47, -37, Math.toRadians(180+-45)))
                                .waitSeconds(0.5)
                                .lineToSplineHeading(new Pose2d(-53, -57, Math.toRadians(180+45)))
                                .waitSeconds(1)
                                .lineToSplineHeading(new Pose2d(-59, -37, Math.toRadians(180+-45)))
                                .waitSeconds(0.5)
                                .lineToSplineHeading(new Pose2d(-53, -57, Math.toRadians(180+45)))
                                .waitSeconds(1)
                                .lineToSplineHeading(new Pose2d(-25, -25, Math.toRadians(180+0)))


                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}