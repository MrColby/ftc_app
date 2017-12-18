/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.isd300.ind.colby;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;


/**
 */

@TeleOp(name="Colby Test", group="Colby")
@Disabled
public class ColbyTest extends LinearOpMode {

    private Robot robot;
    private ElapsedTime timer;


    public void message(String caption, String msg) {
        this.telemetry.addData(caption, msg);
        this.telemetry.update();
    }

    public void message(String caption, String msg1, String msg2) {
        this.telemetry.addData(caption, msg1);
        this.telemetry.addData(caption, msg2);
        this.telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.robot = new Robot(this.telemetry);
        this.timer = new ElapsedTime();

        double timeAllowed = 10000; // milliseconds

        message("debug", "padA: " + gamepad1.a, "padB: " + gamepad1.b);

        this.waitForStart();

        String loseMsg = null;
        while (this.opModeIsActive() && loseMsg == null) {

            Random rand = new Random();
            if (rand.nextInt(2) == 0) {
                loseMsg = a(this.gamepad1, timer, timeAllowed);
            }
            else {
                loseMsg = b(this.gamepad1, timer, timeAllowed);
            }

            if (loseMsg != null) {
                message("Lose", loseMsg);
                sleep(3000);
            }
            //timeAllowed = timeAllowed - .1*timeAllowed;
        }
    }

    private String b(Gamepad pad, ElapsedTime timer, double timeAllowed) {
        boolean pressed = pad.b;
        if (pressed) return "You pressed B too early";
        message("Play", "Press the B button in " + timeAllowed/1000 + " seconds:");
        timer.reset();
        while (timer.milliseconds() < timeAllowed && !pressed) {
            pressed = this.gamepad1.b;
        }
        if (!pressed) return "You didn't press B in " + timeAllowed/1000 + " seconds";
        return null;
    }

    private String a(Gamepad pad, ElapsedTime timer, double timeAllowed) {
        boolean pressed = pad.a;
        if (pressed) return "You pressed A too early";
        message("Play", "Press the A button in " + timeAllowed/1000 + " seconds:");
        timer.reset();
        while (timer.milliseconds() < timeAllowed && !pressed) {
            pressed = this.gamepad1.a;
        }
        if (!pressed) return "You didn't press A in " + timeAllowed/1000 + " seconds";
        return null;
    }




}