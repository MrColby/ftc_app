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

        // multiply the rotation by a scaling constant. Never more than 1.0!
        double clockwise = (0.9) * this.gamepad1.left_stick_x;


        if (fieldCentric) {
            // for field centric support
            Orientation angles = protoBot.getGyroAngles();
            float rotation = angles.firstAngle;
            //if (rotation < 0) rotation = 360 - Math.abs(rotation);
            //if (rotation > 360) rotation = rotation % 360;

            this.telemetry.addData("", "Rotation: " + rotation);


            // rotation is measured conter-clockwise
            double cos = Math.cos(rotation);
            double sin = Math.sin(rotation);

            //this.telemetry.addData("", "Cos: " + cos);
            //this.telemetry.addData("", "Sin: " + sin);


            // calculate the new forward setting and store it temporarily
            double newForward = forward*cos - right*sin;
            right = forward*sin + right*cos;
            forward = newForward;
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

        this.telemetry.addData("", "frontLeft: " + frontLeft);
        this.telemetry.addData("", "frontRight: " + frontRight);
        this.telemetry.addData("", "rearLeft: " + rearLeft);
        this.telemetry.addData("", "rearRight: " + rearRight);
        this.telemetry.update();

        protoBot.drive(frontLeft, frontRight, rearLeft, rearRight);

    }
}
