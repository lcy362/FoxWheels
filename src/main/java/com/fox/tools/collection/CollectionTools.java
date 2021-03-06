package com.fox.tools.collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Equator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                return EqualsBuilder.reflectionEquals(o1, o2, exludedFields);
            }

            @Override
            public int hash(T o) {
                return HashCodeBuilder.reflectionHashCode(o, exludedFields);
            }
        };
        return equator;
    }

    /**
     *
     * @param l1
     * @param l2
     * @param exludedFields fields that don't consider
     * @param <T>
     * @return list[0]: elements in l1 not in l2
     *          list[1]: elements in l2 not in l1
     */
    public static <T> List<Collection<T>> findCollectionDifference(final Collection<T> l1, final Collection<T> l2, final String... exludedFields) {
        Equator<T> equator = generateEquator(exludedFields);
        Collection<T> l1More = CollectionUtils.removeAll(l1, l2, equator);
        Collection<T> l2More = CollectionUtils.removeAll(l2, l1, equator);
        List<Collection<T>> diff = new ArrayList<Collection<T>>();
        diff.add(l1More);
        diff.add(l2More);
        return diff;
    }

    public static void main(String args[]) {
        Equator<String> equator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                if  (StringUtils.equalsIgnoreCase(o1.replaceAll("_", ""), o2.replaceAll("_", ""))) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public int hash(String o) {
                return HashCodeBuilder.reflectionHashCode(o.replaceAll("_", "").toLowerCase());
            }
        };
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        a.add("iscat");
        a.add("ishooman");
        b.add("is_Cat");
        b.add("is_Hooman");
        System.out.println(CollectionUtils.isEqualCollection(a, b, equator));

    }
}
