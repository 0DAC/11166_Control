package org.firstinspires.ftc.teamcode.bot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.SystemConfig;
import org.firstinspires.ftc.teamcode.bot.components.Robot;

/**
 * Created by tom on 9/26/17.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This op mode uses the extended/enhanced
 * PID-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */
//https://github.com/FIRST-Tech-Challenge/SkyStone/wiki/Changing-PID-Coefficients

@TeleOp(name="DrivetrainPIDTuner")
public class DrivetrainPIDTuner extends LinearOpMode {
    Robot bot;

    private DcMotorEx leftFront, leftRear, rightFront, rightRear; // drivetrain motors
    //  default 2, 0, 0, 12 for GoBilda
    public static int NEW_P = 2;
    public static int NEW_I = 0;
    public static int NEW_D = 0;
    public static int NEW_F = 12;
    public static int POS_P = 2;
    public static int SIGN = 1;
    public static int FACTOR = 1;
    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap, telemetry);

        // get reference to DC motor.
        // since we are using the Expansion Hub,
        // cast this motor to a DcMotorEx object.

        leftFront = hardwareMap.get(DcMotorEx.class, SystemConfig.top_l);
        leftRear = hardwareMap.get(DcMotorEx.class, SystemConfig.bot_l);
        rightFront = hardwareMap.get(DcMotorEx.class, SystemConfig.top_r);
        rightRear = hardwareMap.get(DcMotorEx.class, SystemConfig.bot_r);
        // wait for start command.
        waitForStart();
        telemetry.clearAll();

        while(opModeIsActive()) {
            // get the PID coefficients for the RUN_USING_ENCODER  modes.
            PIDFCoefficients pidfLeftFrontOrig = leftFront.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfLeftRearOrig = leftRear.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightFrontOrig = rightFront.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightRearOrig = rightRear.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

            leftFront.setPositionPIDFCoefficients(POS_P);
            leftRear.setPositionPIDFCoefficients(POS_P);
            rightFront.setPositionPIDFCoefficients(POS_P);
            rightRear.setPositionPIDFCoefficients(POS_P);

            // change coefficients using methods included with DcMotorEx class.
            PIDFCoefficients pidfLeftFrontNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            leftFront.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftFrontNew);
            PIDFCoefficients pidfLeftRearNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            leftRear.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftRearNew);
            PIDFCoefficients pidfRightFrontNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            rightFront.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightFrontNew);
            PIDFCoefficients pidfRightRearNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            rightRear.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightRearNew);

            if (gamepad1.x) {
                NEW_P+=SIGN*FACTOR;
                sleep(100);
            }
            if (gamepad1.y) {
                NEW_I+=SIGN*FACTOR;
                sleep(100);
            }
            if (gamepad1.b) {
                NEW_D+=SIGN*FACTOR;
                sleep(100);
            }
            if (gamepad1.right_bumper) {
                POS_P+=FACTOR;
                sleep(100);
            }
            if (gamepad1.left_bumper) {
                POS_P-=FACTOR;
                sleep(100);
            }
            if (gamepad1.a) {
                SIGN*=-1;
                sleep(100);
            }
            if (gamepad1.left_trigger != 0) {
                bot.drive_forward(1,100);
            } else if (gamepad1.right_trigger != 0) {
                bot.strafe_right(1,100);
            } else {
                bot.vhold();
            }
            if (gamepad1.start) {
                FACTOR*=10;
                sleep(100);
            }
            if (gamepad1.back) {
                FACTOR*=.1;
                sleep(100);
            }
            // re-read coefficients and verify change.
            PIDFCoefficients pidfLeftFrontModified = leftFront.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfLeftRearModified = leftRear.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightFrontModified = rightFront.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightRearModified = rightRear.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

            telemetry.addData("Old_P,Old_I,Old_D,Old_F", "%.04f, %.04f, %.0f, %.0f",
                    pidfLeftFrontOrig.p, pidfLeftFrontOrig.i, pidfLeftFrontOrig.d, pidfLeftFrontOrig.f);
            telemetry.addData("New_P,New_I,New_D,New_F,POS_P,Sign,Factor", "%.09f, %.09f, %.09f, %.09f, %.09f, %.01f, %.09f",
                    pidfLeftFrontModified.p, pidfLeftFrontModified.i, pidfLeftFrontModified.d, pidfLeftFrontModified.f,POS_P,SIGN, FACTOR);
            telemetry.update();
        }
    }
}