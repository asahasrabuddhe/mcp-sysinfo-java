package com.technogise.sysinfojava;

import com.technogise.sysinfojava.tools.SysInfoTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SysinfoJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysinfoJavaApplication.class, args);
	}

	@Bean
	public ToolCallbackProvider weatherTools(SysInfoTools tools) {
		return  MethodToolCallbackProvider.builder().toolObjects(tools).build();
	}
}
