
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProgram implements Database{
	
	private DatabaseConnector connection;
	
	public DatabaseProgram(DatabaseConnector connection) {
		this.connection = connection;

		//CREATE TABLE IF NOT EXISTS
		String query = "CREATE TABLE IF NOT EXISTS occasions (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,type varchar(255), info varchar(255), dateFrom DATETIME, dateTo DATETIME, isDone boolean);";
		try {

			PreparedStatement ps = connection.getConnection().prepareStatement(query);
			ps.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 	
		
	}		
	
	public List<Occasion> getMonthOccasion(String monthFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
				
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT * FROM occasions WHERE dateFrom <= '"+monthFilter+"-31 23:59:00' AND dateTo >= '"+monthFilter+"-1 00:00:00'"+" ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return occasions;

	}		

	public List<Occasion> getWeekOccasion(String firstFilter, String secondFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		
		String query = "SELECT * FROM occasions WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return occasions;
	}		

	public List<Occasion> getWeekOccasion(String firstFilter, String secondFilter, String typeFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		
		String query = "SELECT * FROM occasions WHERE dateFrom <= '"+secondFilter+" 23:59:00' AND dateTo >= '"+firstFilter+" 00:00:00'"+" AND type = '"+typeFilter+"' ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return occasions;
	}		
	
	public List<Occasion> getOccasions(String dateFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		
		String query = "SELECT * FROM occasions WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" ORDER BY dateFrom";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return occasions;
	}		
	
	public List<Occasion> getOccasions(String dateFilter, String typeFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT * FROM occasions WHERE dateFrom <= '"+dateFilter+" 23:59:00' AND dateTo >= '"+dateFilter+" 00:00:00'"+" AND type = '"+typeFilter+"' ORDER BY dateFrom";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return occasions;
	}		
	
	public boolean addOccasion(Occasion occ) {
		//get a connection
				Connection cnt = connection.getConnection();
				
				List<Occasion>occasions = new ArrayList<>();
				String query2 = new String();
				if(occ instanceof Event)
					query2 = "SELECT * FROM occasions WHERE (dateFrom >= '"+((Event) occ).getDurationFrom()+"' AND dateFrom < '"+((Event) occ).getDurationTo()+"')"
							+ " OR (dateFrom = '"+((Event) occ).getDurationFrom()+"')"
							+ " OR (dateTo > '"+((Event) occ).getDurationFrom()+"' AND dateTo <= '"+((Event) occ).getDurationTo()+"')"
							+  " OR (dateFrom < '"+((Event) occ).getDurationTo()+"' AND dateTo > '"+((Event) occ).getDurationFrom()+"')"
							+  " OR (dateTo > '"+((Event) occ).getDurationFrom()+"' AND dateFrom < '"+((Event) occ).getDurationTo()+"')";
				else if(occ instanceof Task)
					query2 = "SELECT * FROM occasions WHERE (dateFrom >= '"+((Task) occ).getDurationFrom()+"' AND dateFrom < '"+((Task) occ).getDurationTo()+"')"
							+ " OR (dateFrom = '"+((Task) occ).getDurationFrom()+"')"
							+ " OR (dateTo > '"+((Task) occ).getDurationFrom()+"' AND dateTo < '"+((Task) occ).getDurationTo()+"')"
							+  " OR (dateFrom < '"+((Task) occ).getDurationTo()+"' AND dateTo > '"+((Task) occ).getDurationFrom()+"')"
							+  " OR (dateTo > '"+((Task) occ).getDurationFrom()+"' AND dateFrom < '"+((Task) occ).getDurationTo()+"')";

				try {
					//create a prepared statement
					PreparedStatement ps = cnt.prepareStatement(query2);
					
					//get result and store in result set
					ResultSet rs = ps.executeQuery();
					
					//transform set into list
					while(rs.next()) {
						occasions.add(toOccasion(rs));
					}					
					
					//close resource
					rs.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				} 				
				
				if(occasions.size() > 0)
					return false;
				
				//reset auto-increment
				String query = "ALTER TABLE occasions auto_increment = 1";
				
				try {
					PreparedStatement ps = cnt.prepareStatement(query);
					ps.executeUpdate();
				} catch (SQLException e1) {}
				
				//create a query
				query = "INSERT INTO occasions VALUES (?, ?, ?, ?, ?, ?)";
				
				try {
					//create a prepared statement
					PreparedStatement ps = cnt.prepareStatement(query);
					
					//prepare the values
					if(occ instanceof Event) {
						ps.setInt(1,occ.getID());
						ps.setString(2, "Event");
						ps.setString(3, occ.getInfo());
						ps.setString(4, ((Event) occ).getDurationFrom());
						ps.setString(5, ((Event) occ).getDurationTo());
						ps.setBoolean(6, ((Event) occ).getIsDone());						
					}else if(occ instanceof Task) {
						ps.setInt(1,occ.getID());
						ps.setString(2, "Task");
						ps.setString(3, occ.getInfo());
						ps.setString(4, ((Task) occ).getDurationFrom());
						ps.setString(5, ((Task) occ).getDurationTo());
						ps.setBoolean(6, ((Task) occ).getIsDone());						
					}
					
					//execute the update
					ps.executeUpdate();
					
					//close resource
					ps.close();
					cnt.close();

				} catch (SQLException e) {
					e.printStackTrace();
				} 
				
				return true;
				
	}		
	
	public void updateIsDone(int occasionID, boolean isDone) {
		//UPDATE occasions SET isDone = true
		//WHERE dateFrom = dateFrom;
		
		Connection cnt = connection.getConnection();
		
		//create a query
		String query;
		if(isDone)
			query = "UPDATE occasions SET isDone = true WHERE id = "+occasionID;
		else
			query = "UPDATE occasions SET isDone = false WHERE id = "+occasionID;
		
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

	public boolean updateEventIsDone(String currentDateTime) {
		//UPDATE occasions SET isDone = true
		//WHERE dateFrom = dateFrom;
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT * FROM occasions WHERE dateTo < '"+currentDateTime+"' AND type = 'Event' AND isDone = false";

		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				occasions.add(toOccasion(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		if(occasions.size() > 0) {
			query = "UPDATE occasions SET isDone = true WHERE dateTo < '"+currentDateTime+"' AND type = 'Event'";
			
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
			return true;
		}else {
			return false;
		}


	}	
	
	private Occasion toOccasion(ResultSet rs) throws SQLException {
		
		Occasion occ = null;
		
		int id = rs.getInt("id");
		String type = rs.getString("type");
		String info = rs.getString("info");
		String dateFrom = rs.getString("dateFrom");
		dateFrom = dateFrom.substring(0, dateFrom.length()-2);
		String dateTo = rs.getString("dateTo");
		dateTo = dateTo.substring(0, dateTo.length()-2);
		boolean isDone = rs.getBoolean("isDone");
		
		if(type.equals("Event")) {
			occ = new Event(id,info,dateFrom,dateTo,isDone);			
		}else if(type.equals("Task")) {
		    occ = new Task(id,info,dateFrom,dateTo,isDone);	
		}
		
		return occ;
	}
	
	public void deleteOccasion(int occasionID) {
		
		Connection cnt = connection.getConnection();
		
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
