package frames;

import java.io.EOFException;
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

import movie.Movie;
import movie.Hall;
import movie.Play;
import movie.PlayTimeComparator;

class MovieComparator implements Comparator <Movie> {
	public int compare(Movie m1, Movie m2) {
		return m1.name.compareTo(m2.name);
	}
}


class PlayHallComparator implements Comparator <Play> {
	public int compare(Play p1, Play p2) {
		if(p1.hallNumber==p2.hallNumber) {
			return p1.calendar.compareTo(p2.calendar);
		}
		else {
			if(p1.hallNumber>p2.hallNumber)
				return 1;
			else
				return -1;
		}
	}
}
class PlayNameComparator implements Comparator <Play> {
	public int compare(Play p1, Play p2) {
		if (p1.movie.name.compareTo(p2.movie.name) == 0) {
			return (p1.calendar.compareTo(p2.calendar));
		}
		else {
			return p1.movie.name.compareTo(p2.movie.name);
		}
	}
}

/**
 * 
 * @author xsq
 * 这是一个静态方法类 用于提供文件io的方法
 */
class MyIO {
	/**
	 * 该方法用于输出储存电影信息的线性表
	 * @param list	要输出的影厅线性表
	 * @throws IOException
	 */
	public static void outputMovies(List<Movie> list) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("movies.dat"));
		for(int i = 0; i < list.size(); i++) {
			output.writeObject(list.get(i));
		}
		output.close();
	}
	
	/**
	 * 该方法用于输出储存影厅信息的线性表
	 * @param list	要输出的影厅的线性表
	 * @throws IOException
	 */
	public static void outputHalls(List<Hall> list) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("halls.dat"));
		for(int i = 0; i < list.size(); i++) {
			output.writeObject(list.get(i));
		}
		output.close();
	}
	/**
	 * 该方法用于读入电影信息
	 * @param list	用于储存电影信息的线性表
	 * @return	返回读入电影的个数
	 * @throws ClassNotFoundException
	 */
	public static int inputMovies(List<Movie> list)
			throws ClassNotFoundException {
		try 
		{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("movies.dat"));
			list=(List<Movie>)input.readObject();
			System.out.println(list);
		}
		catch(EOFException ex) {
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return list.size();
	}
	
	/**
	 * 该方法用于读入影厅信息
	 * @param list	用于储存影厅信息的线性表
	 * @return	返回一共读入的影厅个数
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static int inputHalls(List<Hall> list) throws ClassNotFoundException {
		try 
		{
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("halls.dat"));
			list=(LinkedList<Hall>)input.readObject();
		}
		catch(EOFException ex) 
		{
		
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return list.size();
	}
	public static void outputMoviesAsArray(List<Movie> l) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("movies.dat"));
		output.writeObject(l);
		output.close();
	}

	public static void outputHallAsArray(List<Hall> list) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("halls.dat"));
		//int n = list.size();
		//Hall[] l = new Hall[n];
		//for(int i = 0; i < list.size(); i++) {
		//	l[i] = list.get(i);
			//output.writeObject(l[i]);
		//}
		List<Hall> l = new LinkedList<Hall>();
		l.addAll(list);
		output.writeObject(l);
		output.close();
	}

//another inputMovies as array
	public static int inputMoviesAsArray(List<Movie> l) throws ClassNotFoundException {
		int i = 0;
		try {
			ObjectInputStream input = new ObjectInputStream( new FileInputStream("halls.dat"));
			//Movie temp;
			//for( i = 0; true ; i++) {
			//	temp = (Movie)input.readObject();
			//	l[i] = temp;
			//}
			//l = ( Movie[] )input.readObject();
			l = (List<Movie>)input.readObject();
			i = l.size();
			return i;
		}catch(IOException ex)
		{}
		catch(ClassNotFoundException ee){
			
		}
		return i;
		}

// another inputHalls as array 
	public static int inputHallsAsArray(List<Hall> l) throws ClassNotFoundException {
		int i=0;
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("halls.dat"));
			//Hall temp;
			//for(i=0;true;i++) {
			//	temp = (Hall)input.readObject();
			//	l[i]=temp;
			//}
			l = (List<Hall>)input.readObject();
			i = l.size();
		}
		catch (EOFException ex) {
			
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			return i;
		}
	}
}

/**
 * 
 * @author xsq
 * 这是一个静态方法类，提供操作的接口
 *
 */
class Operation {
	//以下三个用于操作电影
	//添加电影
	/**
	 * 该方法用于添加电影
	 * @param list	原有电影线性表的引用
	 * @param name	所添加电影的名称
	 * @param price	所添加电影的价格
	 * @param time	所添加电影的时长
	 * @param tap	所添加电影的标签
	 */
	public static void addMovie(List<Movie> list, String name, int price, int time, String tap) {
		list.add(new Movie(name,price,time,tap));
		Collections.sort(list,new MovieComparator());
	}
	
