package designpattern;

import java.io.*;	
import java.util.*;

public class Login implements LoginInterface
{
	
	ArrayList<String>buyerName = new ArrayList<>();
	ArrayList<String>buyerPassword = new ArrayList<>();
	ArrayList<String>sellerName = new ArrayList<> ();
	ArrayList<String>sellerPassword = new ArrayList<>();
	

	//method to take username and password and allow users to add products
	
	@SuppressWarnings("resource")
	@Override
	public User loginMethod() throws IOException{
		
		User u = new User();

		File file = new File("src\\designpattern\\BuyerInfo");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		File file1 = new File("src\\designpattern\\SellerInfo");
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		
		String s;
		
		while((s = br.readLine())!=null)
		{
			String [] buyers = s.split(":");
			buyerName.add(buyers[0]);
			buyerPassword.add(buyers[1]);
		}
		
		while((s = br1.readLine())!=null)
		{
			String[] sellers = s.split(":");
			sellerName.add(sellers[0]);
			sellerPassword.add(sellers[1]);
		}
		
		
		
		Scanner s1 = new Scanner(System.in);
		System.out.println("Enter Username : ");
		String username = s1.next();
		
		Scanner s2 = new Scanner(System.in);
		System.out.println("Enter Password : ");
		String password = s2.next();
		
		Scanner s3 = new Scanner(System.in);
		System.out.println("Enter type of user  (0 for buyer and 1 for seller): ");
		int userType = s3.nextInt();
		
		u.userName = username;
		u.userType = userType;
		
		boolean authentic_buyer = false;
		boolean authentic_seller = false;
		
		for(int i=0;i<buyerName.size();i++)
		{
			if(username.equals(buyerName.get(i)) && password.equals(buyerPassword.get(i)))
			{
				authentic_buyer = true;
			}
		}
		
		for(int i=0;i<sellerName.size();i++)
		{
			if(username.equals(sellerName.get(i)) && password.equals(sellerPassword.get(i)))
			{
				authentic_seller = true;
			}
		}

		if(userType == 0)
		{
			if(authentic_buyer == true)
			{
				System.out.print("\n User Type : Buyer");				
			}
			else
			{
				System.out.print("\n User with username and/or password doesn't exist! Try again.");
				System.exit(0);
			}
			Person buyer = new Buyer(new MeatProductMenu());
			//Bridge Pattern Implementation
			System.out.print("\n ------------Bridge Pattern Implementation---------------");
			buyer.add();
			buyer.showMenu();
		}
		else if(userType == 1)
		{
			if(authentic_seller == true)
			{
				System.out.print("\n User Type: Seller");
			}
			else
			{
				System.out.print("\n User with username and/or password doesn't exist");				
				System.exit(0);
			}
			Person seller = new Seller(new MeatProductMenu());
			//Bridge Pattern Implementation
			System.out.print("\n ------------Bridge Pattern Implementation---------------");
			seller.showMenu();
		}
		else
		{
			System.out.print("\n Given type of user is not available. Please try again");
			System.exit(0);
		}
		return u;
	}

}