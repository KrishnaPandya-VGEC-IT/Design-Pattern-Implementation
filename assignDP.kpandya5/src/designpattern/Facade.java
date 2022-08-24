package designpattern;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


//Facade pattern: provides single interface to general facilities.

public class Facade 
{	
	User user_cred = new User();
	ArrayList<String>buyers_product = new ArrayList<String>();
	ArrayList<String>theProductList = new ArrayList<String>();
	ArrayList<Integer>theProductType = new ArrayList<>();

	private LoginInterface user;
	
	public Facade() throws IOException
	{
		createDataBase();
		createProductList();
		user = new Login();
	}
	
	public void createDataBase() throws IOException
	{
		 File database = new File("src\\designpattern\\Database.txt");
		 if(database.createNewFile())
		 {
//			 System.out.print("\n Database file created!");
		 }
		 else
		 {
//			 System.out.print("\n File already Exists!");
		 }
	}
	
	public void login() throws IOException
	{
		System.out.print("\n ---- Facade Pattern Implementation!-----\n");
		user_cred = user.loginMethod();
	}
	
	
	public void remind()
	{
		System.out.print("\n Reminding user!");
	}
	
	
	//Create Product List from the ProductInfo file.
	public void createProductList() throws IOException
	{
		File file = new File("src\\designpattern\\ProductInfo");
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String s;
		
		while((s = br.readLine())!=null)
		{	
			String two_things[] = s.split(":");
			theProductType.add((two_things[0].equals("Meat"))?0:1);
			theProductList.add(two_things[1]);
		}
	}
	
	//Attach Product List method to user
	@SuppressWarnings("resource")
	public void attachProductToUser() throws IOException
	{
		File file = new File("src\\designpattern\\ProductInfo");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String s;
		
		while((s = br.readLine())!=null)
		{
			String[] buyers = s.split(":");
			buyers_product.add(buyers[0]);
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n Enter product : ");
		int product = sc.nextInt();
		
		if(product>buyers_product.size())
		{
			System.out.print("\n Please Enter valid product number. The last product is of number : "+buyers_product.size());
			System.exit(0);
		}
		System.out.print("\n Select option (In given integer): ");
		System.out.print("\n 1.Add Trading");
		System.out.print("\n 2.View Trading\n");
		int productOperation = sc.nextInt();
		
		int nProductCategory = theProductType.get(product-1);
		
		selectProduct(theProductList.get(product-1));
		
		Buyer buy = new Buyer(null);
		Seller se = new Seller(null);
		
		if(productOperation == 1)
		{
			ProductFactory pf = new ProductFactory();
			if(nProductCategory == 0)
			{
				ProductMenu pm = pf.getProductType("MeatProduct");
				pm.showAddButton();	
				if(user_cred.userType == 0)
				{
					buy.addTrading(user_cred.userName, theProductList.get(product-1));
				}
				else
				{
					se.addTrading(user_cred.userName, theProductList.get(product-1));
				}
			}
			else if(nProductCategory == 1)
			{
				ProductMenu pm1 = pf.getProductType("ProduceProduct");
				pm1.showAddButton();
				if(user_cred.userType == 0)
				{
					buy.addTrading(user_cred.userName, theProductList.get(product-1));
				}
				else
				{
					se.addTrading(user_cred.userName, theProductList.get(product-1));
				}
			}
		}
		else if(productOperation == 2)
		{
			ProductFactory pf1 = new ProductFactory();
			if(nProductCategory == 0)
			{
				ProductMenu pm = pf1.getProductType("MeatProduct");
				System.out.print("\n-------------Implemented Factory Pattern to identify the type of Product--------");
				pm.showViewButton();
				if(user_cred.userType == 0)
				{
					buy.viewTrading();
				}
				else
				{
					se.viewTrading();
				}			
			}
			else if(nProductCategory == 1)
			{
				ProductMenu pm1 = pf1.getProductType("ProduceProduct");
				System.out.print("\n-------------Implemented Factory Pattern to identify the type of Product--------");
				pm1.showViewButton();
				if(user_cred.userType == 0)
				{
					buy.viewTrading();
				}
				else
				{
					se.viewTrading();
				}			
			}
		}
		else
		{
			System.out.print("\n There are only two available options!Try again!");
			System.exit(0);
		}
	}
	
	public void selectProduct(String product)
	{
		System.out.print("\n Selected Product : "+product);
	}
	
	public void productOperation()
	{
		
	}
}
