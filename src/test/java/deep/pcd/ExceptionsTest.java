package deep.pcd;

import deep.pcd.runtime.MultiException;
import junit.framework.TestCase;

import java.util.List;

import static deep.pcd.PCDP.async;
import static deep.pcd.PCDP.finish;

public class ExceptionsTest extends TestCase {

    public void testSingleException() {
        boolean caughtException = false;
        try {
            finish(() -> {
                async(() -> {
                    throw new RuntimeException("foo");
                });
            });
        } catch (RuntimeException r) {
            caughtException = true;
            assertEquals("foo", r.getMessage());
        } finally {
            assertTrue(caughtException);
        }
    }

    public void testMultiException() {
        boolean caughtException = false;
        try {
            finish(() -> {
                async(() -> {
                    throw new RuntimeException("foo");
                });

                async(() -> {
                    throw new RuntimeException("bar");
                });
            });
        } catch (MultiException multi) {
            List<Throwable> exceptions = multi.getExceptions();
            assertEquals(2, exceptions.size());
            if (exceptions.get(0).getMessage().equals("foo")) {
                assertEquals("bar", exceptions.get(1).getMessage());
            } else {
                assertEquals("bar", exceptions.get(0).getMessage());
                assertEquals("foo", exceptions.get(1).getMessage());
            }
            caughtException = true;
        } finally {
            assertTrue(caughtException);
        }
    }

    class TestException extends Exception {
        public TestException(final String msg) {
            super(msg);
        }
    }
}
