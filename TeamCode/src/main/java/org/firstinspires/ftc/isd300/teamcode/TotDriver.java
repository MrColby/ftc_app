package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by whiar on 12/3/2017.
 */
@TeleOp(name="TotDriver", group="teamtot")
public class TotDriver extends LinearOpMode {

    private TotBot totBot;

    @Override
    public void runOpMode() throws InterruptedException {

        totBot = new TotBot(this.hardwareMap,this.telemetry);

        this.waitForStart();
        while (this.opModeIsActive()) {
            drive();
            raiseOrLowerEyestalk();
        }
        totBot.drive(0, 0, 0, 0);
    }
    private void raiseOrLowerEyestalk() {
        if (this.gamepad1.left_bumper) {
            this.totBot.lowerEyestalk();
        }
        else if (this.gamepad1.right_bumper) {
            this.totBot.riaseEyestalk();
        }
    }
    private void drive(){
        double forward = -1 * this.gamepad1.right_stick_y;
        double right = this.gamepad1.right_stick_x;
        double clockwise = this.gamepad1.left_stick_x;

        double frontLeft = forward + clockwise + right;
        double frontRight = forward - clockwise - right;
        double rearLeft = forward + clockwise - right;
        double rearRight = forward - clockwise + right;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
    }
}

