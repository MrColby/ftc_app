package org.firstinspires.ftc.isd300.ind.braeden;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/6/2017.
 */

public class RobotServant {

    Telemetry telemetry;

    public RobotServant(Telemetry t){

        this.telemetry=t;


    }
    public void call(String s){
        telemetry.addData("speech",s);
        telemetry.update();
    }
}

