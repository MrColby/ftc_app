package org.firstinspires.ftc.isd300.ind.cody;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by colbyl on 10/3/2017.
 */

@TeleOp(name="CodyRobotController", group="Cody")
public class CodyRobotController extends LinearOpMode {

    Cobot myRobot;

    @Override
    public void runOpMode() throws InterruptedException {

        myRobot = new Cobot(this.telemetry);

        waitForStart();

    }
}
