package org.firstinspires.ftc.isd300.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by whiar on 12/3/2017.
 */

public class TotBot {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private Servo eyeStalkServo;

    private ColorSensor eyeStalkColorSensor;
    private DistanceSensor eyeStalkDistanceSensor;

    private DcMotor armMotor;
    private Servo leftHandServo;
    private Servo rightHandServo;

    private DcMotor wheelFrontRightMotor;
    private DcMotor wheelFrontLeftMotor;
    private DcMotor wheelBackLeftMotor;
    private DcMotor wheelBackRightMotor;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    public static final int COLOR_UNKNOWN = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_BLUE = 3;

    public TotBot(HardwareMap ardwareMap, Telemetry elemtry) {
        this.hardwareMap = ardwareMap;
        this.telemetry = elemtry;
        this.intializewheels();
        this.initializeVuforia();

        this.initializeEyestalk();

    }

    public RelicRecoveryVuMark getPictograph() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        call("Pictograph", vuMark + "");
        return vuMark;
    }

    public void drive(double frontLeft, double frontRight, double rearLeft, double rearRight) {
        double greatest = Math.abs(frontLeft);
       if (Math.abs(frontRight)>greatest) greatest = Math.abs(frontRight);
       if (Math.abs(rearLeft)>greatest) greatest = Math.abs(rearLeft);
       if (Math.abs(rearRight)>greatest) greatest = Math.abs(rearRight);
       if (greatest>1){
           frontRight = frontRight / greatest;
           frontLeft = frontLeft / greatest;
           rearLeft = rearLeft / greatest;
           rearRight /= greatest;
       }
       this.wheelFrontRightMotor.setPower(frontRight);
       this.wheelFrontLeftMotor.setPower(frontLeft);
       this.wheelBackRightMotor.setPower(rearRight);
       this.wheelBackLeftMotor.setPower(rearLeft);
    }
    private void initializeArm(){
        this.leftHandServo = this.hardwareMap.get(Servo.class,"left_hand");
        this.rightHandServo = this.hardwareMap.get(Servo.class,"right_hand");
        this.leftHandServo.setPosition(0.5);
        this.rightHandServo.setPosition(0.5);
    }
    private void initializeEyestalk(){
        this.eyeStalkServo = this.hardwareMap.get(Servo.class, "eyestalk_servo");
        this.eyeStalkColorSensor = hardwareMap.get(ColorSensor.class, "eyestalk_sensor");
        this.eyeStalkDistanceSensor = hardwareMap.get(DistanceSensor.class, "eyestalk_sensor");
        this.eyeStalkServo.setPosition(0.5);
    }




    public void armUpDown(boolean up) {

    }

    public void armGrabRelease(boolean close) {

    }

    public void riaseEyestalk(){
        this.eyeStalkServo.setPosition(0.9);
    }

    public void lowerEyestalk(){
        this.eyeStalkServo.setPosition(0.1);
    }

    public void openHands(){
        this.leftHandServo.setPosition(0.9);
        this.rightHandServo.setPosition(0.9);
    }

    public void closeHands(){
        this.leftHandServo.setPosition(.45);
        this.rightHandServo.setPosition(.45);
    }

    public int eyestalkSense() {
        return COLOR_UNKNOWN;
    }


    private void intializewheels() {
        this.wheelFrontLeftMotor = this.hardwareMap.get(DcMotor.class, "front_left");
        this.wheelFrontRightMotor = this.hardwareMap.get(DcMotor.class, "front_right");
        this.wheelBackLeftMotor = this.hardwareMap.get(DcMotor.class, "back_left");
        this.wheelBackRightMotor = this.hardwareMap.get(DcMotor.class, "back_right");

        this.wheelFrontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        this.wheelBackLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        this.wheelFrontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        this.wheelBackRightMotor.setDirection(DcMotor.Direction.REVERSE);

        this.wheelFrontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelFrontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelBackRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelBackLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.wheelBackLeftMotor.setPower(0);
        this.wheelBackRightMotor.setPower(0);
        this.wheelFrontLeftMotor.setPower(0);
        this.wheelFrontRightMotor.setPower(0);
    }

    private void initializeVuforia() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "ARwYsgn/////AAAAGdnqVxXGf0FGosxoyPdT4+QytPJ1N6S60MJfFRlQ2F3BJCxLMYxkVonlquEdJiynVouUNxQCtG8RTiGlWsMYDbtiv+MK+k2fxCZNK7EFkIiJHWNE1C/BxphLZcTnDtUYvqPIQloGWRAULJz8kB/k7HrD1tV6yIGgchNZg598BPY1RFh7Z2q898lZCHLRNIlSIvznkrbrVg/b0hnwWQ2YFxaWv6QHGWjPJSGkbkqYDnOkG3BCZcvEDy71Fd7uqxTHN7Pfn0jsJHm4McgtbTzCZy1cG7pRruSfmRO6kfJK/z9mDBMeIvPDL+9SssxOZ7WtQjokfaO2l/JtlGigUkd7y6tXj1xy4XGE5JBdjMaFRSD4";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = relicTrackables.get(0);
        this.relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        this.relicTrackables.activate();

    }
    public void call(String caption, String message) {
        telemetry.addData(caption, message);
        telemetry.update();
    }
}