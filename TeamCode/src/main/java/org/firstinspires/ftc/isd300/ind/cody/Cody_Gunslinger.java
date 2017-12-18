package org.firstinspires.ftc.isd300.ind.cody;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by helcod22 on 11/28/2017.
 */
@TeleOp(name="Cody_gunslinger", group="Dolphin")
@Disabled
public class Cody_Gunslinger extends LinearOpMode{
    public void call(String caption, String message){
        telemetry.addData (caption, message);
        telemetry.update();
    }
    public void runOpMode() throws InterruptedException {
        boolean playing = true;
        this.waitForStart();
        double time = 2000;
        while (opModeIsActive() && playing ==true) {
            call ("alert","go!");
            if (gamepad1.x == true)  {
                //break;
                playing = false;
            }
        }
    }
}
