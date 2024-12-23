package deep.pcd.await;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static deep.pcd.PCDP.*;
import static org.junit.Assert.assertEquals;

/**
 * Simple test that spawns an async await, which does nothing interesting. It waits for two futures to be resolved by
 * two other asyncs.
 *
 * @author vcave
 */
@RunWith(JUnit4.class)
public class TestAwait1b {

    @Test
    public void testMethod() {

        final boolean[] result = {false};
        result[0] = new TestAwait1b().run();
        assertEquals(true, result[0]);
    }

    public boolean run() {
        finish(() -> {
            final Future<Integer> f1 = future(() -> {
                return 1;
            });
            final CompletableFuture<Integer> f2 = new CompletableFuture<>();

            async(() -> {
                f2.complete(2);
            });

            asyncAwait(() -> {
                Math.random();
            }, f1, f2);
        });

        return true;
    }
}
