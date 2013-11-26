package data;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import movie.Movie;
import movie.Hall;

public interface movieService extends Remote{
	public List<Movie> distributeMovie() throws RemoteException;
	public List<Hall> distributeHall() throws RemoteException;
	public void writeBack() throws RemoteException;
	public void getMvoie(List<Movie> list) throws RemoteException;
	public void getHall(List<Hall> list) throws RemoteException;
}