	//删除电影
	/**
	 * 改方法用于删除电影
	 * @param list		原有电影线性表的引用		
	 * @param index		From 0 to n 		
	 */
	public static void removeMovie(List<Movie> list, int index) {
		list.remove(index);
	}
	
	//得到一个电影的信息
	/**
	 * 得到一个电影的信息
	 * @param list		原有电影线性表的引用
	 * @param index		From 0 to n 
	 * @param s			s引用得到电影的信息
	 */
	public static String[] getMessage(List<Movie> list, int index) {
		String[] s = new String[4];
		s[0]="名称: "+list.get(index).name;
		s[1]="类型: "+list.get(index).tap;
		s[2]="价格: "+list.get(index).price;
		s[3]="时间: "+list.get(index).time;
		return s;
	}
	
	//以下方法用于管理场次
	//场次信息获得方法：
	/*
	 * Play p;
	 * 影片名称 String p.movie.name
	 * 影片类型 String p.movie.tap
	 * 影片价格 int 	p.movie.price
	 * 影片时长int 	p.movie.time
	 * 场次余票int 	p.ticketLeft
	 * 场次影厅int 	p.hallNumber
	 * 场次座位表boolean[10][10] p.tickets			//true 表示已售出  false 表示代售
	 */
	
	//添加场次
	/**
	 * 该方法用于添加场次
	 * @param list		原有电影线性表的引用
	 * @param hallNumber		添加场次所在影厅编号
	 * @param year		添加场次时间的年
	 * @param month		添加场次时间的月
	 * @param date		添加场次时间的日
	 * @param hour		添加场次时间的时
	 * @param minute	添加场次时间的分
	 * @param m			添加场次的电影
	 */
	public static void addPlay(List<Hall> list,int hallNumber, int year, int month, int date, int hour, int minute, Movie m) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_YEAR, date);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		Play temp = new Play(m,c,hallNumber);
		list.get(hallNumber).playList.add(temp);
		Collections.sort(list.get(hallNumber).playList, new PlayTimeComparator());
	}
	public static void addPlay(List<Hall> list,int hallNumber,Calendar c, Movie m) {
		Play temp = new Play(m,c,hallNumber);
		list.get(hallNumber).playList.add(temp);
		Collections.sort(list.get(hallNumber).playList, new PlayTimeComparator());
	}
	
	//删除场次
	/**
	 * 该方法用于删除场次
	 * @param list		原有电影线性表的引用
	 * @param p			要删去的场次
	 */
	public static void removePlay(List<Hall> list, Play p) {
		int i;
		int j;
		for( i=0; i<list.size(); i++) {
			for( j=0; j<list.get(i).playList.size(); j++)
				if(list.get(i).playList.get(j) == p) {
					list.get(i).playList.remove(j);
					return;
				}
		}
	}
	
	//影厅排列
	/**
	 * 该方法用于影厅排列 依据影厅排列
	 * @param hallList	原有的影厅线性表
	 * @param playList	用于储存排好序的场次线性表
	 */
	public static void playSortByHall (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayHallComparator());
	}
	
	//时间排列
	/**
	 * 该方法用于影厅排列 依据时间排列
	 * @param hallList	原有的影厅线性表
	 * @param playList	用于储存排好序的场次线性表
	 */
	public static void playSortByTime (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayTimeComparator());
	}
	
	/**
	 * 该方法用于影厅排列 依据电影排列
	 * @param hallList	原有的影厅线性表
	 * @param playList	用于储存排好序的场次线性表
	 */
	public static void playSortByName (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayNameComparator());
	}
	
	/**
	 * 
	 * @param index	从0到3，分别表示按照影厅、时间、电影名称排序
	 * @param hallList		原有影厅的线性表
	 * @param playList		用于储存排序后的场次的线性表
	 */
	public static void sort(int index, List<Hall> hallList, List<Play> playList) {
		if(index == 0) {
			playSortByHall(hallList,playList);
		}
		else if(index == 1) {
			playSortByTime(hallList,playList);
		}
		else if(index == 2) {
			playSortByName(hallList,playList);
		}
	}
	
	//以下 售票
	/**
	 * 该方法用于提供售票功能
	 * @param p	代售票所在场次
	 * @param x	代售票座位的行
	 * @param y	代售票座位的列
	 * @return	如果成功售出返回true
	 */
	public static boolean sellTicket(Play p, int x, int y) {
		if(p.tickets[x][y]) {
			return false;
		}
		else {
			p.tickets[x][y] = true;
			return true;
		}
	}
}


public class TicketManager {
	public static void main(String args[]) {
		Movie m = new Movie("sb",10,200,"悬疑");
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
	}

}
