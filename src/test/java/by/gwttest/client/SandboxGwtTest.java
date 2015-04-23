package by.gwttest.client;

import com.google.gwt.junit.client.GWTTestCase;

public class SandboxGwtTest extends GWTTestCase {
    @Override
    public String getModuleName() {
        return "by.gwttest.PacketSend";
    }

    public void testSandbox() {
        assertTrue(true);
    }
}