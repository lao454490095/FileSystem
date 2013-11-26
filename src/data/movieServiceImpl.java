package data;

/*import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

class Movie implements java.io.Serializable {
	public String name;
	// Set<String> taps = new HashSet<String>(); //default: a set of 10
	public String tap = new String();
	public int price;
	public int time; // minutes

	public Movie(String name, int price, int time, String tap) {
		this.name = name;
		this.tap = tap;
		this.price = price;
		this.time = time;
	}
}

public class TicketManager {
	public static void main(String args[]) {
		List<Movie> l = new LinkedList<Movie>();
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(new FileInputStream("movies2.dat"));
			// m = (Movie)input.readObject();
			l = (List<Movie>) input.readObject();
			input = new ObjectInputStream(new FileInputStream("halls2.dat"));
		} catch (IOException ex) {
		} catch (ClassNotFoundException ex) {
		}
		System.out.println(l);
		System.out.println(l.get(0).name);

	}
}*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import data.movieService;
import movie.Movie;
import movie.Hall;
import movie.Play;


public class movieServiceImpl extends UnicastRemoteObject implements movieService{
	private ObjectInputStream fromFile;
	private ObjectInputStream fromFile2;
	private ObjectOutputStream toFile;
	private List<Movie> l;
	private List<Hall> l2;
	/*public static void main(String args[]) {
	}*/
/*	public static void main(String args[]) {
		Movie m = new Movie("sb",10,100,"悬疑");
		List<Hall> hl = new LinkedList<Hall>();
		for(int i = 0; i < 20 ;i++) {
			Hall h = new Hall();
			h.addNewPlay(new Play(m,new GregorianCalendar(),i));
			hl.add(h);
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("halls.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeObject(hl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public List<Movie> distributeMovie() throws RemoteException
	{	
		return l;
	}
	public List<Hall> distributeHall() throws RemoteException
	{	
		return l2;
	}
	public void getMvoie(List<Movie> list) throws RemoteException
	{
		l=list;
	}
	public void getHall(List<Hall> list) throws RemoteException
	{
		l2=list;
	}
	
	public void writeBack() throws RemoteException
	{
		try
		{
		toFile = new ObjectOutputStream(new FileOutputStream("movies.dat"));
		toFile.writeObject(l);
		toFile = new ObjectOutputStream(new FileOutputStream("halls.dat"));
		toFile.writeObject(l2);
		}catch (IOException ee) {
			System.out.println("IOException111");
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public movieServiceImpl() throws RemoteException
	{
		super();
		l = new LinkedList<Movie>();
		l2 = new LinkedList<Hall>();
		try 
		{
			// l=new LinkedList<Movie>();
			fromFile = new ObjectInputStream(new FileInputStream("movies.dat"));
			System.out.println("before cast");
			l = (List<Movie>)fromFile.readObject();
			//服务器发送过来的是一个异常
			System.out.println(l);
			System.out.println("after cast");
			fromFile2= new ObjectInputStream(new FileInputStream("halls.dat"));
			l2 = (List<Hall>)fromFile2.readObject();
			System.out.println(l2);
		} catch (IOException ee) {
			System.out.println("IOException111");
		} catch (ClassNotFoundException eee) {
			System.out.println("ClassNotFoundException111");
		}
	}

}
