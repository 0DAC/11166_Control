package org.firstinspires.ftc.teamcode.util;

import android.app.Dialog;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="SandBox Testing")
public class SandBox extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Dialog prompt = new GetSide().onCreateDialog(null);
        prompt.show();
        telemetry.addData("Response: ", prompt.);
    }
}
