package Utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Stack;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Models.User;
import Models.Movie;
import Models.Ratings;

public class XMLSerializer implements  Serializer{
	
	private Stack<Object> stack = new Stack<Object>();
	private File datastore;
	
	
	

	public void push(Object movieInfo) {
		stack.push(movieInfo);
	}
	
	public Object pop() {
		return stack.pop();
	}
	
	public void	read() throws Exception{
		ObjectInputStream is = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			is = xstream.createObjectInputStream(new FileReader(datastore));
			Object obj = is.readObject();
			while(obj != null) {
				stack.push(obj);
				obj= is.readObject();
			}
		}
		finally {
			if(is != null)
			{
				is.close();
			}
		}
}
	
	public void	write() throws Exception{
		ObjectOutputStream os = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			os = xstream.createObjectOutputStream(new FileWriter(datastore));
			os.writeObject(stack);

		}
			finally {
				if(os !=null) {
					os.close();
				}
			}
		}

	public List<Ratings> loadRatings(String string) {
		
		return null;
	}

	public List<Movie> loadmovies(String string) {
		
		return null;
	}

	public List<User> loadUsers(String string) {
		
		return null;
	}
}
