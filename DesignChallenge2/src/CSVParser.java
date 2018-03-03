import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVParser extends DataParser{

	public void readData(String filename) {
		BufferedReader br = null;
		String line = "";
		String comma = ",";
		int month,day,year;
		
		try {
			br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
				String [] calendar = line.split(comma);
				
				String date = calendar[0];//parsing the month/day/year
				String [] split = date.split("/");
				month = Integer.parseInt(split[0]);
				day = Integer.parseInt(split[1]);
				year = Integer.parseInt(split[2]);
				calendar[2] = calendar[2].trim();
				
				super.processData(calendar[1],month,day,year,calendar[2]);
				
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}		
	}
	
	public void writeData(String filename, Event event) {
		String newline = "\n";
		String comma = ",";
		
		FileWriter fileWriter = null;
		
		try {
			
			fileWriter = new FileWriter (filename, true);
			fileWriter.append(String.valueOf(event.getMonth()));
			fileWriter.append("/");
			fileWriter.append(String.valueOf(event.getDay())); //converting int to string
			fileWriter.append("/");
			fileWriter.append(String.valueOf(event.getYear()));
			fileWriter.append(comma);
			fileWriter.append(event.getInfo());
			fileWriter.append(comma);
			fileWriter.append(event.getColorString());
			fileWriter.append(newline);
			
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}

}
