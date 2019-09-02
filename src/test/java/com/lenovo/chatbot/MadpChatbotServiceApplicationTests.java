package com.lenovo.chatbot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MadpChatbotServiceApplicationTests {
	
	@Test
	public void contextLoads() {
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		System.out.println(path);
	}

}

