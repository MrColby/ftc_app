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

    // what color island we start on
    private boolean red;

    // close to the mats, or far
    private boolean far;

    // the robot
    private TotBot totBot;

    // so we can access opmode functions
    private LinearOpMode linearOpMode;

    /*
    LinearOpMode tells us what color we are on and where we start
     */
    public Autotamouse(boolean red, boolean far, LinearOpMode linearOpMode) {
        this.red = red;
        this.far = far;
        this.linearOpMode = linearOpMode;
    }

    /*
        All the work is done in this method
     */
    public void runMission() throws InterruptedException {

        // init the robot
        totBot = new TotBot(this.linearOpMode.hardwareMap,this.linearOpMode.telemetry);

        // make sure the hands are open. This seems to have to happen even when the hands already start open
        openHands();

        // wait for the start button to be pushed
        this.linearOpMode.waitForStart();


        // first hold the block so that we keep it when we turn
        grabBlock();

        // get the mark that tells us which column to put the block into
        RelicRecoveryVuMark mark = getPictograph();
        if (mark == RelicRecoveryVuMark.UNKNOWN) mark = RelicRecoveryVuMark.CENTER;
        this.call("Mark", mark.toString());
        pause(1000);

        // knock off the jewel. We should return to the starting position. If we can't tell the jewel color, we stay.
        this.knockingOffJewel();

        // go put the block away and park. Every island requires different driving instructions.
        if (this.red == true) {
            call("", "Starting on red"); pause(1000);
            if (this.far == true) {
                call("", "Starting far"); pause(1000);
                if (mark == RelicRecoveryVuMark.RIGHT) {
                    placeBlockRedFarRight();
                } else if (mark == RelicRecoveryVuMark.CENTER) {
                    placeBlockRedFarCenter();
                } else if (mark == RelicRecoveryVuMark.LEFT) {
                    placeBlockRedFarLeft();
                }
            }
            else {
                call("", "Starting close"); pause(1000);
                if (mark == RelicRecoveryVuMark.RIGHT) {
                    placeBlockRedCloseRight();
                } else if (mark == RelicRecoveryVuMark.CENTER) {
                    placeBlockRedCloseCenter();
                } else if (mark == RelicRecoveryVuMark.LEFT) {
                    placeBlockRedCloseLeft();
                }
            }
        }
        else {
            call("", "Starting on blue");
            if (this.far == true) {
                call("", "Starting far"); pause(1000);
                if (mark == RelicRecoveryVuMark.RIGHT) {
                    placeBlockBlueFarRight();
                } else if (mark == RelicRecoveryVuMark.CENTER) {
                    placeBlockBlueFarCenter();
                } else if (mark == RelicRecoveryVuMark.LEFT) {
                    placeBlockBlueFarLeft();
                }
            }
            else {
                call("", "Starting close"); pause(1000);
                if (mark == RelicRecoveryVuMark.RIGHT) {
                    placeBlockBlueCloseRight();
                } else if (mark == RelicRecoveryVuMark.CENTER) {
                    placeBlockBlueCloseCenter();
                } else if (mark == RelicRecoveryVuMark.LEFT) {
                    placeBlockBlueCloseLeft();
                }
            }
        }





        /*
        Wait until time has expired so messages display, etc.
         */
        while(this.linearOpMode.opModeIsActive()) {

        }

    }

    private void placeBlockBlueCloseCenter() {
        call("", "BlueCloseCenter");

        twirl(-0.5, 500);

        this.drive(0.5, 0, 700);

        twirl(-0.5, 1300);

        this.drive(0.2, 0, 1100);

        twirl(-0.5, 500);

        this.drive(0.2, 0, 1100);
    }

    private void placeBlockBlueCloseLeft() {
        call("", "BlueCloseLeft");

        twirl(-0.5, 500);

        this.drive(0.5, 0, 700);

        twirl(-0.5, 1300);

        this.drive(0.2, 0, 1100);

        twirl(-0.5, 500);

        this.drive(0.2, 0, 1100);
    }

    private void placeBlockBlueCloseRight() {
        call("", "BlueCloseRight");

        twirl(-0.5, 500);

        this.drive(0.5, 0, 700);

        twirl(-0.5, 1300);

        this.drive(0.2, 0, 1100);

        twirl(-0.5, 500);

        this.drive(0.2, 0, 1100);
    }

    private void placeBlockBlueFarCenter() {
        call("", "BlueFarCenter");

        this.drive(0.5, 0, 1000);

        twirl(-0.5, 1400);

        this.drive(0.5, 0, 600);

        twirl(-0.5, 1000);

        this.drive(0.5, 0, 450);

        twirl(0.5, 1100);

        this.drive(0.5, 0, 150);
    }

    private void placeBlockBlueFarLeft() {
        call("", "BlueFarLeft");

        this.drive(0.5, 0, 1000);

        twirl(-0.5, 1400);

        this.drive(0.5, 0, 600);

        twirl(-0.5, 1000);

        this.drive(0.5, 0, 450);

        twirl(0.5, 1100);

        this.drive(0.5, 0, 150);
    }

    private void placeBlockBlueFarRight() {
        call("", "BlueFarRight");

        this.drive(0.5, 0, 1000);

        twirl(-0.5, 1400);

        this.drive(0.5, 0, 600);

        twirl(-0.5, 1000);

        this.drive(0.5, 0, 450);

        twirl(0.5, 1100);

        this.drive(0.5, 0, 150);
    }

    private void placeBlockRedFarCenter() {
        call("", "RedFarCenter");

        this.drive(0.5, 0, 1000);

        twirl(0.5, 1400);

        this.drive(0.5, 0, 500);

        twirl(-0.5, 500);

        this.drive(0.5, 0, 250);

        twirl(0.5, 550);

        this.drive(0.5, 0, 250);
    }

    private void placeBlockRedFarLeft() {
        call("", "RedFarLeft");

        this.drive(0.5, 0, 1000);

        twirl(0.5, 1400);

        this.drive(0.5, 0, 500);

        twirl(-0.5, 500);

        this.drive(0.5, 0, 250);

        twirl(0.5, 550);

        this.drive(0.5, 0, 250);
    }

    private void placeBlockRedFarRight() {
        call("", "RedFarRight");

        this.drive(0.5, 0, 1000);

        twirl(0.5, 1400);

        this.drive(0.5, 0, 500);

        twirl(-0.5, 500);

        this.drive(0.5, 0, 250);

        twirl(0.5, 550);

        this.drive(0.5, 0, 250);
    }

    private void placeBlockRedCloseCenter() {
        call("", "RedCloseCenter");

        twirl(0.5, 500);

        this.drive(0.5, 0, 1000);

        twirl(0.5, 1600);

        this.drive(0.2, 0, 1800);

    }

    private void placeBlockRedCloseLeft() {
        call("", "RedCloseLeft");
        twirl(0.5, 500);

        this.drive(0.5, 0, 1000);


        twirl(0.5, 1600);

        this.drive(0.2, 0, 1800);
    }

    private void placeBlockRedCloseRight() {
        call("", "RedCloseRight");
        twirl(0.5, 500);

        this.drive(0.5, 0, 1000);

        twirl(0.5, 1300);

        this.drive(0.2, 0, 1500);

        twirl(0.5, 200);

        this.drive(0.2, 0, 400);

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

    /*
        Lower the eyestalk, see what the color sensor sees, knock off the jewel.
     */
    private void knockingOffJewel() {
        // lower the eyestalk inbetween the jewels
        totBot.lowerEyestalk();
        // wait for a second to read the color
        pause(1000);
        TotBot.Color color = totBot.getEyestalkColor();
        pause(1000);

        double power;
        if(this.red == true && color == TotBot.Color.RED) {
            this.call("", "Looking at red; turning clockwise");
            // turn clockwise
            power = 0.4;
        }
        else if(this.red == true && color == TotBot.Color.BLUE){
            this.call("", "Looking at blue; turning counter");
            // turn counter-clockwise
            power = -0.4;
        }
        else if(this.red == false && color == TotBot.Color.BLUE){
            this.call("", "Looking at blue; turning clockwise");
            power = 0.4;
        }
        else if(this.red == false && color == TotBot.Color.RED){
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

    /*
        return what the camera sees
     */
    private RelicRecoveryVuMark getPictograph() {
        // set the mark for later
        RelicRecoveryVuMark mark = RelicRecoveryVuMark.UNKNOWN;
        twirl(-0.3, 300);
        ElapsedTime timer = new ElapsedTime();
        while(timer.milliseconds() < 1500) {
            mark = totBot.getPictograph();
        }
        twirl(0.3, 300);
        return mark;
    }

    /*
        do nothing for this number of milliseconds
     */
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

    /*
        drive sideways.
     */
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

    /*
        positive power values will turn clockwise
        time to run the motors (ms)
     */
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
        this.linearOpMode.telemetry.addData(caption, message);
        this.linearOpMode.telemetry.update();
    }


}
