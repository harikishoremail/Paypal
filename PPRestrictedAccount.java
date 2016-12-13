package in.msitprogram.iiit.paypal.accounts;

import in.msitprogram.iiit.paypal.persistance.DataStore;

import java.io.Serializable;
import java.util.Scanner;

public class PPRestrictedAccount extends PPAccount// implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3679067548103043174L;
	
	private String parentEmail;
	private float withdrawLimit;
	private String  businessemail;
	

	public PPRestrictedAccount(Profile profile) 
	{
		super(profile);
	}

	public String getStudentEmail() 
	{
		return getEmail();
	}

	public float getWithdrawLimit() 
	{
		return withdrawLimit;
	}

	public void setWithdrawLimit(float withdrawLimit) 
	{
		this.withdrawLimit = withdrawLimit;
	}

	public String getParentEmail() 
	{
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) 
	{
		this.parentEmail = parentEmail;
	}
	public String getbEmail() 
	{
		return parentEmail;
	}

	public void setbEmail(String businessemail) 
	{
		this.businessemail = businessemail;
	}

}
