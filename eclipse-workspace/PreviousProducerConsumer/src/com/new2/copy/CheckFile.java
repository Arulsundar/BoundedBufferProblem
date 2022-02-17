package com.new2.copy;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckFile {
	private String path="C:\\Users\\gnana-pt4726\\Desktop\\New\\files";
	private File file=new File(path);
	private OutputStream writer;
	public CheckFile() {
//		    this.writer = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
//			this.reader = Files.newBufferedReader(path);
		for (File subfile : file.listFiles())
		{
			if(subfile!=null)
		      subfile.delete();
			else
				break;
		}

	}

	public void writeQueue(MyArrayBlockingQueue producerCopy) throws IOException {
		Path p=Paths.get(path+"/"+System.currentTimeMillis()+".bin");
		Path newFile = Files.createFile(p);
		this.writer=Files.newOutputStream(newFile);
		int count=producerCopy.size();
		while(count-- >0)
		{   
			Integer e=producerCopy.take();
//			writer.write(String.format("%s%n", e).getBytes());
			writer.write(e);
			System.out.println("writing to a file:,"+e);
		}
	}

	public  MyArrayBlockingQueue readQueue( ) throws IOException, ClassNotFoundException, NumberFormatException {
		MyArrayBlockingQueue consumer=new MyArrayBlockingQueue(5);
		File[] files=file.listFiles();
		for(File file:files)
		{
			Path p=file.toPath();
			System.out.println(p);
//			this.reader = Files.newBufferedReader(p);
//			this.reader = Files.newInputStream(p);
//				while(!consumer.queueFull())
//				{
//					String line=reader.readLine();
//					
//					if(line!=null)
//					{
//			         consumer.put(Integer.valueOf(line));
//			         System.out.println(line);
//					}
//					
//				}
			try
			{
			int filepointer=0;
			while(!consumer.queueFull())
			{
				byte[] line=Files.readAllBytes(p);
				int i;
				for(i=filepointer;i<line.length;i++)
				{
		         consumer.put((int) line[i]);
		         System.out.println((int) line[i]);
				}
				filepointer=i;
			}
			}
			finally
			{
				file.delete();
				System.out.println("Reading over");
			}
			break;
		}
		
		
		return consumer;
	}
}
