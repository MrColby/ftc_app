package org.firstinspires.ftc.isd300.ind.jack;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by colbyl on 10/4/2017.
 */
@TeleOp(name="TheGameBopit", group="jack")
@Disabled
public class Bopit extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
       this.talk("Ready","Go");
        this.waitForStart();

        while (this.opModeIsActive()) {





        }

    }
    public void talk(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }

    public void test() {
        String msg = "";

        if (this.gamepad1.a == true) {
            msg = "A is pressed";
        } else if (this.gamepad1.b == true) {
            msg = "B is pressed";
        } else if (this.gamepad1.x == true) {
            msg = "X is pressed";
        }
        talk("update", msg);
    }



}