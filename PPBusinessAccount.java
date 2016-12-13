package in.msitprogram.iiit.paypal.accounts;

import java.util.ArrayList;

public class PPBusinessAccount extends PPAccount {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -24002802258140177L;
	private ArrayList <PPRestrictedAccount> accountOperators;

	public PPBusinessAccount(Profile profile) {
		super(profile);
		new ArrayList<PPRestrictedAccount>();
	}
	
	public void addAccountOperator(PPRestrictedAccount accountOperator){
		//add account operators after checking if there are duplicates
	}

	public ArrayList <PPRestrictedAccount> getAccountOperators() {
		return accountOperators;
	}

	public void setAccountOperators(ArrayList <PPRestrictedAccount> accountOperators) {
		this.accountOperators = accountOperators;
	}

}
