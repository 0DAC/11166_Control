package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class LimitSwitchInterface {
    private DigitalChannel touch_sensor;

    public LimitSwitchInterface(LinearOpMode run_op) {
        touch_sensor = run_op.hardwareMap.get(DigitalChannel.class, "sensor_digital");
        touch_sensor.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean isPressed() {
        return touch_sensor.getState();
    }

    public String getDeviceName() {
        return touch_sensor.getDeviceName();
    }
    public void close() {
        touch_sensor.close();
    }
}
