package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;
import java.util.ArrayList;

public class PPAccount implements Serializable
{
	private static final long serialVersionUID = -8259515440215464422L;
	
	private Profile profile;
	private String email;
	private float accountBal;
	private boolean isActivated;
	private String activationCode;
	private String createdOn;
	private String typeOfAccount;
	private ArrayList<Transaction> transactions;
	
	public PPAccount(Profile profile) 
	{
		// TODO Auto-generated constructor stub
		this.setProfile(profile);
	}
	public float getAccountBal() 
	{
		return accountBal;
	}

	public void setAccountBal(float accountBal) 
	{
		this.accountBal = accountBal;
	}

	public boolean isActivated() 
	{
		return isActivated;
	}

	public void setActivated(boolean isActivated) 
	{
		this.isActivated = isActivated;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email=email;	
	}
	public String getEmail()
	{
		return email;
	}

	public Profile getProfile() 
	{
		return profile;
	}

	public void setProfile(Profile profile) 
	{
		this.profile = profile;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	public String toString()
	{
		return "Hi"+profile.getName()+"    Type of Account :"+getTypeOfAccount()+"\t\t\tBalance :"+getAccountBal()+"\nEmail Id: "+getEmail()+"\nCreated On :"+getCreatedOn();
	}

	public void activate(String activationCode) 
	{
		
		// TODO Auto-generated method stub
		
	}
	
	public void suspend() 
	{
		// TODO Auto-generated method stub
	}


	public boolean withdraw(float withdrawAmount) 
	{
		return false;
	}


	public boolean addFunds(float creditAmount) 
	{
		
		return false;
	}
	
	public boolean sendMoney(float creditAmount) 
	{
		
		return false;
	}
	
	public boolean requestMoney(float creditAmount) 
	{
		
		return false;
	}

	
}
