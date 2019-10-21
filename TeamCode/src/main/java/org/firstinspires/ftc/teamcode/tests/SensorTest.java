package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sensors.*;

@TeleOp(name = "Sensor Test", group = "System Check")
public class SensorTest extends LinearOpMode {
    /**
     * Test script that displays sensor readings
     */
    public void runOpMode() {
        DistanceSensorInterface distance_sensor;
        ColorSensorInterface color_sensor;
        LimitSwitchInterface touch_sensor;

        distance_sensor = new DistanceSensorInterface(this);
        color_sensor = new ColorSensorInterface(this);
        touch_sensor = new LimitSwitchInterface(this);

        telemetry.addData("System Check: ", "Sensors");
        telemetry.addData(">>", "Press start to begin systems check");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            space();
            telemetry.addData("Device: ", distance_sensor.getDeviceName());
            telemetry.addData("Data: ", String.format("Distance: %.2f cm", distance_sensor.getDistance()));
            space();
            telemetry.addData("Device: ", color_sensor.getDeviceName());
            telemetry.addData("Data: ", String.format("Color: %s", color_sensor.classifyColor() ? "Black" : "Yellow"));
            space();
            telemetry.addData("Device: ", touch_sensor.getDeviceName());
            telemetry.addData("Data: ", String.format("The button %s", touch_sensor.isPressed() ? "is pressed" : "is not pressed"));
            space();

            telemetry.update();
        }
    }

    private void space() {
        telemetry.addData("", "---------------------------------------------------------------------");
    }
}
