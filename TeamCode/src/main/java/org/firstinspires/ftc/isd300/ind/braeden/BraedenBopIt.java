package org.firstinspires.ftc.isd300.ind.braeden;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by colbyl on 10/6/2017.
 */
@TeleOp(name="RobotOverlordsBopItOrders", group="Braeden")
public class BraedenBopIt extends LinearOpMode {

    private boolean DEBUG = false;

    private static final int X = 0;
    private static final int Y = 1;
    private static final int A = 2;
    private static final int B = 3;

    @Override
    public void runOpMode() throws InterruptedException {

        boolean playing=true;
        //This is starting the game up by making Boolean playing=true
        double waitTime = 3000;

        this.waitForStart();
        //This stops the robot from shutting off every time you hit start
        while(opModeIsActive()&& playing) {
            int command = instructUser();
            playing = buttonTest(command,waitTime);
            waitTime = waitTime*.9;
        }
        ElapsedTime timer = new ElapsedTime();
        call("You Lose");
        while (timer.milliseconds()<1000) {

        }

    }



    private boolean buttonTest (int command, double timeLimit){
        ElapsedTime timer = new ElapsedTime();
        boolean isCorrect=false;
        while (timer.milliseconds()<timeLimit) {
            if (command == X && this.gamepad1.x && !this.gamepad1.y && !this.gamepad1.a && !this.gamepad1.b){
                isCorrect = true;
                break;
            }
            else if (command == Y && this.gamepad1.y && !this.gamepad1.x && !this.gamepad1.a && !this.gamepad1.b){
                isCorrect = true;
                break;
            }
           else  if (command == A && this.gamepad1.a && !this.gamepad1.y && !this.gamepad1.x && !this.gamepad1.b) {
                isCorrect = true;
                break;
            }
            else if (command == B && this.gamepad1.b && !this.gamepad1.y && !this.gamepad1.a && !this.gamepad1.x) {
                isCorrect = true;
                break;
            }
            else  if (this.gamepad1.x) {
                isCorrect = false;
                break;
            }
            else  if (this.gamepad1.y) {
                isCorrect = false;
                break;
            }
            else  if (this.gamepad1.a) {
                isCorrect = false;
                break;
            }
            else  if (this.gamepad1.b) {
                isCorrect = false;
                break;
            }

        }
        if(isCorrect == true){
            sendPositiveFeedback();
            while (timer.milliseconds() < timeLimit){

            }
        }


        return isCorrect;

    }

    private void sendPositiveFeedback () {
        ElapsedTime timer = new ElapsedTime();
        call("Good job");
        while (timer.milliseconds()< 100){
        }
        call("");
    }

    private int instructUser() {
        Random random=new Random();
        int command = random.nextInt(4);
        String msg;
        if (command == X) {
            msg = "X It";
        } else if (command == Y) {
            msg = "Y It";
        }
        else if (command == A) {
            msg = ("A It");
        }
        else if (command == B) {
            msg = ("B It");
        }else {
            msg = ("You Broke It");
            //This is the message before everything explodes
        }
        call(msg);
        return command;
    }

    public void debug(String msg) {
        if (DEBUG) {
            ElapsedTime timer = new ElapsedTime();
            call(msg);
            while (timer.milliseconds() < 1000) {

            }
        }
    }




    public void call(String s){
        telemetry.addData("speech",s);
        telemetry.update();
    }


}



