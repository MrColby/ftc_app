package org.firstinspires.ftc.isd300.ind.jack;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/4/2017.
 */

public class Terminator {
    Telemetry telemetry;
    public Terminator(Telemetry t) {
        telemetry = t;
    }

    public void talk(String caption, String message){
        telemetry.addData(caption, message);
        telemetry.update();
    }


}
