package designpattern;
import java.io.*;
import java.util.ArrayList;

public class ProductIterator extends ArrayListIterator{

	ArrayList<String> products = new ArrayList<String>();
	
	public ProductIterator() throws IOException
	{
		File file = new File("src\\designpattern\\ProductInfo");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String s;
		while((s = br.readLine()) != null)
		{
			products.add(s);
		}
		br.close();
	}
	
	
	private class Product implements ListIterator
	{
		int pos;
		@Override
		public boolean hasNext() {
			if(pos<products.size())
			{
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext())
			{
				return products.get(pos++);
			}
			return null;
		}
		
	}
	
	@Override
	public boolean hasNext() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ListIterator getIterator() {
		return new Product();
	}
}
