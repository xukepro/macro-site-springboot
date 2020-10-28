package com.xuke.macrosite;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuke on 2020/10/22
 */
public class SimpleTest {

    @Test
    public void test() {
        Date date = new Date();
        System.out.println(date.getTime());

        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println(timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(sdf.format(date));
    }
}
