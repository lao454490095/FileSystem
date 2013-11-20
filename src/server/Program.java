package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import data.movieService;
import data.movieServiceImpl;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;
import movie.Movie;
import java.util.List;
import java.util.LinkedList;

public class Program 
{
	public static void main(String[] args)
	{
		try
		{
			movieService movieservice=new movieServiceImpl();
			System.out.println(movieservice);
			LocateRegistry.createRegistry(8000);
			Naming.rebind("rmi://127.0.0.1:8000/TicketManager",movieservice);
			System.out.println("service start!");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
