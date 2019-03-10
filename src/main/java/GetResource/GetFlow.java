package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import performance.Main;

public class GetFlow {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(getWifiFlow("com.ss.android.ugc.aweme"));
            Thread.sleep(3000);
        }
    }

    /**
     * 流量
     * @param PackageName
     * @return
     * @throws IOException
     */
    public static long getWifiFlow(String PackageName) throws IOException {
        String Pid = PID(PackageName);
        long str3 = 0;
        String cmd = "adb -s " + Main.devices + " shell cat /proc/" + Pid + "/net/dev";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec(cmd);
            try {
                if (proc.waitFor() != 0) {
                    System.err.println("exit value = " + proc.exitValue());
                }
                String line = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = in.readLine()) != null) {
                    stringBuffer.append(line + " ");
                    String str1 = line.toString();
                    if (str1.contains("wlan0:")) {
                        List<String> list = Arrays.asList(str1.split("\\s+"));
                        String rcv = list.get(2).trim();
                        String send = list.get(10).trim();
                        long a = Long.parseLong(rcv);
                        long b = Long.parseLong(send);
                        System.out.println("【流量数据统计】：上行：" + ((a / 1024) / 1024) + "MB" + "上行：" + ((b / 1024) / 1024) + "MB");
                        str3 = ((a + b) / 1024) / 1024;
                        break;
                    }
                }

            } catch (InterruptedException e) {
                System.err.println(e);
            } finally {
                try {
                    proc.destroy();
                } catch (Exception e2) {
                }
            }
        } catch (Exception StringIndexOutOfBoundsException) {
        }
        return str3;
    }

    //获取PID
    public static String PID(String PackageName) throws IOException {
        String PID = null;
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = runtime.exec("adb -s " + Main.devices + " shell ps | grep " + PackageName);
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line + " ");
            }
            String str1 = stringBuffer.toString();
            if (str1.contains(PackageName)) {
                String str2 = str1.substring(8, 15);
                PID = str2;
                PID = PID.trim();
            } else {
                PID = null;
            }

        } catch (InterruptedException e) {
            System.err.println(e);
        } finally {
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
        return PID;
    }

}
  

