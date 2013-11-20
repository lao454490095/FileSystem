package data;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import movie.Movie;
import movie.Hall;

public interface movieService extends Remote{
	public List<Movie> getMovie() throws RemoteException;
	public List<Hall> getHall() throws RemoteException;
}


