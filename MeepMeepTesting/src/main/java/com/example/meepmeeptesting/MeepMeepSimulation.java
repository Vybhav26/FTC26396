package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepSimulation {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)



                //BLUE BASKET STARTS HERE
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder
                                        (new Pose2d(0, -70, Math.toRadians(-90.00)))
                                .lineTo(new Vector2d(0, -28))
                                .lineTo(new Vector2d(-49, -30))
                                .splineTo(new Vector2d(-63, -61), Math.toRadians(44))
                                .splineTo(new Vector2d(-64, -55), Math.toRadians(90))
                                .splineTo(new Vector2d(-60, -29), Math.toRadians(62.49))
                                .splineTo(new Vector2d(-67, -60), Math.toRadians(90))
                                .splineTo(new Vector2d(-68, -24), Math.toRadians(83.38))
                                .splineTo(new Vector2d(-70, -53), Math.toRadians(90))
                                .splineTo(new Vector2d(-19, -0), Math.toRadians(180))
                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
