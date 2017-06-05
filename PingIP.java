package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class PingIP {
	
	private String domainOne, domainTwo, domainThree;
	private Boolean stop = false;

	public PingIP() {}
	
	public PingIP(String domainOne, String domainTwo, String domainThree) {
		
		this.domainOne = domainOne;
		this.domainTwo = domainTwo;
		this.domainThree = domainThree;
	}
	
  public void runSystemCommand(String command, String fileName) {
	  
	  new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					Process p = Runtime.getRuntime().exec(command);
					BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));

					String s = "";

				
					//Reading output stream of the command.
					while((s = inputStream.readLine()) != null && !stop) {
						/**
						 * Create a new Date instance each time
						 * it sends a packet and receives a 
						 * response from the domain it is pinging.
						 * Then, write both the ping data and date
						 * it was received.
						 */
			
						writeToFile(s + "\n", fileName);
		
					}

				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
  
  public void writeToFile(String s, String fileName) {
	  
	  try {
		  	FileWriter fw = new FileWriter(fileName, true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    
		    out.println(s);
		    out.close();
			
	  } catch (IOException e) {
			    
	  }
  }
  
  public void sortString() {
	  
  }

	public void setDomainOne(String domainOne) {
		this.domainOne = domainOne;
	}


	public void setDomainTwo(String domainTwo) {
		this.domainTwo = domainTwo;
	}


	public void setDomainThree(String domainThree) {
		this.domainThree = domainThree;
	}
	
	public String getDomainOne() {
		return domainOne;
	}

	public String getDomainTwo() {
		return domainTwo;
	}


	public String getDomainThree() {
		return domainThree;
	}
	
	public void setStop(Boolean stop) {
		this.stop = stop;
	}
	
}
