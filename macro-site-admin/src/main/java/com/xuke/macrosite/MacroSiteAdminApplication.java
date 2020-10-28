package com.xuke.macrosite;

import com.xuke.macrosite.service.impl.MyUserDetailsService;
import com.xuke.macrosite.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author xuke
 */
@SpringBootApplication
@Slf4j
@MapperScan("com.xuke.macrosite.dao")
public class MacroSiteAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MacroSiteAdminApplication.class, args);
        log.info("MacroSite启动成功");
    }

    @Bean
    public SpringUtil getSpringUtil() {
        return new SpringUtil();
    }
}
