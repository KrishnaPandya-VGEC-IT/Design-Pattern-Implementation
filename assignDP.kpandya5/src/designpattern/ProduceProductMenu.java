package designpattern;

public class ProduceProductMenu implements ProductMenu{

	@Override
	public void showMenu() {
		System.out.print("\n Showing the produce product menu!");
	}

	@Override
	public void showAddButton() {
		System.out.print("\n Adding Product to ProduceProductMenu!");
	}

	@Override
	public void showViewButton() {
		System.out.print("\n Showing view button!");		
	}

	@Override
	public void showRadioButton() {
		System.out.print("\n Showing radio button!");
	}

	@Override
	public void showLabels() {
		System.out.print("\n Showing labels!");	}

	@Override
	public void showComboxes() {
		System.out.print("\n Showing comboxes!");
	}

}
