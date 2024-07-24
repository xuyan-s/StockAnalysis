package com.xuyan.stock.config;

import com.xuyan.stock.pojo.vo.StockInfoConfig;
import com.xuyan.stock.utils.IdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description 公共配置类
 * @createDate 2024-07-20 16:36:55
 */
@Configuration
@EnableConfigurationProperties(StockInfoConfig.class)
public class CommonConfig {

    /**
     * 密码加密、匹配器Bean
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }

}
