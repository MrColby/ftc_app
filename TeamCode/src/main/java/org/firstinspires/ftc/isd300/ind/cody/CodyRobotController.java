package org.firstinspires.ftc.isd300.ind.cody;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by colbyl on 10/3/2017.
 */

@TeleOp(name="CodyRobotController", group="Cody")
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

            // get four choices for two possible gamepad options
            int choice = random.nextInt(4);

            // wait for a part of a second with the screen blank before telling the next command
            timer.reset();
            report("Info", "");
            while (timer.milliseconds() < 200) {
            }

            // tell the player what to do
            if (choice == 0) {
                report("info", "X");
            } else if (choice == 1) {
                report("info", "A");
            } else if (choice == 2) {
                report("info", "B");
            } else if (choice == 3) {
                report("info", "Y");
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
            } else {
                // they haven't made the right choice; force the game to end
                inPlay = false;
            }

            // leaves 80% of the time for the next time through
            waitTime = 0.8 * waitTime;

        }
    }

    public void report(String caption,String message) {

        this.telemetry.addData(caption, message);

        this.telemetry.update();
    }

}
