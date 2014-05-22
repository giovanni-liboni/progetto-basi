package volo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
	BufferedWriter writer = null;

  	public WriteToFile(){
  		String content = "This is the content to write into file";
  		 
  		File file = new File("popola_volo.sql");
  		try {
			writer = new BufferedWriter( new FileWriter(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	public void writeToFile(String s){
  		s= s+System.getProperty("line.separator");
  		try {
			writer.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
	public void close()
	{
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
