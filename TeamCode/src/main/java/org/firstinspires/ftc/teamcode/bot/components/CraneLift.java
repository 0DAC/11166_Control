package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SystemConfig;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

public class CraneLift {
    private DcMotor left, right; // vertical extension
    private Servo extender, grabber, turner;

    //  vertical extension
    private final int VMIN_POSITION= -250;
    private final int VMAX_POSITION = 10;
    private final int VMOVE_INCREMENT = 50;
    private final double VMOVE_UP_POWER = 0.8;
    private final double VMOVE_DOWN_POWER = 0.1;
    private int VRIGHT_POS, VLEFT_POS;

    // horizontal extension
    private final double H_OUT = 0.65;
    private final double H_IN = 0.25;
    private boolean extended = false;

    // grabber rotator
    private double rotator_pos = 0.7;
    private double ROTATOR_SPEED = 0.1;

    // stone grabber
    private final double GRABBER_CLOSED = 0.14,
                    GRABBER_OPEN = 0.8;
    private int grabber_state = 0; // 0 = open, 1 = closed

    // stone turner
    private final double TURN_POS = 0.5;

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotor.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotor.class, SystemConfig.right_lift);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setZeroPowerBehavior(BRAKE);
        right.setZeroPowerBehavior(BRAKE);

        left.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.FORWARD);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        extender = hardwareMap.get(Servo.class, SystemConfig.lift_extend);
        grabber = hardwareMap.get(Servo.class, SystemConfig.lift_grabber);
        turner = hardwareMap.get(Servo.class, SystemConfig.lift_turner);

        turner.setPosition(TURN_POS);
        extender.setPosition(H_IN);
        grabber.setPosition(GRABBER_OPEN);
    }

    // vertical lift
    public void vextend() {
        //if (VLEFT_POS > VMAX_POSITION || VRIGHT_POS > VMAX_POSITION) return;
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(-VMOVE_UP_POWER);

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(-VMOVE_UP_POWER);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();
    }

    public void vretract() {
        //if (VLEFT_POS > VMAX_POSITION || VRIGHT_POS > VMAX_POSITION) return;
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(VMOVE_DOWN_POWER);

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(VMOVE_DOWN_POWER);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();
    }

    public void vstop() {
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setTargetPosition(VLEFT_POS);
        left.setPower(VMOVE_UP_POWER);

        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setTargetPosition(VRIGHT_POS);
        right.setPower(VMOVE_UP_POWER);

        double time = System.currentTimeMillis();
        while ((left.isBusy() || right.isBusy()) && ((System.currentTimeMillis()-time <= 500))) {
        }

        left.setZeroPowerBehavior(BRAKE);
        left.setPower(0);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setZeroPowerBehavior(BRAKE);
        right.setPower(0);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // extender
    public void hextend() {
        extender.setPosition(H_OUT);
    }

    public void hretract() {
        extender.setPosition(H_IN);
    }
    public void htoggle() {
        if (extended) hretract();
        else hextend();
        extended = !extended;
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
        if (grabber_state == 0) grab_stone();
        else drop_stone();
        grabber_state = 1- grabber_state;
    }

    public void rotate_grabber_ccw() {
        rotator_pos = Math.max(0, rotator_pos-ROTATOR_SPEED);
        turner.setPosition(rotator_pos);
    }

    public void rotate_grabber_cw() {
        rotator_pos = Math.min(1, rotator_pos+ROTATOR_SPEED);
        turner.setPosition(rotator_pos);
    }

    /**
     * Composite grabbing action
     */
    public void deploy() {
        vextend();
        vextend();
        vretract();
        vretract();

        hextend();
        grab_stone();
        hretract();

        vextend();
        vextend();
        vretract();
        vretract();
    }

}
