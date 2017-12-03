package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by colbyl on 12/2/2017.
 */
@Autonomous(name="ColbyProtoBlueBack", group="Colby")
public class BlueBack extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        AutonomousMission mission = new AutonomousMission(this, AutonomousMission.BLUE, AutonomousMission.BACK);
        mission.runMission();

    }
}
