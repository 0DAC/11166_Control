package org.firstinspires.ftc.teamcode.auto;

//@Autonomous(name="BlueAuto", group="DogeCV")

/*
public class AutoControlSystem extends LinearOpMode {

    // Hardware declaration
    private DcMotor flDrive;
    private DcMotor frDrive;
    private DcMotor rlDrive;
    private DcMotor rrDrive;
    private DcMotor lintake_motor;
    private DcMotor rintake_motor;
    private Servo clamp_servo;

    double pos = 0;

    // Detector object
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException{

        // Start wakeLock
        //wakeLock.acquire();

        flDrive = hardwareMap.get(DcMotor.class, "left_1");
        frDrive = hardwareMap.get(DcMotor.class, "right_1");
        rlDrive = hardwareMap.get(DcMotor.class, "left_2");
        rrDrive = hardwareMap.get(DcMotor.class, "right_2");
        lintake_motor = hardwareMap.get(DcMotor.class, "intake_1");
        rintake_motor = hardwareMap.get(DcMotor.class, "intake_2");
        clamp_servo = hardwareMap.get(Servo.class, "clamp_servo");

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

        clamp_servo.setPosition(1000);

        int val = 8;
        // move forwards (start with back facing the wall)
        encoderDrive(.5, val, val, val, val);

        // lower the intake claws
        encoderDrive(.5, 16, 16);
        clamp_servo.setPosition(120);

        encoderDrive(.5, -val, -val, -val, -val);
        // turn right
        encoderDrive(.5, 16, 6-16, 16, -16);
        // move forward
        encoderDrive(.5, val, val, val, val);
    }

    public void encoderDrive ( double speed, int flDrivePos, int frDrivePos, int rlDrivePos, int rrDrivePos) {

        flDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rlDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rrDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rlDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rrDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        flDrivePos = flDrivePos * (1125 / ((42 / 35) * (32))) + flDrive.getCurrentPosition();
        frDrivePos = frDrivePos * (1125 / ((42 / 35) * (32))) + frDrive.getCurrentPosition();
        rlDrivePos = rlDrivePos * (1125 / ((42 / 35) * (32))) + rlDrive.getCurrentPosition();
        rrDrivePos = rrDrivePos * (1125 / ((42 / 35) * (32))) + rrDrive.getCurrentPosition();

        flDrive.setTargetPosition(flDrivePos);
        frDrive.setTargetPosition(frDrivePos);
        rlDrive.setTargetPosition(rlDrivePos);
        rrDrive.setTargetPosition(rrDrivePos);

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
        lintake_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rintake_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l_servo_pos = l_servo_pos* (1125 / ((42 / 35) * (32))) + flDrive.getCurrentPosition();
        r_servo_pos = r_servo_pos * (1125 / ((42 / 35) * (32))) + frDrive.getCurrentPosition();

        lintake_motor.setTargetPosition(l_servo_pos);
        rintake_motor.setTargetPosition(r_servo_pos);

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

 */