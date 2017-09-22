package syway.txxs.com.syway;

import org.junit.Test;

import syway.txxs.com.syway.util.Crypto;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testPas(){
        System.out.println(Crypto.string2MD5("1234qwer"));
        System.out.println(Crypto.convertMD5(Crypto.string2MD5("1234qwer")));
    }
}