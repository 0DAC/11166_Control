package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

public class MainFoundationAuto {
    Robot bot;
    Side side;

    public MainFoundationAuto(Robot bot, Side side) {
        this.side = side;
        this.bot = bot;
        bot.raise_foundations();
    }

    public void run() {
        bot.drive_backward(0.5, 50);
        if (side == Side.RIGHT) bot.strafe_right(0.5, 20);
        else bot.strafe_left(0.5, 20);
        bot.drive_backward(0.3, 10);
        bot.lower_foundations();
        if (side == Side.RIGHT) bot.strafe_left(0.5, 40);
        else bot.strafe_right(0.5, 40);
        if (side == Side.RIGHT) bot.turn_90_ccw(0.5);
        else bot.turn_90_cw(0.5);
        bot.raise_foundations();
        if (side == Side.RIGHT) bot.strafe_right(0.5, 20);
        else bot.strafe_left(0.5, 20);
        bot.drive_forward(0.5, 100);
    }
}
