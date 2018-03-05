package online.himakeit.lightmusic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
        /**
         * == 内存比较
         * equals 面值比较
         * hashcode 字符串数值化比较
         */
        String a = "a" + "b" + 1;
        String b = "ab1";
        String c = new String("ab1");
        assertEquals(true, a == b);
        assertEquals(false, b == c);
        assertEquals(true, a.equals(b));
        assertEquals(true, b.equals(c));
    }
}