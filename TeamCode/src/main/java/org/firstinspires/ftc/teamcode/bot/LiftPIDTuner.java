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

@TeleOp(name="LiftPIDTuner")
public class LiftPIDTuner extends LinearOpMode {
    Robot bot;

    private DcMotorEx left, right; // vertical extension
//  default 2, 0.5, 0, 11
    public static double NEW_P = .00001;
    public static double NEW_I = 0;
    public static double NEW_D = 0;
    public static double NEW_F = 10.6;
    public static double SIGN = 1;
    public static double FACTOR = 1;
    @Override
    public void runOpMode() {
        bot = new Robot(hardwareMap, telemetry);

        // get reference to DC motor.
        // since we are using the Expansion Hub,
        // cast this motor to a DcMotorEx object.

        left = hardwareMap.get(DcMotorEx.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotorEx.class, SystemConfig.right_lift);

        // wait for start command.
        waitForStart();
        telemetry.clearAll();

        while(opModeIsActive()) {
            // get the PID coefficients for the RUN_USING_ENCODER  modes.
            PIDFCoefficients pidfLeftOrig = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightOrig = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

            // change coefficients using methods included with DcMotorEx class.
            PIDFCoefficients pidfLeftNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            left.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftNew);
            PIDFCoefficients pidfRightNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
            right.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightNew);

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
            if (gamepad1.a) {
                SIGN*=-1;
                sleep(100);
            }
            if (gamepad1.left_trigger != 0) {
                bot.vraise_lift();
            } else if (gamepad1.right_trigger != 0) {
                bot.vlower_lift();
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
            PIDFCoefficients pidfLeftModified = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
            PIDFCoefficients pidfRightModified = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

//            telemetry.addData("Left: P,I,D,F (orig)", "%.04f, %.04f, %.0f, %.0f",
//                    pidfLeftOrig.p, pidfLeftOrig.i, pidfLeftOrig.d, pidfLeftOrig.f);
//            telemetry.addData("Left: P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f",
//                    pidfLeftModified.p, pidfLeftModified.i, pidfLeftModified.d, pidfLeftModified.f);
//            telemetry.addData("Right: P,I,D,F (orig)", "%.04f, %.04f, %.0f, %.0f",
//                    pidfRightOrig.p, pidfRightOrig.i, pidfRightOrig.d, pidfRightOrig.f);
//            telemetry.addData("Right: P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.0f",
//                    pidfRightModified.p, pidfRightModified.i, pidfRightModified.d, pidfRightModified.f);
//            telemetry.addData("SIGN,FACTOR", "%s, %s",SIGN,FACTOR);
            telemetry.addData("P,I,D,F (modified), Sign, Factor", "%.09f, %.09f, %.09f, %.09f, %.01f, %.09f",
                    pidfLeftModified.p, pidfLeftModified.i, pidfLeftModified.d, pidfLeftModified.f,SIGN, FACTOR);
            telemetry.update();
        }
    }
}