package ca2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializationUtility 
{
	public static void save(String path, String name, Object obj)
	{
		try
		{
			//handle to a file output stream
			FileOutputStream fos = new FileOutputStream(path + name);	
			//handle to an object output stream
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//write the object to the stream
			oos.writeObject(obj);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Object load(String path, String name)
	{
		Object obj = null;
		
		try
		{
			FileInputStream fis = new FileInputStream(path + name);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return obj;
	}
}



















