package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by whiar on 12/3/2017.
 */

public class TotBot {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private DcMotor wheelFrontRightMotor;
    private DcMotor wheelFrontLeftMotor;
    private DcMotor wheelBackLeftMotor;
    private DcMotor wheelBackRightMotor;

    public static final int COLOR_UNKNOWN = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_BLUE = 3;

    public TotBot(HardwareMap ardwareMap, Telemetry elemtry) {
        this.hardwareMap = ardwareMap;
        this.telemetry = elemtry;
        this.intializewheels();
    }

    public void drive(double frontLeft, double frontRight, double rearLeft, double rearRight) {
        /**
         *
         */
    }

    public void armUpDown(boolean up) {

    }

    public void armGrabRelease(boolean close) {

    }

    public RelicRecoveryVuMark vuphoriaScanner() {
        return null;
    }

    public void eyestalkMove(boolean up) {

    }

    public int eyestalkSense() {
        return COLOR_UNKNOWN;
    }


    private void intializewheels() {
        this.wheelFrontLeftMotor = this.hardwareMap.get(DcMotor.class, "front_left");
        this.wheelFrontRightMotor = this.hardwareMap.get(DcMotor.class, "front_right");
        this.wheelBackLeftMotor = this.hardwareMap.get(DcMotor.class, "back_left");
        this.wheelBackRightMotor = this.hardwareMap.get(DcMotor.class, "back_right");
    }

}