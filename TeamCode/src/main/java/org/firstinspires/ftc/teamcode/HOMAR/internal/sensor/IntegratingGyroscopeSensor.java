package org.firstinspires.ftc.teamcode.HOMAR.internal.sensor;

import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class IntegratingGyroscopeSensor implements DerivativeSensor {
    IntegratingGyroscope gyro;
    HeadingConverter converter = new HeadingConverter();
    public IntegratingGyroscopeSensor(IntegratingGyroscope gyro) {
        this.gyro = gyro;
    }
    @Override
    public double getValue() {
        converter.update(gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZXY, AngleUnit.RADIANS).firstAngle);
        return converter.getConvertedHeading();
    }

    @Override
    public double getDerivative() {
        return gyro.getAngularVelocity(AngleUnit.RADIANS).zRotationRate;
    }
}
