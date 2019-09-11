# 求min和max的算法
```
for (int position : positions) {
            min = Math.max(min, Math.min(position, length - position)/speed);
            max = Math.max(max, Math.max(position, length - position)/speed);
}
```
# 模拟过程分析
将length*2是因为采用半秒作为单位，为了确保秒数为整数，所有数值在原来基础上乘2
# minState和maxState
因为初始方向未知，需要枚举所有可能2^n。n为蚂蚁的数量。用二进制表示每个蚂蚁的方向，蚂蚁数目为5，如十进制24即为11000,即前两只蚂蚁朝右，后三只蚂蚁朝左
# trace数组
trace数组存储所有的蚂蚁行走路径，第一纬度为枚举的所有可能种数，第二维度为蚂蚁的数目。第三维度为ArrayList，存储着蚂蚁每秒钟的移动路径。路径不包括起始点和结束点
# debugMin和debugMax
debugMin记录实际模拟得到的最小秒数，用于和min进行对比从而判断模拟结果的正确性。debugMin的值为min的两倍

