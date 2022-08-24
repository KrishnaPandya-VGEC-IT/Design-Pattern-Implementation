package designpattern;

public class ProductFactory 
{
	public ProductMenu getProductType(String product_type)
	{
		if(product_type == null)
		{
			return null;
		}
		if(product_type.equalsIgnoreCase("ProduceProduct"))
		{
			return new ProduceProductMenu();
		}
		if(product_type.equalsIgnoreCase("MeatProduct"))
		{
			return new MeatProductMenu();
		}
		return null;
	}
}
