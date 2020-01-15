package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HOMAR.internal.drivetrain.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.SystemConfig;

public class Robot {
    //private FinishableIntegratedController controller;
    public MecanumDrivetrain drive;
    private Servo l_foundation, r_foundation;
    private Intake intake;
    private CraneLift lift;
    //private BNO055IMUImpl gyro;

    private final long TURN_90_TIME = 550;

    public static double  RIGHT_FOUNDATION_UP = 1,
            RIGHT_FOUNDATION_DOWN  = 0.195,
            LEFT_FOUNDATION_UP   = 0.1,
            LEFT_FOUNDATION_DOWN = 0.739;

    public static int TICKS_PER_REVOLUTION = 383;

    public Robot(HardwareMap hmp) {
        // configure motors
        DcMotor frontLeft = hmp.get(DcMotor.class, "driveFrontLeft");
        DcMotor frontRight = hmp.get(DcMotor.class, "driveFrontRight");
        DcMotor backLeft = hmp.get(DcMotor.class, "driveBackLeft");
        DcMotor backRight = hmp.get(DcMotor.class, "driveBackRight");

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        drive = new MecanumDrivetrain(new DcMotor[]{frontLeft, frontRight, backLeft, backRight});

        // config foundation servos

        l_foundation = hmp.get(Servo.class, SystemConfig.left_foundation_servo);
        r_foundation = hmp.get(Servo.class, SystemConfig.right_foundation_servo);

        // configure foundation grabber servos

        intake = new Intake(hmp);

        // configure crane lift
        lift = new CraneLift(hmp);

        // configure outerIntake motors
        /*gyro = hmp.get(BNO055IMUImpl.class, "gyro");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();  //Figure out why the naive one doesn't have a public constructor
        gyro.initialize(parameters);
        while (!gyro.isGyroCalibrated());

        controller =    new FinishableIntegratedController(new IntegratingGyroscopeSensor(gyro),
                        new PIDController(1, 1, 1), // default values to 1, will tune later
                        new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));

        drive = new HeadingableMecanumDrivetrain(new DcMotor[]{frontLeft, frontRight, backLeft, backRight},
                controller);*/
     }

     public void dist_drive(double distance, double angle, double power) {
        drive.drive_distance(distance, angle, power);
     }

     public void intake() {
         intake.intake();
     }
     public void spit() {
         intake.spit();
    }
     public void stop_intake() {
         intake.stop();
    }

    public void turn_90_ccw(double speed) {
        drive.setRotation(speed);
        pause(TURN_90_TIME);
        drive.setRotation(0);
    }
    public void turn_90_cw(double speed) {
        drive.setRotation(-speed);
        pause(TURN_90_TIME);
        drive.setRotation(0);
    }

    public void raise_foundations() {
        l_foundation.setPosition(LEFT_FOUNDATION_UP);
        r_foundation.setPosition(RIGHT_FOUNDATION_UP);
    }
    public void lower_foundations() {
        l_foundation.setPosition(LEFT_FOUNDATION_DOWN);
        r_foundation.setPosition(RIGHT_FOUNDATION_DOWN);
    }

    public void vraise_lift() { lift.vextend(); }
    public void vlower_lift() { lift.vretract(); }
    public void vstop() {lift.vstop();}

    public void hraise_lift() { lift.hextend(); }
    public void hlower_lift() { lift.hretract();}
    public void hstop() {lift.hstop();}

    public void toggle_grabber() {
        lift.toggle_grabber();
    }

     public void xbox_drive(double move_x, double move_y, double turn_x) {
         double course = Math.atan2(-move_y, move_x) - Math.PI/2;
         double velocity = Math.hypot(move_x, move_y);
         double rotation = -turn_x;
         power_drive(course, velocity, rotation);
     }

     public void encoder_drive(double speed, int[] speeds) {
        for (int i = 0; i < drive.motors.length; i ++) {
            drive.motors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drive.motors[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drive.motors[i].setTargetPosition(speeds[i]);
            drive.motors[i].setPower(speed);
        }
        while (true) {
            for (int i = 0; i < drive.motors.length; i ++)
                if (drive.motors[i].isBusy()) continue;
            break;
        }
         for (int i = 0; i < drive.motors.length; i ++) {
             drive.motors[i].setPower(0);
             drive.motors[i].setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         }
     }

    /**
     * Mechanum drive by power vector
     * @param course: angle repective to positive x-axis
     * @param velocity: magnitude
     * @param rotation: rotation speed around center of mass
     */
     public void power_drive(double course, double velocity, double rotation) {
         drive.setCourse(course);
         drive.setVelocity(velocity);
         drive.setRotation(rotation);
     }

     public void stop() {
         power_drive(0, 0, 0);
     }

     public void pause(long time) {
         double t = System.currentTimeMillis();
         while (System.currentTimeMillis()-t < time);
     }

     public void print_encoder_vals(Telemetry t) {
         t.addData("Front Left:", drive.motors[0].getCurrentPosition());
         t.addData("Front Right:", drive.motors[1].getCurrentPosition());
         t.addData("Back Left:", drive.motors[2].getCurrentPosition());
         t.addData("Back Right:", drive.motors[3].getCurrentPosition());
     }

     //public PIDTuner getPIDTuner(Gamepad pad, Telemetry t) { return new PIDTuner(drive, (PIDController) controller.algorithm, pad, t); }
}