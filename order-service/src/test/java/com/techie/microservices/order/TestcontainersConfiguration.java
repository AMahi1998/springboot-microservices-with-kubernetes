//package com.techie.microservices.order;
//
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.utility.DockerImageName;
//
//@TestConfiguration(proxyBeanMethods = false)
//class TestcontainersConfiguration {
//
//	@Bean
//	@ServiceConnection
//	MySQLContainer<?> mysqlContainer() {
//
//		MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("mysql:8.1.0"));
//		container.start();
//		System.out.println("Container started: " + container.getContainerName());
//		return container;
//	}
//
//}
