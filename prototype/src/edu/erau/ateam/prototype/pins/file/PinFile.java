package edu.erau.ateam.prototype.pins.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A class that represents a hardware file*/
abstract class PinFile{
	public final File file;

	protected PinFile(String...locations) {
		StringBuilder builder = new StringBuilder("sys");
		appendFile(builder,"devices");
		for(String string : locations)appendFile(builder,string);
		file = new File(builder.toString());
	}
	
	private static void appendFile(StringBuilder builder, String string){
		builder.append(File.separator);
		builder.append(string);
	}
	
	protected void writeToFile(String value){
		if(file.exists()){
			FileWriter writer = null;
			
			try {
				writer = new FileWriter(file);
				writer.write(value);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(writer!=null){
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else System.out.println(file.getPath()+" does not exist");
	}
	
	public boolean exists(){
		return file.exists();
	}

	/** read the file and return it as a string */
	public String readRaw() {//update to java 7 to clean this crap up
		BufferedReader reader = null;

		String result = "0";
		try {
			reader = new BufferedReader(new FileReader(file));
			result = reader.readLine();
		} catch (IOException e) {
			System.out.println(e.getClass().getName()+": "+e.getMessage()+" with file "+file.getAbsolutePath());
			return "0";
		}
		
		try {
			if(reader!=null)reader.close();
		} catch (IOException e) {
			System.out.println(e.getClass().getName()+": "+e.getMessage()+" with file "+file.getAbsolutePath());
		}
		return result;
	}
}