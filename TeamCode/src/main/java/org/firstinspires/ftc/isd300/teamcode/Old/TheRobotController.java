package org.firstinspires.ftc.isd300.teamcode.Old;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.isd300.teamcode.Old.TheRobot;


/**
 * Created by colbyl on 10/3/2017.
 */
@TeleOp(name="TheTeamRobotController", group="team")

public class TheRobotController extends LinearOpMode {

    TheRobot robot;

        /*Random random = new Random();
        int num = random.nextInt(5);
        if (num == 0) return "You have to dance for 3 seconds.";
        else if (num == 1) return "You have to do 10 push ups";
        else if (num == 2) return "You have to sing Happy Birthday loudly standing like a stork.";
        else if (num == 3) return "try to stand on hands";
        return "Tell Jason you love him";*/


    @Override
    public void runOpMode() throws InterruptedException {

        robot = new TheRobot(telemetry, this.hardwareMap);
        this.waitForStart();
        while (this.opModeIsActive()) {
          robot.driveY(this.gamepad1);
          robot.driveX(this.gamepad1);
          robot.driveTL(this.gamepad1);
          robot.drivedTR(this.gamepad1);
          robot.setEyeStalkPosition(this.gamepad1);

        }
    }

}

