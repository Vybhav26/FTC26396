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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, -60,Math.toRadians(0)))
//                                .afterDisp(.5,  arm.raiseArmForLowerBasket())
//                                .waitSeconds(.100)
//                                .afterDisp(.5, yaw.moveWristCenter())
                .strafeTo(new Vector2d(0,-33))
                .waitSeconds(.100)
//                        arm.initializeArm(),
//                        arm.raiseArmForSpecimenPickUpFromWall(),
//                        drive.actionBuilder(new Pose2d(0, -36, 0))
                .lineToX(35)
                //.splineTo(new Vector2d(35, 0), Math.toRadians(90))
                .turnTo(Math.toRadians(90))
                .lineToY(-2)
                .waitSeconds(.100)
                .strafeTo(new Vector2d(48, -6))
                .waitSeconds(.100)
                .lineToY(-48)
                .lineToY(-2)
                .strafeTo(new Vector2d(57, -6))
                .waitSeconds(.100)
                .lineToY(-48)
                .lineToY(-2)
                .strafeTo(new Vector2d(62, -6))
                .waitSeconds(.100)
                .lineToY(-2)
                .waitSeconds(.100)
                .lineToY(-15)
                .turnTo(Math.toRadians(270))
                .lineToY(-6)
                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}