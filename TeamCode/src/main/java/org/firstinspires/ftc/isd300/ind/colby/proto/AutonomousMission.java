package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

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

    public AutonomousMission(LinearOpMode pLinearOpMode, int pColor, int pPosition) {
        this.linearOpMode = pLinearOpMode;
        this.color = pColor;
        this.position = pPosition;

        protoBot = new ProtoBot(this.linearOpMode.hardwareMap, this.linearOpMode.telemetry);

        linearOpMode.waitForStart();

        protoBot.lowerEyestalk();
        int color = protoBot.getEyestalkColor();
        protoBot.message("I don't know","color is: "+color);
        ElapsedTime time = new ElapsedTime();
        while (time.milliseconds() < 3000){

        }





    }

    public void runMission() {

        this.linearOpMode.waitForStart();

        while (this.linearOpMode.opModeIsActive()) {

            this.protoBot.getPictograph();

        }

    }
}
