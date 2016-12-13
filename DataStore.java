package in.msitprogram.iiit.paypal.persistance;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Profile;
import in.msitprogram.iiit.paypal.accounts.Transaction;

import java.io.*;
import java.util.*;

public class DataStore implements Serializable
{
	private static final long serialVersionUID = -5203955612264882521L;
	@SuppressWarnings("unchecked")
	
	public static PPAccount lookupAccount(String email) 
	{
		Vector<PPAccount> v=new Vector<PPAccount>();
		boolean flag=false;
		int i;
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Personalaccount.txt"));
			v=(Vector<PPAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		
		for(i=0; i<v.size(); i++)
		{
			if( (v.get(i).getEmail()).equalsIgnoreCase(email) ) 
			{
				flag=true;
				break;
			}
			
		}
		
		if(flag)
			return v.get(i);
		else
			return null;
	}
	@SuppressWarnings("unchecked")
	public static void writeAccount(PPAccount account)
	{
		Vector<PPAccount> v=new Vector<PPAccount>();
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Personalaccount.txt"));
			v=(Vector<PPAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}	
		v.add(account);
		try
		{
			ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("Personalaccount.txt"));
			obj1.writeObject(v);
			obj1.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
		
	}
	@SuppressWarnings("unchecked")
	public static void updateAccount(String email,float amount,int val)throws IOException
	{
		Vector<PPAccount> v=new Vector<PPAccount>();
		try
		{
		
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Personalaccount.txt"));
			v=(Vector<PPAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{}
		for(int i=0;i<v.size();i++)
		{
			System.out.println("\n"+email+amount+val);
			if((v.get(i).getEmail()).equals(email))
			{
				if(val==0)
					v.get(i).setAccountBal(v.get(i).getAccountBal()-amount);
				if(val==1)
					v.get(i).setAccountBal(v.get(i).getAccountBal()+amount);
			}
			System.out.println("Remainning balance is "+v.get(i).getAccountBal());
		}
		try
		{
			ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("Personalaccount.txt"));
			obj1.writeObject(v);
			obj1.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
		
	}
/*
 * Student account Start here*/
	
	@SuppressWarnings("unchecked")
	public static void writeStudent(PPRestrictedAccount account)
	{
		Vector<PPRestrictedAccount> v=new Vector<PPRestrictedAccount>();
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentaccount.txt"));
			v=(Vector<PPRestrictedAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		v.add(account);
		try
		{
			ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("studentaccount.txt"));
			obj1.writeObject(v);
			obj1.close();
		}
		catch(IOException e)
		{
			System.out.print(e);
		}
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentaccount.txt"));
			v=(Vector<PPRestrictedAccount>)ois.readObject();
			ois.close();
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
			
	}
		@SuppressWarnings("unchecked")
		public static PPRestrictedAccount lookupStudentAccount(String email) 
		{
			Vector<PPRestrictedAccount> v=new Vector<PPRestrictedAccount>();
			boolean flag=false;
			int i;
			try
			{
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentaccount.txt"));
				v=(Vector<PPRestrictedAccount>)ois.readObject();
				ois.close();
			}
			catch(Exception e)
			{
				System.out.print(e);
			}
			for(i=0;i<v.size();i++)
			{
				//System.out.println(v.get(i).toString());
				if(v.get(i).getStudentEmail().equals(email))
				{
					flag=true;
					break;
				}
				
			}
			if(flag)
				return v.get(i);
			else
				return null;
		}
			public static void updateStudentAccount(String email,float amount,int val)throws IOException
			{
				Vector<PPRestrictedAccount> v=new Vector<PPRestrictedAccount>();
				try
				{
				
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentaccount.txt"));
					v=(Vector<PPRestrictedAccount>)ois.readObject();
					ois.close();
				}
				
				catch(Exception e)
				{}
				for(int i=0;i<v.size();i++)
				{
					if((v.get(i).getEmail()).equals(email))
					{
						if(val==0)
							v.get(i).setAccountBal(v.get(i).getAccountBal()-amount);
						if(val==1)
							v.get(i).setAccountBal(v.get(i).getAccountBal()+amount);
					}
				}
				try
				{
					ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("studentaccount.txt"));
					obj1.writeObject(v);
					obj1.close();
				}
				catch(IOException e)
				{
					System.out.print(e);
				}
				
			}
			@SuppressWarnings("unchecked")
			public static void writeOperator(PPBusinessAccount account)
			{
				Vector<PPBusinessAccount> v=new Vector<PPBusinessAccount>();
				try
				{
				try
				{
				
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("operator.txt"));
					v=(Vector<PPBusinessAccount>)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("operator.txt"));
					v=(Vector<PPBusinessAccount>)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
					
				
				v.add(account);
				try
				{
					ObjectOutputStream obj1 = new ObjectOutputStream(new FileOutputStream("operator.txt"));
					obj1.writeObject(v);
					obj1.close();
				}
				catch(IOException e)
				{}
				try
				{
				
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("operator.txt"));
					v=(Vector<PPBusinessAccount>)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
					
			}
	@SuppressWarnings("unchecked")
			public static PPBusinessAccount lookupOperator(String email) 
			{
				Vector<PPBusinessAccount> v=new Vector<PPBusinessAccount>();
				boolean flag=false;
				int i;
				try
				{
				
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("operator.txt"));
					v=(Vector<PPBusinessAccount>)ois.readObject();
					ois.close();
				}
				catch(Exception e)
				{
					System.out.print(e);
				}
				for(i=0;i<v.size();i++)
				{
					if(v.get(i).getEmail().equalsIgnoreCase(email))
					{
						flag=true;
						break;
					}
					
				}
				if(flag)
					return v.get(i);
				else
					return null;
			}
	@SuppressWarnings("unchecked")
	public static void writeTransaction(Transaction T) throws ClassNotFoundException, IOException
	 {
		try{
			Vector <Transaction> V = new Vector <Transaction> ();
			File fp =new File("Transactions.txt");
			FileInputStream filinput = new FileInputStream(fp);
			fp.createNewFile();
			ObjectInputStream OIS = new ObjectInputStream ((filinput));
			V = (Vector<Transaction>) OIS.readObject();
			OIS.close();
			
			V.add(T);
			
			ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(fp));
			OOS.writeObject(V);
			OOS.close();
			}
		 catch (Exception e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			}	
	 }
	@SuppressWarnings("unchecked")
	public static void Transaction(String email) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int i,j=1;
		@SuppressWarnings("unused")
		boolean Flag = false;
		Vector <Transaction> V = new Vector <Transaction> ();
		File fp =new File("Transactions.txt");
		FileInputStream filinput = new FileInputStream(fp);
		ObjectInputStream OIS = new ObjectInputStream ((filinput));
		V = (Vector<Transaction>) OIS.readObject();
		OIS.close();
		System.out.println("Last 5 Transactions are");
		for (i=0;i<V.size()&&j<=5;i++)
		{
			 
				 	if ((V.get(i).getEmail()).equalsIgnoreCase(email))			
					
				 	{ 	Flag = true;
				 		System.out.println("______________________________________________________________");
				 		System.out.println(" " +V.get(i).gettDateTime() );
				 		System.out.println(" Account Type : " +V.get(i).getAccount().getTypeOfAccount());
				 		System.out.println(" Naration : " +V.get(i).getNarration());
				 		System.out.println(" Reference : " +V.get(i).getReference());
				 		System.out.println(" Status : " +V.get(i).getStatus());
				 		System.out.println(" Debit : " +V.get(i).getDebit());
				 		System.out.println(" Credit : " +V.get(i).getCredit());	
				 		System.out.println("______________________________________________________________");
				 		j++;
				 	}
		
	}
}
}