package com.demo.case1;

import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.Test;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class RetransformTest2 extends AbstractInterceptTest {

    @Test
    public void testInterceptConstructor() throws UnmodifiableClassException {
        Instrumentation instrumentation = ByteBuddyAgent.install();

        // install transformer
        installMethodInterceptor(TARGET_CLASS_NAME, SAY_HELLO_METHOD, 1);
        //installConstructorInterceptor(TARGET_CLASS_NAME, 1);

        // call target class
        try {
            callBizFoo(1);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            // check interceptors
            checkMethodInterceptor(SAY_HELLO_METHOD, 1);
        }

        installMethodInterceptor(TARGET_CLASS_NAME, SAY_HELLO_METHOD, 2);
        //installConstructorInterceptor(TARGET_CLASS_NAME, 1);

        // do retransform
        // reTransform(instrumentation, BizFoo.class);

        // test again
        try {
            callBizFoo(2);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            // check interceptors
            checkMethodInterceptor(SAY_HELLO_METHOD, 2);
        }
    }

}

