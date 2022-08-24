package designpattern;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Buyer extends Person
{
	public Buyer(ProductMenu productmenu) {
		super(productmenu);
	}

	@Override
	public void showMenu() 
	{
		
	}

	@Override
	public ProductMenu CreateProductMenu() {
		return null;
	}

	//Method to add trading to database file for bidding
	@Override
	public void addTrading(String username, String ProductName) throws IOException {
		
		int bid = decideBidding();
		boolean confirm_bidding = discussBidding();
		boolean acceptable = return_acceptable(bid,username,ProductName);
		if(acceptable == false)
		{
			System.out.print("\n Discarding the bid because higher bid/baseprice is available for the same product!");
		}
		else if(confirm_bidding)
		{
			submitOffering(username, ProductName, bid);
		}
		else
		{
			System.out.print("\n Bid Discarded.");
		}
	}

	//checks whether bid is acceptable or not. (Lower bid than previous one is not acceptable)
	@SuppressWarnings("resource")
	public boolean return_acceptable(int bid, String username, String ProductName) throws  IOException
	{
		File file = new File("src\\designpattern\\Database.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String>userNames = new ArrayList<>();
		ArrayList<Integer>userTypes = new ArrayList<>();
		ArrayList<String>productNames = new ArrayList<>();
		ArrayList<Integer>bids = new ArrayList<>();
		int max_bid_val=0;
		boolean ans = true;
		String s;
		
		while((s = br.readLine())!=null)
		{
			String[] lines = s.split(" ");
			userNames.add(lines[0]);
			userTypes.add(Integer.parseInt(lines[1]));
			productNames.add(lines[2]);
			bids.add(Integer.parseInt(lines[3]));
		}
		
		for(int i=0;i<productNames.size();i++)
		{
			if(ProductName.equals(productNames.get(i)) && bids.get(i)>=max_bid_val)
			{
				max_bid_val = bids.get(i);
			}
		}
		
		if(bid<=max_bid_val)
		{
			ans = false;
		}
		return ans;
	}
	
	//Checks for highest bids of a product and displays them
	@Override
	public void viewTrading() throws FileNotFoundException,IOException
	{
		File file = new File("src\\designpattern\\Database.txt");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String>userNames = new ArrayList<>();
		ArrayList<Integer>userTypes = new ArrayList<>();
		ArrayList<String>productNames = new ArrayList<>();
		ArrayList<Integer>bids = new ArrayList<>();
		
		String s;
		
		while((s = br.readLine())!=null)
		{
			String[] lines = s.split(" ");
			userNames.add(lines[0]);
			userTypes.add(Integer.parseInt(lines[1]));
			productNames.add(lines[2]);
			bids.add(Integer.parseInt(lines[3]));
		}

		System.out.print("\n\n Viewing Trading for Buyer\n");
		System.out.print("\n User \t Product \t Bid");
		for(int i=0;i<productNames.size();i++)
		{
			if(userTypes.get(i) == 0)
			{
				System.out.print("\n "+userNames.get(i)+"\t"+productNames.get(i)+"\t"+bids.get(i));
			}
		}
	}

	//Decides value for bidding
	@SuppressWarnings("resource")
	@Override
	public int decideBidding() {
		Scanner sc = new Scanner(System.in);
		int bid = 0;
		System.out.print("\n Enter Bid Amount : ");				
		bid = sc.nextInt();
		return bid;
	}

	//to Confirm bidding (Synonym of MarkOffering)
	@SuppressWarnings("resource")
	@Override
	public boolean discussBidding() 
	{
		boolean assume = true;
		System.out.print("\n Do you want to mark the Bidding? type yes or no : \n");
		Scanner sc = new Scanner(System.in);
		String answer = sc.nextLine();
		if(answer.equalsIgnoreCase("NO"))
		{
			assume = false;
		}
		else if(answer.equalsIgnoreCase("YES"))
		{
			assume = true;
		}
		else
		{
			System.out.print("Your Entered input is not matching option. Assuming Yes.");
		}
		return assume;
	}

	//Adds the offering to database file and also dynamically updates userInfo
	@SuppressWarnings("resource")
	@Override
	public void submitOffering(String userName, String ProductName, int BidAmount) throws IOException 
	{
		FileWriter fw = new FileWriter("src\\designpattern\\Database.txt",true);
		String s = userName+" "+Integer.toString(0)+" "+ProductName+" "+Integer.toString(BidAmount)+"\n";
		fw.flush();
		fw.write(s);
		fw.close();
		
		File file = new File("src\\designpattern\\Database.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String>userNames = new ArrayList<>();
		ArrayList<Integer>userTypes = new ArrayList<>();
		ArrayList<String>productNames = new ArrayList<>();
		ArrayList<Integer>bids = new ArrayList<>();
		
		String second_s;
		
		while((second_s = br.readLine())!=null)
		{
			String[] lines = second_s.split(" ");
			userNames.add(lines[0]);
			userTypes.add(Integer.parseInt(lines[1]));
			productNames.add(lines[2]);
			bids.add(Integer.parseInt(lines[3]));
		}
		
		File userProduct = new File("src\\designpattern\\UserProduct.txt");
		if(userProduct.createNewFile())
		{
//			 System.out.print("\n UserProduct file created!");
		}
		else
		{
//			 System.out.print("\n UserProduct file already Exists!");
		}	
		FileWriter writer = new FileWriter(userProduct);
		FileWriter appendWriter = new FileWriter(userProduct,true);
		writer.write("");
		
		ArrayList<String>done_products = new ArrayList<>();

		String to_write = "";
		for(int i=0;i<productNames.size();i++)
		{
			String product_checkname = productNames.get(i);
			int max_bid = bids.get(i);
			String owner_name = userNames.get(i);
			for(int j=i+1;j<productNames.size();j++)
			{
				if(product_checkname.equals(productNames.get(j)) && bids.get(j)>max_bid)
				{
					max_bid = bids.get(j);
					owner_name = userNames.get(j);
				}
			}
			if(!done_products.contains(product_checkname))
			{
				to_write+=owner_name+":"+product_checkname+"\n";
				appendWriter.write(to_write);
				done_products.add(product_checkname);
			}
		}	
		writer.flush();
		appendWriter.flush();
		writer.close();
		appendWriter.close();
	}
}
