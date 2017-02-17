package com.tools.calculation;

import com.tools.bean.com.tools.calculation.GeoUtils;
import org.junit.Test;

/**
 * Created by lcy on 2017/2/17.
 */
public class GeoUtilTest {
    @Test
    public void testDistance() {
        Double lon1 = 117.2458333d;
        Double lat1 = 34.22944444d;
        Double lon2 = 114.2080556d;
        Double lat2 = 30.78388889d;
        System.out.println(GeoUtils.getDistance(lat1, lon1, lat2, lon2));
    }
}
