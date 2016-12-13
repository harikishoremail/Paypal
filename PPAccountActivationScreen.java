package in.msitprogram.iiit.paypal.console;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.persistance.DataStore;
import in.msitprogram.iiit.paypal.utils.PPToolkit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

public class PPAccountActivationScreen
{	private static File fp;
	@SuppressWarnings({ "unchecked", "static-access" })
	public static void show() throws IOException, ClassNotFoundException 
	{
		String email = ""; //change to get console input
		Scanner scan=new Scanner(System.in);

		System.out.println("Enter your email id");
		email=scan.next();
		PPAccount account = DataStore.lookupAccount(email);
		PPRestrictedAccount RestrictedAccount=DataStore.lookupStudentAccount(email);
		PPBusinessAccount BusinessAccount=DataStore.lookupOperator(email);
		System.out.println("Account actiavation sceen");
		if(account!=null)
		{
			Vector<PPAccount> v=new Vector<PPAccount>();
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Personalaccount.txt"));
				v=(Vector<PPAccount>)ois.readObject();
				ois.close();
			}
			catch (Exception e) 
			{
				System.out.println("Error: Unable to write file");
			}
			
			System.out.println("1.active account\n2.Suspend account\n3.Resend code\n\n Select one Option");
			int ch=scan.nextInt();
			
			switch(ch)
			{
			case 1:  for(int i=0; i<v.size(); i++)
					{
						if( (v.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
							if( !v.get(i).isActivated() )
							{
								for(int j=3; j!=0; j--)
								{
									System.out.println("Enter Activation Code");
									String code=scan.next();
									
									if ( account.getActivationCode().equals(code) )
									{
										System.out.print("Account sucessfully activated");
										v.get(i).setActivated(true);
										try 
										{
											ObjectOutputStream obj1= new ObjectOutputStream(new FileOutputStream("Personalaccount.txt"));
											obj1.writeObject(v);
											obj1.close();
										} 
										catch (Exception e) 
										{
											System.out.println("Error: unable to write file");
										}	
										MainMenu.show();
										break;
									}
								}
								System.out.print("Your account was blocked try again");		
							}
						}
					}
			break;
			
			case 2: for(int i=0; i<v.size(); i++)
					{
						if( (v.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
						if( account.isActivated() )
							{
								System.out.print("Account sucefully activated");
							
								System.out.print("Do you like to supended your account[Y/N]");
								char s=scan.next().charAt(0);
								if(s=='y'||s=='Y')
								{
									account.setActivated(false);
									try 
									{
										ObjectOutputStream obj1= new ObjectOutputStream(new FileOutputStream("Personalaccount.txt"));
										v.remove(account);
										v.addElement(account);
										obj1.writeObject(v);
										obj1.close();
									} 
									catch (Exception e) 
									{
										System.out.println("Error: Unable to write the file");
									}	
									MainMenu.show();
								} 
							}
						}
					}
			
			break;
			
			case 3: String code=account.getActivationCode();
					System.out.println("\nActivation code is "+code);
					System.out.println("Do you like to active your account[Y/N]");
					String str=scan.nextLine();
					MainMenu main=new MainMenu();
					PPAccountActivationScreen active= new PPAccountActivationScreen();
					if(str.equals("Y")){
						main.show();
					}else active.show();
			break;
		}
	
	}
	if(RestrictedAccount !=null)
	{
		Vector<PPRestrictedAccount> v1=new Vector<PPRestrictedAccount>();
		try
		{
		
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentaccount.txt"));
			v1=(Vector<PPRestrictedAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("1.active account\n2.Suspend account\n3.respond to code\n\n Select one Option");
		int ch=scan.nextInt();
			
			switch(ch)
			{
			case 1:  for(int i=0; i<v1.size(); i++)
					{
						if( (v1.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
							if( !v1.get(i).isActivated() )
							{
								System.out.println("Your not activated");
				
								// accept activation code, check if valid, if not give 2 more attempts
								for(int j=3; j!=0; j--) //3 attempts
								{
									System.out.println("Enter Activation Code :");
									String code=scan.next();
									
									if ( v1.get(i).getActivationCode().equals(code) )
									{
										System.out.println("Account sucefully activated");
									
										v1.get(i).setActivated(true);
										try
										{
											ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("studentaccount.txt"));
											obj1.writeObject(v1);
											obj1.close();
										}
										catch(IOException e)
										{
											System.out.print(e);
										}
										MainMenu.show();
										break;
									}
								}
								System.out.print("Your account was blocked for security reason try again");
								MainMenu.show();
							}
						}
					}
			
			break;
			
			case 2: for(int i=0; i<v1.size(); i++)
					{
						if( (v1.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
						if( v1.get(i).isActivated() )
							{
								System.out.print("Account sucessfully activated");
							
								System.out.print("Do you like to suspend account[Y/N]");
								char suspend=scan.next().charAt(0);
								
								if(suspend=='Y')
								{
									v1.get(i).setActivated(false);
									try 
									{
										ObjectOutputStream obj1= new ObjectOutputStream(new FileOutputStream(fp));
										obj1.writeObject(v1);
										obj1.close();
									} 
									catch (Exception e) 
									{
										System.out.println(e);
									}	
									MainMenu.show();
								} 
							}
						}
					}
			
			break;
			
			case 3:	String code=account.getActivationCode();
					System.out.println("\nActivation code is "+code);
					System.out.println("Do you like to active your account[Y/N]");
					String str=scan.nextLine();
					MainMenu main=new MainMenu();
					PPAccountActivationScreen active= new PPAccountActivationScreen();
					if(str.equals("Y")){
						main.show();
					}else active.show();
					break;
		}
	}else if(BusinessAccount!=null)
	{
		fp=new File("operator.txt");
		Vector<PPBusinessAccount> v=new Vector<PPBusinessAccount>();
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fp));
			v=(Vector<PPBusinessAccount>)ois.readObject();
			ois.close();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		System.out.println("1.active account\n2.Suspend account\n3.respond to code\n\n Select one Option");
		int ch=scan.nextInt();
			
			switch(ch)
			{
			case 1:  for(int i=0; i<v.size(); i++)
					{
						if( (v.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
							if( !v.get(i).isActivated() )
							{
								for(int j=1; j==3; j++)
								{
									System.out.println("Enter your Activation Code :");
									String code=scan.next();
									
									if ( v.get(i).getActivationCode().equals(code) )
									{
										System.out.println("We sucessfully activated your account");
									
										v.get(i).setActivated(true);
										try 
										{
											ObjectOutputStream obj1= new ObjectOutputStream(new FileOutputStream(fp));
											obj1.writeObject(v);
											obj1.close();
										} 
										catch (Exception e) 
										{
											System.out.println(e);
										}	
										MainMenu.show();
										break;
									}
								}
								System.out.print("Your account was blocked try again later");
								MainMenu.show();
							}
						}
					}
			
			break;
			
			case 2: for(int i=0; i<v.size(); i++)
					{
						if( (v.get(i).getEmail()).equalsIgnoreCase(email) ) 
						{
						if( v.get(i).isActivated() )
							{
							System.out.println("We sucessfully activated your account");
							
								System.out.println("Do You Want to suspend account [Y/N]");
								char str=scan.next().charAt(0);
								
								if(str=='Y')
								{
									v.get(i).setActivated(false);
									try 
									{
										ObjectOutputStream obj1= new ObjectOutputStream(new FileOutputStream(fp));
										obj1.writeObject(v);
										obj1.close();
									} 
									catch (Exception e) 
									{
										System.out.println(e);
									}	
									MainMenu.show();
								} 
							}
						}
					}
			
			break;
			
			case 3: String s=BusinessAccount.getActivationCode();
					System.out.print("\n Your Activation Code is "+s);
					String code=account.getActivationCode();
					System.out.println("\nActivation code is "+code);
					System.out.println("Do you like to active your account[Y/N]");
					String str=scan.nextLine();
					MainMenu main=new MainMenu();
					PPAccountActivationScreen active= new PPAccountActivationScreen();
					if(str.equals("Y")){
						main.show();
					}else active.show();
					break;
		}
	}
	else
		System.out.print("You enter the wrong email id");
	}
	
}