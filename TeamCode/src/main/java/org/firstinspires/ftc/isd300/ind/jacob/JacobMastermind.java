package org.firstinspires.ftc.isd300.ind.jacob;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by colbyl on 11/18/2017.
 */
@TeleOp(name="JacobMasterMind",group="Jacob")
@Disabled
public class JacobMastermind extends LinearOpMode{


    //CODE_LENGTH defines how many "pegs" need to be guessed
    private static final int CODE_LENGTH  = 4;





    //store the code that's being guessed in it's array
    private int [] code = new int[CODE_LENGTH];

    //translate the numbers we use to the button names player sees
    private Map buttonMapping = new HashMap();

        private static final int X=0;
    private static final int Y=1;
    private static final int A=2;
    private static final int B=3;
    private static final int LB=4;
    private static final int RB=5;


    public void runOpMode() throws InterruptedException {



        //init the number-to-button mapping
        initButtonMap();

        //create the random code to be guessed
        initCode();


        this.waitForStart();

        while (this.opModeIsActive()){
          int [] guesses = getUserGuesses();

        }

        message("The Code Was " + getCodeDisplay(code));
        pause(3000);


    }
    private int [] getUserGuesses(){
        int[] guesses = new int [CODE_LENGTH];
        int guessIndex = 0;

        while (guessIndex < CODE_LENGTH){

            message("Guess A, B, X, Y, RB,or LB (" + getCodeDisplay(guesses) + ")");

            boolean bool = this.gamepad1.a;

            if (this.gamepad1.a) {
                guesses[guessIndex++] = A;
                while (this.gamepad1.a) {
                    //do nothing until button is let go
                }
            }
            else if (this.gamepad1.b) {
                guesses[guessIndex++] = B;
                while (this.gamepad1.b) {
                    //do nothing until button is let go
                }

            }
            else if (this.gamepad1.x) {
                guesses[guessIndex++] = X;
                while (this.gamepad1.x) {
                    //do nothing until button is let go
                }

            }
            else if (this.gamepad1.left_bumper) {
                guesses[guessIndex++] = LB;
                while (this.gamepad1.left_bumper) {
                    //do nothing until button is let go
                }


            }
            else if (this.gamepad1.right_bumper) {
                guesses[guessIndex++] = RB;
                while (this.gamepad1.right_bumper) {
                    //do nothing until button is let go
                }




            }
            else if (this.gamepad1.y) {
                guesses[guessIndex++] = Y;
                while (this.gamepad1.right_bumper) {
                    //do nothing until button is let go
                }


            }

        }
        return guesses;
    }


    private void initCode(){
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++){
            code[i] = random.nextInt(buttonMapping.size());
        }

    }


    private void initButtonMap(){

        buttonMapping.put(X, "X");
        buttonMapping.put(Y,"Y");
        buttonMapping.put(A,"A");
        buttonMapping.put(B,"B");
        buttonMapping.put(LB,"LB");
        buttonMapping.put(RB,"RB");
    }



    /*
    return the display form of the code
     */
    private String getCodeDisplay(int [] array) {
        String msg = "";

        for (int i=0; i < array.length; i++){
            msg = msg + buttonMapping.get(array[i]) + " ";

        }

        return msg;








    }





    /*
    Send a single string to the phone.
    without a caption
     */
    private void message(String msg){
        this.telemetry.addData("", msg);
        this.telemetry.update();

    }
    /*
    Pause for milliseconds
     */
    private void pause(double milliseconds){
        ElapsedTime timer = new ElapsedTime();
        while (timer.milliseconds()<milliseconds){
            //do nothing. Where waiting.
        }
    }






}
