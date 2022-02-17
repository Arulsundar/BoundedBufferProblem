package com.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileStore {
	private Path path = Paths.get("C:\\Users\\gnana-pt4726\\Desktop\\New\\temp.txt");
	private OutputStream writer;
	private BufferedReader reader;

	public FileStore() {
		try {
			this.writer = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
			this.reader = Files.newBufferedReader(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeLine(Integer e) throws IOException {

		writer.write(String.format("%s%n", e).getBytes());
		System.out.println("writing to a file,"+e);
	}
	
	public String readLine() throws IOException
	{
		String line=reader.readLine();
//		if(line != null)
//		{
//		    System.out.println("Read from File");
//		   
//		}
		 return line;
	}
   public void writeQueue(byte[] queue ,int capacity) throws IOException
   {
	   writer.write(queue, 0, capacity*4);
   }
   public void readQueue(int[] cp) throws IOException
   {
	   for(int i:cp)
		   i=reader.read();
   }
}
