import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PSVParser extends DataParser{

	public void readData(String filename) {
		String line;
		String pipe = "\\|";
		BufferedReader br = null;
		int month,day,year;
		
		try {
			br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
			String calendar [] = line.split(pipe);
			
			String date = calendar[1];//parsing the month/day/year
			
			String [] split = date.split("/");
			month = Integer.parseInt(split[0].trim());
			day = Integer.parseInt(split[1].trim());
			year = Integer.parseInt(split[2].trim());
			calendar[2] = calendar[2].trim();
	
			super.processData(calendar[0],month,day,year,calendar[2]);
			
			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		catch (IOException e) {
			//e.printStackTrace();
		}		
	}

	public void writeData(String filename, Event event) {
		
	}
	
}
