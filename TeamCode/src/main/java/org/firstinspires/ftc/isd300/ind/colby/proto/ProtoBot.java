package org.firstinspires.ftc.isd300.ind.colby.proto;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by colbyl on 12/2/2017.
 */

public class ProtoBot {

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private Gamepad gamepad1;
    private Gamepad gamepad2;

    private DcMotor wheelFrontRightMotor;
    private DcMotor  wheelFrontLeftMotor;
    private DcMotor  wheelBackLeftMotor;
    private DcMotor  wheelBackRightMotor;

    private DcMotor armMotor;
    private Servo leftHandServo;
    private Servo rightHandServo;

    private Servo eyeStalkServo;

    private ColorSensor eyeStalkColorSensor;
    private DistanceSensor eyeStalkDistanceSensor;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;

    public static final int COLOR_UNKNOWN = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_BLUE = 3;

    public ProtoBot(HardwareMap pHardwareMap, Telemetry pTelemetry, Gamepad pGamepad1, Gamepad pGamepad2) {
        this.hardwareMap = pHardwareMap;
        this.telemetry = pTelemetry;
        this.gamepad1 = pGamepad1;
        this.gamepad2 = pGamepad2;
        this.initializeWheels();
        this.initializeArm();
        this.initializeEyestalk();
        this.initializeVuforia();
    }

    public RelicRecoveryVuMark getPictograph() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        //message("Pictograph", vuMark + "");
        return vuMark;
    }

    public double getEyestalkDistance() {
        //message("Distance sensor", eyeStalkDistanceSensor.getDistance(DistanceUnit.CM) + "");
        return eyeStalkDistanceSensor.getDistance(DistanceUnit.CM);
    }

    public int getEyestalkColor() {
        double SCALE_FACTOR = 255;
        float hsvValues[] = {0F, 0F, 0F};
        Color.RGBToHSV((int) (eyeStalkColorSensor.red() * SCALE_FACTOR),
                (int) (eyeStalkColorSensor.green() * SCALE_FACTOR),
                (int) (eyeStalkColorSensor.blue() * SCALE_FACTOR),
                hsvValues);
        //message("Color sensor", hsvValues[0]+", " + hsvValues[1] + ", " + hsvValues[2]);
        if (hsvValues[0] > hsvValues[1] && hsvValues[0] > hsvValues[2]) return COLOR_RED;
        else if (hsvValues[1] > hsvValues[0] && hsvValues[1] > hsvValues[2]) return COLOR_GREEN;
        else if (hsvValues[2] > hsvValues[0] && hsvValues[2] > hsvValues[1]) return COLOR_RED;
        return COLOR_UNKNOWN;

    }

    public void drive() {
        double r = Math.hypot(this.gamepad1.left_stick_x, this.gamepad1.left_stick_y);
        double robotAngle = Math.atan2(this.gamepad1.left_stick_y, this.gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = this.gamepad1.right_stick_x;
        double v1 = r * Math.cos(robotAngle) + rightX;
        double v2 = r * Math.sin(robotAngle) - rightX;
        double v3 = r * Math.sin(robotAngle) + rightX;
        double v4 = r * Math.cos(robotAngle) - rightX;

        wheelFrontLeftMotor.setPower(v1);
        wheelFrontRightMotor.setPower(v2);
        wheelBackLeftMotor.setPower(v3);
        wheelBackRightMotor.setPower(v4);
    }

    private void initializeWheels() {
        this.wheelFrontLeftMotor  = this.hardwareMap.get(DcMotor.class, "front_left");
        this.wheelFrontRightMotor  = this.hardwareMap.get(DcMotor.class, "front_right");
        this.wheelBackLeftMotor     = this.hardwareMap.get(DcMotor.class, "back_left");
        this.wheelBackRightMotor     = this.hardwareMap.get(DcMotor.class, "back_right");

        this.wheelFrontLeftMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.wheelFrontRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.wheelBackLeftMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.wheelBackRightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        this.wheelFrontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelFrontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelBackLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wheelBackRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.wheelFrontLeftMotor.setPower(0);
        this.wheelFrontRightMotor.setPower(0);
        this.wheelBackLeftMotor.setPower(0);
        this.wheelBackRightMotor.setPower(0);

    }

    private void initializeArm() {
        // until we get another hub, we can't have a fifth motor
        //this.armMotor = this.hardwareMap.get(DcMotor.class, "arm");
        this.leftHandServo = this.hardwareMap.get(Servo.class, "left_hand");
        this.rightHandServo = this.hardwareMap.get(Servo.class, "right_hand");
    }

    private void initializeEyestalk() {
        this.eyeStalkServo  = this.hardwareMap.get(Servo.class, "eyestalk_servo");
        this.eyeStalkColorSensor = hardwareMap.get(ColorSensor.class, "eyestalk_sensor");
        this.eyeStalkDistanceSensor = hardwareMap.get(DistanceSensor.class, "eyestalk_sensor");
        this.eyeStalkServo.setPosition(0.5);
    }

    private void initializeVuforia() {
 /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
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

    private void message(String caption, String msg) {
        this.telemetry.addData(caption, msg);
        this.telemetry.update();
    }

}
