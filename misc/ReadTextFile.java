package misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadTextFile {

	public static void main(String[] args) {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			String line = null;
			while (null != (line = reader.readLine())) {
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Input file not found!");
		} catch (IOException e) {
			System.out.println("I/O error occurred reading a new line from the input file!");
		}
		
	}

}
