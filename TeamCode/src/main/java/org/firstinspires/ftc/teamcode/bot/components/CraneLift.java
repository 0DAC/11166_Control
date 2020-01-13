package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;

public class CraneLift {
    private DcMotor left, right; // vertical extension
    private Servo extender, grabber, turner;
    private final double VSPEED = 0.2;

    // horizontal extension
    private int h_curr_pos = 1;
    private final int HSPEED = 1;

    // stone grabber
    private final double GRABBER_CLOSED = 0.1,
                    GRABBER_OPEN = 0.09;
    private int state = 0; // 0 = open, 1 = closed

    // stone turner
    private final double TURN_POS = 0.5;

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotor.class, SystemConfig.right_lift);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        extender = hardwareMap.get(Servo.class, SystemConfig.lift_extend);
        grabber = hardwareMap.get(Servo.class, SystemConfig.lift_grabber);
        turner = hardwareMap.get(Servo.class, SystemConfig.lift_turner);

        turner.setPosition(TURN_POS);
    }

    // vertical lift
    public void vextend() {
        left.setPower(-VSPEED);
        right.setPower(-VSPEED);
    }

    public void vretract() {
        left.setPower(VSPEED);
        right.setPower(VSPEED);
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

}
