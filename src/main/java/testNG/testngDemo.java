package testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by liyu on 2018/8/31.
 */
public class testngDemo {
    @BeforeMethod
    public void setup()
    {
        System.out.println("begin test");
    }
    @Test
    public void testtets1()
    {

            System.out.println("at test1");

    }
    @Test
    public void test2()
    {
        System.out.println("at test2");
    }
    @Test
    public void test3()
    {
        System.out.println("at test3");
    }
    @AfterMethod
    public void teardown()
    {
        System.out.println("end test");
    }
}
