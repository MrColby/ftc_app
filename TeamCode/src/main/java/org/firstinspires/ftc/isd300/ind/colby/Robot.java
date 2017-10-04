package org.firstinspires.ftc.isd300.ind.colby;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/2/2017.
 */

public class Robot {

    private Telemetry messenger;

    public Robot(Telemetry tel) {
        this.messenger = tel;
    }

    public void message(String caption, String msg) {
        this.messenger.addData(caption, msg);
        this.messenger.update();
    }
}
