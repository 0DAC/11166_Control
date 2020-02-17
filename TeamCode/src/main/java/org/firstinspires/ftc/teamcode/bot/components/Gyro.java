package org.firstinspires.ftc.teamcode.bot.components;

import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.SystemConfig;

public class Gyro {
    /** In this sample, for illustration purposes we use two interfaces on the one gyro object.
     * That's likely atypical: you'll probably use one or the other in any given situation,
     * depending on what you're trying to do. {@link IntegratingGyroscope} (and it's base interface,
     * {@link Gyroscope}) are common interfaces supported by possibly several different gyro
     * implementations. {@link NavxMicroNavigationSensor}, by contrast, provides functionality that
     * is unique to the navX Micro sensor.
     */
    IntegratingGyroscope gyro;
    NavxMicroNavigationSensor navxMicro;

    // A timer helps provide feedback while calibration is taking place
    ElapsedTime timer = new ElapsedTime();

    public Gyro(HardwareMap hmp, Telemetry t) {
        navxMicro = hmp.get(NavxMicroNavigationSensor.class, SystemConfig.gyro);
        gyro = (IntegratingGyroscope)navxMicro;

        timer.reset();
        while (navxMicro.isCalibrating())  {
            t.addData("Status:", "Calibrating Gyro, Don't Move!");
            t.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            t.update();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException ie) {
                t.addData("Gyro Calibration Error: ", ie.getMessage());
                t.update();
            }
        }
    }

    public double[] get_rotations() { return new double[] {get_x_rotation(), get_y_rotation(), get_z_rotation()}; }
    public double get_x_rotation() { return gyro.getAngularVelocity(AngleUnit.DEGREES).xRotationRate; }
    public double get_y_rotation() { return gyro.getAngularVelocity(AngleUnit.DEGREES).yRotationRate; }
    public double get_z_rotation() { return gyro.getAngularVelocity(AngleUnit.DEGREES).zRotationRate; }

    public double[] get_orientation() { return new double[] {get_heading(), get_roll(), get_pitch()}; }
    public double get_heading() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.firstAngle));
    }
    public double get_roll() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.secondAngle));
    }
    public double get_pitch() {
        Orientation angles = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        return AngleUnit.DEGREES.normalize(AngleUnit.DEGREES.fromUnit(angles.angleUnit, angles.thirdAngle));
    }
}
