package designpattern;
public class MeatProductMenu implements ProductMenu
{
	@Override
	public void showMenu() {
		System.out.print("\n Showing the meat product menu!");
	}

	@Override
	public void showAddButton() {
		System.out.print("\n Added Product to MeatProductMenu!");
	}

	@Override
	public void showViewButton() {
		System.out.print("\n Viewing Product of MeatProductMenu!");		
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
