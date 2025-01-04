package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenRed {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)


                //BLUE BASKET STARTS HERE
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder
                                        (new Pose2d(20, -60, Math.toRadians(-90)))
                                //where is wall y position?


                                //Path for Red Basket Specimen Thingy
                                //Starting from red wall side
                                .lineToSplineHeading(new Pose2d(20, -40 , Math.toRadians(0)))
                                .waitSeconds(0.5)
                                //turns right and goes to human player end
                                .lineToSplineHeading(new Pose2d(60, -40 , Math.toRadians(0)))
                                .waitSeconds(0.5)
                                //goes backward and faces toward blocks
                                .lineToSplineHeading(new Pose2d(0, -40 , Math.toRadians(90)))
                                //moves toward blocks
                                .waitSeconds(0.5)
                                .lineToSplineHeading(new Pose2d(0, -33 , Math.toRadians(90)))
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

                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
