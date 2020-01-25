package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class CraneLift {
    private DcMotor left, right; // vertical extension
    private Servo extender, grabber, turner;

    //  vertical extension
    private final int MOVE_INCREMENTS = 50;
    private final int MAX_POSITION = 250;
    private final double MOVE_POWER = 0.5;

    // horizontal extension
    private double h_curr_pos = 0;
    private final double HSPEED = 0.02;

    // grabber rotator
    private double rotator_pos = 0.5;
    private double ROTATOR_SPEED = 0.1;

    // stone grabber
    private final double GRABBER_CLOSED = 0.65,
                    GRABBER_OPEN = 0.07;
    private int state = 0; // 0 = open, 1 = closed

    // stone turner
    private final double TURN_POS = 0.5;

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotor.class, SystemConfig.right_lift);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        extender = hardwareMap.get(Servo.class, SystemConfig.lift_extend);
        grabber = hardwareMap.get(Servo.class, SystemConfig.lift_grabber);
        turner = hardwareMap.get(Servo.class, SystemConfig.lift_turner);

        turner.setPosition(TURN_POS);
    }

    // vertical lift
    public void vextend() {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setTargetPosition(Math.min(left.getCurrentPosition()+MOVE_INCREMENTS,MAX_POSITION));
        left.setPower(MOVE_POWER);

        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setTargetPosition(Math.min(right.getCurrentPosition()+MOVE_INCREMENTS, MAX_POSITION));
        right.setPower(MOVE_POWER);

        double time = System.currentTimeMillis();
        while (left.isBusy() && right.isBusy() && (System.currentTimeMillis()-time <= 1000)) {}

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setPower(0);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setPower(0);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void vretract() {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setTargetPosition(Math.max(left.getCurrentPosition()-MOVE_INCREMENTS, 0));
        left.setPower(MOVE_POWER);

        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setTargetPosition(Math.max(right.getCurrentPosition()-MOVE_INCREMENTS, 0));
        right.setPower(MOVE_POWER);

        double time = System.currentTimeMillis();
        while (left.isBusy() && right.isBusy() && (System.currentTimeMillis()-time <= 1000)) {}

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setPower(0);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setPower(0);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void vstop() {
        left.setPower(0);
        right.setPower(0);
    }

    // extender
    public void hextend() {
        h_curr_pos = Math.min(h_curr_pos + HSPEED, 2400);
        extender.setPosition(h_curr_pos/2400);
    }

    public void hretract() {
        h_curr_pos = Math.max(h_curr_pos - HSPEED, 0);
        extender.setPosition(h_curr_pos/2400);
    }
    public void hstop() {
    }

    // stone grabber
    public void grab_stone() {
        grabber.setPosition(GRABBER_CLOSED);
    }

    public void drop_stone() {
        grabber.setPosition(GRABBER_OPEN);
    }

    public void toggle_grabber() {
        // if it's currently open, close
        if (grabber.getPosition() == 0) grab_stone();
        else drop_stone();
    }

    public void rotate_grabber_ccw() {
        rotator_pos = Math.max(0, rotator_pos-ROTATOR_SPEED);
        turner.setPosition(rotator_pos);
    }

    public void rotate_grabber_cw() {
        rotator_pos = Math.min(1, rotator_pos+ROTATOR_SPEED);
        turner.setPosition(rotator_pos);
    }

}
