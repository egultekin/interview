package fileops;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class ReadInput {

	private int bytesRead, parseFrom;
	private BufferedInputStream stream;
	private byte[] current;
	
	public ReadInput(String filePath) throws IOException {
		bytesRead = 0;
		
		stream = new BufferedInputStream(new FileInputStream(filePath));
		current = readNextChunk();
	}
	
	public void close() {
		try {
			stream.close();
		} catch (IOException e) {
			System.out.println("An IOException occurred while closing input stream.");
			e.printStackTrace();
		} finally {
			bytesRead = 0;
		}
	}
	
	private byte[] readNextChunk() throws IOException {
		int read;
		byte[] buf = new byte[1024];
		
		if (-1 == (read = stream.read(buf, 0, buf.length))) {
			parseFrom = -1;
			return null;
		}
		
		bytesRead += read;
		parseFrom = 0;
		
		if (read == buf.length) return buf;
		return Arrays.copyOf(buf, read);
	}
	
	public int nextInt() throws IOException {
		int result = 0;
		
		while (parseFrom > -1 && current != null) {					
			if (parseFrom > current.length-1) current = readNextChunk();
			if (current != null && current[parseFrom] != 32 && current[parseFrom] != '\r' && current[parseFrom] != '\n')
				result = result*10 + current[parseFrom++]-'0';
			else {
				parseFrom++;
				break;
			}
		}
		
		if (parseFrom == -1) result = Integer.MAX_VALUE;
		return result;
	}
	
	public static void main(String[] args) {
		try {
			ReadInput reader = new ReadInput(args[0]);
			int a = reader.nextInt();
			while (a != Integer.MAX_VALUE) {
				System.out.println(a);
				a = reader.nextInt();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
