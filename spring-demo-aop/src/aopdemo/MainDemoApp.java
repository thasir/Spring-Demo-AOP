package aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopdemo.dao.AccountDAO;
import aopdemo.dao.MembershipDAO;

public class MainDemoApp {
	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		// get the membership bean from the spring contaienr
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		// call the membership method
		theMembershipDAO.addAccount();
		// call the business method
		theAccountDAO.addAccount();
		// do it again
		System.out.println("\n let's call it again!\n");
		// call the business method again
		theAccountDAO.addAccount();
		// close the context
		context.close();
	}
}
