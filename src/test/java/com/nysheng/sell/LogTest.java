package com.nysheng.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LogTest {
    @Test
    public void test(){
        String msg="nys";
        log.debug("debug: {}",msg);
        log.info("info: {}",msg);
        log.error("error: {}",msg);
    }
}
