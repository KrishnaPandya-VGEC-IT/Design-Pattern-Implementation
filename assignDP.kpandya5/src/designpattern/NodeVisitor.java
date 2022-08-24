package designpattern;

public abstract class NodeVisitor 
{
	public abstract void visitProduct(ReminderVisitor visitor);
	public abstract void visitTrading(ReminderVisitor visitor);
	public abstract void visitFacade(Facade facade);
	
}
