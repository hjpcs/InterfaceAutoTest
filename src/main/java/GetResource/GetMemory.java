package GetResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import Common.Commons;
import performance.Main;

public class GetMemory {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(talHeapSize("com.ss.android.+"));
        }
    }

    /**
     * test
     *
     * @param PackageName
     * @return
     * @throws IOException
     */
    public static double talHeapSize(String PackageName) throws IOException {
        double Heap = 0;
        String heapStr = "0";
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("adb -s " + Main.devices + " shell top -n 1| grep " + PackageName);
            try {
                if (proc.waitFor() != 0) {
                    System.err.println("exit value = " + proc.exitValue());
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.print(line+"\t");
                    if (line.contains(PackageName) && line.contains("/") == false) {
                        List<String> strList = Arrays.asList(line.split("\\s+"));
                        heapStr = strList.get(9).trim();
                        if (heapStr.contains("S")) {
                            heapStr = strList.get(9).trim().replace("S", "");
                            break;
                        } else {
                            heapStr = strList.get(8).trim().replace("S", "");
                            break;
                        }
                    }
                }
                Heap = Double.parseDouble(heapStr);
            } catch (InterruptedException e) {
                System.err.println(e);
            } finally {
                try {
                    proc.destroy();
                } catch (Exception e2) {
                }
            }
        } catch (Exception StringIndexOutOfBoundsException) {
            System.out.print("请检查设备是否连接 内存 ");
            StringIndexOutOfBoundsException.printStackTrace();
            return (-0.1);
        }
        return Commons.streamDouble(Heap / 1024);
    }

}
