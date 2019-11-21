package proC.utils;

public interface Constants {
    double BASE_LENGTH = 1.0;
    double BASE_RADIUS = BASE_LENGTH/2;
    // 具体 * 多少待定
    double BASE_LENGTH_IN_PIXELS = BASE_LENGTH * 40;
    double BASE_RADIUS_IN_PIXELS = BASE_RADIUS * 40;
    // 频率
    double FRAMERATE = 60;
    double FRAMERATE_IN_MILLIS = FRAMERATE * 1000;
    double SECONDS_PER_FRAME = 1/FRAMERATE;
    // 一帧耗时
    double MILLIS_PER_FRAME = 1000/FRAMERATE;
    // 角速度,大概用不到
    double PADDLE_ANGULAR_VELOCITY = 1080 * BASE_LENGTH;
    double DELTA_ANGLE = PADDLE_ANGULAR_VELOCITY/FRAMERATE;
    double GRAVITY = 9.8;
    // 正方形 20 * 20
    double BOARD_WIDTH = 20;
    int CELL_NUM = 20;

}
