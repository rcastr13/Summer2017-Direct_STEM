/**
 * From https://gist.github.com/madan712/4509039
 */
//import java.time.Instant;
//import java.time.Duration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;

public class PingIP{
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
						/**
						 * Create a new Date instance each time
						 * it sends a packet and receives a 
						 * response from the domain it is pinging.
						 * Then, write both the ping data and date
						 * it was received.
						 */
						Date sent = new Date();
						writeToFile((sent + ""), fileName);
						writeToFile(s + "\n", fileName);
						Date received = new Date();
						writeToFile((received + "\n"), fileName);
					}

				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
 
	public static void writeToFile(String s, String fileName){
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

	public static void sortString(){
		//?
	}

	public static void main(String[] args){
		/*
		 * I added a bunch more domains but
		 * I don't remember them all. The
		 * formatting is more or less the
		 * same though.
		 */
		String ip = "google.com";
		String ip2 = "yahoo.com";
		String ip3 = "facebook.com";

		ArrayList<String> listOfDomains = new ArrayList<String>();
		listOfDomains.add(ip);
		listOfDomains.add(ip2);
		listOfDomains.add(ip3);

		for(String domain: listOfDomains){
			runSystemCommand("ping -i 5 " + domain, domain + ".txt");
		}

		/**
		 * XXX Possible DEAD code below.
		 */
		// Mac
		//Start record of ping time.
		//Instant start = Instant.now();
		//System.out.println("Pinging Google...");
		//runSystemCommand("ping " + "-i 10 " + ip, "pings/google.txt");
		//End record of pinging.
		//Instant end = Instant.now();
		//Duration duration = Duration.between(start, end);

		//System.out.println("Pinging Yahoo...");	
		//runSystemCommand("ping " + "-i 10 " + ip2, "pings/yahoo.txt");

		//System.out.println("Pinging Facebook...");
		//runSystemCommand("ping " + "-i 10 " + ip3, "pings/facebook.txt");

		// Windows
		//runSystemCommand("ping " + "-w 10000 " + ip + " -t", "google.txt");
	}
}
