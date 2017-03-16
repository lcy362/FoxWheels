package com.fox.tools.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Collection;

/**
 * Created by lcy on 2017/3/9.
 */
public class CollectionTools {

    /**
     * is all elements in two collections the same
     * don't need to implenment equals() in T class
     * @param l1
     * @param l2
     * @param exludedFields fields that don't consider
     * @param <T>
     * @return
     */
    public static <T> boolean isEqualCollection(Collection<T> l1, Collection<T> l2, final String... exludedFields) {
        Equator<T> equator = generateEquator(exludedFields);
        return CollectionUtils.isEqualCollection(l1, l2, equator);
    }

    private static  <T> Equator<T> generateEquator(final String... exludedFields) {
        Equator<T> equator = new Equator<T>() {
            @Override
            public boolean equate(T o1, T o2) {
                if (o1 == null && o2 == null) {
                    return true;
                }
                if (o1 == null || o2 == null) {
                    return false;
                }
                if (o1.getClass() != o2.getClass()) {
                    return false;
                }
                return org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals(o1, o2, exludedFields);
            }

            @Override
            public int hash(T o) {
                return org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode(o, exludedFields);
            }
        };
        return equator;
    }
}
