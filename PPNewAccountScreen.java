package in.msitprogram.iiit.paypal.console;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Profile;
import in.msitprogram.iiit.paypal.persistance.DataStore;
import in.msitprogram.iiit.paypal.utils.PPToolkit;

public class PPNewAccountScreen
{
	Profile profile;
	PPAccount account;
	String email;
	Scanner scan;
	PPRestrictedAccount RestrictedAccount;
	PPBusinessAccount BusinessAccount;
	public PPNewAccountScreen(String email) 
	{
		profile = new Profile();
		scan = new Scanner(System.in);
		this.email = email;
	}

	public void show() throws IOException, ClassNotFoundException 
	{
		profile=createProfile();
		System.out.println("Select account type\n1.Personal\n2.Student\n3.Business");
		int type=scan.nextInt();
		switch(type){
		case 1:
			createPersonalAccount();
			break;
		case 2:
			createStudentAccount();
			break;
		case 3:
			createBusinessAccount();
			break;
		default:System.out.println("You enter wrong option");
		}
	}

	private Profile createProfile() 
	{
		Profile profile=new Profile();
		System.out.println("Enter your name");
		profile.setName(scan.next());
		System.out.println("Enter your address");
		profile.setAddress(scan.next());
		System.out.println("Enter your Phone number");
		profile.setPhone(scan.next());
		return profile;
	}

	private void createBusinessAccount() throws IOException 
	{
		BusinessAccount=new PPBusinessAccount(profile);	
		BusinessAccount.setEmail(email);
		BusinessAccount.setTypeOfAccount("Business Accoount");
		scan=new Scanner(System.in);
		System.out.println("Enter number of Operator for this account");
		int num=scan.nextInt();
		for(int j=0;j<num;j++){
			System.out.println("Enter Your "+j+"Operator details");
			System.out.println("Enter your email id"); 
		    String mail=scan.next();
			Profile pro =new Profile();
			pro=createProfile();
			RestrictedAccount=new PPRestrictedAccount(pro);
			RestrictedAccount.setEmail(mail);
			System.out.print("Enter withdraw limit");
			RestrictedAccount.setWithdrawLimit(scan.nextFloat());
			BusinessAccount.addAccountOperator(RestrictedAccount);
		}
		Date date = new Date();
		BusinessAccount.setCreatedOn(date.toString());
		BusinessAccount.setActivated(false);
		String code=PPToolkit.generateActivationCode();
		System.out.println("Enter Initial Balance :");
		BusinessAccount.setAccountBal(scan.nextFloat());
		BusinessAccount.setActivationCode(code);
		
		DataStore.writeOperator(BusinessAccount);	
		System.out.println("\nActivation Code is:"+code);	
		try {
			completeAccountCreation();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void createStudentAccount() throws IOException, ClassNotFoundException 
	{
		RestrictedAccount=new PPRestrictedAccount(profile);
		new DataStore();
		Date date = new Date();
		new PPToolkit();
		@SuppressWarnings("resource")
		Scanner parent=new Scanner(System.in);
		System.out.print("Enter Your Parent Email");
		String Parentemail=parent.next();
		RestrictedAccount.setParentEmail(Parentemail);
		RestrictedAccount.setEmail(email);
		if(DataStore.lookupAccount(Parentemail)==null && DataStore.lookupStudentAccount(Parentemail)==null)	{
			System.out.println("Parent account not found");
			MainMenu.show();
		}
		else{
		System.out.print("Enter withdraw limit");
		RestrictedAccount.setWithdrawLimit(scan.nextFloat());
		RestrictedAccount.setTypeOfAccount("Student Account");
		RestrictedAccount.setCreatedOn(date.toString());
		RestrictedAccount.setActivated(false);
		
		String code=PPToolkit.generateActivationCode();
		RestrictedAccount.setActivationCode(code);
		
		System.out.println("Enter the intial balance");
		RestrictedAccount.setAccountBal(scan.nextFloat()); 
		DataStore.writeStudent(RestrictedAccount);
		System.out.println("Activation Code is:"+code);
		completeAccountCreation();
		}
	}

	/*
	 * this method create the personal account, saves it to the file system
	 * and redirects the users to the next screen
	 */

	private void createPersonalAccount() throws IOException, ClassNotFoundException 
	{
		account=new PPAccount(profile);
		account.setEmail(email);
		account.setTypeOfAccount("Personal Account");
		Date date = new Date();
		account.setCreatedOn(date.toString());
		new PPToolkit();
		new DataStore();
		account.setActivated(false);
		String code=PPToolkit.generateActivationCode();
		account.setActivationCode(code);
		System.out.println("Enter Initial Balance :");
		account.setAccountBal(scan.nextFloat()); 
		DataStore.writeAccount(account);	
		System.out.println("\nActivation Code is:"+code);	
		completeAccountCreation();
	}
	
	private void completeAccountCreation() throws IOException, ClassNotFoundException
	{
		System.out.println("Do you like to active your account[Y/N]");
		Scanner scan1=new Scanner(System.in);
		String str=scan1.nextLine();
		new MainMenu();
		new PPAccountActivationScreen();
		if(str.equals("Y")){
			PPAccountActivationScreen.show();
		}else MainMenu.show();
	}
}
