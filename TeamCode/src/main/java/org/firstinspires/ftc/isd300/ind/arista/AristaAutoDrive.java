
package org.firstinspires.ftc.isd300.ind.arista;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="AristaBopIt", group="Arista")
@Disabled
public class AristaAutoDrive extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(this.telemetry, this.gamepad1, this.gamepad2);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();



    }
}