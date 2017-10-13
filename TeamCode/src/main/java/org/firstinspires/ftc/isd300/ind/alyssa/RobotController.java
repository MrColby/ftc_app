package org.firstinspires.ftc.isd300.ind.alyssa;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Random;

/**
 * Created by colbyl on 10/5/2017.
 */
@TeleOp(name="PandaRobotController", group="Panda")
public class RobotController extends LinearOpMode {
    private Panda panda;
    @Override
    public void runOpMode() throws InterruptedException {
        panda = new Panda(this.telemetry);
        panda.call("alert", "panda");
        this.waitForStart();
        String message;
        boolean lost = false;
        Random rand = new Random();
        double time = 2000;
        while (opModeIsActive() && !lost) {
            int choice = rand.nextInt(8);
            if (choice == 0) {
                call("alert", "push A button");
            }
            else if (choice == 1) {
                call("alert", "push B button");
            }
            else if (choice == 2) {
                call("alert", "push X button");
            }
            else if (choice == 3) {
                call("alert", "push Y button");
            }
            else if (choice == 4) {
                call("alert", "push up button");
            }
            else if (choice == 5) {
                call("alert", "push down button");
            }
            else if (choice == 6) {
                call("alert", "push left button");
            }
            else if (choice == 7) {
                call("alert", "push right button");
            }

            ElapsedTime timer = new ElapsedTime();
            timer.reset();
            while (timer.milliseconds() < time) {
            }

            if (choice == 0 && (gamepad1.a == false)) {
                    lost = true;
            }
            else if (choice == 1 && (gamepad1.b == false)) {
                lost = true;
            }
            else if (choice == 2 && (gamepad1.x == false)) {
                lost = true;
            }
            else if (choice == 3 && (gamepad1.y == false)) {
                lost = true;
            }
            else if (choice == 4 && (gamepad1.dpad_up == false)) {
                lost = true;
            }
            else if (choice == 5 && (gamepad1.dpad_down == false)) {
                lost = true;
            }
            else if (choice == 6 && (gamepad1.dpad_left == false)) {
                lost = true;
            }
            else if (choice == 7 && (gamepad1.dpad_right == false)) {
                lost = true;
            }
            else {
                time = .9*time;
            }

        }
    }
    public int countUp (int x) {
        x = x + 1;
        panda.call("alert", "you have " + x + " seconds");
        return x;
    }
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }
}
