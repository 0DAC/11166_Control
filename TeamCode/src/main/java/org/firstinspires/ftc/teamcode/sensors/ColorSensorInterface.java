package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;


public class ColorSensorInterface {

    private ColorSensor colorSensor;
    // maximum variance in R,B,G values from absolute zero tolerated
    private static int TOLERANCE = 300;

    public ColorSensorInterface(LinearOpMode run_op) {
        // you can use this as a regular DistanceSensorac6
        colorSensor = run_op.hardwareMap.get(ColorSensor.class, "sensor_range");
        colorSensor.enableLed(true);
    }

    /**
     * Detects black
     * @returns: true if pure black, false otherwise
     */
    public boolean classifyColor() {
        return (colorSensor.red()+colorSensor.green()+colorSensor.blue()) < TOLERANCE;
    }

    public String getDeviceName() {
        return colorSensor.getDeviceName();
    }
    public void close() {
        colorSensor.close();
    }
}
