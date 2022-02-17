package com.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class IOFile {

	private Path path = Paths.get("C:\\Users\\gnana-pt4726\\Desktop\\New\\test1.txt");
	private OutputStream writer;
	private BufferedReader reader;

	public IOFile() {
		try {
			this.writer = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
			this.reader = Files.newBufferedReader(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeLine(Integer e) throws IOException {

		writer.write(String.format("%s%n", e).getBytes());
//		System.out.println("writing to a file,"+e);
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

}


