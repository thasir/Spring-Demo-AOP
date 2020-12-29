package aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopdemo.dao.AccountDAO;
import aopdemo.dao.MembershipDAO;
import aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {
	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		// get the bean from spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		System.out.println("\nMain Program :AroundDemoApp");
		System.out.println("calling getFortune");
		String data = theFortuneService.getFortune();
		System.out.println("my fortune is: " + data);
		System.out.println("Finished");
		// close the context
		context.close();
	}
}
