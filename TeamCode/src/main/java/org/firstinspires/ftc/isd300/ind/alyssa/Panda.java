package org.firstinspires.ftc.isd300.ind.alyssa;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/5/2017.
 */

public class Panda {


    Telemetry phone;

    public Panda(Telemetry p)    {
        this.phone = p;
    }

    public void call(String caption, String message) {
        phone.addData(caption, message);
        phone.update();
    }
}
