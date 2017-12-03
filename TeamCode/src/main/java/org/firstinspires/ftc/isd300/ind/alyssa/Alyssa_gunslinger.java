package org.firstinspires.ftc.isd300.ind.alyssa;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by colbyl on 11/28/2017.
 */
@TeleOp(name="Alyssa_gunslinger", group="sheep")
public class Alyssa_gunslinger extends LinearOpMode{
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }
    public void runOpMode() throws InterruptedException {
        boolean playing = true;
        this.waitForStart();
        double time = 2000;
        while (opModeIsActive() && playing == true) {
            call("alert", "go!");
            if (gamepad1.x == true) {
                //break;
                playing = false;
            }
        }
    }
}

