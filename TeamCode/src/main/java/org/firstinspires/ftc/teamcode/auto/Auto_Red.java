package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto_Red", group="DogeCV")
public class Auto_Red extends LinearOpMode {

    // Hardware declaration
    private DcMotor flDrive;
    private DcMotor frDrive;
    private DcMotor rlDrive;
    private DcMotor rrDrive;
    private DcMotor lintake_motor;
    private DcMotor rintake_motor;
    private Servo l_foundation, r_foundation;

    double pos = 0;

    // Detector object
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException{

        // Start wakeLock
        //wakeLock.acquire();

        telemetry.setAutoClear(false);
        flDrive = hardwareMap.get(DcMotor.class, "left_1");
        frDrive = hardwareMap.get(DcMotor.class, "right_1");
        rlDrive = hardwareMap.get(DcMotor.class, "left_2");
        rrDrive = hardwareMap.get(DcMotor.class, "right_2");
        lintake_motor = hardwareMap.get(DcMotor.class, "intake_1");
        rintake_motor = hardwareMap.get(DcMotor.class, "intake_2");
        l_foundation = hardwareMap.get(Servo.class, "left_foundation");
        r_foundation = hardwareMap.get(Servo.class, "right_foundation");

        flDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rlDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rrDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        flDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rlDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rrDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rlDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rrDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flDrive.setDirection(DcMotor.Direction.FORWARD);
        frDrive.setDirection(DcMotor.Direction.REVERSE);
        rlDrive.setDirection(DcMotor.Direction.FORWARD);
        rrDrive.setDirection(DcMotor.Direction.REVERSE);

        // set servos to up
        telemetry.addData("Status", "Moving Servos Up...");
        telemetry.update();
        l_foundation.setPosition(.5);
        r_foundation.setPosition(.7);

        waitForStart();

        int val_0 = 15; //fine
        telemetry.addData("Status", "Moving Forward...");
        telemetry.update();
        encoderDrive(.1, val_0, val_0, val_0, val_0);
        telemetry.addData("Status", "Adjusting Trajectory...");
        telemetry.update();
        int val_1 = -7;
        encoderDrive(.2, -val_1, val_1, -val_1, val_1);
        int val_9 = 20;
        encoderDrive(.2, val_9, val_9, val_9, val_9);
        encoderDrive(.2, val_1, -val_1, val_1, -val_1);
        telemetry.addData("Status", "Moving Forward...");
        telemetry.update();
        int val_7 = 10;
          encoderDrive(.2, val_7, val_7, val_7, val_7);
        telemetry.addData("Status", "Lowering Servos...");
        telemetry.update();
        l_foundation.setPosition(1.0);
        r_foundation.setPosition(.1);
        sleep(500);
        telemetry.addData("Status", "Backing Up...");
        telemetry.update();
        int val_8 = 46;
        encoderDrive(.05, -val_8, -val_8, -val_8, -val_8);
        telemetry.addData("Status", "Moving Servos Up...");
        telemetry.update();
        l_foundation.setPosition(.5);
        r_foundation.setPosition(.7);
        /*telemetry.addData("Status", "Compensating...");
        telemetry.update();
        int val_2 = 7;
        encoderDrive(.05, -val_2, val_2, -val_2, val_2);
        telemetry.addData("Status", "Turning...");
        telemetry.update();
        int val_3 = 8;
        encoderDrive(.05, -val_3, val_3, -val_3, val_3);
        telemetry.addData("Status", "Backing Up...");
        telemetry.update();
        int val_4 = 10;
        encoderDrive(.05, -val_4, -val_4, -val_4, -val_4);
        */
        sleep(10000000);


        /*l_foundation.setPosition(1200);
        l_foundation.setPosition(0);

        int val = 15;
        // move forwards (start with back facing the wall)
        encoderDrive(.5, val, val, val, val);

        // extend the intake claws
        l_foundation.setPosition(2047);
        r_foundation.setPosition(1000);

        encoderDrive(.5, -val, -val, -val, -val);
        // turn left
        encoderDrive(.5, -16, 16, -16, 16);
        // move forward
        encoderDrive(.5, val, val, val, val);*/
    }

    public void encoderDrive ( double speed, int flDrivePos, int frDrivePos, int rlDrivePos, int rrDrivePos) {

        flDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rlDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rrDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        flDrivePos = flDrivePos * (1125 / ((42 / 35) * (32))) + flDrive.getCurrentPosition();
        frDrivePos = frDrivePos * (1125 / ((42 / 35) * (32))) + frDrive.getCurrentPosition();
        rlDrivePos = rlDrivePos * (1125 / ((42 / 35) * (32))) + rlDrive.getCurrentPosition();
        rrDrivePos = rrDrivePos * (1125 / ((42 / 35) * (32))) + rrDrive.getCurrentPosition();

        flDrive.setTargetPosition(flDrivePos);
        frDrive.setTargetPosition(frDrivePos);
        rlDrive.setTargetPosition(rlDrivePos);
        rrDrive.setTargetPosition(rrDrivePos);

        flDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rlDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rrDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        flDrive.setPower(speed);
        frDrive.setPower(speed);
        rlDrive.setPower(speed);
        rrDrive.setPower(speed);

        while (flDrive.isBusy() || frDrive.isBusy() || rlDrive.isBusy() || rrDrive.isBusy()) {

        }

        flDrive.setPower(0);
        frDrive.setPower(0);
        rlDrive.setPower(0);
        rrDrive.setPower(0);

        flDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rlDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rrDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void encoderDrive ( double speed, int l_servo_pos, int r_servo_pos) {

        lintake_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rintake_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        l_servo_pos = l_servo_pos* (1125 / ((42 / 35) * (32))) + flDrive.getCurrentPosition();
        r_servo_pos = r_servo_pos * (1125 / ((42 / 35) * (32))) + frDrive.getCurrentPosition();

        lintake_motor.setTargetPosition(l_servo_pos);
        rintake_motor.setTargetPosition(r_servo_pos);

        lintake_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rintake_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lintake_motor.setPower(speed);
        rintake_motor.setPower(speed);

        while (lintake_motor.isBusy() || rintake_motor.isBusy()) {

        }

        lintake_motor.setPower(0);
        rintake_motor.setPower(0);

        lintake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rintake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}