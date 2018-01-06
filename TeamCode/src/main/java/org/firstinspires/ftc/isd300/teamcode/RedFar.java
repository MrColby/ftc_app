package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by whiar on 12/3/2017.
 */
@Autonomous(name="RedFar", group="teamtot")
public class RedFar extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Autotamouse marmotot =  new Autotamouse(true, true, this);
        marmotot.runMission();
    }
}
