package org.firstinspires.ftc.isd300.ind.cody;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.isd300.ind.jack.TheRobot;

/**
 * Created by helcod22 on 11/6/2017.
 */
@Disabled
public class TileRunnerController extends LinearOpMode {

    TheRobot robot;



    @Override
    public void runOpMode() throws InterruptedException {

        robot = new TheRobot(telemetry, this.hardwareMap);
        this.waitForStart();
        while (this.opModeIsActive()) {
            robot.driveY(this.gamepad1);
            robot.driveX(this.gamepad1);
            robot.driveTL(this.gamepad1);
            robot.drivedTR(this.gamepad1);

        }
    }



}
