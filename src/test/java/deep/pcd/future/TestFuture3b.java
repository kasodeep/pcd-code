package deep.pcd.future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static deep.pcd.PCDP.finish;
import static deep.pcd.PCDP.future;
import static org.junit.Assert.assertEquals;

/**
 * Test various combination of async nesting
 *
 * @author vcave
 */
@RunWith(JUnit4.class)
public class TestFuture3b {

    @Test
    public void testMethod() {

        final boolean[] result = {false};
        finish(() -> {
            try {
                result[0] = new TestFuture3b().run();
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(true, result[0]);
    }

    public boolean run() throws ExecutionException, InterruptedException {
        final int CONSTANT = 10;

        final Future<Future<Integer>> promise = future(() -> {
            final Future<Integer> future = future(() -> {
                return CONSTANT;
            });
            return future;
        });

        return promise.get().get() == CONSTANT;
    }
}
