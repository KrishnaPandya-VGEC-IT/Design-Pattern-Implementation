package designpattern;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Person 
{
	protected ProductMenu pm;
	
	ProductMenu theProductMenu;
	
	public Person(ProductMenu productmenu)
	{
		
	}
	
	public abstract void showMenu();
	
	public void add()
	{
		System.out.print("\n Adding to productMenu.");
	}
	
	public void showAddButton()
	{
		System.out.print("\n Showing Add Button!");
	}
	
	public void showViewButton()
	{
		System.out.print("\n Showing View Button!");		
	}
	
	public void showRadioButton()
	{
		System.out.print("\n Showing Radio Button!");		
	}
	 
	public void showLabels()
	{
		System.out.print("\n Showing Labels!");		
	}
	public abstract void addTrading(String username, String ProductName) throws IOException;
	
	public abstract void viewTrading() throws FileNotFoundException, IOException;
	
	public abstract int decideBidding(); 
	
	public abstract boolean discussBidding();
	
	public abstract void submitOffering(String userName, String ProductName, int BidAmount) throws IOException;

	public abstract ProductMenu CreateProductMenu();
}
