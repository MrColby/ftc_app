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

        totBot.moveHands(false, 0.5);
        pause(1000);
        totBot.freezeHands();
        this.linearOpMode.waitForStart();
        String mark;
        totBot.moveHands(true, 0.5);
        pause(1000);
        totBot.freezeHands();
        twirl(-0.3, 300);
        ElapsedTime timer = new ElapsedTime();
        while(timer.milliseconds() < 1500) {
            mark = totBot.getPictograph();
        }
        twirl(0.3, 300);
            totBot.lowerEyestalk();
        pause(1000);
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

        pause(500);

        totBot.riaseEyestalk();
        twirl(-power, 500);

        twirl(0.5, 900);
        pause(1000);
        this.drive(0.5, 0, 1000);
        pause(1000);
        twirl(0.5, 950);
        pause(1000);
        this.drive(0.2, 0, 250);


        while(this.linearOpMode.opModeIsActive()) {

        }





    }
    private void pause (double milliseconds) {
        ElapsedTime timer = new ElapsedTime();
        while(timer.milliseconds() < milliseconds) {

        }
    }


    /*
      Just like joystick values.
      powerForward ranges from -1.0 to 1.0
      powerRight ranges from -1.0 to 1.0
      powerRight controls strafe
     */
    private void drive (double powerForward, double powerRight, double time){
        double frontLeft = powerForward  + powerRight;
        double frontRight = powerForward  - powerRight;
        double rearLeft = powerForward  - powerRight;
        double rearRight = powerForward  + powerRight;
        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
        pause(time);
        totBot.drive(0,0,0,0);
    }
    private void strafe (double power, double time) {

        double frontLeft = power;
        double frontRight = -power;
        double rearLeft = -power;
        double rearRight = power;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
        pause(time);

        totBot.drive(0, 0, 0, 0);
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
