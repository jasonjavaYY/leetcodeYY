package lc.lc5;

/*
* 设计停车系统
*
* 停车场总共有三种不同大小车位：大，中和小，每种尺寸分别有固定数目的车位。
请你实现 ParkingSystem 类：
ParkingSystem(int big, int medium, int small) 初始化 ParkingSystem 类，三个参数分别对应每种停车位的数目。
bool addCar(int carType) 检查是否有 carType 对应的停车位。 carType 有三种类型：大，中，小，分别用数字 1， 2 和 3 表示。
* 一辆车只能停在  carType 对应尺寸的停车位中。如果没有空车位，请返回 false ，否则将该车停入车位并返回 true 。
*
* 示例 1：
输入：
["ParkingSystem", "addCar", "addCar", "addCar", "addCar"]
[[1, 1, 0], [1], [2], [3], [1]]
输出：
[null, true, true, false, false]
解释：
ParkingSystem parkingSystem = new ParkingSystem(1, 1, 0);
parkingSystem.addCar(1); // 返回 true ，因为有 1 个空的大车位
parkingSystem.addCar(2); // 返回 true ，因为有 1 个空的中车位
parkingSystem.addCar(3); // 返回 false ，因为没有空的小车位
parkingSystem.addCar(1); // 返回 false ，因为没有空的大车位，唯一一个大车位已经被占据了
*
* 方法一：模拟
为每种车维护一个计数器，初始值为车位的数目。此后，每来一辆车，就将对应类型的计数器减 11。当计数器为 00 时，说明车位已满。
* */
//数学 停车场有大，中和小车位。实现ParkingSystem类：
//ParkingSystem(int big, int medium, int small)初始化ParkingSystem类，三个参数对应每种车位数
//bool addCar(int carType) 检查是否有carType的车位。大，中，小，分别用1，2和3，车只能停对应尺寸
public class _1603 {
    class ParkingSystem {
        int big, medium, small;//记录大、中、小三类车位数
        //构造方法，初始化大、中、小的值
        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }
        //加车
        public boolean addCar(int carType) {
            if (carType == 1) {//判断车类型值，如果对应车位数>0就--返回真
                if (big > 0) {
                    big--;
                    return true;
                }
            } else if (carType == 2) {
                if (medium > 0) {
                    medium--;
                    return true;
                }
            } else if (carType == 3) {
                if (small > 0) {
                    small--;
                    return true;
                }
            } //否则返回false
            return false;
        }
    }
}
