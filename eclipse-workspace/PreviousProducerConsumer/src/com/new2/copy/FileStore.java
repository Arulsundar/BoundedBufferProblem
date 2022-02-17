package com.new2.copy;

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
	private  BufferedReader reader;
	public FileStore() {
		try {
			this.writer = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
			this.reader = Files.newBufferedReader(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeQueue(MyArrayBlockingQueue producerCopy) throws IOException {
		int count=producerCopy.size();
		while(count-- >0)
		{   
			Integer e=producerCopy.take();
			writer.write(String.format("%s%n", e).getBytes());
			System.out.println("writing to a file:,"+e+":"+producerCopy.size());
		}
	}

	public  MyArrayBlockingQueue readQueue( ) throws IOException, ClassNotFoundException, NumberFormatException {
		MyArrayBlockingQueue consumer=new MyArrayBlockingQueue(5);
		while(!consumer.queueFull())
		{
			String line=reader.readLine();
			
			if(line!=null)
			{
	         consumer.put(Integer.valueOf(line));
	         System.out.println(line);
			}
			
		}
		return consumer;
	}
}
