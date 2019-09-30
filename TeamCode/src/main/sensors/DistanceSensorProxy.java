package TeamCode.src.main.sensors;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorProxy {
    private DistanceSensor distanceSensor;

    public DistanceSensorProxy() {
        // you can use this as a regular DistanceSensorac6
        distanceSensor = hardwareMap.get(DistanceSensor.class, "sensor_range");
    }

    /**
     * Converts raw sensor data into usable distance
     * @returns: double distance value measured by the sensor in CM
     */
    public double readDistance() {
        return distanceSensor.getDistance(DistanceUnit.CM);
    }

}
