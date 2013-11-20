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
 * ����һ����̬������ �����ṩ�ļ�io�ķ���
 */
class MyIO {
	/**
	 * �÷���������������Ӱ��Ϣ�����Ա�
	 * @param list	Ҫ�����Ӱ�����Ա�
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
	 * �÷��������������Ӱ����Ϣ�����Ա�
	 * @param list	Ҫ�����Ӱ�������Ա�
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
	 * �÷������ڶ����Ӱ��Ϣ
	 * @param list	���ڴ����Ӱ��Ϣ�����Ա�
	 * @return	���ض����Ӱ�ĸ���
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
	 * �÷������ڶ���Ӱ����Ϣ
	 * @param list	���ڴ���Ӱ����Ϣ�����Ա�
	 * @return	����һ�������Ӱ������
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
 * ����һ����̬�����࣬�ṩ�����Ľӿ�
 *
 */
class Operation {
	//�����������ڲ�����Ӱ
	//��ӵ�Ӱ
	/**
	 * �÷���������ӵ�Ӱ
	 * @param list	ԭ�е�Ӱ���Ա������
	 * @param name	����ӵ�Ӱ������
	 * @param price	����ӵ�Ӱ�ļ۸�
	 * @param time	����ӵ�Ӱ��ʱ��
	 * @param tap	����ӵ�Ӱ�ı�ǩ
	 */
	public static void addMovie(List<Movie> list, String name, int price, int time, String tap) {
		list.add(new Movie(name,price,time,tap));
		Collections.sort(list,new MovieComparator());
	}
	
	//ɾ����Ӱ
	/**
	 * �ķ�������ɾ����Ӱ
	 * @param list		ԭ�е�Ӱ���Ա������		
	 * @param index		From 0 to n 		
	 */
	public static void removeMovie(List<Movie> list, int index) {
		list.remove(index);
	}
	
	//�õ�һ����Ӱ����Ϣ
	/**
	 * �õ�һ����Ӱ����Ϣ
	 * @param list		ԭ�е�Ӱ���Ա������
	 * @param index		From 0 to n 
	 * @param s			s���õõ���Ӱ����Ϣ
	 */
	public static String[] getMessage(List<Movie> list, int index) {
		String[] s = new String[4];
		s[0]="����: "+list.get(index).name;
		s[1]="����: "+list.get(index).tap;
		s[2]="�۸�: "+list.get(index).price;
		s[3]="ʱ��: "+list.get(index).time;
		return s;
	}
	
	//���·������ڹ�����
	//������Ϣ��÷�����
	/*
	 * Play p;
	 * ӰƬ���� String p.movie.name
	 * ӰƬ���� String p.movie.tap
	 * ӰƬ�۸� int 	p.movie.price
	 * ӰƬʱ��int 	p.movie.time
	 * ������Ʊint 	p.ticketLeft
	 * ����Ӱ��int 	p.hallNumber
	 * ������λ��boolean[10][10] p.tickets			//true ��ʾ���۳�  false ��ʾ����
	 */
	
	//��ӳ���
	/**
	 * �÷���������ӳ���
	 * @param list		ԭ�е�Ӱ���Ա������
	 * @param hallNumber		��ӳ�������Ӱ�����
	 * @param year		��ӳ���ʱ�����
	 * @param month		��ӳ���ʱ�����
	 * @param date		��ӳ���ʱ�����
	 * @param hour		��ӳ���ʱ���ʱ
	 * @param minute	��ӳ���ʱ��ķ�
	 * @param m			��ӳ��εĵ�Ӱ
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
	
	//ɾ������
	/**
	 * �÷�������ɾ������
	 * @param list		ԭ�е�Ӱ���Ա������
	 * @param p			Ҫɾȥ�ĳ���
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
	
	//Ӱ������
	/**
	 * �÷�������Ӱ������ ����Ӱ������
	 * @param hallList	ԭ�е�Ӱ�����Ա�
	 * @param playList	���ڴ����ź���ĳ������Ա�
	 */
	public static void playSortByHall (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayHallComparator());
	}
	
	//ʱ������
	/**
	 * �÷�������Ӱ������ ����ʱ������
	 * @param hallList	ԭ�е�Ӱ�����Ա�
	 * @param playList	���ڴ����ź���ĳ������Ա�
	 */
	public static void playSortByTime (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayTimeComparator());
	}
	
	/**
	 * �÷�������Ӱ������ ���ݵ�Ӱ����
	 * @param hallList	ԭ�е�Ӱ�����Ա�
	 * @param playList	���ڴ����ź���ĳ������Ա�
	 */
	public static void playSortByName (List<Hall> hallList, List<Play> playList) {
		for(int i=0; i<hallList.size(); i++) {
			playList.addAll(hallList.get(i).playList);
		}
		Collections.sort(playList, new PlayNameComparator());
	}
	
	/**
	 * 
	 * @param index	��0��3���ֱ��ʾ����Ӱ����ʱ�䡢��Ӱ��������
	 * @param hallList		ԭ��Ӱ�������Ա�
	 * @param playList		���ڴ��������ĳ��ε����Ա�
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
	
	//���� ��Ʊ
	/**
	 * �÷��������ṩ��Ʊ����
	 * @param p	����Ʊ���ڳ���
	 * @param x	����Ʊ��λ����
	 * @param y	����Ʊ��λ����
	 * @return	����ɹ��۳�����true
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
		Movie m = new Movie("sb",10,200,"����");
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
