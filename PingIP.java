/*
 * From https://gist.github.com/madan712/4509039
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PingIP {

  public static void runSystemCommand(String command, String fileName) {

		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command
			while ((s = inputStream.readLine()) != null) {
				//System.out.println(s);
				writeToFile(s, fileName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  public static void writeToFile(String s, String fileName) {
	  
	  try(FileWriter fw = new FileWriter(fileName, true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(s);
			    out.close();
			} catch (IOException e) {
			    
			}
  }
  
  public static void sortString() {
	  
  }

	public static void main(String[] args) {
		
		String ip = "google.com";
		runSystemCommand("ping " + "-i 10" + ip, "google");
		
	}
}
