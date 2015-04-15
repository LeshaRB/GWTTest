package by.gwttest.client.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "by.gwttest.client.GWTTestClient";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}