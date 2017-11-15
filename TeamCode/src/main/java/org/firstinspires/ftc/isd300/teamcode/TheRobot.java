package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/3/2017.
 */

public class TheRobot {

    private Telemetry telly;

    private HardwareMap hardwareMap;

   TheRobotHardware hardware = new TheRobotHardware();

    public TheRobot(Telemetry tel, HardwareMap map) {
        telly = tel;
        hardwareMap = map;
        hardware.init(hardwareMap);
    }

    public void transmit(String caption,String message){
        telly.addData(caption, message);
        telly.update();
    }

    public void setEyeStalkPosition(Gamepad gamepad) {
        if(gamepad.a){
            hardware.setEyeStalkPosition(0.3141500203);
        }
        else if(gamepad.y){
            hardware.setEyeStalkPosition(0);
        }
    }

    public void driveY(Gamepad gamepad){
        double frontRight = gamepad.right_stick_y;
        double frontLeft = gamepad.right_stick_y;
        double backRight = gamepad.right_stick_y;
        double backLeft = gamepad.right_stick_y;

        hardware.front_left.setPower(-1*frontLeft);
        hardware.front_right.setPower(frontRight);
        hardware.back_left.setPower(-1*backLeft);
        hardware.back_right.setPower(backRight);

    }
    public void driveX(Gamepad gamepad){
        double frontRight = gamepad.left_stick_x;
        double frontLeft = gamepad.left_stick_x;
        double backRight = gamepad.left_stick_x;
        double backLeft = gamepad.left_stick_x;

        hardware.front_left.setPower(frontLeft);
        hardware.back_left.setPower(-1*backLeft);
        hardware.back_right.setPower(-1*backRight);
        hardware.front_right.setPower(frontRight);

    }
    public void driveTL(Gamepad gamepad) {
        double frontRight = gamepad.left_trigger;
        double frontLeft = gamepad.left_trigger;
        double backRight = gamepad.left_trigger;
        double backLeft = gamepad.left_trigger;

        hardware.front_left.setPower(frontLeft);
        hardware.front_right.setPower(frontRight);
        hardware.back_left.setPower(backLeft);
        hardware.back_right.setPower(backRight);
    }


    public void servo(Gamepad gamepad){
        boolean servoMotor = gamepad.dpad_up;


    }

    public void drivedTR(Gamepad gamepad){
        double frontRight = gamepad.right_trigger;
        double frontLeft = gamepad.right_trigger;
        double backRight = gamepad.right_trigger;
        double backLeft = gamepad.right_trigger;

        hardware.front_left.setPower(-1*frontLeft);
        hardware.front_right.setPower(-1*frontRight);
        hardware.back_left.setPower(-1*backLeft);
        hardware.back_right.setPower(-1*backRight);



    }




}
