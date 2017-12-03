package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by whiar on 12/3/2017.
 */

public class TotBot{
    public static final int COLOR_UNKNOWN = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_BLUE = 3;

    public void joystickDrive() {
        //balance on platform
    }
    public void autonomousDrive(double x, double y, double time, double power) {
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

}
