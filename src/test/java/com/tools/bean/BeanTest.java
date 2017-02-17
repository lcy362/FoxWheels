package com.tools.bean;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lcy on 2017/2/17.
 */
public class BeanTest {
    @Test
    public void testMergeBeans() throws Exception {
        TestObject t1 = new TestObject();
        TestObject t2 = new TestObject();
        t1.setId("12");
        t2.setName("x");
        t2.setScore("96");
        BeanOperationUtil.merge(t1, t2);
        Assert.assertEquals(t1.getId(), "12");
        Assert.assertEquals(t1.getName(), "x");
        Assert.assertEquals(t1.getScore(), "96");
    }
}
