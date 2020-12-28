package aopdemo.aspect;

import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	// lets start with before advice
	@Before("aopdemo.aspect.LuvAopExperssions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n====>>Executing @Before Advice on addAccount()");

		// display the method signatture
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " + methodSig);
		// display method arguments
		// get args
		Object[] args = theJoinPoint.getArgs();
		// loop through args
		for (Object tempArg : args) {
			System.out.println(tempArg);
			if (tempArg instanceof Account) {
				// downcast and print account specific stuff
				Account theAccount = (Account) tempArg;
				System.out.println("Account Name: " + theAccount.getName());
				System.out.println("Account Level: " + theAccount.getLevel());
			}
		}
	}

}
