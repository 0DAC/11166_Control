package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SystemConfig;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;

public class CraneLift {
    private DcMotorEx left, right; // vertical extension

    public static final double P = 15;//29;
    public static final double I = 0;
    public static final double D = 0.5;//3.78; // TODO: test this
    public static final double leftF = 11.62;
    public static final double rightF = 11.80;

    private Servo extender, grabber, turner;
    private Telemetry t;

    //  vertical extension
    private final int VMIN_POSITION= 250;
    private final int VMAX_POSITION = 10;
    private final int VMOVE_INCREMENT = 50;
    private final double VMOVE_UP_POWER = 1;
    private final double VMOVE_DOWN_POWER = 1;
    private int VRIGHT_POS, VLEFT_POS;

    // horizontal extension
    private final double H_OUT = 0.65;
    private final double H_IN = 0.25;
    private boolean extended = false;

    // grabber rotator
    private double ROTATOR_IN = 0.1;
    private double ROTATOR_OUT = 0.5;
    private boolean rotator_out = true;

    // stone grabber
    private final double GRABBER_CLOSED = 0.14,
                    GRABBER_OPEN = 0.8;
    private int grabber_state = 0; // 0 = open, 1 = closed

    // put nine different heights for now
    public CraneLift(HardwareMap hardwareMap, Telemetry t) {
        this.t = t;

        left = hardwareMap.get(DcMotorEx.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotorEx.class, SystemConfig.right_lift);

        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        PIDFCoefficients pidfLeftOrig = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightOrig = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
        PIDFCoefficients pidfLeftNew = new PIDFCoefficients(P, I, D, leftF);
        left.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftNew);
        PIDFCoefficients pidfRightNew = new PIDFCoefficients(P, I, D, rightF);
        right.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightNew);

        // re-read coefficients and verify change.
        PIDFCoefficients pidfLeftModified = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightModified = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //set dummy position before changing runmode
        left.setTargetPosition(0);
        right.setTargetPosition(0);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left.setZeroPowerBehavior(FLOAT);
        right.setZeroPowerBehavior(FLOAT);

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        extender = hardwareMap.get(Servo.class, SystemConfig.lift_extend);
        grabber = hardwareMap.get(Servo.class, SystemConfig.lift_grabber);
        turner = hardwareMap.get(Servo.class, SystemConfig.lift_turner);

        // init servos to these values
        turner.setPosition(ROTATOR_OUT);
        extender.setPosition(H_IN);
        grabber.setPosition(GRABBER_OPEN);
    }

    // vertical lift
    public void vextend() {
        //if (VLEFT_POS > VMAX_POSITION || VRIGHT_POS > VMAX_POSITION) return;
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(VMOVE_UP_POWER);

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(VMOVE_UP_POWER);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        t.addData("Left Lift:", VLEFT_POS);
        t.addData("Right Lift:", VRIGHT_POS);
        t.update();
    }

    public void vretract() {
        if (VLEFT_POS < VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left.setPower(-VMOVE_DOWN_POWER);

            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setPower(-VMOVE_DOWN_POWER);

            VLEFT_POS = left.getCurrentPosition();
            VRIGHT_POS = right.getCurrentPosition();
        }
        else {
            left.setPower(0);
            right.setPower(0);
            left.setZeroPowerBehavior(BRAKE);
            right.setZeroPowerBehavior(BRAKE);
        }
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(-VMOVE_DOWN_POWER);

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(-VMOVE_DOWN_POWER);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        t.addData("Left Lift:", VLEFT_POS);
        t.addData("Right Lift:", VRIGHT_POS);
        t.update();
    }

    public void vstop() {

//        left.setPower(0);
//        right.setPower(0);
//        left.setZeroPowerBehavior(BRAKE);
//        right.setZeroPowerBehavior(BRAKE);
        left.setTargetPosition(VLEFT_POS);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setTargetPosition(VRIGHT_POS);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        t.addData("Left Lift:", VLEFT_POS);
        t.addData("Right Lift:", VRIGHT_POS);
        t.update();


/*        double time = System.currentTimeMillis();
        while ((left.isBusy() || right.isBusy()) && ((System.currentTimeMillis()-time <= 500))) {
        }

        left.setZeroPowerBehavior(BRAKE);
        left.setPower(0);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        right.setZeroPowerBehavior(BRAKE);
        right.setPower(0);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
  */
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

    public void toggle_rotator() {
        if (rotator_out) turner.setPosition(ROTATOR_IN);
        else turner.setPosition(ROTATOR_OUT);
        rotator_out = !rotator_out;
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
