package designpattern;
public class ClassProductList implements ReminderVisitor{
	public void accept(NodeVisitor visitor)
	{
		
	}

	@Override
	public void visitor(Reminder reminder) {
		System.out.print("\n Implemented Visitor Pattern");
	}
}
