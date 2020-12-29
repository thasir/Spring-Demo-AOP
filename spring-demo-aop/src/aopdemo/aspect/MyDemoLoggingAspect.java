package aopdemo.aspect;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Around("execution(* aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n====> executing @Around on method: " + method);
		// get beginning time stamp
		long begin = System.currentTimeMillis();
		// now lets execute the method
		Object result = null;
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			// rethrow exception
			throw e;
		}
		// get the ending time stamp
		long end = System.currentTimeMillis();
		// compute and display the duration
		long duration = end - begin;
		myLogger.info("\n====> Duration: " + duration / 1000.0 + " seconds");
		return result;
	}

	@After("execution(* aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		// printout which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====> executing @after (finally) on method: " + method);
	}

	@AfterThrowing(pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		// printout which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====> executing @afterThrowing on method: " + method);
		// log the exception
		myLogger.info("\n====> the exception is : " + theExc);
	}

	// add a new advice for @aafterreturning on the find accounts
	@AfterReturning(pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====> executing @afterReturning on method: " + method);
		// print out the results of the method call
		myLogger.info("\n====> result is : " + result);
		// lets post process the data ...lets modify it
		// convert the account name to uppercase
		convertAccountNamesToUppercase(result);
		myLogger.info("\n====> result is : " + result);
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
		myLogger.info("\n====>>Executing @Before Advice on addAccount()");

		// display the method signatture
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " + methodSig);
		// display method arguments
		// get args
		Object[] args = theJoinPoint.getArgs();
		// loop through args
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());
			if (tempArg instanceof Account) {
				// downcast and print account specific stuff
				Account theAccount = (Account) tempArg;
				myLogger.info("Account Name: " + theAccount.getName());
				myLogger.info("Account Level: " + theAccount.getLevel());
			}
		}
	}

}
