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

    public static final int A = 0;
    public static final int B = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        Random random = new Random();
        this.waitForStart();
        while(this.opModeIsActive()){
            int time = 3000 + random.nextInt(7000);
            ElapsedTime timer = new ElapsedTime();
            while(timer.milliseconds() < time){

            }
            int choice = random.nextInt(2);
            if (choice == A){
                call("alert", "push A button");
            }
            else if (choice == B) {
                call("alert", "push B button");
            }
            boolean user1Choice = false;
            boolean user2Choice = false;
            while(true){
                if (choice == A){
                    user1Choice = this.gamepad1.a;
                    user2Choice = this.gamepad2.a;
                }
                else if (choice == B){
                    user1Choice = this.gamepad1.b;
                    user2Choice = this.gamepad2.b;
                }
                if(user1Choice||user2Choice) {
                    break;
                }


            }

            if(user1Choice && user2Choice){
                call("alert", "tie");
            }
            else if(user1Choice){
                call("alert", "user1won");
            }
            else if(user2Choice){
                call("alert", "user2won");
            }
            timer.reset();
            while(timer.milliseconds()<3000){

            }
        }
    }
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }
}
