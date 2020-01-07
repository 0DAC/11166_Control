package org.firstinspires.ftc.teamcode.bot.actions;

import com.disnodeteam.dogecommander.Command;

import org.firstinspires.ftc.teamcode.bot.components.ServoLatch;

public class ToggleLatch implements Command {
    ServoLatch latch;
    boolean done;

    public ToggleLatch(ServoLatch servoLatch) {
        this.latch = servoLatch;
        done = false;
    }

    @Override
    public void start() {
        latch.toggle();
        done = true;
    }

    @Override
    public void periodic() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isCompleted() {
        return done;
    }
}
