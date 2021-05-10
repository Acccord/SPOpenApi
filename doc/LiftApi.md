## 升降板API

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
    implementation 'com.github.Acccord:SPOpenApi:1.0.7'
}
```

### 第2步：打开串口
``` java
    /**
     * 打开串口
     * @param portStr   串口号
     *
     * @return 0：打开串口成功
     *        -1：无法打开串口：没有串口读/写权限！
     *        -2：无法打开串口：未知错误！
     *        -3：无法打开串口：参数错误！
     */
    LiftSerial.instance().open(String portStr);
```

### 第3步：数据接收
``` java
    /**
     * 添加串口数据回调
     * @param dataListener   串口返回数据回调
     */
    LiftSerial.instance().addDataListener(OnLiftDataListener dataListener);
    
    //在不使用的时候移除回调
    LiftSerial.instance().removeDataListener(OnLiftDataListener dataListener);
```

### 功能命令
- 1，读取当前升降高度
``` java
    /**
     * 读取高度
     * @param add 板子编号，只有一块板子默认1
     */
    LiftSerial.instance().getHigh(1);

    /**
     * 返回的高度在OnLiftDataListener回调以下方法中
     * LiftBean参数
     * @param Position 高度数据
     * @param switch   开关状态
     */
    void liftDataBack(LiftBean liftBean)
```

- 2，到达高度
``` java
    /**
     * 到达某个高度
     * @param high 高度值
     * @param add  板子编号，只有一块板子默认1
     */
    LiftSerial.instance().highCmd(int high, int add);
```

- 3，到达底部（无缓冲效果）
``` java
    /**
     * @param add  板子编号，只有一块板子默认1
     */
    LiftSerial.instance().toZero(int add);
```

- 4，电磁锁和灯光
``` java
    /**
     * @param door   1打开门 0关闭门
     * @param light  1打开灯光 0关闭灯光
     * @param add    板子编号，只有一块板子默认1
     */
     LiftSerial.instance().doorCmd(int door, int light, int add);
```