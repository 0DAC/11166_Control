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
    private final int VMIN_POSITION= 120;
    private final double VMOVE_UP_POWER = 1;
    private final double VMOVE_DOWN_POWER = .5;

    int VRIGHT_POS, VLEFT_POS, VLEFT_TARGET, VRIGHT_TARGET;

    int height;

    int ticksPerBlock;

    // horizontal extension
    private final double H__AUTO_OUT = .75;
    private final double H_TELE_OUT = .35;
    private final double H_MAX_OUT = .9;
    private final double H_GRABBER_BOT = .4;
    private final double H_CAPSTONE = 0.33;
    private final double H_IN = 0.25;
    private final double H_ENGAGE = 0.28;
    boolean H_FULLY_EXTENDED = false;

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
    private final double GRABBER_CLOSED = 0.69,
                    GRABBER_OPEN = .2;
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

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setZeroPowerBehavior(FLOAT);
        right.setZeroPowerBehavior(FLOAT);

        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        VLEFT_TARGET = 0;
        VRIGHT_TARGET = 0;

        ticksPerBlock = 120;

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
        height += change;
    }

<<<<<<< HEAD
    public void lift_to_level() {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
=======
    public void lift_to_level(double liftlevel, double power) {
        if (height != 0) {
            left.setPositionPIDFCoefficients(Up_Pos_P);
            right.setPositionPIDFCoefficients(Up_Pos_P);
            VLEFT_POS += liftlevel * 120;
            VRIGHT_POS += liftlevel * 120;
            left.setPower(VMOVE_UP_POWER);
            left.setTargetPosition(VLEFT_POS);
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
>>>>>>> 69f7a3408b12dc774a7127202b2c5a6876e43f8a

        if (height == 1) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 2) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 3) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 4) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 5) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 6) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 7) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 8) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        } else if (height == 9) {
            VLEFT_TARGET = height * ticksPerBlock;
            VRIGHT_TARGET = height * ticksPerBlock;
        }

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_TARGET);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_TARGET);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

//        if (liftlevel != 0) {
//            left.setPositionPIDFCoefficients(Up_Pos_P);
//            right.setPositionPIDFCoefficients(Up_Pos_P);
//            VLEFT_POS += liftlevel * 120;
//            VRIGHT_POS += liftlevel * 120;
//            left.setPower(VMOVE_UP_POWER);
//            left.setTargetPosition(VLEFT_POS);
//            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            right.setPower(VMOVE_UP_POWER);
//            right.setTargetPosition(VRIGHT_POS);
//            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        } else {
//            left.setPositionPIDFCoefficients(Down_Pos_P);
//            right.setPositionPIDFCoefficients(Down_Pos_P);
//            if (VLEFT_POS > VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
//                VLEFT_POS=0;
//                VRIGHT_POS=0;
//                left.setPower(VMOVE_DOWN_POWER);
//                left.setTargetPosition(VLEFT_POS);
//                left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//                right.setPower(VMOVE_DOWN_POWER);
//                right.setTargetPosition(VRIGHT_POS);
//                right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }
//            else {
//                left.setPower(0);
//                right.setPower(0);
//                left.setZeroPowerBehavior(BRAKE);
//                right.setZeroPowerBehavior(BRAKE);
//            }
//        }
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
        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();
    }

    public void vextend() {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
        VLEFT_POS+=120;
        VRIGHT_POS+=120;
        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
//
//    public void vretract() {
//        left.setPositionPIDFCoefficients(Down_Pos_P);
//        right.setPositionPIDFCoefficients(Down_Pos_P);
//        if (VLEFT_POS > VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
//            VLEFT_POS-=120;
//            VRIGHT_POS-=120;
//            left.setPower(VMOVE_DOWN_POWER);
//            left.setTargetPosition(VLEFT_POS);
//            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            right.setPower(VMOVE_DOWN_POWER);
//            right.setTargetPosition(VRIGHT_POS);
//            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        }
//        else {
//            left.setPower(0);
//            right.setPower(0);
//            left.setZeroPowerBehavior(BRAKE);
//            right.setZeroPowerBehavior(BRAKE);
//        }
//    }

    public void v_glide_up() {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left.setPower(.6);

        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setPower(.6);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();
    }

    public void v_glide_down() {
        left.setPositionPIDFCoefficients(Down_Pos_P);
        right.setPositionPIDFCoefficients(Down_Pos_P);
        if (VLEFT_POS > VMIN_POSITION || VRIGHT_POS > VMIN_POSITION)  {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left.setPower(-.2);

            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setPower(-.2);

            VLEFT_POS = left.getCurrentPosition();
            VRIGHT_POS = right.getCurrentPosition();
        }
        else {
            left.setPower(0);
            right.setPower(0);
            left.setZeroPowerBehavior(BRAKE);
            right.setZeroPowerBehavior(BRAKE);

            VLEFT_POS = left.getCurrentPosition();
            VRIGHT_POS = right.getCurrentPosition();
        }
    }

    public void slack() {
        left.setZeroPowerBehavior(BRAKE);
        left.setPower(0);

        right.setZeroPowerBehavior(BRAKE);
        right.setPower(0);

        // wait for some time
        double t = System.currentTimeMillis();
        while (System.currentTimeMillis()-t < time_ms);

        VLEFT_POS = left.getCurrentPosition();
        VRIGHT_POS = right.getCurrentPosition();

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void nudge_up () {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
        VLEFT_TARGET=20;
        VRIGHT_TARGET=20;

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS+VLEFT_TARGET);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS+VRIGHT_TARGET);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void nudge_down () {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
        VLEFT_TARGET=-40;
        VRIGHT_TARGET=-40;

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS+VLEFT_TARGET);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS+VRIGHT_TARGET);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void ground_stone_raise () {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);

        VLEFT_TARGET=140;
        VLEFT_TARGET=140;

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS+VLEFT_TARGET);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS+VRIGHT_TARGET);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void ground_stone_retract () {
        left.setPositionPIDFCoefficients(Up_Pos_P);
        right.setPositionPIDFCoefficients(Up_Pos_P);
        VLEFT_TARGET=-120;
        VLEFT_TARGET=-120;

        left.setPower(VMOVE_UP_POWER);
        left.setTargetPosition(VLEFT_POS+VLEFT_TARGET);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setPower(VMOVE_UP_POWER);
        right.setTargetPosition(VRIGHT_POS+VRIGHT_TARGET);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void vstop() {
        left.setTargetPosition(VLEFT_POS);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        right.setTargetPosition(VRIGHT_POS);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        t.addData("Left Lift:", VLEFT_POS);
        t.addData("Right Lift:", VRIGHT_POS);
//        t.update();
    }

    public void toggle_capstone() {
        // if it's currently down, put up
        if (capper_state == 0) protect_capstone();
        else place_capstone();
        capper_state = 1- capper_state;
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

    public void grabstone() {
        grab_stone();
        hretract();
    }

    public void placestone() {

    }

    public void resetclaw() {

    }

}
