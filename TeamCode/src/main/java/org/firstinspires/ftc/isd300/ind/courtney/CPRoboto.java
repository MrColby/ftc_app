package org.firstinspires.ftc.isd300.ind.courtney;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by colbyl on 10/3/2017.
 */

public class CPRoboto {

    private Telemetry telly;

    private HardwareMap hardwareMap;

   TileRunnerHardware hardware = new TileRunnerHardware();

    public CPRoboto(Telemetry tel, HardwareMap map) {
        telly = tel;
        hardwareMap = map;
        hardware.init(hardwareMap);
    }

    public void transmit(String caption,String message){
        telly.addData(caption, message);
        telly.update();
    }

    public void drive(double front_right, double front_left, double back_right, double back_left){
        hardware.front_left.setPower(front_left);
        hardware.front_right.setPower(front_right);
        hardware.back_left.setPower(back_left);
        hardware.back_right.setPower(back_right);
    }
}
