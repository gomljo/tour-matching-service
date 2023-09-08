package com.backpacking.global.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.backpacking"})
public class JpaConfig {
}
