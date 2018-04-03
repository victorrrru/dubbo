package com.fww;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo {
    @Test
    public void create() throws ParseException {
        for (int i = 0; i < 60; i++) {
            int step = new Random().nextInt(10000) + 1000;
            String dateStr = "2017-11-13 00:00:00";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(dateStr);
            Date newDate = DateUtils.addDays(date, -i);
            String newDateStr = format.format(newDate);
            String sql = "INSERT INTO `smart_report`.`report_step` (`object_id`, `count_base`, `object_type`, `step_number`, `type`, `time_date`, `gmt_create`) VALUES ('1', '1', '0', '"+ step +"', '0', '" + newDateStr + "', '2017-11-29 18:05:01');";
            System.out.println(sql);
        }
    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        treeMap.put(10, "10");
        treeMap.put(85, "85");
        treeMap.put(15, "15");
        treeMap.put(70, "70");
        treeMap.put(20, "20");
        treeMap.put(60, "60");
        treeMap.put(30, "30");
        treeMap.put(50, "50");

        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
