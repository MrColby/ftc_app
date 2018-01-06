package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by whiar on 12/3/2017.
 */

public class Autotamouse {
    private boolean red;
    private boolean far;
    private TotBot totBot;
    private LinearOpMode linearOpMode;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    public Autotamouse(boolean red, boolean far, LinearOpMode linearOpMode) {
        this.red = red;
        this.far = far;
        this.linearOpMode = linearOpMode;
        this.hardwareMap = this.linearOpMode.hardwareMap;
        this.telemetry = this.linearOpMode.telemetry;
    }
    public void runMission() throws InterruptedException {

        totBot = new TotBot(this.hardwareMap,this.telemetry);

        this.linearOpMode.waitForStart();
        totBot.armDown();
        pause(500);
        int color = totBot.getEyestalkColor();
        double power;
        if(this.red == true && color == TotBot.COLOR_RED) {
            power = 0.4;
        }
        else if(this.red == true && color == TotBot.COLOR_BLUE){
            power = -0.4;
        }
        else if(this.red == false && color == TotBot.COLOR_BLUE){
            power = 0.4;
        }
        else if(this.red == false && color == TotBot.COLOR_RED){
            power = -0.4;
        }
        else {
            power = 0;
        }

        twirl(power, 500);
        totBot.armUp();
        twirl(-power, 500);
        
        while(this.linearOpMode.opModeIsActive()) {

        }
        totBot.armUp();


    }
    private void pause (double milliseconds) {
        ElapsedTime timer = new ElapsedTime();
        while(timer.milliseconds() < milliseconds) {

        }
    }



    private void drive (double forward, double right){
        double frontLeft = forward  + right;
        double frontRight = forward  - right;
        double rearLeft = forward  - right;
        double rearRight = forward  + right;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
    }

    private void twirl (double power, double time){
        double frontLeft = power;
        double frontRight = -power;
        double rearLeft = power;
        double rearRight = -power;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
        pause(time);

        totBot.drive(0, 0, 0, 0);


    }
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }


}
