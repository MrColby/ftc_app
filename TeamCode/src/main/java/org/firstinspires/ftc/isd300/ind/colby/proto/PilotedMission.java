package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by colbyl on 12/2/2017.
 */
@TeleOp(name="ColbyProtoPiloted", group="Colby")
public class PilotedMission extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        ProtoBot protoBot = new ProtoBot(this.hardwareMap, this.telemetry, this.gamepad1, this.gamepad2);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            protoBot.drive();

        }
    }
}
