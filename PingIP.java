/*
 */
import java.time.Instant;
import java.time.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class PingIP {
	public static void runSystemCommand(String command, String fileName){
		new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

					String s = "";

				
					//Reading output stream of the command.
					while((s = inputStream.readLine()) != null){
						writeToFile(s, fileName);
					}

				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
 
	public static void writeToFile(String s, String fileName) {
		try{
			FileWriter fw = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);

			out.println(s);
			out.close();
		}catch(IOException e){
			System.out.println(e);
		}
	}

	public static void sortString() {
		//?
	}

	public static void main(String[] args) {
		String ip = "google.com";
		String ip2 = "yahoo.com";
		String ip3 = "facebook.com";

		// Mac
		//Start record of ping time.
		Instant start = Instant.now();
		System.out.println("Pinging Google...");
		runSystemCommand("ping " + "-i 10 " + ip, "pings/google.txt");
		//End record of pinging.
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);

		System.out.println("Pinging Yahoo...");	
		runSystemCommand("ping " + "-i 10 " + ip2, "pings/yahoo.txt");

		System.out.println("Pinging Facebook...");
		runSystemCommand("ping " + "-i 10 " + ip3, "pings/facebook.txt");

		// Windows
		//runSystemCommand("ping " + "-w 10000 " + ip + " -t", "google.txt");
	}
}
