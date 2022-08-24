package designpattern;

public class Reminder extends NodeVisitor
{

	@Override
	public void visitProduct(ReminderVisitor visitor) 
	{
		System.out.print("\n Visiting Product");
	}

	@Override
	public void visitTrading(ReminderVisitor visitor) {
		System.out.print("\n Visiting Trading");
	}

	@Override
	public void visitFacade(Facade facade) {
		System.out.print("\n Visiting Facade");
	}

}
