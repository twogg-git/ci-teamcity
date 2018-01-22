package javatest.code;


import org.junit.Assert;
import org.junit.Test;

public class PrettyResponseTest {

    @Test
    public void testGetResponse() {
        PrettyResponse tester = new PrettyResponse();
        String response = tester.getResponse();
        Assert.assertEquals(response, "OK");
    }

    @Test
    public void testGetResponseError() {
        PrettyResponse tester = new PrettyResponse();
        String response = tester.getResponse();
        Assert.assertEquals(response, "FAIL");
        //Assert.assertNotEquals(response, "FAIL!");
    }
}