package deep.pcd.finish;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static deep.pcd.PCDP.async;
import static deep.pcd.PCDP.finish;
import static org.junit.Assert.assertEquals;

/**
 * Test simple finish that doesn't involve any asyncs
 *
 * @author vcave
 */
@RunWith(JUnit4.class)
public class TestFinish8 {

    @After
    public void tearDown() {

    }

    @Test
    public void testMethod() {

        final boolean result = new TestFinish8().run();
        assertEquals(true, result);
    }

    public boolean run() {
        Foo foo = new Foo();
        return true;
    }

    class Foo {
        public Foo() {
            finish(() -> {
                async(() -> {
                });
            });
        }
    }
}
