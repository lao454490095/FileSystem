package movie;

import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;


public class Movie implements java.io.Serializable {
	public String name;
	//Set<String> taps = new HashSet<String>();	//default: a set of 10 
	public String tap = new String();
	public int price;
	public int time;	//minutes
	
	public Movie(String name, int price, int time, String tap) {
		this.name = name;
		this.tap=tap;
		this.price = price;
		this.time = time;
	}
}