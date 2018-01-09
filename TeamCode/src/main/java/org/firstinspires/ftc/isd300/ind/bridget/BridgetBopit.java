package org.firstinspires.ftc.isd300.ind.bridget;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Random;

/**
 * Created by colbyl on 11/13/2017.
 */
@TeleOp(name="BridgetBopit",group="Bridget")
@Disabled
public class BridgetBopit extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    Random random= new Random();

    @Override
    public void runOpMode() throws InterruptedException {


        double scale=.9;
        double waittime=3000;
        int score=-1;

        this.waitForStart();

        boolean playing=true;
        String msg;
        while(playing) {
            int choose=random.nextInt(4);
            if(choose==0){
                msg="x";
            }
            else if(choose==1)
            {
                msg="y";
            }
            else if (choose==2)
            {
                msg="b";
            }
            else{
                msg="a";
            }


            this.telemetry.addData("",msg);
            this.telemetry.update();
            //wait for 3 sec

            //set timer to 0
            timer.reset();
            while(timer.milliseconds()<waittime){
                // we are waiting
            }
            if(choose==0 && this.gamepad1.x)
            {
                this.telemetry.addData("","");
                this.telemetry.update();
            }
            else  if(choose==1 && this.gamepad1.y)
            {
                this.telemetry.addData("","");
                this.telemetry.update();
            }
            else  if(choose==2 && this.gamepad1.b)
            {
                this.telemetry.addData("","");
                this.telemetry.update();
            }
            else  if(choose==3 && this.gamepad1.a)
            {
                this.telemetry.addData("","");
                this.telemetry.update();
            }
            else{
                playing=false;
            }

            timer.reset();
            while(timer.milliseconds()<100){

            }
            waittime=scale*waittime;
            score=score+1;
        }

        this.telemetry.addData("","your score was "+score) ;
        this.telemetry.update();


        timer.reset();
        while(timer.milliseconds()<3000){

        }



    }
}
