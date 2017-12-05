package org.firstinspires.ftc.isd300.ind.colby.proto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by colbyl on 12/2/2017.
 */
@TeleOp(name="ColbyProtoPiloted", group="Colby")
public class PilotedMission extends LinearOpMode {


    private boolean fieldCentric = false;
    ProtoBot protoBot;

    @Override
    public void runOpMode() throws InterruptedException {

        protoBot = new ProtoBot(this.hardwareMap, this.telemetry);

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            checkFieldCentric();
            this.openOrCloseHands();
            this.raiseOrLowerEyestalk();
            drive();

        }

        stopMission();
    }

    private void raiseOrLowerEyestalk() {
        if (this.gamepad1.left_bumper) {
            this.protoBot.lowerEyestalk();
        }
        else if (this.gamepad1.right_bumper) {
            this.protoBot.raiseEyestalk();
        }
    }

    private void openOrCloseHands() {
        if (this.gamepad1.x) {
            protoBot.closeHands();
        }
        else if (this.gamepad1.b) {
            protoBot.openHands();
        }
    }

    private void checkFieldCentric() {
        if (this.gamepad1.left_stick_button) {
            this.fieldCentric = true;
        }
        else if (this.gamepad1.right_stick_button) {
            this.fieldCentric = false;
        }
    }

    private void stopMission() {
        protoBot.drive(0, 0, 0, 0);
    }

    private void drive() {
        double forward = -1*this.gamepad1.right_stick_y;
        double right = this.gamepad1.right_stick_x;
        double clockwise = this.gamepad1.left_stick_x;
        double axisSensitivityScalingConstant = 0.01;






        if (fieldCentric) {
            // for field centric support
            Orientation angles = protoBot.getGyroAngles();
            float rotation = angles.firstAngle;
            if (rotation < 0) rotation = 360 - Math.abs(rotation);
            if (rotation > 360) rotation = rotation % 360;

            protoBot.message("ilu", "Rotation: " + rotation);



            double temp = forward*Math.cos(rotation) + right*Math.sin(rotation);
            right = -forward*Math.sin(rotation) + right*Math.cos(rotation);
            forward = temp;

            /*double temp = forward*Math.cos(rotation) - right*Math.sin(rotation);
            right = forward*Math.sin(rotation) + right*Math.cos(rotation);
            forward = temp;*/
        }


        double frontLeft = forward + clockwise + right;
        double frontRight = forward - clockwise - right;
        double rearLeft = forward + clockwise - right;
        double rearRight = forward - clockwise + right;

        double max = Math.abs(frontLeft);
        if (Math.abs(frontRight)>max) max = Math.abs(frontRight);
        if (Math.abs(rearLeft)>max) max = Math.abs(rearLeft);
        if (Math.abs(rearRight)>max) max = Math.abs(rearRight);
        if (max>1) {
            frontLeft/=max; frontRight/=max; rearLeft/=max; rearRight/=max;
        }

        protoBot.drive(frontLeft, frontRight, rearLeft, rearRight);

    }
}
