package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class SpecimenBlue {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)


                //BLUE BASKET STARTS HERE
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder
                                        (new Pose2d(-20, 60, Math.toRadians(-90)))
                                //where is wall y position?


                                //Path for Red Basket Specimen Thingy
                                //picks up first block
                                .lineToSplineHeading(new Pose2d(-52, 57 , Math.toRadians(90)))
                                .waitSeconds(0.5)
                                //goes to submersible
                                .lineToSplineHeading(new Pose2d(0, 35 , Math.toRadians(-90)))
                                .waitSeconds(0.5)
                                //goes to second block
                                .lineToSplineHeading(new Pose2d(-49, 40 , Math.toRadians(-90)))
                                .waitSeconds(0.5)
                                //places block near human player
                                .lineToSplineHeading(new Pose2d(-56, 57 , Math.toRadians(90)))
                                .waitSeconds(0.5)
                                //goes to 3rd block
                                .lineToSplineHeading(new Pose2d(-57, 40 , Math.toRadians(-90)))
                                .waitSeconds(0.5)
                                //drops 3rd block and picks 2nd block specimen up
                                .lineToSplineHeading(new Pose2d(-57, 57 , Math.toRadians(90)))
                                .waitSeconds(0.5)
                                //puts second block at submersible
                                .lineToSplineHeading(new Pose2d(1, 35 , Math.toRadians(-90)))
                                .waitSeconds(0.5)
                                //goes back to the 4th block
                                .lineToSplineHeading(new Pose2d(-59, 40 , Math.toRadians(-135)))
                                .waitSeconds(0.5)
                                //drops 4th block, picks up 3rd
                                .lineToSplineHeading(new Pose2d(-59, 57 , Math.toRadians(90)))
                                .waitSeconds(0.5)
                                .lineToSplineHeading(new Pose2d(2, 35 , Math.toRadians(-90)))








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
