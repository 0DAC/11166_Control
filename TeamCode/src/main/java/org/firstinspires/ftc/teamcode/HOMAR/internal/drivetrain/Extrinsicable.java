package org.firstinspires.ftc.teamcode.HOMAR.internal.drivetrain;

/**
 * Created by Gabriel on 2018-05-17.
 * Should probably vextend some stuff (Holonomic, perhaps, and a new interface that is just the sensing part of Headingable)
 * Also it should probably have a better name
 */

public interface Extrinsicable {
    void setExtrinsic(boolean extrinsic);
    boolean getExtrinsic();
    void setExtrinsicOffset(double extrinsicOffset);
    double getExtrinsicOffset();
    void updateCourse();
}
