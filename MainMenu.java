package in.msitprogram.iiit.paypal.console;

import in.msitprogram.iiit.paypal.accounts.Profile;
import in.msitprogram.iiit.paypal.persistance.DataStore;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu 
{
	
	public static void show() throws IOException, ClassNotFoundException
	{
		String email=null;
		System.out.println("\n__________________________________");
		System.out.println("welcome to paypal");
		System.out.println("1. SignIn");
		System.out.println("2. Signup");
		System.out.println("3. Exit");
		System.out.println("__________________________________");
		System.out.println("select one Option");
		Scanner sc=new Scanner(System.in);
		int ch=sc.nextInt();  
		switch(ch)
		{
		case 1: System.out.println("Enter your email id"); 
			    email=sc.next();
			    PPAccountScreen pa=new PPAccountScreen(email);
			    pa.show();
			    break;
		case 2: System.out.println("Enter a new email id");
		   		email=sc.next();
		   		if(DataStore.lookupAccount(email)==null || DataStore.lookupStudentAccount(email)==null )
				{
		   			PPNewAccountScreen pp=new PPNewAccountScreen(email); //if not create the user profile
		   			pp.show();
				}
				else
				{
					System.out.println("Account already exits");
					MainMenu.show();
				}
		   	
				break;
		case 3: System.exit(0);

		}	
		
	}

}
