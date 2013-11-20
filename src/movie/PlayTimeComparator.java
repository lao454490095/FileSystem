package movie;

import java.util.Comparator;

import movie.Play;

public class PlayTimeComparator implements Comparator <Play> {
	public int compare(Play p1, Play p2) {
		if( p1.calendar.compareTo(p2.calendar)==0) {
			return (p1.movie.name.compareTo(p2.movie.name));
		}
		else {
			return p1.calendar.compareTo(p2.calendar);
		}
	}
}