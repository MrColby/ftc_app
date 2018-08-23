
package org.firstinspires.ftc.isd300.ind.arista;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

    @TeleOp(name="AristaBopIt", group="Arista")
    public class AristaBopIt extends LinearOpMode {
    }
    }
    @Override
    public void runOpMode() {

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        Robot robot = new Robot(this.telemetry, this.gamepad1, this.gamepad2);

        @TeleOp(name="PandaBopIt", group="Panda")
        public class AlyssaBopIt extends LinearOpMode {
            @Override
            public void runOpMode() throws InterruptedException {

                int score = 0;
                int countDracula = 0;

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
                        score++;
                        countDracula++;
                        if (countDracula == 3) {
                            time = .9 * time;
                            countDracula = 0;
                        }
                    }

                }
                this.telemetry.addData("","your score is "+ score) ;
                this.telemetry.update();
                waitForALittleBit(2500);
            }
            private void waitForALittleBit(double time){
                ElapsedTime timer = new ElapsedTime();
                timer.reset();
                while (timer.milliseconds() < time) {
                }
            };

            public void call(String caption, String message) {
                telemetry.addData(caption, message);
                telemetry.update();
            }
        }
