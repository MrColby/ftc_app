package org.firstinspires.ftc.isd300.ind.courtney;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.Random;

/**
 * Created by colbyl on 10/3/2017.
 */
@TeleOp(name="DHController", group="Courtney")

public class DHController extends LinearOpMode {

    CPRoboto robot;

    private String getRandomMessage() {

        String oddMsg = "Wow we're friends now.";
        String evenMsg = "We can never be friends.";



       Random random = new Random();
        String msg;
        int num = random.nextInt(10)+1;
        int remainder = num % 2;
        if (remainder == 0) {
            msg = "Let's be friends.";
        }
        else {
            msg = "I want to run over your crippled cat.";
        }
        return msg;
        /*Random random = new Random();
        int num = random.nextInt(5);
        if (num == 0) return "You have to dance for 3 seconds.";
        else if (num == 1) return "You have to do 10 push ups";
        else if (num == 2) return "You have to sing Happy Birthday loudly standing like a stork.";
        else if (num == 3) return "try to stand on hands";
        return "Tell Jason you love him";*/
    }

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new CPRoboto(telemetry);
        robot.transmit("Msg", getRandomMessage());
        this.waitForStart();


    }
}
