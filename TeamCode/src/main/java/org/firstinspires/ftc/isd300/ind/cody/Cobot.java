package org.firstinspires.ftc.isd300.ind.cody;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/3/2017.
 */

public class Cobot {

    Telemetry phone;

    public Cobot(Telemetry tell) {


        phone = tell;

        report ("info", "Hi");

    }
    public void report(String caption,String message){

        phone.addData(caption, message);

        phone.update();
    }
}
