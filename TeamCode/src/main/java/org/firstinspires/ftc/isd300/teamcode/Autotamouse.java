package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by whiar on 12/3/2017.
 */

public class Autotamouse {
    private boolean red;
    private boolean far;
    private TotBot totBot;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    public Autotamouse(boolean red, boolean far, Telemetry telemetry, HardwareMap hardwareMap) {
        this.red = red;
        this.far = far;
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }
    public void runMission() {
        totBot = new TotBot(this.hardwareMap,this.telemetry);
        RelicRecoveryVuMark vuMark = totBot.getPictograph();
    }
}
