package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by whiar on 12/3/2017.
 */
@Autonomous(name="BlueClose", group="teamtot")
public class BlueClose extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        Autotamouse marmotot =  new Autotamouse(false, false);
        this.waitForStart();
        //marmotot.run();
        while (this.opModeIsActive()){
        }

    }
}
