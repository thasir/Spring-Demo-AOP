package aopdemo.dao;

import org.springframework.stereotype.Component;

import aopdemo.Account;

@Component
public class AccountDAO {
	public void addAccount(Account theAccount, boolean vip) {
		System.out.println(getClass() + " Doing my DB work :adding an account");
	}

	public boolean doWork() {
		System.out.println(getClass() + " :doWork()");
		return true;
	}
}
