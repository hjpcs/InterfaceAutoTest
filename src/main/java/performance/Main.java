package performance;

import java.io.IOException;
import java.util.ArrayList;

import static GetResource.GetFlow.getWifiFlow;
import static GetResource.GetFps.fps;
import static GetResource.GetMemory.talHeapSize;
import static GetResource.GetTop.topCpu;
import static GetResource.Getbattery.battery;

/**
 * Created by liyu on 2018/10/25.
 */
public class Main {
    public static String devices = "14375200";
    public static String packageName = "com.ss.android.+";
    public static ArrayList<Double> cpuList = new ArrayList<>();
    public static ArrayList<Double> memList = new ArrayList<>();
    public static ArrayList<Double> batteryList = new ArrayList<>();
    public static ArrayList<Double> flowList = new ArrayList<>();
    public static ArrayList<Double> fpsList = new ArrayList<>();
    public static ArrayList<Double> lostFrameList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 20; i++) {
            Double cpu = topCpu(packageName);
            Double mem = talHeapSize(packageName);
            Double flow = (double)(getWifiFlow(packageName));
            Double fps = fps(packageName)[0];
            Double lostFrameRate = fps(packageName)[1];
            Double battery = battery();
            System.out.println("------------------------第" + i + "次--------------------");
            System.out.println("【Cpu数据统计】" + cpu + "%");
            System.out.println("【内存数据统计】" + mem + "MB");
            System.out.println("【流量数据统计】：" + flow+ "MB");
            System.out.println("【fps数据统计】：" + fps);
            System.out.println("【丢帧率数据统计】：" + lostFrameRate);
            System.out.println("【电量数据统计】" + battery);
            System.out.println("---------------------------------------------------");
            Thread.sleep(1000);
            cpuList.add(cpu);
            memList.add(mem);
            flowList.add(flow);
            fpsList.add(fps);
            lostFrameList.add(lostFrameRate);
            batteryList.add(battery);
        }

        System.out.println("------------------------测试结果--------------------");
        System.out.println("cpuList:" + cpuList );
        System.out.println("memList:" +memList );
        System.out.println("flowList:" +flowList);
        System.out.println("fpsList:" + fpsList);
        System.out.println("lostFrameList:" + lostFrameList);
        System.out.println("batteryList" + batteryList);
        System.out.println("---------------------------------------------------");
    }

}
