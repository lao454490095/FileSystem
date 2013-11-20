package movie;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import movie.Play;
import movie.PlayTimeComparator;

public class Hall implements java.io.Serializable {
	//public boolean seats[][] = new boolean[64][64];
	public List<Play> playList = new LinkedList<Play>();	//maybe more
	public boolean addNewPlay(Play p) {
		boolean temp = playList.add(p);
		Collections.sort(playList, new PlayTimeComparator());
		return temp;
	}
}