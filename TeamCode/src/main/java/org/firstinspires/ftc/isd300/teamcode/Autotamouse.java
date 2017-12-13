package org.firstinspires.ftc.isd300.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

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

        drive(.6, 0);

        ElapsedTime timer=new ElapsedTime();
        while (timer.milliseconds()<3000) {

        }
        drive(0, 0);
        twirl(.5);
        timer.reset();
        while (timer.milliseconds()<1000) {

        }
        drive(0, 0);


        while (this.linearOpMode.opModeIsActive()) {

            this.totBot.getPictograph();

        }

    }

    private void drive (double forward, double right){
        double frontLeft = forward  + right;
        double frontRight = forward  - right;
        double rearLeft = forward  - right;
        double rearRight = forward  + right;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
    }

    private void twirl (double twirl){
        double frontLeft = twirl;
        double frontRight = -twirl;
        double rearLeft = twirl;
        double rearRight = -twirl;

        totBot.drive(frontLeft, frontRight, rearLeft, rearRight);
    }


}
