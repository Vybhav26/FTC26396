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
                                        (new Pose2d(0, 70, Math.toRadians(90.00))) //Starting Position
                                .splineTo(new Vector2d(0, 27), Math.toRadians(270.00)) //Moves to hang 1 specimen on high chamber
                                .lineTo(new Vector2d(49, 35)) //Moves to Sample 1
                                .lineTo(new Vector2d(49, 24)) //Picks up Sample 1
                                .splineTo(new Vector2d(48, 49), Math.toRadians(44)) //Moves in position to put Sample 1 in high basket
                                .splineTo(new Vector2d(57, 58), Math.toRadians(44)) //Puts Sample 1 in high basket
                                .splineTo(new Vector2d(59, 25), Math.toRadians(-90)) //Moves/picks up Sample 2
                                .splineTo(new Vector2d(61, 49), Math.toRadians(62.49)) //Moves/puts  Sample 2 to/in high basket
                                .splineTo(new Vector2d(69, 33), Math.toRadians(-59.14)) //Moves/picks up Sample 3
                                .splineTo(new Vector2d(68, 52), Math.toRadians(83.38)) //Moves/puts  Sample 3 to/in high basket
                                .splineTo(new Vector2d(27, 5), Math.toRadians(180.00)) //Moves to accomplish Level 1 Hang
                                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
