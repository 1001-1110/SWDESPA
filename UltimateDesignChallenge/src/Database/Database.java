package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Entry.Book;
import Entry.Entries;
import Entry.EntriesCollection;
import Entry.EntriesIterator;
import Entry.Entry;
import Entry.Slot;

public class Database{
	
	//"com.mysql.jdbc.Driver","jdbc:mysql://112.211.95.65:3306/","superuser","kathyemir","swdespa"
	private volatile static Database instance = null;
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://112.205.74.172:3306/";
	private final String USERNAME = "superuser";
	private final String PASSWORD = "kathyemir";
	private final String DATABASE = "swdespa";
	
	public synchronized static Database getInstance() {
        if (instance == null) {
        	instance = new Database();
        }
		return instance;
	}
	
	private Connection getConnection () {
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					URL + 
					DATABASE + "?autoReconnect=true&useSSL=false", 
					USERNAME,
					PASSWORD);
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	protected Database() {

		//CREATE TABLE IF NOT EXISTS
		
		String query = "CREATE TABLE IF NOT EXISTS slots (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,slotType varchar(255), doctor varchar(255), dateFrom DATETIME, dateTo DATETIME);";

		String query2 = "CREATE TABLE IF NOT EXISTS books (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,bookType varchar(255), client varchar(255), dateFrom DATETIME, dateTo DATETIME, slotsID int NOT NULL);";

		String query3 = "CREATE TABLE IF NOT EXISTS notifs (id int NOT NULL AUTO_INCREMENT PRIMARY KEY, booksID int);";

		try {

			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.execute();
			ps = getConnection().prepareStatement(query2);
			ps.execute();
			ps = getConnection().prepareStatement(query3);
			ps.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 	
		
	}		
	
	public Entries getMonthSlots(String monthFilter) {
		
		Entries entries = new EntriesCollection();
				
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		String query = "SELECT * FROM slots WHERE dateFrom <= '"+monthFilter+"-31 23:59:00' AND dateTo >= '"+monthFilter+"-1 00:00:00'"+" ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toSlot(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;

	}		

	public Entries getMonthBooks(String monthFilter) {
		
		Entries entries = new EntriesCollection();
				
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		String query = "SELECT * FROM books WHERE dateFrom <= '"+monthFilter+"-31 23:59:00' AND dateTo >= '"+monthFilter+"-1 00:00:00'"+" ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toBook(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;

	}		
	
	public Entries getWeekSlots(String firstFilter, String secondFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		
		String query = "SELECT * FROM slots WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toSlot(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}		

	public Entries getWeekBooks(String firstFilter, String secondFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		
		String query = "SELECT * FROM books WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toBook(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}		
	
	public Entries getWeekSlots(String firstFilter, String secondFilter, String personFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		String query = "SELECT * FROM slots WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" AND doctor = '"+personFilter+"' ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toSlot(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}	
	
	public Entries getWeekBooks(String firstFilter, String secondFilter, String personFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		String query = "SELECT * FROM books WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" AND client = '"+personFilter+"' ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toBook(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}	
	
	public Entries getSlots(String dateFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		
		String query = "SELECT * FROM slots WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toSlot(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}		
	
	public Entries getBooks(String dateFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		
		String query = "SELECT * FROM books WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toBook(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}		
	
	public Entries getSlots(String dateFilter, String personFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		String query = null;
		//create string query
		query = "SELECT * FROM slots WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" AND doctor = '"+personFilter+"' ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toSlot(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}		
	
	public Entries getBooks(String dateFilter, String personFilter) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		String query = null;
		//create string query
		query = "SELECT * FROM books WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" AND client = '"+personFilter+"' ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				entries.add(toBook(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return entries;
	}
	
	public Entries getIDSlots(int id[]) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		for(int i = 0 ; i < id.length ; i++) {
			String query = "SELECT * FROM slots WHERE id = "+id[i]+" ORDER BY dateFrom";
			System.out.println(query);
			
			try {
				//create prepared statement
				PreparedStatement ps = cnt.prepareStatement(query);
				
				//get result and store in result set
				ResultSet rs = ps.executeQuery();
				
				//transform set into list
				while(rs.next()) {
					entries.add(toSlot(rs));
				}
				
				//close all the resources
				ps.close();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} 			
		}
		try {
			cnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entries;		
	}
	
	public Entries getIDBooks(int id[]) {
		
		Entries entries = new EntriesCollection();
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		//create string query
		for(int i = 0 ; i < id.length ; i++) {
			String query = "SELECT * FROM books WHERE id = "+id[i]+" ORDER BY dateFrom";
			
			try {
				//create prepared statement
				PreparedStatement ps = cnt.prepareStatement(query);
				
				//get result and store in result set
				ResultSet rs = ps.executeQuery();
				
				//transform set into list
				while(rs.next()) {
					entries.add(toBook(rs));
				}
				
				//close all the resources
				ps.close();
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} 			
		}
		try {
			cnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entries;			
	}
	
	public boolean addEntries(Entries entries) {
		//get a getConnection()
				Connection cnt = getConnection();
				EntriesIterator et = entries.getIterator();
				
				String query2 = new String();
				
				if(et.peek() instanceof Slot)
					query2 = "SELECT * FROM slots WHERE (dateFrom >= '"+et.first().getDurationFrom()+"' AND dateFrom < '"+et.last().getDurationTo()+"')"
							+ " OR (dateFrom = '"+et.first().getDurationFrom()+"')"
							+ " OR (dateTo > '"+et.first().getDurationFrom()+"' AND dateTo <= '"+et.last().getDurationTo()+"')"
							+  " OR (dateFrom < '"+et.last().getDurationTo()+"' AND dateTo > '"+et.first().getDurationFrom()+"')"
							+  " OR (dateTo > '"+et.first().getDurationFrom()+"' AND dateFrom < '"+et.last().getDurationTo()+"')";
				else if(et.peek() instanceof Book)
					query2 = "SELECT * FROM books WHERE (dateFrom >= '"+et.first().getDurationFrom()+"' AND dateFrom < '"+et.last().getDurationTo()+"')"
							+ " OR (dateFrom = '"+et.first().getDurationFrom()+"')"
							+ " OR (dateTo > '"+et.first().getDurationFrom()+"' AND dateTo <= '"+et.last().getDurationTo()+"')"
							+  " OR (dateFrom < '"+et.last().getDurationTo()+"' AND dateTo > '"+et.first().getDurationFrom()+"')"
							+  " OR (dateTo > '"+et.first().getDurationFrom()+"' AND dateFrom < '"+et.last().getDurationTo()+"')";

				try {
					//create a prepared statement
					PreparedStatement ps = cnt.prepareStatement(query2);
					
					//get result and store in result set
					ResultSet rs = ps.executeQuery();
					//transform set into list
					if(rs.next()) {
						rs.close();
						return false;
					}
						
					//close resource
					rs.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				} 				
				
				//reset auto-increment (unsure, may cause bugs)
				/*String query = "ALTER TABLE occasions auto_increment = 1";
				
				try {
					PreparedStatement ps = cnt.prepareStatement(query);
					ps.executeUpdate();
				} catch (SQLException e1) {}*/
				
				//create a query
				String query = null;
				if(et.peek() instanceof Slot)
					query = "INSERT INTO slots VALUES (?, ?, ?, ?, ?)";
				else if(et.peek() instanceof Book)	
					query = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?)";
				
				while(et.hasNext()) {
					
					try {
						//create a prepared statement
						PreparedStatement ps = cnt.prepareStatement(query);
						Entry entry = et.next();
						
						//prepare the values
						if(entry instanceof Slot) {
							ps.setInt(1,entry.getId());
							ps.setString(2, "Single");
							ps.setString(3, ((Slot) entry).getDoctor());
							ps.setString(4, entry.getDurationFrom());
							ps.setString(5, entry.getDurationTo());					
						}else if(entry instanceof Book) {
							ps.setInt(1,entry.getId());
							ps.setString(2, "Single");
							ps.setString(3, ((Book) entry).getClient());
							ps.setString(4, entry.getDurationFrom());
							ps.setString(5, entry.getDurationTo());
							ps.setInt(6, ((Book) entry).getSlotsID());						
						}
						
						//execute the update
						ps.executeUpdate();
						
						//close resource
						ps.close();

					} catch (SQLException e) {
						e.printStackTrace();
					} 
					
				}

				try {
					cnt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;

	}		
	
	public Entry toSlot(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String slotType = rs.getString("slotType");
		String doctor = rs.getString("doctor");
		String durationFrom = rs.getString("dateFrom");
		durationFrom = durationFrom.substring(0, durationFrom.length()-2);
		String durationTo = rs.getString("dateTo");
		durationTo = durationTo.substring(0, durationTo.length()-2);
		String yearSplitFrom[] = durationFrom.split(" ")[0].split("-"); 
		String hourSplitFrom[] = durationFrom.split(" ")[1].split(":");
		String yearSplitTo[] = durationTo.split(" ")[0].split("-"); 
		String hourSplitTo[] = durationTo.split(" ")[1].split(":");		
		//YYYY-MM-DD HH:MM:SS
		//int year, int month, int dayOfMonth, int hourOfDay, int minute
		Calendar dateTo = new GregorianCalendar(Integer.parseInt(yearSplitTo[0]),Integer.parseInt(yearSplitTo[1])-1,Integer.parseInt(yearSplitTo[2]),Integer.parseInt(hourSplitTo[0]),Integer.parseInt(hourSplitTo[1]));
		Calendar dateFrom = new GregorianCalendar(Integer.parseInt(yearSplitFrom[0]),Integer.parseInt(yearSplitFrom[1])-1,Integer.parseInt(yearSplitFrom[2]),Integer.parseInt(hourSplitFrom[0]),Integer.parseInt(hourSplitFrom[1]));
		//String doctor, String slotType, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id
		return new Slot(doctor,slotType,dateFrom,dateTo,durationFrom,durationTo,id);
	}

	public Entry toBook(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String bookType = rs.getString("bookType");
		String client = rs.getString("client");
		String durationFrom = rs.getString("dateFrom");
		durationFrom = durationFrom.substring(0, durationFrom.length()-2);
		String durationTo = rs.getString("dateTo");
		int slotsID = rs.getInt("slotsID");
		durationTo = durationTo.substring(0, durationTo.length()-2);
		String yearSplitFrom[] = durationFrom.split(" ")[0].split("-"); 
		String hourSplitFrom[] = durationFrom.split(" ")[1].split(":");
		String yearSplitTo[] = durationTo.split(" ")[0].split("-"); 
		String hourSplitTo[] = durationTo.split(" ")[1].split(":");		
		//YYYY-MM-DD HH:MM:SS
		//int year, int month, int dayOfMonth, int hourOfDay, int minute
		Calendar dateTo = new GregorianCalendar(Integer.parseInt(yearSplitTo[0]),Integer.parseInt(yearSplitTo[1])-1,Integer.parseInt(yearSplitTo[2]),Integer.parseInt(hourSplitTo[0]),Integer.parseInt(hourSplitTo[1]));
		Calendar dateFrom = new GregorianCalendar(Integer.parseInt(yearSplitFrom[0]),Integer.parseInt(yearSplitFrom[1])-1,Integer.parseInt(yearSplitFrom[2]),Integer.parseInt(hourSplitFrom[0]),Integer.parseInt(hourSplitFrom[1]));
		//String client, String bookType, int slotsID, Calendar dateFrom, Calendar dateTo, String durationFrom, String durationTo, int id
		return new Book(client,bookType,slotsID,dateFrom,dateTo,durationFrom,durationTo,id);
	}	
	
	public void deleteEntry(int occasionID) {
		
		Connection cnt = getConnection();
		
		//create a query
		String query = "DELETE FROM occasions WHERE id = "+occasionID;
		
		try {
			//create a prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//execute the update
			ps.executeUpdate();
			
			//close resource
			ps.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}
	
}
