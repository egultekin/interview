package fileops;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadIntegers {
	private final int BUFFERSIZE = 1024;
	private boolean positive;
	private long collect;
	private BufferedInputStream inputStream;
	private List<Long> resultList;
	
	public ReadIntegers(String file) throws FileNotFoundException {
		positive = true;
		collect = 0;
		
		inputStream = new BufferedInputStream(new FileInputStream(file));
		resultList = new ArrayList<>();
	}
	
	public void parse() throws IOException {
		int bytesRead = 0;
		int read;
		byte[] buf = new byte[BUFFERSIZE];
		while (-1 != (read = inputStream.read(buf, 0, BUFFERSIZE))) {
			for (int i = 0; i < read; i++) {
				byte next = buf[i];
				if (next == '-') positive = false;
				else if (next == '\r') continue;
				else if (next == '\n' || next == '\t' || next == 32) {
					if (positive) resultList.add(collect);
					else resultList.add(-1*collect);
					
					positive = true;
					collect = 0;
				} else if (next >= '0' && next <= '9') collect = collect*10+next-'0';
				else throw new NumberFormatException("Not a number.");
			}
				
			if (positive) resultList.add(collect);
			else resultList.add(-1*collect);
			
			positive = true;
			collect = 0;
			bytesRead += read;
		}
	}
	
	public static void main(String[] args) {
		if (args.length > 1) System.out.println("Usage: ReadIntegers <fileName>\n");
		else
			try {
				ReadIntegers ri = new ReadIntegers(args[0]);
				ri.parse();
			} catch (FileNotFoundException fnf) {
				System.out.println("The file specified cannot be found.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
