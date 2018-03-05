
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
	}	

	public List<Occasion> getOccasions(String dateFilter) {
		//Create empty list of contacts
		List<Occasion>occasions = new ArrayList <Occasion>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT * FROM occasions WHERE dateFrom like \""+dateFilter+"%\""+" ORDER BY dateFrom";
		
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
		String query = "SELECT * FROM occasions WHERE dateFrom like \""+dateFilter+"%\""+"AND type = '"+typeFilter+"' ORDER BY dateFrom";

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
							+ " OR (dateTo > '"+((Event) occ).getDurationFrom()+"' AND dateTo <= '"+((Event) occ).getDurationTo()+"')";
				else if(occ instanceof Task)
					query2 = "SELECT * FROM occasions WHERE (dateFrom >= '"+((Task) occ).getDurationFrom()+"' AND dateFrom < '"+((Task) occ).getDurationTo()+"')"
							+ " OR (dateTo > '"+((Task) occ).getDurationFrom()+"' AND dateTo < '"+((Task) occ).getDurationTo()+"')";

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
				} 				
				
				if(occasions.size() > 0)
					return false;
				
				//create a query
				String query = "INSERT INTO occasions VALUES (?, ?, ?, ?, ?)";
				
				try {
					//create a prepared statement
					PreparedStatement ps = cnt.prepareStatement(query);
					
					//prepare the values
					if(occ instanceof Event) {
						ps.setString(1, "Event");
						ps.setString(2, occ.getInfo());
						ps.setString(3, ((Event) occ).getDurationFrom());
						ps.setString(4, ((Event) occ).getDurationTo());
						ps.setBoolean(5, ((Event) occ).getIsDone());						
					}else if(occ instanceof Task) {
						ps.setString(1, "Task");
						ps.setString(2, occ.getInfo());
						ps.setString(3, ((Task) occ).getDurationFrom());
						ps.setString(4, ((Task) occ).getDurationTo());
						ps.setBoolean(5, ((Task) occ).getIsDone());						
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
	
	private Occasion toOccasion(ResultSet rs) throws SQLException {
		
		Occasion occ = null;
		
		String type = rs.getString("type");
		String info = rs.getString("info");
		String dateFrom = rs.getString("dateFrom");
		String dateTo = rs.getString("dateTo");
		boolean isDone = rs.getBoolean("isDone");
		
		if(type.equals("Event")) {
			occ = new Event(info,dateFrom,dateTo,isDone);			
		}else if(type.equals("Task")) {
		    occ = new Task(info,dateFrom,dateTo,isDone);	
		}
		
		return occ;
	}
	/*
	public void delete() {
		//get a connection
		Connection cnt = connection.getConnection();
		
		//create a query
		String query = "DELETE FROM " + Product.TABLE + " WHERE 1 == 1";
		
		try {
			//create a prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//execute the update
			ps.executeUpdate();
			
			//close resource
			ps.close();
			cnt.close();
			
			System.out.println("DELETE SUCCESS!");
		} catch (SQLException e) {
			System.out.println("DELETE FAILED!");
			e.printStackTrace();
		} 
		
	}
	*/
}
