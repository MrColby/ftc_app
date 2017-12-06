package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by whiar on 12/3/2017.
 */
@Autonomous(name="RedClose", group="teamtot")
public class RedClose extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Autotamouse marmotot =  new Autotamouse(false, false, telemetry, hardwareMap);
        this.waitForStart();
        marmotot.runMission();
        while (this.opModeIsActive()) {

        }

    }
}
