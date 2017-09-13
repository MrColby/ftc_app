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
package org.firstinspires.ftc.isd300.ind.jack;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.isd300.teamcode.ISD300HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - drive forward for 12.0 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Jack1: Auto Drive By Time", group="Jack")
public class  JackDrive extends LinearOpMode {

    /* Declare OpMode members. */
    ISD300HardwarePushbot robot = new ISD300HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime timer = new ElapsedTime();


    static final double FORWARD_SPEED = 1.0;
    static final double TURN_SPEED = 0.5;
    static final double ARM_SPEED = 0.25;

    private void moveArm(double power, double seconds) {
        robot.armMotor.setPower(power);
        timer.reset();
        while (opModeIsActive() && (timer.seconds() < seconds)) {
            telemetry.addData("Motion", "Arming");
            telemetry.update();
        }
        robot.armMotor.setPower(0);
    }

    private void moveArmWhileDriving(double leftWheelPower, double rightWheelPower, double armPower, double seconds, boolean stopWheels, boolean stopArm) {
        robot.rightMotor.setPower(rightWheelPower);
        robot.leftMotor.setPower(leftWheelPower);
        robot.armMotor.setPower(armPower);
        timer.reset();
        while (opModeIsActive() && (timer.seconds() < seconds)) {
            telemetry.addData("Motion", "Driving and Arming");
            telemetry.update();
        }

        if (stopWheels) {
            robot.rightMotor.setPower(0);
            robot.leftMotor.setPower(0);
        }

        if (stopArm) {
            robot.armMotor.setPower(0);
        }
    }


    private void drive(double leftWheelPower, double rightWheelPower, double seconds, boolean stopWheels){
        robot.rightMotor.setPower(rightWheelPower);
        robot.leftMotor.setPower(leftWheelPower);
        timer.reset();
        while (opModeIsActive() && (timer.seconds() < seconds)) {
        }
        if (stopWheels) {

          robot.leftMotor.setPower(0);
          robot.leftMotor.setPower(0);
        }



    }

    @Override
    public void runOpMode() {





        /*
         * Initialize the drive system variables.
         * The init() method of the hardware //class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.rightMotor.setPower(.5);
        timer.reset();
        while (opModeIsActive()){
            telemetry.addData("hello", "i'm a robot");
        }

        drive(.5, .5, 5.0, true);

        /*

        // fully extend the arm
        moveArm(.1, 1.0);
        sleep(1000);
        moveArm(.1, 1.0);
        sleep(1000);
        moveArm(.1, 1.0);

        */

       // moveArm(.1, 1.0);
        // move the arm back up from fully extended
       // moveArm(-.1, 2.0);



        //this.moveArmWhileDriving(0.5, 0.5, 0.4, 2.5, true, true);




        sleep(1000);
    }
}


