package org.firstinspires.ftc.isd300.ind.arista;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by whiar on 12/12/2017.
 */
@TeleOp(name = "ButtonRace", group = "Arista")
public class ButtonRace extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Random random = new Random();
        this.waitForStart();
        while(this.opModeIsActive()){
            int time = 3000 + random.nextInt(7000);
            ElapsedTime timer = new ElapsedTime();
            while(timer.milliseconds() < time){

            }
            int choice = random.nextInt(8);
            call("alert", "push A button");    
        }
    }
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }
}
