package com.xuke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuke
 */
@SpringBootApplication
@MapperScan("com.xuke.macrosite.dao")
public class MacroSiteAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MacroSiteAdminApplication.class, args);
    }

}
