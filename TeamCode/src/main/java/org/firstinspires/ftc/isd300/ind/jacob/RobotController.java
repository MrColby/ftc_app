package org.firstinspires.ftc.isd300.ind.jacob;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by colbyl on 10/4/2017.
 */
@TeleOp(name="JacobBopIt", group="jacob")
@Disabled
public class RobotController extends LinearOpMode {
    Terminator terminator;
    @Override
    public void runOpMode() throws InterruptedException {
        terminator = new Terminator(this.telemetry);
        terminator.talk("Info", "hello");

        this.waitForStart();

    }
}
