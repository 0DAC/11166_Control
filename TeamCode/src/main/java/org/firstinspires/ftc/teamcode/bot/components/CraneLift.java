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

    public static final double Up_Pos_P = 20;

    public static final double Down_Pos_P = 4;

    public static final double P = 4;
    public static final double I = 0;
    public static final double D = 0;
    public static final double F = 10.6;

    private Servo extender, grabber, turner, capper;
    private Telemetry t;

    //  vertical extension
    private final int VMIN_POSITION= 150;
    private final double VMOVE_UP_POWER = 1;
    private final double VMOVE_DOWN_POWER = .4;

    int VRIGHT_POS, VLEFT_POS, VLEFT_TARGET, VRIGHT_TARGET;

    int height = 0;

    int ticksPerBlock;

    // horizontal extension
    private final double H__AUTO_OUT = .75;
    private final double H_TELE_OUT = .42;
    private final double H_MAX_OUT = .9;
    private final double H_GRABBER_BOT = .4;
    private final double H_CAPSTONE = 0.33;
    private final double H_IN = 0.25;
    private final double H_ENGAGE = 0.28;
    boolean H_FULLY_EXTENDED = false;
    boolean IS_STATIONARY = false;

    //Capstone Holder Values
    private double CAPSTONE_UP = 0.73;
    private double CAPSTONE_DOWN = 0.38;

    // grabber rotator
    //TODO: Tune these values for the capper
    private double ROTATOR_CAPSTONE = 0.5;
    private double ROTATOR_IN = 0.16;
    private double ROTATOR_OUT = 0.85;
    boolean rotator_out = false;

    // stone grabber
    private final double GRABBER_CLOSED = 0.1,
                    GRABBER_OPEN = .88;
    int grabber_state = 0; // 0 = open, 1 = closed
    int capper_state = 0; // 0 = closed, 1 = open

    public CraneLift(HardwareMap hardwareMap, Telemetry t) {
        this.t = t;

        left = hardwareMap.get(DcMotorEx.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotorEx.class, SystemConfig.right_lift);

        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);

        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        PIDFCoefficients pidfLeftOrig = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightOrig = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
        PIDFCoefficients pidfLeftNew = new PIDFCoefficients(P, I, D, F);
        left.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftNew);
        PIDFCoefficients pidfRightNew = new PIDFCoefficients(P, I, D, F);
        right.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightNew);

        // re-read coefficients and verify change.
        PIDFCoefficients pidfLeftModified = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightModified = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        left.setZeroPowerBehavior(FLOAT);
        right.setZeroPowerBehavior(FLOAT);

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        VLEFT_TARGET = 0;
        VRIGHT_TARGET = 0;

        ticksPerBlock = 130;

        extender = hardwareMap.get(Servo.class, SystemConfig.lift_extend);
        grabber = hardwareMap.get(Servo.class, SystemConfig.lift_grabber);
        turner = hardwareMap.get(Servo.class, SystemConfig.lift_turner);
        capper = hardwareMap.get(Servo.class, SystemConfig.lift_capper);

        // init servos to these values
        capper.setPosition(CAPSTONE_UP);
        turner.setPosition(ROTATOR_IN);
        extender.setPosition(H_IN);
        grabber.setPosition(GRABBER_OPEN);
    }

    // vertical lift

    public void update_height (int change) {
        if (height >= 0 && height <=11) {
            height += change;
        } else {
            height = 0;
        }
    }

    public void stationarystatus(boolean stationary) {
        IS_STATIONARY=stationary;
    }

    public void lift_to_level() {
        if (height == 1) {
            lift_by_ticks(height * ticksPerBlock +30);
        } else if (height == 2) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 3) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 4) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 5) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 6) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 7) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 8) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 9) {
            lift_by_ticks(height * ticksPerBlock +0);
        } else if (height == 10) {
            lift_by_ticks(height * ticksPerBlock +100);
        } else if (height == 11) {
            lift_by_ticks(height * ticksPerBlock +100);
        }
    }

    public void disengage_by_level() {
        if (height == 1) {
            lift_by_ticks(height * ticksPerBlock + 30 + 50);
        } else if (height == 2) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 3) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 4) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 5) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 6) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 7) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 8) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 9) {
            lift_by_ticks(height * ticksPerBlock + 50);
        } else if (height == 10) {
            lift_by_ticks(height * ticksPerBlock +150);
        } else if (height == 11) {
            lift_by_ticks(height * ticksPerBlock +150);
        }
    }

    public void vretract() {
        left.setPositionPIDFCoefficients(Down_Pos_P);
        right.setPositionPIDFCoefficients(Down_Pos_P);

        VLEFT_TARGET = 0;
        VRIGHT_TARGET = 0;

        if (VLEFT_POS > VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
            left.setPower(VMOVE_DOWN_POWER);
            left.setTargetPosition(VLEFT_TARGET);
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            right.setPower(VMOVE_DOWN_POWER);
            right.setTargetPosition(VRIGHT_TARGET);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            left.setPower(0);
            right.setPower(0);
            left.setZeroPowerBehavior(BRAKE);
            right.setZeroPowerBehavior(BRAKE);
        }
    }

    public void v_glide_up() {
        left.setPositionPIDFCoefficients(Down_Pos_P);
        right.setPositionPIDFCoefficients(Down_Pos_P);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        VLEFT_TARGET = right.getCurrentPosition();
        VRIGHT_TARGET = right.getCurrentPosition();

        left.setPower(.6);
        right.setPower(.6);
    }

    public void v_glide_down() {
        left.setPositionPIDFCoefficients(Down_Pos_P);
        right.setPositionPIDFCoefficients(Down_Pos_P);

        if (VLEFT_POS > VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
            left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            left.setPower(.4);
            right.setPower(.4);
        }
        else {
            slack();
        }
    }

    public void slack() {
        left.setZeroPowerBehavior(BRAKE);
        right.setZeroPowerBehavior(BRAKE);

        left.setPower(0);
        right.setPower(0);

        VLEFT_TARGET = left.getCurrentPosition();
        VLEFT_TARGET = right.getCurrentPosition();
    }

    public void lift_by_ticks (int ticks) {
        VLEFT_TARGET = ticks;
        VRIGHT_TARGET = ticks;

        VLEFT_POS = left.getCurrentPosition();

        if (VLEFT_TARGET > VLEFT_POS) {
            left.setPositionPIDFCoefficients(Up_Pos_P);
            right.setPositionPIDFCoefficients(Up_Pos_P);

            left.setPower(VMOVE_UP_POWER);
            right.setPower(VMOVE_UP_POWER);
        } else {
            left.setPositionPIDFCoefficients(Down_Pos_P);
            right.setPositionPIDFCoefficients(Down_Pos_P);

            left.setPower(VMOVE_DOWN_POWER);
            right.setPower(VMOVE_DOWN_POWER);
        }

        left.setTargetPosition(VLEFT_TARGET);
        right.setTargetPosition(VRIGHT_TARGET);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void vstop() {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);

        left.setPower(VMOVE_UP_POWER);
        right.setPower(VMOVE_UP_POWER);

        left.setTargetPosition(VLEFT_TARGET);
        right.setTargetPosition(VLEFT_TARGET);

        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        t.addData("Left Lift Position & Target:","%d, %d", VLEFT_POS, VLEFT_TARGET);
        t.addData("Right Lift Position & Target:","%d, %d", VRIGHT_POS, VRIGHT_TARGET);

//        t.update();
    }

    public void toggle_capstone() {
        // if it's currently down, put up
        if (capper_state == 0) protect_capstone();
        else place_capstone();
        capper_state = 1-capper_state;
    }

    // extender
    public void hteleextend() {
        extender.setPosition(H_TELE_OUT);
    }
    public void hautoextend() {
        extender.setPosition(H__AUTO_OUT);
    }
    public void hmaxextend() {extender.setPosition(H_MAX_OUT);}
    public void hengage() {extender.setPosition(H_ENGAGE);}


    public void h_grabber_bot() {
        extender.setPosition(H_GRABBER_BOT);
    }

    public void hretract() {
        extender.setPosition(H_IN);
    }

    public void hcapstonepos() {
        extender.setPosition(H_CAPSTONE);
        H_FULLY_EXTENDED = false;
    }

    public void htoggle() {
        if (H_FULLY_EXTENDED) hretract();
        else hteleextend();
        H_FULLY_EXTENDED = !H_FULLY_EXTENDED;
    }

    public void protect_capstone() {
        capper.setPosition(CAPSTONE_UP);
    }

    public void place_capstone() {
        capper.setPosition(CAPSTONE_DOWN);
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

    public void rotator_retract() {
        turner.setPosition(ROTATOR_IN);
    }
    public void rotator_extend() {
        turner.setPosition(ROTATOR_OUT);
    }

    public void turner_out() {
        turner.setPosition(ROTATOR_OUT);
    }

    public void turner_in() {
        turner.setPosition(ROTATOR_IN);
    }
    public void toggle_rotator() {
        if (rotator_out) rotator_retract();
        else rotator_extend();
        rotator_out = !rotator_out;
    }

    public double extender_pos() { return extender.getPosition();}
    public double grabber_pos() { return grabber.getPosition();}
    public double turner_pos() { return turner.getPosition();}
    public double capper_pos() { return capper.getPosition();}

    public void capstone_turn() {
        turner.setPosition(ROTATOR_CAPSTONE);
    }
}
