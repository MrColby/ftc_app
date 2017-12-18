package org.firstinspires.ftc.isd300.ind.braeden;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by colbyl on 10/6/2017.
 */
@TeleOp(name="RobotOverlordsOrders", group="Braeden")
@Disabled
public class RobotOverlord extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        RobotServant q=new RobotServant(this.telemetry);
        q.call("Hello");
        q.call("Lets be friends");
        this.waitForStart();
        int x=0;
        int y = 0;
        while(opModeIsActive()){
            x=x+1;
            if (x == 100000) {
                x = 0;
                y = y + 1;
                q.call("y= "+y);
            }

        }
    }
}
