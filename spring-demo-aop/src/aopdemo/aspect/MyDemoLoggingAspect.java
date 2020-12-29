package aopdemo.aspect;

import java.util.List;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	@AfterThrowing(pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		// printout which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====> executing @afterThrowing on method: " + method);
		// log the exception
		System.out.println("\n====> the exception is : " + theExc);
	}

	// add a new advice for @aafterreturning on the find accounts
	@AfterReturning(pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====> executing @afterReturning on method: " + method);
		// print out the results of the method call
		System.out.println("\n====> result is : " + result);
		// lets post process the data ...lets modify it
		// convert the account name to uppercase
		convertAccountNamesToUppercase(result);
		System.out.println("\n====> result is : " + result);
	}

	private void convertAccountNamesToUppercase(List<Account> result) {
		// loop through the accounts
		for (Account tempAccount : result) {
			// get uppercase version of the name
			String theUpperName = tempAccount.getName().toUpperCase();
			// update the name to the account
			tempAccount.setName(theUpperName);

		}
	}

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
