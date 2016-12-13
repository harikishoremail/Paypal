package in.msitprogram.iiit.paypal.console;

import java.io.IOException;
import java.rmi.server.SocketSecurityException;
import java.util.Date;
import java.util.Scanner;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Profile;
import in.msitprogram.iiit.paypal.accounts.Transaction;
import in.msitprogram.iiit.paypal.persistance.DataStore;

public class PPAccountScreen 
{
	static PPAccount account;
	Profile profile;
	Scanner scan;
	String email;
	float mainbal;
	static PPRestrictedAccount RestrictedAccount;
	
	public PPAccountScreen(String email) 
	{
		scan = new Scanner(System.in);
		account = DataStore.lookupAccount(email);
		RestrictedAccount=DataStore.lookupStudentAccount(email);
		this.email=email;
		
	}
	
	@SuppressWarnings("unused")
	public void show() throws IOException, ClassNotFoundException 
	{
		if(account!=null)
		{
			if(account.isActivated())
			{
				for(;;){
				System.out.println("___________________________________________________________________________________________________________");
				System.out.println(account);
				System.out.println("___________________________________________________________________________________________________________");
				System.out.println("\n1.WithDraw Money\n2.Send Money\n3.Request Money\n4.Add Funds\n5.Transactoin\n6.exit\n\n Enter your option");
				System.out.println("___________________________________________________________________________________________________________");
				int ch=scan.nextInt();
				switch(ch)
				{
					case 1:	
							withdrawFunds();
							break;	
					case 2:
							sendMoney();
							break;	
					case 3:	requestMoney();
							break;
					
					case 4: addFunds();
							break;
					case 5: DataStore.Transaction(email);
							break;	
					case 6:System.exit(1);
					default: System.out.println("You enter the wrong Option");
			}	
			}
		}
	}
	else if( RestrictedAccount!=null)
	{
		 if(RestrictedAccount.isActivated())//if active
		 {
					
					for(;;)
					{
						System.out.println("___________________________________________________________________________________________________________");
						System.out.println("\n"+RestrictedAccount);
						System.out.println("___________________________________________________________________________________________________________");
						System.out.println("\n1.WithDraw Money\n2.Request Money\n3.Last 5 transaction\n4.exit\nEnter your option");
						System.out.println("___________________________________________________________________________________________________________");
						int ch=scan.nextInt();
						switch(ch)
						{
							case 1:	System.out.println("Enter the ammount you like to with draw");
									Scanner draw=new Scanner(System.in);
										float withdraw=draw.nextFloat();
									if (withdraw > RestrictedAccount.getWithdrawLimit() )
										{
										System.out.println("Error: you excuted your limit");
										}
									else if ((withdraw > RestrictedAccount.getAccountBal()))
										{
										System.out.println("Error : Insufficient funds");
										}
									else
									{
										DebitTransaction(RestrictedAccount,"withdrawn the money","******","completed",withdraw);
										DataStore.updateStudentAccount(RestrictedAccount.getEmail(), withdraw, 0);
										RestrictedAccount.setAccountBal(RestrictedAccount.getAccountBal()-withdraw);
										System.out.println("Funds sucess full please take the cash");
									}
								break;
						/*	case 9:	sendMoney(); 	
									break;*/
							case 2: requestMoney();
									break;
							/*case 6: 
										System.out.println("Enter the amount you like to add");
										@SuppressWarnings("resource")
										Scanner draw1=new Scanner(System.in);
										float addfunds=draw1.nextFloat();	
										CreditTransaction(RestrictedAccount,"Adding Funds","*******","completed",addfunds);	
										DataStore.updateStudentAccount(RestrictedAccount.getEmail(), addfunds, 1);
										RestrictedAccount.setAccountBal(RestrictedAccount.getAccountBal()+addfunds);
									break;*/
							case 3:	DataStore.Transaction(email);
									break;	
							case 4: System.exit(1);
							
							default: System.out.println("Enter the wright Option");
									 break;
						}
					}			
		}
	}
			
		else
		{
			System.out.println("Account not activated");
			PPAccountActivationScreen active=new PPAccountActivationScreen();
			PPAccountActivationScreen.show();
		}
}

	@SuppressWarnings("resource")
	private static void withdrawFunds() throws IOException, ClassNotFoundException 
	{
		// implement the withdraw funds user interface here
		
		//use the account object as needed for withdraw funds
		System.out.println("Enter the ammount you like to with draw");
		Scanner draw=new Scanner(System.in);
		float withdraw=draw.nextFloat();
		
	if (("Personal Account").compareTo(account.getTypeOfAccount())==0)
		{

			if (withdraw > account.getAccountBal())
			{
				System.out.println("In sufficient in your ammount");
			}
			else 
			{
				System.out.println(account.getTypeOfAccount()+account.getAccountBal());
				DebitTransaction(account,"WithDraw the money","somebody","completed",withdraw);
				DataStore.updateAccount(account.getEmail(), withdraw, 0);
				account.setAccountBal(account.getAccountBal()-withdraw);
				System.out.println("Please collect the cash");
			}
		}
	}


