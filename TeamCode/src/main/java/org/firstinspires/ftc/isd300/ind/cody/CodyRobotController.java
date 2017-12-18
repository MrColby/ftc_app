package org.firstinspires.ftc.isd300.ind.cody;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by colbyl on 10/3/2017.
 */

@TeleOp(name="CodyRobotController", group="Cody")
@Disabled
public class CodyRobotController extends LinearOpMode {

   // Cobot myRobot;

    @Override
    public void runOpMode() throws InterruptedException {

        // random decides which button or stick should be pressed
        Random random = new Random();

        // timer controls how long the player has to press the button
        ElapsedTime timer = new ElapsedTime();
        // inPlay keeps you playing as long as you are pressing the right buttons
        boolean inPlay = true;

        // waits until the phone's start button
        waitForStart();

        // you get 3 seconds to press when the game begins
        double waitTime = 3000;

        // loops until you don't press the right button or until you stop by the phone
        while (this.opModeIsActive() && inPlay == true) {

            // get a total of 16 possible gamepad options
            int choice = random.nextInt(15);

            // wait for a part of a second with the screen blank before telling the next command
            timer.reset();
            report("Status", "");
            while (timer.milliseconds() < 200) {
            }

            // tell the player what to do
            if (choice == 0) {
                report("Status", "X");
            } else if (choice == 1) {
                report("Status", "A");
            } else if (choice == 2) {
                report("Status", "B");
            } else if (choice == 3) {
                report("Status", "Y");
            } else if (choice == 4) {
                report("Status", "D_PAD UP");
            } else if (choice == 5) {
                report("Status", "D_PAD LEFT");
            } else if (choice == 6) {
                report("Status", "D_PAD DOWN");
            } else if (choice == 7) {
                report("Status", "D_PAD RIGHT");
            } else if (choice == 8) {
                report("Status", "RIGHT TRIGGER");
            } else if (choice == 9) {
                report("Status", "LEFT TRIGGER");
            } else if (choice == 10) {
                report("Status", "RIGHT BUTTON");
            } else if (choice == 11) {
                report("Status", "LEFT BUTTON");
            } else if (choice == 12) {
                report("Status", "RIGHT STICK UP");
            } else if (choice == 13) {
                report("Status", "RIGHT STICK UP");
            } else if (choice == 13) {
                report("Status", "RIGHT STICK RIGHT");
            } else if (choice == 14) {
                report("Status", "RIGHT STICK LEFT");
            }
            // reset the timer and give them time to press it
            timer.reset();
            while (timer.milliseconds() < waitTime) {

            }

            if (choice == 0 && this.gamepad1.x) {
                // do nothing; they made the right choice
            } else if (choice == 1 && this.gamepad1.a) {
                // do nothing; they made the right choice
            } else if (choice == 2 && this.gamepad1.b) {
                // do nothing; they made the right choice
            } else if (choice == 3 && this.gamepad1.y) {
                // do nothing; they made the right choice
            } else if (choice == 4 && this.gamepad1.dpad_up) {
                // do nothing; they made the right choice
            } else if (choice == 5 && this.gamepad1.dpad_left) {
                // do nothing; they made the right choice
            } else if (choice == 6 && this.gamepad1.dpad_down) {
                // do nothing; they made the right choice
            } else if (choice == 7 && this.gamepad1.dpad_right) {
                // do nothing; they made the right choice
            } else if (choice == 8 && this.gamepad1.right_trigger > 0.5) {
                // do nothing; they made the right choice
            } else if (choice == 9 && this.gamepad1.left_trigger > 0.5) {
                // do nothing; they made the right choice
            } else if (choice == 10 && this.gamepad1.right_bumper) {
                // do nothing; they made the right choice
            } else if (choice == 11 && this.gamepad1.left_bumper) {
                // do nothing; they made the right choice
            } else if (choice == 12 && this.gamepad1.right_stick_y < -.5) {
                // do nothing; they made the right choice
            } else if (choice == 12 && this.gamepad1.left_stick_y < -.5) {
                // do nothing; they made the right choice
            } else if (choice == 13 && this.gamepad1.right_stick_x <.5){
                // do nothing; they made the right choice
            } else if (choice == 14 && this.gamepad1.right_stick_x < -.5){
                // do nothing; they made the right choice
            } else{

                // they haven't made the right choice; force the game to end
                inPlay = false;
                report("Status","You lose, play again :)");
            }

            // leaves 90% of the time for the next time through
            waitTime = 0.9 * waitTime;
        }

    }

    public void report(String caption,String message) {

        this.telemetry.addData(caption, message);

        this.telemetry.update();
    }
}