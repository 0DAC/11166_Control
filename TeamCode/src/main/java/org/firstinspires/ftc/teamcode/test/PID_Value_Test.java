package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.SystemConfig;

/**
 * Created by tom on 9/26/17.
 * This assumes that you are using a REV Robotics Expansion Hub
 * as your DC motor controller.  This op mode uses the extended/enhanced
 * PID-related functions of the DcMotorEx class.  The REV Robotics Expansion Hub
 * supports the extended motor functions, but other controllers (such as the
 * Modern Robotics and Hitechnic DC Motor Controllers) do not.
 */

@Autonomous(name="PID_Value_Test", group = "Concept")
public class PID_Value_Test extends LinearOpMode {

    private DcMotorEx left, right; // vertical extension

    public static final double NEW_P = 2.5;
    public static final double NEW_I = 0.1;
    public static final double NEW_D = 0.2;
    public static final double NEW_F = 12.6;

    public void runOpMode() {
        // get reference to DC motor.
        // since we are using the Expansion Hub,
        // cast this motor to a DcMotorEx object.

        left = hardwareMap.get(DcMotorEx.class, SystemConfig.left_lift);
        right = hardwareMap.get(DcMotorEx.class, SystemConfig.right_lift);

        // wait for start command.
        waitForStart();

        // get the PID coefficients for the RUN_USING_ENCODER  modes.
        PIDFCoefficients pidfLeftOrig = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightOrig = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // change coefficients using methods included with DcMotorEx class.
        PIDFCoefficients pidfLeftNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
        left.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfLeftNew);
        PIDFCoefficients pidfRightNew = new PIDFCoefficients(NEW_P, NEW_I, NEW_D, NEW_F);
        right.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfRightNew);

        // re-read coefficients and verify change.
        PIDFCoefficients pidfLeftModified = left.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);
        PIDFCoefficients pidfRightModified = right.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        // display info to user.
        while(opModeIsActive()) {
            telemetry.addData("Runtime", "%.03f", getRuntime());
            telemetry.addData("Left: P,I,D,F (orig)", "%.04f, %.04f, %.0f, %.0f",
                    pidfLeftOrig.p, pidfLeftOrig.i, pidfLeftOrig.d, pidfLeftOrig.f);
            telemetry.addData("Left: P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f",
                    pidfLeftModified.p, pidfLeftModified.i, pidfLeftModified.d, pidfLeftModified.f);
            telemetry.addData("Right: P,I,D,F (orig)", "%.04f, %.04f, %.0f, %.0f",
                    pidfRightOrig.p, pidfRightOrig.i, pidfRightOrig.d, pidfRightOrig.f);
            telemetry.addData("Right: P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.0f",
                    pidfRightModified.p, pidfRightModified.i, pidfRightModified.d, pidfRightModified.f);
            telemetry.update();
        }
    }
}