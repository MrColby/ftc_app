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

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.isd300.teamcode.Wallaby;

/**
 */

@Autonomous(name="Colby: Auto Drive By Time", group="Colby")
public class ColbyTest extends LinearOpMode {

    /* Declare OpMode members. */
    Wallaby robot = new Wallaby();   // Use a Pushbot's hardware
    private ElapsedTime timer = new ElapsedTime();

    private void messageNow(String caption, String msg) {
        telemetry.addData(caption, msg, timer.seconds());
        telemetry.update();
    }


    private void hangOn(double time, String caption, String msg) {
        timer.reset();
        while (opModeIsActive() && (timer.seconds() < time)) {
            messageNow(caption, msg);
        }
    }

    private void setArmMoving(double power) {
        robot.armMotor.setPower(power);
    }

    private void stopArm() {
        messageNow("Stopping arm", "Stopping the arm now");
        robot.armMotor.setPower(0);
    }

    private void setRobotMovingStraight(double power) {
        robot.rightDrive.setPower(power);
        robot.leftDrive.setPower(power);
    }

    private void setRobotTurning(double leftPower, double rightPower) {
        robot.leftDrive.setPower(leftPower);
        robot.rightDrive.setPower(rightPower);
    }

    private void stopDriving() {
        messageNow("Stopping wheels", "Stopping wheels now");
        robot.leftDrive.setPower(0.0);
        robot.rightDrive.setPower(0.0);
    }


    @Override
    public void runOpMode() {



        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run, Mr. Colby!");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way


        // set the robot driving straight at 50% power, then let it go for 3 seconds
        setRobotMovingStraight(0.5);
        hangOn(5, "Driving", "Straight for 5 seconds");

        // start the robot turning at 30% power for 3 seconds
        setRobotTurning(-0.5, 0.5);
        hangOn(5, "Turning", "Turn for 5 seconds");

        // get the car moving. Let it drive for 5 second without the arm
        setRobotMovingStraight(0.5);
        hangOn(5, "Driving", "Straight for 1 seconds and moving arm 1 second during");

        // after 5 seconds of driving, set the arm moving as well for 1 second as it drives
        setArmMoving(.4);
        hangOn(1, "Arming", "Arming while driving for 1 second");

        // then stop the arm, but let the car continue to drive for 5 more seconds
        stopArm();
        hangOn(5, "Driving", "Driving for a final 5 seconds");

        // then stop the car
        stopDriving();
        hangOn(3, "Resting", "Resting for 3 seconds");

        // put the arm back without the car moving
        setArmMoving(-.4);
        hangOn(1, "Arming", "Arming while driving for 1 second");
        stopArm();

        // then go back
        setRobotMovingStraight(-0.5);
        // we drove for 11 seconds, so back up 11 seconds
        hangOn(5, "Driving", "Driving for a final 11 seconds");

        // turn the robot back
        setRobotTurning(0.5, -0.5);
        hangOn(5, "Turning", "Turn back for 5 seconds");

        // back up to where we started
        setRobotMovingStraight(-0.5);
        hangOn(5, "Driving", "Backing up for a final 5 seconds");

        stopDriving();


        // I wonder why this was in the sample code?
        sleep(1000);


    }
}


