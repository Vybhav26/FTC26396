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
                //BLUE BASKET STARTS HERE
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder
                                        (new Pose2d(0, 65, Math.toRadians(0)))
                                //Basic Blue Left or Red Right Auto
                                .lineTo(new Vector2d(48.00, 64.00))
                                .lineTo(new Vector2d(35.00, 64.00))
                                .lineTo(new Vector2d(35.00, 24.00))
                                .lineTo(new Vector2d(54.50, 24.54))
                                .lineTo(new Vector2d(54.50, 64.00))
                                .lineTo(new Vector2d(59.00, 64.00))
                                .lineTo(new Vector2d(39.04, 64.00))
                                .lineTo(new Vector2d(39.00, 24.24))
                                .lineTo(new Vector2d(65.50, 24.00))
                                .lineTo(new Vector2d(65.50, 64.00))
                                .lineTo(new Vector2d(59.00, 64.00))
                                .lineTo(new Vector2d(59.00, 24.00))
                                .lineTo(new Vector2d(71.00, 24.00))
                                .lineTo(new Vector2d(71.00, 52.00))
                                .lineTo(new Vector2d(32.00, 52.00))
                                .lineTo(new Vector2d(32.00, -0.00))
                                .splineTo(new Vector2d(17.00, -0.00), Math.toRadians(-90.00))
                                .build());


                                /*
                                This is Blue Basket Drop, but without Spline

                                .forward(2)
                                .turn(Math.toRadians(45))
                                .forward(35)
                                .turn(Math.toRadians(-45))
                                .forward(-25)
                                .turn(Math.toRadians(90))
                                .turn(Math.toRadians(-70))
                                .forward(30)
                                .forward(-30)
                                .turn(Math.toRadians(70))
                                .turn(Math.toRadians(-60))
                                .forward(30)
                                .forward(-30)
                                .turn(Math.toRadians(60))
                                .turn(Math.toRadians(-110))
                                .forward(50)
                                */
                                //BLUE BASKET ENDS HERE




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}