	private static void requestMoney() throws ClassNotFoundException, IOException 
	{
		String mail;
		float money;
		Scanner sm = new Scanner (System.in);
		
		
		if (("Personal Account").compareTo(account.getTypeOfAccount())==0)
		{
			System.out.println("Enter the  email Id to request money : ");
			mail = sm.nextLine();
			System.out.print("Enter the amount you like to request : ");
			money = sm.nextFloat();
			 
			account = DataStore.lookupAccount(mail);
			RestrictedAccount=DataStore.lookupStudentAccount(mail);
			
			if (account == null && RestrictedAccount==null)
			{
					
				System.out.println("Error : Given Benificiary Account Doesn't exists!!");
				MainMenu.show();
				
			}

			if(money > account.getAccountBal())
			{
				System.out.println("Error : You've exceedeed the withdrawl limit of " + account.getEmail() + " 's account");
			}
		
			else
			{
				
				System.out.println("Your request has been sent to " + account.getEmail() + " 's account ");
			}
		
		}
		
		else 
		{
			mail = account.getEmail();
			RestrictedAccount = DataStore.lookupStudentAccount(mail);
			
			System.out.print("Enter the amount to be requested : ");
			money = sm.nextFloat();
			if (DataStore.lookupAccount(RestrictedAccount.getParentEmail())==null)
			{
					System.out.println("\n Error : Your parent mail id : " +RestrictedAccount.getParentEmail() + " is not yet created . Insist your parent to create");
			}
			else
			{
			System.out.println("Your request has been sent to your parent's account  : " +RestrictedAccount.getParentEmail());
			}
		}
	}

	private static void sendMoney() throws ClassNotFoundException, IOException 
	{
		String mail;
		float money;
		PPAccount account1;
		System.out.print("\nEnter the Benificiary email's account : ");
		@SuppressWarnings("resource")
		Scanner sm = new Scanner (System.in);
		mail = sm.nextLine();
		System.out.print("Enter the amount to be sent : ");
		money = sm.nextFloat();
		 
		account1 = DataStore.lookupAccount(mail);
		PPRestrictedAccount account2 = DataStore.lookupStudentAccount(mail); 	
	
		if (account1==null && account2==null)
		{
			System.out.println("\nError : Account doesn't exists");
			MainMenu.show();
		}
		else
		{
			if (money > account.getAccountBal())
			{
				System.out.println("\nError : In-sufficient funds to make the transaction !!");
			}
			else 
			{
			if (("Personal Account").compareTo(account.getTypeOfAccount())==0)
			{
				
				DebitTransaction(account,"sending money","*******","completed",money);
				DataStore.updateAccount(account.getEmail(), money, 0);
				account.setAccountBal(account.getAccountBal()-money);
				}
			else
			{
				DebitTransaction(account,"sending money","*********","completed",money);
				DataStore.updateStudentAccount(account.getEmail(), money, 0);
				account.setAccountBal(account.getAccountBal()-money);
			}
		
			
			if ( account1!=null && ("Personal Account").compareTo(account1.getTypeOfAccount())==0)
			{
				CreditTransaction(account1,"Receiving money ","**********","completed",money);
				DataStore.updateAccount(account1.getEmail(), money, 1);
				System.out.println("Credited Successfully to "+mail);
			}
			else
			{
				if ( account.getEmail().compareTo(account2.getParentEmail())==0)
				{
				CreditTransaction(account2,"Receiving money","*********","completed",money);
				DataStore.updateStudentAccount(account2.getEmail(), money, 1);
				System.out.println("Credited Successfully to your child : " +account2.getEmail());
				}
				else 
				{
					System.out.println("Error : You are not gaurdian of this account");
				}
				
			}
		
		}
		}
		
	}

	private void addFunds() throws IOException, ClassNotFoundException 
	{
		System.out.println("Enter the amount you like to add");
		@SuppressWarnings("resource")
		Scanner draw=new Scanner(System.in);
		float addfunds=draw.nextFloat();
		if (("Personal Account").compareTo(account.getTypeOfAccount())==0)
		{
			CreditTransaction(account,"Adding Funds","*******","completed",addfunds);	
			DataStore.updateAccount(account.getEmail(), addfunds, 1);
			account.setAccountBal(account.getAccountBal()+addfunds);
			
		}
		System.out.println("Sucefully funds add to your account"+account.getAccountBal());
	}
	public static void DebitTransaction(PPAccount account,String narration,String reference,String status,float debit) throws ClassNotFoundException, IOException
	{
		Transaction transaction = new Transaction();
		transaction.settDateTime(SetDateTime());
		transaction.setAccount(account);
		transaction.setNarration(narration);
		transaction.setReference(reference);
		transaction.setStatus(status);
		transaction.setDebit(debit);
		transaction.setCredit(0);	
		transaction.setEmail(account.getEmail());
		DataStore.writeTransaction(transaction);
	}
	

	public static void CreditTransaction(PPAccount account,String narration,String reference,String status,float credit) throws ClassNotFoundException, IOException
	{
		Transaction transaction = new Transaction();
		transaction.settDateTime(SetDateTime());
		transaction.setAccount(account);
		transaction.setNarration(narration);
		transaction.setReference(reference);
		transaction.setStatus(status);
		transaction.setDebit(0);
		transaction.setCredit(credit);
		transaction.setEmail(account.getEmail());
		DataStore.writeTransaction(transaction);		
	}
	

	public static String SetDateTime()
	{
		
		Date date = new Date();
		String Time = String.format("Transaction Date/Time : %tc", date );
	   // System.out.printf(Time);		
		return Time;
	}
}