package org.firstinspires.ftc.isd300.ind.colby;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by colbyl on 10/2/2017.
 */


public class GamepadTracker {

    private Gamepad gamepad;

    private float last_left_stick_x;



    public GamepadTracker(Gamepad gp) {
        this.gamepad = gp;
    }


}
