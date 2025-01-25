package com.example.meepmeeptesting1;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -60, 0))
                .waitSeconds(1)
                //.splineTo(new Vector2d(0, -34), Math.toRadians(0))
                .turnTo(Math.toRadians(-90))
                .lineToY(-36)
                .waitSeconds(1)
                .turnTo(Math.toRadians(0))
                .lineToX(38)
                .turnTo(Math.toRadians(90))
                .lineToY(-10)
                .waitSeconds(1)
                .strafeTo(new Vector2d(44, -10))
                .turnTo(Math.toRadians(90))
                .lineToY(-58)
                .lineToY(-10)
                .strafeTo(new Vector2d(54, -10))
                .waitSeconds(1)
                .lineToY(-57)
                .lineToY(-10)
                .strafeTo(new Vector2d(61, -10))
                .waitSeconds(1)
                .lineToY(-57)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}