
package in.msitprogram.iiit.paypal.utils;

import java.util.*;

public class PPToolkit 
{

	public static String generateActivationCode() 
	{
		Random rand =new Random();
		int randomInteger = rand.nextInt(100000);
		String s=Integer.toString(randomInteger);
	    return s;
	}

	public static void sendActivationCode(String phone) 
	{
		
	}

}
