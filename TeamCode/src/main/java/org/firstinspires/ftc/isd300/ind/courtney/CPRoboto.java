package org.firstinspires.ftc.isd300.ind.courtney;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/3/2017.
 */

public class CPRoboto {

    private Telemetry telly;

    public CPRoboto(Telemetry tel) {
        telly = tel;
    }

    public void transmit(String caption,String message){
        telly.addData(caption, message);
        telly.update();
    }

}
