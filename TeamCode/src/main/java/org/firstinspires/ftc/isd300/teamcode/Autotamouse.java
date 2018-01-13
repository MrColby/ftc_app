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

        // init the robot
        totBot = new TotBot(this.hardwareMap,this.telemetry);

        openHands();

        // wait for the start button to be pushed
        this.linearOpMode.waitForStart();



        grabBlock();

        // get the mark that tells us which column to put the block into
        String mark = getPictograph();
        this.call("Mark", mark);
        pause(1000);
        // knock off the jewel
        //this.knockingOffJewel();

        //eyestalk segment ends - leave platform
        if (this.red == true && this.far == false && mark.equals("RIGHT")) {
            placeBlockRedCloseRight();
        }
        else if (this.red == true && this.far == false && mark.equals("CENTER")) {
            placeBlockRedCloseCenter();
        }
        else if (this.red == true && this.far == false && mark.equals("LEFT")) {
            placeBlockRedCloseLeft();
        }
        if (this.red == true && this.far == true && mark.equals("RIGHT")) {
            placeBlockRedFarRight();
        }
        else if (this.red == true && this.far == true && mark.equals("CENTER")) {
            placeBlockRedFarCenter();
        }
        else if (this.red == true && this.far == true && mark.equals("LEFT")) {
            placeBlockRedFarLeft();
        }
        else if (this.red == false && this.far == false && mark.equals("RIGHT")) {
            placeBlockBlueCloseRight();
        }
        else if (this.red == false && this.far == false && mark.equals("CENTER")) {
            placeBlockBlueCloseCenter();
        }
        else if (this.red == false && this.far == false && mark.equals("LEFT")) {
            placeBlockBlueCloseLeft();
        }
        if (this.red == false && this.far == true && mark.equals("RIGHT")) {
            placeBlockBlueFarRight();
        }
        else if (this.red == false && this.far == true && mark.equals("CENTER")) {
            placeBlockBlueFarCenter();
        }
        else if (this.red == false && this.far == true && mark.equals("LEFT")) {
            placeBlockBlueFarLeft();
        }



        /*
        Wait until time has expired so messages display, etc.
         */
        while(this.linearOpMode.opModeIsActive()) {

        }





    }

    private void placeBlockBlueCloseCenter() {

    }

    private void placeBlockBlueCloseLeft() {

    }

    private void placeBlockBlueCloseRight() {

    }

    private void placeBlockBlueFarCenter() {

    }

    private void placeBlockBlueFarLeft() {

    }

    private void placeBlockBlueFarRight() {

    }

    private void placeBlockRedFarCenter() {

    }

    private void placeBlockRedFarLeft() {

    }

    private void placeBlockRedFarRight() {

    }

    private void placeBlockRedCloseCenter() {
// turn counter-clockwise 90 degrees
        pause(1000);
        call("", "first clockwise turn on island");
        pause(1000);
        twirl(0.5, 500);
        pause(1000);
        call("", "driving straight off island");
        pause(1000);
        this.drive(0.5, 0, 1000);
        pause(1000);
        //call("", "Strafing toward blue");
        //strafe(true, 1000);
        //pause(1000);
        call("", "second clockwise turn toward box");
        pause(1000);
        twirl(0.5, 1600);
        pause(1000);
        call("", "driving straight to box");
        pause(1000);
        this.drive(0.2, 0, 1800);
        pause(1000);
    }

    private void placeBlockRedCloseLeft() {

    }

    private void placeBlockRedCloseRight() {
        // turn counter-clockwise 90 degrees
        pause(1000);
        call("", "first clockwise turn on island");
        pause(1000);
        twirl(0.5, 500);
        pause(1000);
        call("", "driving straight off island");
        pause(1000);
        this.drive(0.5, 0, 1000);
        pause(1000);
        //call("", "Strafing toward blue");
        //strafe(true, 1000);
        //pause(1000);
        call("", "second clockwise turn toward box");
        pause(1000);
        twirl(0.5, 1600);
        pause(1000);
        call("", "driving straight to box");
        pause(1000);
        this.drive(0.2, 0, 1800);
        pause(1000);
    }

    private void grabBlock() {
        // hold the preloaded block' move motors for 1 second
        totBot.moveHands(true, 0.5);
        pause(1000);
        totBot.freezeHands();
    }

    private void openHands() {
        // make sure the hands are open. Run for 1 second.
        totBot.moveHands(false, 0.5);
        pause(1000);
        totBot.freezeHands();
    }

    private void knockingOffJewel() {
        // lower the eyestalk inbetween the jewels
        totBot.lowerEyestalk();
        // wait for a second to read the color
        pause(1000);
        int color = totBot.getEyestalkColor();
        pause(1000);

        double power;
        if(this.red == true && color == TotBot.COLOR_RED) {
            this.call("", "Looking at red; turning clockwise");
            // turn clockwise
            power = 0.4;
        }
        else if(this.red == true && color == TotBot.COLOR_BLUE){
            this.call("", "Looking at blue; turning counter");
            // turn counter-clockwise
            power = -0.4;
        }
        else if(this.red == false && color == TotBot.COLOR_BLUE){
            this.call("", "Looking at blue; turning clockwise");
            power = 0.4;
        }
        else if(this.red == false && color == TotBot.COLOR_RED){
            this.call("", "Looking at red; turning counter");
            power = -0.4;
        }
        else {
            this.call("", "Color unknown; not turning");
            power = 0;
        }


        pause(1000);

        // turn in that direction
        twirl(power, 500);

        pause(1000);

        totBot.riaseEyestalk();

        // turn back
        twirl(-power, 500);
    }

    private String getPictograph() {
        // set the mark for later
        String mark = "Unknown";
        twirl(-0.3, 300);
        ElapsedTime timer = new ElapsedTime();
        while(timer.milliseconds() < 1500) {
            mark = totBot.getPictograph();
        }
        twirl(0.3, 300);
        return mark;
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
    private void strafe (boolean left, double time) {

        int direction = 1;
        if (left) direction = -1;
        double frontLeft = direction * .6;
        double frontRight = direction * -.5;
        double rearLeft = direction * -.5;
        double rearRight = direction * .6;

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
