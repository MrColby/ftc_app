package org.firstinspires.ftc.isd300.ind.jacob;

import android.telecom.Call;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


            }


/**
 * Created by colbyl on 11/28/2017.
 */
        @TeleOp(name="BestGunslinger", group="JacobGunslinger")
        public class JacobGunslinger extends LinearOpMode {
            public void call(String caption, String message) {
                telemetry.addData(caption, message);
                telemetry.update();
            }
            public void runOpMode() throws InterruptedException {
                this.waitForStart();
                while (opModeIsActive()) {
                    call("Now!!", "Shoot!");


                }




    }

