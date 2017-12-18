package org.firstinspires.ftc.isd300.ind.colby;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by colbyl on 10/7/2017.
 */

@TeleOp(name="ColbyMastermind", group="Colby")
@Disabled
public class ColbyMastermind extends LinearOpMode {

    // CODE_LENGTH defines how many 'pegs' need to be guessed
    private static final int CODE_LENGTH = 4;

    // store the code that's being guess in this array
    private int [] code = new int[CODE_LENGTH];

    // translate the numbers we use to the button names the player sees
    private Map buttonMapping = new HashMap();

    // not 0 so we can recognize non-guesses
    private static final int X = 1;
    private static final int Y = 2;
    private static final int A = 3;
    private static final int B = 4;
    private static final int LB = 5;
    private static final int RB = 6;
// add number of tries
    @Override
    public void runOpMode() throws InterruptedException {

        // init the number-to-button mapping
        initButtonMap();

        // create the random code to be guessed
        initCode();

        this.waitForStart();

        /*
            Give instructions.
         */



        int [] feedback = {0, 0};
        while (this.opModeIsActive() && feedback[0] < CODE_LENGTH) {

            /*

            First, test for joystick exit command.

             */

            int [] guesses = getUserGuesses();
            feedback = compare(guesses, code);

        }

        if (this.opModeIsActive()) {
            if (feedback[0] == CODE_LENGTH) {
                message("You won");
            }
            else {
                message("The code was " + getCodeDisplay(code));
            }
            pause(3000);
        }





    }

    /*
      Return two integers:
          how many are the right color in the correct position
          how many are the right color in the wrong position
     */
    private int [] compare(int [] guesses, int [] code) {

        int [] feedback = {0, 0};

        // copy code so we can modify it
        int [] codeCopy = arrayCopy(code);

        // walk through first and see how many are exactly right.
        // delete them from both if they are right so they don't get counted twice
        for (int i=0; i < guesses.length; i++) {
            if (guesses[i] == codeCopy[i]) {
                guesses[i] = -1;
                codeCopy[i] = -1;
                feedback[0]++;
            }
        }

        // walk through the remaining guesses. See if there are any of that color remaining in the
        // codeCopy. If yes, then add another and delete from codeCopy so it doesn't get counted twice.
        for (int i=0; i < guesses.length; i++) {
            if (guesses[i] > -1) {
                for (int j=0; j < codeCopy.length; j++) {
                    if (guesses[i] == codeCopy[j]) {
                        feedback[1]++;
                        guesses[i] = -1;
                        break;
                    }
                }
            }
            if (guesses[i] == codeCopy[i]) {
                guesses[i] = -1;
                codeCopy[i] = -1;
                feedback[0]++;
            }
        }


        return feedback;
    }

    private int [] arrayCopy(int [] src) {
        int [] copy = new int[src.length];
        for (int i=0; i < src.length; i++) {
            copy[i] = src[i];
        }
        return copy;
    }

    private int [] getUserGuesses() {
        int [] guesses = new int[CODE_LENGTH];
        int guessIndex = 0;

        while (guessIndex < CODE_LENGTH) {


            message("Guess A, B, X, Y, LB, or RB (" + getCodeDisplay(guesses) + ")");
            if (this.gamepad1.a) {
                guesses[guessIndex++] = A;
                while (this.gamepad1.a) {
                    // do nothing more until they let go of the key
                }
            }
            else if (this.gamepad1.b) {
                guesses[guessIndex] = B;
                while (this.gamepad1.b) {
                    // do nothing more until they let go of the key
                }
            }
            else if (this.gamepad1.x) {
                guesses[guessIndex] = X;
                while (this.gamepad1.x) {
                    // do nothing more until they let go of the key
                }
            }
            else if (this.gamepad1.y) {
                guesses[guessIndex] = Y;
                while (this.gamepad1.y) {
                    // do nothing more until they let go of the key
                }
            }
            else if (this.gamepad1.left_bumper) {
                guesses[guessIndex] = LB;
                while (this.gamepad1.left_bumper) {
                    // do nothing more until they let go of the key
                }
            }
            else if (this.gamepad1.right_bumper) {
                guesses[guessIndex] = RB;
                while (this.gamepad1.right_bumper) {
                    // do nothing more until they let go of the key
                }
            }
        }
        return guesses;
    }

    private void initCode() {
        Random random = new Random();
        for (int i=0; i < CODE_LENGTH; i++) {
            code[i] = random.nextInt(buttonMapping.size());
        }
    }

    private void initButtonMap() {
        buttonMapping.put(X, "X");
        buttonMapping.put(Y, "Y");
        buttonMapping.put(A, "A");
        buttonMapping.put(B, "B");
        buttonMapping.put(LB, "LB");
        buttonMapping.put(RB, "RB");
    }

    /*
    Returns the display form of the code
     */
    private String getCodeDisplay(int [] array) {
        String msg = "";

        for (int i=0; i < array.length; i++) {
            if (array[i] == 0) {
                // if they haven't guessed, put in an underscore
                msg = msg + "_ ";
            }
            else {
                msg = msg + buttonMapping.get(array[i]) + " ";
            }
        }

        return msg;


    }

    /*
    Pause for milliseconds
     */
    private void pause(double milliseconds) {
        ElapsedTime timer = new ElapsedTime();
        while (timer.milliseconds() < milliseconds) {
            // do nothing. We're waiting.
        }
    }

    /*
    Send a single string message to the phone
    without a caption
     */
    private void message(String msg) {
        this.telemetry.addData("", msg);
        this.telemetry.update();
    }



}
