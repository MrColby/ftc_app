package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by colbyl on 12/2/2017.
 */

public class AutonomousMission {

    public static final int RED = 0;
    public static final int BLUE = 1;
    public static final int FRONT = 0;
    public static final int BACK = 1;

    private LinearOpMode linearOpMode;
    private int color;
    private int position;

    private ProtoBot protoBot;

    private void twirl (double desiredDegreeOfTurn, double power, boolean clockwise){
        power = Math.abs(power);
        if (!clockwise) {
            power = -1 * Math.abs(power);
        }

        Orientation angles = protoBot.getGyroAngles();
        double startingPosition = angles.firstAngle;

        double desiredPosition;
        if (clockwise) {
           desiredPosition = startingPosition - desiredDegreeOfTurn;
           if (desiredPosition < 0) {
               desiredPosition = desiredPosition + 360;
           }
        }
        else {
           desiredPosition = startingPosition + desiredDegreeOfTurn;
           if (desiredPosition >=360) {
               desiredPosition = desiredPosition - 360;
           }
        }

        this.twirl(power);
        double currentPosition = protoBot.getGyroAngles().firstAngle;

        boolean keepTurning = true;

        while ( keepTurning ){
            protoBot.message ("test","Turning " + desiredDegreeOfTurn + " degrees. Started at " + startingPosition + " and now at " + currentPosition);
            currentPosition = angles.firstAngle;
            if (clockwise) {
                if (currentPosition <= desiredPosition) {
                    keepTurning = false;
                }
            }
            else {
                if (currentPosition >= desiredPosition) {
                    keepTurning = false;
                }
            }
        }
        this.twirl(0);



    }

    private void twirl (double power){
        double frontLeft = power;
        double frontRight = -power;
        double rearLeft = power;
        double rearRight = -power;

        protoBot.drive(frontLeft, frontRight, rearLeft, rearRight);



    }

    public AutonomousMission(LinearOpMode pLinearOpMode, int pColor, int pPosition) {
        this.linearOpMode = pLinearOpMode;
        this.color = pColor;
        this.position = pPosition;

        protoBot = new ProtoBot(this.linearOpMode.hardwareMap, this.linearOpMode.telemetry);








    }

    public void runMission() {

        this.linearOpMode.waitForStart();


        while (this.linearOpMode.opModeIsActive()) {
            this.twirl(90, .5, true);
        }

        /*


        protoBot.lowerEyestalk();

        ElapsedTime time = new ElapsedTime();
        while (time.milliseconds() < 500) {

        }

        int color = protoBot.getEyestalkColor();
        if (this.color == RED && this.position == FRONT) {
            if(color == ProtoBot.COLOR_RED){
                this.twirl(0.5);
            }
            else if(color == ProtoBot.COLOR_BLUE){
                this.twirl(-0.5);
            }
        }


        time.reset();
        while (time.milliseconds() < 500){

        }
        this.twirl(0);
        //protoBot.message("I don't know","color is: "+color);
    */

    }
}
