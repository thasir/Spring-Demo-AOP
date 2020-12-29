package aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;

	// add a new method: findAccounts()
	public List<Account> findAccounts() {
		List<Account> myAccounts = new ArrayList<>();
		Account temp1 = new Account("LongJohn", "Silver");
		Account temp2 = new Account("Thasir", "Platinum");
		Account temp3 = new Account("Taseer", "Gold");
		// add them to our accounts list
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		return myAccounts;
	}

	public void addAccount(Account theAccount, boolean vip) {
		System.out.println(getClass() + " Doing my DB work :adding an account");
	}

	public boolean doWork() {
		System.out.println(getClass() + " :in doWork()");
		return true;
	}

	public String getName() {
		System.out.println(getClass() + " :in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " :in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " :in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " :in setServiceCode()");
		this.serviceCode = serviceCode;
	}

}
