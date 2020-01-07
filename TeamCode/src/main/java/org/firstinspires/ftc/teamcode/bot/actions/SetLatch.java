package org.firstinspires.ftc.teamcode.bot.actions;

import com.disnodeteam.dogecommander.Command;

import org.firstinspires.ftc.teamcode.bot.components.ServoLatch;

public class SetLatch implements Command {
    ServoLatch latch;
    boolean newState;
    boolean done;

    public SetLatch(ServoLatch servoLatch, boolean latchUp) {
        latch = servoLatch;
        newState = latchUp;
        done = false;
    }

    @Override
    public void start() {
        latch.setState(newState);
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
