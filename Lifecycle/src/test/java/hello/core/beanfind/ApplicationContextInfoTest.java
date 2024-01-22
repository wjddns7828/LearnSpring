package hello.core.beanfind;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;

class ApplicationContextInfoTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
	
	
//	 @Test
//	 @DisplayName("모든 Bean 출력") 
//	 void findAllBean() { 
//  		String[] beanNames = ac.getBeanDefinitionNames(); 
// 		for (String beanName : beanNames) { 
// 			Object bean= ac.getBean(beanName); 
// 			System.out.println("Name : " + beanName + "/ Bean : "+ bean);
// 		 }
//	  }
	 
	@Test
	@DisplayName("Application Bean 출력")
	void findApplicationBean() {
		String[] beanNames = ac.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanName);
			if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				Object bean= ac.getBean(beanName); 
				System.out.println("Name : " + beanName + "/ Bean : "+ bean);
			}
		}
	}

}
