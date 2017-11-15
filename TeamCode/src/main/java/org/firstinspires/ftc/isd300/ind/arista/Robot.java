package org.firstinspires.ftc.isd300.ind.arista;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 9/26/2017.
 */

public class Robot {

    private String name;
    private Telemetry telegraph;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    public Robot(Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2) {
        this.telegraph = telemetry;
        this.gamepad1=gamepad1;
        this.gamepad2=gamepad2;
    }
//pandas
    public void drive(int distance) {

    }
    public void turn(int degrees) {

    }
    public void setName(String mName) {
        this.name = mName;
    }

    public String getName() {
        return this.name;
    }
    public void speak(String caption, String message){
        telegraph.addData(caption,message);
        telegraph.update();
    }
}
