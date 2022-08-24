package designpattern;
import java.io.IOException;

//interface to log in the system used by Login.java

public interface LoginInterface 
{
	public User loginMethod() throws IOException;
}
