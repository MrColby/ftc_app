package org.firstinspires.ftc.isd300.ind.braeden;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by colbyl on 10/6/2017.
 */
@TeleOp(name="RobotOverlordsBopItOrders", group="Braeden")
public class BopIt extends LinearOpMode {

    private boolean DEBUG = false;

    private int X = 0;
    private int Y = 1;
    private int A = 2;
    private int B = 3;

    public void debug(String msg) {
        if (DEBUG) {
            ElapsedTime timer = new ElapsedTime();
            call(msg);
            while (timer.milliseconds() < 1000) {

            }
        }
    }


    public int testGamepad(int command) {
        if (command ==X && this.gamepad1.x) {
            debug("good x");
            return 1;
        }
        else if (command == Y && this.gamepad1.y) {
            debug ("good y");
           return 1;
        }
        else if (command == A && this.gamepad1.a) {
            debug ("good y");
            return 1;
        }
        else if (command == B && this.gamepad1.b) {
            debug ("good y");
            return 1;
        }
        else {
            if (this.gamepad1.x || this.gamepad1.y || this.gamepad1.a || this.gamepad1.b) {
                debug("Bad button");
                return 0;
            }
        }
        return -1;

    }

    public void call(String s){
        telemetry.addData("speech",s);
        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        // add comments in front of every line explaining the purpose or logic
        Random random=new Random();
        ElapsedTime timer=new ElapsedTime();
        // This is the making or starting a new variable "ElapsedTime"
        boolean playing=true;
        //This is starting the game up by making Boolean playing=true
        double waitTime = 3000;

        this.waitForStart();
        //This stops the robot from shutting off every time you hit start
        while(opModeIsActive()&& playing) {
            int command = random.nextInt(4);
            String msg = "Yo";
            if (command == X) {
                msg = "X It";
            } else if (command == Y) {
                msg = "Y It";
            }
            else if (command == A) {
                call("A It");
            }
            else if (command == B) {
                call("B It");
            }else {
                call("You Broke It");
                //This is the message before everything explodes
            }

            call(msg);
            timer.reset();
            int imPressed = -1;
            while(timer.milliseconds()<waitTime){
                for (int x=0; x < 10; x++) {

                }

                if (imPressed == -1) imPressed = this.testGamepad(command);
                else if (imPressed == 1){
                    int newVote = this.testGamepad(command);
                    if (newVote != imPressed) {
                        playing = false;
                    }
                }
            }

            if (imPressed == -1) {
                // didn't push a button
                debug ("didn't push a button");
                playing = false;
                //This is for debugging mode
            }
            else if (imPressed == 0) {
                // pushed the wrong button
                debug("pushed the wrong button");
                playing = false;
                //This is for debugging mode
            }
            else {
                timer.reset();
                call("");
                while (timer.milliseconds() < 500) {
                    // pause so they can get their finger off!
                }
                waitTime = .9 * waitTime;
                //This decreases the time you have to hit the button evey time you play a round

            }


        }

        call("You are bad");
        timer.reset();
        while (timer.milliseconds() < 2000) {

        }
    }
}



