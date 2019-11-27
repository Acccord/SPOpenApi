## LOCKER API
- [English](https://github.com/Acccord/AndroidSerialPort/blob/master/doc/LockerApi-en.md)

#### 第1步：配置
在项目的build.gradle添加
```
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
```
在模块的build.gradle添加
```
    dependencies {
        implementation 'com.github.Acccord:SPOpenApi:1.0.3'
    }
```

### 第2步：打开串口
``` java
     /**
      * 打开串口
      * @param portStr   串口号
      * @param ibaudRate 波特率
      *
      * @return 0：打开串口成功
      *        -1：无法打开串口：没有串口读/写权限！
      *        -2：无法打开串口：未知错误！
      *        -3：无法打开串口：参数错误！
      */
    LockerSerial.instance().open(String portStr, int ibaudRate);
```

### 第3步：数据接收
``` java
    /**
     * 添加串口数据回调
     * @param dataListener   串口返回数据回调
     */
    LockerSerial.instance().addDataListener(OnLockerDataListener dataListener);
    
    //在不使用的时候移除回调
    LockerSerial.instance().removeDataListener(OnLockerDataListener dataListener);
```

### 功能命令
- 1，打开箱门
``` java
    /**
     * 打开箱子
     *
     * @param lockerNumber   柜子号码
     * @param doorNumber     门号码
     */
    LockerSerial.instance().openDoor(int lockerNumber, int doorNumber);
```

- 2，读取箱子状态
``` java
    /**
     * 读取箱子状态
     *
     * @param lockerNumber   柜子号码
     */
    LockerSerial.instance().readDoors(int lockerNumber);
    
    //返回的箱子状态信息在OnLockerDataListener以下方法中返回
    /**
     * 返回箱子状态信息
     * {"cmd":"Read","SubPCB":1,"D1":1,"D2":1,"D3":1,"D4":1,"D5":1,"D6":1,"D7":1,"D8":1,"D9":1,"D10":1,"D11":1,"D12":1,"S1":1,"S2":1,"S3":1,"S4":1,"S5":1,"S6":1,"S7":1,"S8":1,"S9":1,"S10":1,"S11":1,"S12":1}
     * 说明：
     * 
     * "D1":1---门开,0---门关
     * "S1":1---红外无遮挡,0---红外有遮挡
     * @param dataStr 
     */
    void doorStatus(String dataStr);
```

### 更新记录
- 2019-08-13 优化串口打开方式和回调结果
- 2019-11-26 SDK地址更新
