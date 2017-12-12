package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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
    private LinearOpMode linearOpMode;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    public Autotamouse(boolean red, boolean far, LinearOpMode linearOpMode) {
        this.red = red;
        this.far = far;
        this.linearOpMode = linearOpMode;
        this.hardwareMap = this.linearOpMode.hardwareMap;
        this.telemetry = this.linearOpMode.telemetry;
    }
    public void runMission() throws InterruptedException {
        totBot = new TotBot(this.hardwareMap,this.telemetry);

        this.linearOpMode.waitForStart();

        while (this.linearOpMode.opModeIsActive()) {

            this.totBot.getPictograph();

        }

    }
}
