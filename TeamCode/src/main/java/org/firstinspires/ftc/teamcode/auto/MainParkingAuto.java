package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.bot.components.Robot;

public class MainParkingAuto {
    Robot bot;
    Side side;

    public MainParkingAuto(Robot bot, Side side) {
        this.side = side;
        this.bot = bot;
    }

    public void run() {
        bot.raise_foundations();
        bot.drive_forward(0.5, 4);
        bot.strafe_right(0.5, side.coeff*15);
    }
}
