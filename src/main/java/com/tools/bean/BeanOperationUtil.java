package com.tools.bean;

import org.apache.commons.lang.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Created by lcy on 2017/2/17.
 */
public class BeanOperationUtil {
    /**
     * merge not null attributes of two objects with the same type
     * use value in target when both objects has value
     * @param target
     * @param destination
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T merge(T target, T destination) throws Exception {
        //different class, return
        if (!StringUtils.equals(target.getClass().getName(), destination.getClass().getName())) {
            return target;
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());

        for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
            if (descriptor.getWriteMethod() != null) {
                Object originalValue = descriptor.getReadMethod()
                        .invoke(target);
                //only use destination's value when target value is null
                if (originalValue == null) {
                    Object defaultValue = descriptor.getReadMethod().invoke(
                            destination);
                    descriptor.getWriteMethod().invoke(target, defaultValue);
                }
            }
        }
        return target;
    }
}
