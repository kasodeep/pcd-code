package deep.pcd;

import junit.framework.TestCase;

import static deep.pcd.PCDP.async;

public class InvalidStateTest extends TestCase {
    public void testAsyncWithoutFinish() {
        boolean caughtException = false;
        try {
            async(() -> {
                System.err.println("Howdy!");
            });
        } catch (IllegalStateException e) {
            caughtException = true;
            assertEquals(PCDP.missingFinishMsg, e.getMessage());
        } finally {
            assertTrue(caughtException);
        }
    }
}
