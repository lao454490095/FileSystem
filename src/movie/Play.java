package movie;

import java.util.Calendar;

public class Play implements java.io.Serializable {	//³¡´Îclass
	public Movie movie;
	public Calendar calendar;
	private int ticketLeft;
	public boolean[][] tickets = new boolean[10][10];
	public int hallNumber;
	public Play(Movie m,Calendar c,int n) {
		this.movie = m;
		this.calendar = c;
		ticketLeft = 10*10;
		hallNumber = n;
	}
	
	public Play() {
		ticketLeft = 10*10;
	}
	//method :
	public boolean ticketSell(int x, int y) {
		if(tickets[x][y]){
			return false;
		}
		else {
			tickets[x][y]=true;
			ticketLeft--;
			return true;
		}
	}
	public int ticketLeft() {
		return ticketLeft;
	}
}