package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorInterface {
    private DistanceSensor distanceSensor;

    public DistanceSensorInterface(LinearOpMode run_op) {
        // you can use this as a regular DistanceSensorac6
        distanceSensor = run_op.hardwareMap.get(DistanceSensor.class, "sensor_range");
    }

    /**
     * Converts raw sensor data into usable distance
     * @returns: double distance value measured by the sensor in CM
     */
    public double getDistance() {
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

    public String getDeviceName() {
        return distanceSensor.getDeviceName();
    }
    public void close() {
        distanceSensor.close();
    }
}
