package com.lhuc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 程序注解配置
 *
 * @author lhucstart
 */
@Configuration
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.lhuc.**.mapper")
public class ApplicationConfig {

}
