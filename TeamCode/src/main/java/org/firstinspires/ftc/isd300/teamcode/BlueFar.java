package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by whiar on 12/3/2017.
 */
@Autonomous(name="BlueFar", group="teamtot")
public class BlueFar extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Autotamouse marmotot =  new Autotamouse(false, true, this);
        marmotot.runMission();
    }
}
