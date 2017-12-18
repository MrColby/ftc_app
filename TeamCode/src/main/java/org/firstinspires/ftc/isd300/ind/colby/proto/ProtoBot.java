package org.firstinspires.ftc.isd300.ind.colby.proto;

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
 * Created by colbyl on 12/2/2017.
 */

public class ProtoBot {

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private BNO055IMU imu;



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

    public ProtoBot(HardwareMap pHardwareMap, Telemetry pTelemetry) {
        this.hardwareMap = pHardwareMap;
        this.telemetry = pTelemetry;
        this.initializeWheels();
        this.initializeArm();
        this.initializeEyestalk();
        this.initializeVuforia();
        initializeImu();
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

    public void raiseEyestalk() {

        this.eyeStalkServo.setPosition(.75);
    }

    public void lowerEyestalk() {
        this.eyeStalkServo.setPosition(.1);
    }

    public int getEyestalkColor() {
        double SCALE_FACTOR = 255;
        float hsvValues[] = {0F, 0F, 0F};
        Color.RGBToHSV((int) (eyeStalkColorSensor.red() * SCALE_FACTOR),
                (int) (eyeStalkColorSensor.green() * SCALE_FACTOR),
                (int) (eyeStalkColorSensor.blue() * SCALE_FACTOR),
                hsvValues);
        message("Color sensor", hsvValues[0]+", " + hsvValues[1] + ", " + hsvValues[2]);
        if (hsvValues[0] > hsvValues[1] && hsvValues[0] > hsvValues[2]) return COLOR_RED;
        else if (hsvValues[1] > hsvValues[0] && hsvValues[1] > hsvValues[2]) return COLOR_GREEN;
        else if (hsvValues[2] > hsvValues[0] && hsvValues[2] > hsvValues[1]) return COLOR_RED;
        return COLOR_UNKNOWN;

    }

    public void drive(double frontLeft, double frontRight, double rearLeft, double rearRight) {

        double max = Math.abs(frontLeft);
        if (Math.abs(frontRight)>max) max = Math.abs(frontRight);
        if (Math.abs(rearLeft)>max) max = Math.abs(rearLeft);
        if (Math.abs(rearRight)>max) max = Math.abs(rearRight);
        if (max>1) {
            frontLeft/=max; frontRight/=max; rearLeft/=max; rearRight/=max;
        }

        this.wheelFrontLeftMotor.setPower(frontLeft);
        this.wheelFrontRightMotor.setPower(frontRight);
        this.wheelBackLeftMotor.setPower(rearLeft);
        this.wheelBackRightMotor.setPower(rearRight);



    }

    public Orientation getGyroAngles() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        //this.message("imu:",angles.firstAngle + ", " + angles.secondAngle + ", " + angles.thirdAngle);
        return angles;
    }

    public void openHands() {
        // to open more, decrease left and increase right
        double percentOpen = 0.7;
        this.leftHandServo.setPosition(1-percentOpen);
        this.rightHandServo.setPosition(percentOpen);
    }
        // Changed only right hand from .9 to .45 and vice versa
    public void closeHands() {
        // to close more, increase left and decrease right
        double percentClosed = 0.52;
        this.leftHandServo.setPosition(percentClosed);
        this.rightHandServo.setPosition(1-percentClosed);
    }
    /*public void drive() {
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightY = gamepad1.right_stick_y;
        final double frontLeft = r * Math.cos(robotAngle) - rightY;
        final double frontRight = r * Math.sin(robotAngle) + rightY;
        final double backLeft = r * Math.sin(robotAngle) - rightY;
        final double backRight = r * Math.cos(robotAngle) + rightY;

        this.wheelFrontLeftMotor.setPower(frontLeft);
        this.wheelFrontRightMotor.setPower(frontRight);
        this.wheelBackLeftMotor.setPower(backLeft);
        this.wheelBackRightMotor.setPower(backRight);

    }*/

    private void initializeWheels() {
        this.wheelFrontLeftMotor  = this.hardwareMap.get(DcMotor.class, "front_left");
        this.wheelFrontRightMotor  = this.hardwareMap.get(DcMotor.class, "front_right");
        this.wheelBackLeftMotor     = this.hardwareMap.get(DcMotor.class, "back_left");
        this.wheelBackRightMotor     = this.hardwareMap.get(DcMotor.class, "back_right");

        this.wheelFrontLeftMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.wheelFrontRightMotor.setDirection(DcMotor.Direction.REVERSE);//
        this.wheelBackLeftMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        this.wheelBackRightMotor.setDirection(DcMotor.Direction.REVERSE);//

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
        this.leftHandServo.setPosition(0.5);
        this.rightHandServo.setPosition(0.5);
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

    private void initializeImu() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 0);
    }

    public void message(String caption, String msg) {
        this.telemetry.addData(caption, msg);
        this.telemetry.update();
    }

}
