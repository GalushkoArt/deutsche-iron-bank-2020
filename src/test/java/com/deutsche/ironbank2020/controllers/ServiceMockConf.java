package com.deutsche.ironbank2020.controllers;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Evgeny Borisov
 */
@TestConfiguration
@ComponentScan(basePackages = "com.deutsche.ironbank2020.services")
public class ServiceMockConf {
}
