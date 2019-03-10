package testCase;

import org.testng.annotations.Test;

/**
 * Created by liyu on 2017/8/3.
 */

public class TestngMethods {
    /**
     * 默认情况下这个方法将被忽略,如果需要执行,需要在xml中配置allow-return-values="true"
     *
     * @return
     */
    @Test
    public String getName() {
        System.err.println("return name.... getName()");
        return "name";
    }

    @Test
    public void funtest() {
        System.err.println("this is funtest......");
    }

    @Test
    public void saveMethod1() {
        System.err.println("this is saveMethod1......");
    }

    @Test
    public void saveMethod2() {
        System.err.println("this is saveMethod2......");
    }

    @Test
    public void saveMethod3() {
        System.err.println("this is saveMethod3......");
    }

}