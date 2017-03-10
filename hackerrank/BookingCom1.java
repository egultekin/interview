package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Timer;

public class BookingCom1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br;
		InputStreamReader isr;
		int rectangles = 0;
		int squares = 0;
		int polygons = 0;
		
		Scanner sc = new Scanner(System.in);
		
			String line = br.readLine();
			do {
				if (line.isEmpty()) break;
				String[] sides = line.split(" ");
				if (sides[0].equals(sides[1]) && sides[1].equals(sides[2]) && sides[2].equals(sides[3])) squares++;
				else if (sides[0].equals(sides[2]) && sides[1].equals(sides[3])) rectangles++;
				else polygons++;
			} while (br.ready() && (line = br.readLine()) != null);
		
		System.out.println(squares + " " + rectangles + " " + polygons);
	} 
}
