package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	// this is where we add all of our related advices for logging
	// lets start with before advice
	@Before("execution(public void aopdemo.dao.AccountDAO.addAccount())")
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>Executing @Before Advice on addAccount()");
	}
}
