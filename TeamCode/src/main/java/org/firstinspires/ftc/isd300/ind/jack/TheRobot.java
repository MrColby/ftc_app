package org.firstinspires.ftc.isd300.ind.jack;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.isd300.ind.courtney.TileRunnerHardware;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/3/2017.
 */

public class TheRobot {

    private Telemetry telly;

    private HardwareMap hardwareMap;

   org.firstinspires.ftc.isd300.ind.courtney.TileRunnerHardware hardware = new TileRunnerHardware();

    public TheRobot(Telemetry tel, HardwareMap map) {
        telly = tel;
        hardwareMap = map;
        hardware.init(hardwareMap);
    }

    public void transmit(String caption,String message){
        telly.addData(caption, message);
        telly.update();
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

        hardware.front_left.setPower(-1*frontLeft);
        hardware.front_right.setPower(-1*frontRight);
        hardware.back_left.setPower(backLeft);
        hardware.back_right.setPower(backRight);

    }
}
