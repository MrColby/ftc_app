package org.firstinspires.ftc.isd300.ind.colby;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/7/2017.
 */

public class GamepadTelemetry extends GamepadHandler {

    protected Telemetry telemetry;



    public GamepadTelemetry(Gamepad pad, String i, Telemetry tel) {
        super(pad, i);
        this.telemetry = tel;
    }



     /* ACT ON GAMEPAD CHANGES*/

    protected void gamepad_x(boolean b) {
        this.telemetry.addData("event",  this.id + " X: " + b); this.telemetry.update();
    }

    protected void gamepad_y(boolean b) {
        this.telemetry.addData("event", this.id + " Y: " + b); this.telemetry.update();
    }

    protected void gamepad_a(boolean b) {
        this.telemetry.addData("event", this.id + " A: " + b); this.telemetry.update();
    }

    protected void gamepad_b(boolean b) {
        this.telemetry.addData("event", this.id + " B: " + b); this.telemetry.update();
    }

    protected void gamepad_start(boolean b) {
        this.telemetry.addData("event", this.id + " Start: " + b); this.telemetry.update();
    }

    protected void gamepad_back(boolean b) {
        this.telemetry.addData("event", this.id + " Back: " + b); this.telemetry.update();
    }

    protected void gamepad_dpad_down(boolean b) {
        this.telemetry.addData("event", this.id + " Dpad Down: " + b); this.telemetry.update();
    }

    protected void gamepad_dpad_left (boolean b) {
        this.telemetry.addData("event", this.id + " Dpad Left: " + b); this.telemetry.update();
    }

    protected void gamepad_dpad_up (boolean b) {
        this.telemetry.addData("event", this.id + " Dpad Up: " + b); this.telemetry.update();
    }

    protected void gamepad_dpad_right (boolean b) {
        this.telemetry.addData("event", this.id + " Dpad Right: " + b); this.telemetry.update();
    }

    protected void gamepad_guide (boolean b) {
        this.telemetry.addData("event", this.id + " Guide: " + b); this.telemetry.update();
    }

    protected void gamepad_left_bumper (boolean b) {
        this.telemetry.addData("event", this.id + " Left Bumper: " + b); this.telemetry.update();
    }

    protected void gamepad_left_stick_button (boolean b) {
        this.telemetry.addData("event", this.id + " Left Stick Button: " + b); this.telemetry.update();
    }

    protected void gamepad_left_stick(float x, float y) {
        this.telemetry.addData("event", this.id + " Left Stick: (" + x + ", " + y +")"); this.telemetry.update();
    }

    protected void gamepad_left_trigger (float f) {
        this.telemetry.addData("event", this.id + " Left Trigger: " + f); this.telemetry.update();
    }

    protected void gamepad_right_bumper (boolean b) {
        this.telemetry.addData("event", this.id + " Right Bumper: " + b); this.telemetry.update();
    }

    protected void gamepad_right_stick_button (boolean b) {
        this.telemetry.addData("event", this.id + " Right Stick Button: " + b); this.telemetry.update();

    }

    protected void gamepad_right_stick(float x, float y) {
        this.telemetry.addData("event", this.id + " Right Stick: (" + x + ", " + y +")"); this.telemetry.update();
    }

    protected void gamepad_right_trigger (float f) {
        this.telemetry.addData("event", this.id + " Right Trigger: " + f); this.telemetry.update();
    }






}
