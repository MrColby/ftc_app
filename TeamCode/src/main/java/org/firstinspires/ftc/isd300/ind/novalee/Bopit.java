package org.firstinspires.ftc.isd300.ind.novalee;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Random;
/**
 * Created by colbyl on 11/10/2017.
 */
@TeleOp(name="Novaleebopit", group="Novalee")
public class Bopit extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Random random=new Random();
        int choice=random.nextInt(2);
        ElapsedTime timer = new ElapsedTime();
        this.waitForStart();
        if(choice==0){
            this.telemetry.addData("Info", "Press X");
            this.telemetry.update();
        }
        else{
            this.telemetry.addData("Info", "Press y");
            this.telemetry.update();

        }
        timer.reset();
        while (timer.milliseconds()<5000) {
        //twiddle
        }
        if(this.gamepad1.x==true){
            this.telemetry.addData("Info","Good Job");
            this.telemetry.update();

        }
        else{
            this.telemetry.addData("Info", "Horrible Job");
            this.telemetry.update();
        }
        timer.reset();
        while (timer.milliseconds()<5000){

        }

    }
}
