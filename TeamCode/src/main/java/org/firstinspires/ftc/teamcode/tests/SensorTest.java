package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensors.*;

@TeleOp(name = "Overall Sensor Test", group = "System Check")
public class SensorTest extends LinearOpMode {
    /**
     * Test script that displays sensor readings
     */
    public void runOpMode() {
        DistanceSensorInterface distance_sensor;
        ColorSensorInterface color_sensor;

        distance_sensor = new DistanceSensorInterface(this);
        color_sensor = new ColorSensorInterface(this);

        telemetry.addData("System Check: ", "Sensors");
        telemetry.addData(">>", "Press start to begin systems check");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            space();
            telemetry.addData("Device: ", distance_sensor.getDeviceName());
            telemetry.addData("Data: ", String.format("%.2f cm", distance_sensor.getDistance()));
            space();
            telemetry.addData("Device: ", color_sensor.getDeviceName());
            telemetry.addData("Data: ", String.format("%s cm", color_sensor.classifyColor() ? "Black" : "Yellow"));
            space();

            telemetry.update();
        }
    }

    private void space() {
        telemetry.addData("", "---------------------------------------------------------------------");
    }
}
