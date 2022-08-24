package designpattern;
import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		boolean cont = true;
		Scanner sc = new Scanner(System.in);
		while(cont) 
		{
			Facade f = new Facade();
			f.login(); //login method of facade
			
			ProductIterator productIterator = new ProductIterator();
			
			//Iterator Pattern Implementation
			System.out.print("\n ------------------Implemented Iterator Pattern -----\n");
			
			System.out.print("\n Select Product (in integer) : ");
			
			ListIterator iterator;
			int i=1;
			
			for(iterator = productIterator.getIterator();iterator.hasNext();)
			{
				String product = (String)iterator.next();
				System.out.print("\n "+i+". "+product);
				i+=1;
			}
			
			f.attachProductToUser();
			
			//Visitor Pattern Implementation
			System.out.print("\n--------------------Implemented Visitor Pattern ----------\n");
			NodeVisitor nodevisitor = new Reminder();
			
			nodevisitor.visitProduct(new ClassProductList());
			nodevisitor.visitTrading(new ClassProductList());
			
			System.out.print("\n Do you want to exit or login again? Press -1 to exit and 1 to login again!\n");
			int op = sc.nextInt();
			
			switch(op)
			{
				case -1:
					System.out.print("\n Exiting from program!");
					System.exit(0);
					break;

				case 1:
					break;					
					
				default:
					System.out.print("\n Entered option is not available. Assuming relogin");
					break;
			}
		}
	}
}
