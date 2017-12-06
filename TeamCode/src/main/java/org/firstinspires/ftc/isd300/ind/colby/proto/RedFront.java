package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by colbyl on 12/2/2017.
 */
@Autonomous(name="ColbyProtoRedFront", group="Colby")
public class RedFront extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousMission mission = new AutonomousMission(this, AutonomousMission.RED, AutonomousMission.FRONT);



        mission.runMission();

    }
}
