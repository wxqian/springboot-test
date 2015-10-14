package com.wx.qian;

import com.wx.qian.redis.config.WxConfig;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigurationProperties({WxConfig.class})
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
