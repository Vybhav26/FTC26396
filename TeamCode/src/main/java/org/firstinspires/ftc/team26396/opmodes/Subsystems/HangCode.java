package org.firstinspires.ftc.team26396.opmodes.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class HangCode {
    private DcMotor HangMotor1;
    private DcMotor HangMotor2;

    // Preset arm positions (in encoder ticks)
    private final int HangingPosExtend = 1500; //TODO: Edit as Needed
    private final int HangingPosRetract = 1000; //TODO: Edit as Needed
    private final int HangZero = 0;

    private static final double HangingSlide_POWER = 0.8;


    public HangCode(DcMotor Motor1, DcMotor Motor2) {
        this.HangMotor1 = Motor1;
        this.HangMotor2 = Motor2;


        if(HangMotor1.getCurrentPosition() != 0 || HangMotor2.getCurrentPosition() != 0){
            setHangPosition(HangZero);
        }else{
            setHangPosition(HangZero);
        }


        //REFER TO ORIGINAL ARM CODE
        // Configure the lift motor with encoders
        HangMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HangMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Configure the lift motor with encoders
        HangMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        HangMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        HangMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void controlHang(Gamepad gamepad) {

        //Preset to 0


        // Lift Motor Control using preset positions
        if (gamepad.a) {
            setHangPosition(HangingPosExtend); // Lift to high position, for hanging
        } else if (gamepad.b) {
            setHangPosition(HangingPosRetract); // Once on submerisible, hook itself on it by reducing linearSlide length
        }
    }
    // Set Lift position using encoder ticks
    private void setHangPosition(int targetPosition) {
        HangMotor1.setTargetPosition(targetPosition);
        HangMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        HangMotor1.setPower(HangingSlide_POWER);

        HangMotor2.setTargetPosition(targetPosition);
        HangMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        HangMotor2.setPower(HangingSlide_POWER);
    }

//    private void resetHangPosition(){

//    }
}