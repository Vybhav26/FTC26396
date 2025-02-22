//package com.example.meepmeeptesting1;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.noahbres.meepmeep.MeepMeep;
//import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
//import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
//
//public class MeepMeepTesting2 {
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(800);
//
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .build();
//
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, 36, 90.00))
//        new Pose2d(0, 33, 90.00);
//        new Pose2d(-35, 33, 180.00);
//        Pose2d pose3 = new Pose2d(-35, -5, 90);
//        Pose2d pose4 = new Pose2d(-44, -5, 90);
//        Pose2d pose5 = new Pose2d(-44, 40, 90);
//        Pose2d pose6 = new Pose2d(-44, -5, 90);
//        Pose2d pose9 = new Pose2d(-55, 6, 90);
//        Pose2d pose10 = new Pose2d(-55, 40, 90);
//        Pose2d pose11 = new Pose2d(-55, 6, 90);
//
//        // Move to pose1
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose1.position.y)
//                        .build()
//        );
//        safeWaitSeconds(0.1);
//
//        // Move to pose2 and turn
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToX(pose2.position.x)
//                        .turnTo(Math.toRadians(90))
//                        .build()
//        );
//        safeWaitSeconds(0.1);
//
//        // Move to pose3
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose3.position.y)
//                        .build()
//        );
//        safeWaitSeconds(0.1);
//
//        // Strafe to pose4
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .strafeTo(pose4.position)
//                        .build()
//        );
//        safeWaitSeconds(0.1);
//
//        // Move to pose5
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose5.position.y)
//                        .build()
//        );
//
//        // Move to pose6
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose6.position.y)
//                        .build()
//        );
//
//        // Strafe to pose9
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .strafeTo(pose9.position)
//                        .build()
//        );
//        safeWaitSeconds(0.1);
//
//        // Move to pose10
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose10.position.y)
//                        .build()
//        );
//
//        // Move to pose11
//        Actions.runBlocking(
//                drive.actionBuilder(drive.pose)
//                        .lineToY(pose11.position.y)
//                        .build()
//        );
//    }
//
//        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
//    }
//}