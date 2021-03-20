package com.elite.agents.server.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.elite.agents.server.service")
public class ServiceConfiguration {
}
