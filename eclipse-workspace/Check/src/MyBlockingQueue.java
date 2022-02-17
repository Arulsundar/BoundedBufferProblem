import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyBlockingQueue {
    private Queue<Integer> q ;
    private final int CAPACITY ;
    private final Path fileStorePath ;
    
    public MyBlockingQueue(int capacity) {
        this.q = new LinkedList<Integer>();
        this.CAPACITY = capacity;
        this.fileStorePath = Paths.get("C:\\Users\\gnana-pt4726\\Desktop\\New\\temp.txt");

        try {
            Files.newBufferedWriter(fileStorePath, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shiftFromFileToQueue() throws IOException {
    	//Read file
        List<String> lines = Files.readAllLines(fileStorePath) ;

        int startIx = 0 ;
        // Fill queue from the values stored in file
        for(String s : lines) {
            if(q.size() < CAPACITY) {
                q.offer(Integer.valueOf(s));
                startIx++;
            } else break ;
        }
        //Rewrite file after pushing values to the queue
        Files.write(fileStorePath, lines.subList(startIx, lines.size())) ;
    }

    public synchronized Integer poll() throws InterruptedException, IOException {
    	//if queue is empty wait until producer produces
        while(q.size() == 0)
            wait() ;
        //Remove the top most element from the queue for consuming
        Integer polled = q.poll() ;
        //Fill the queue after consuming
        shiftFromFileToQueue();

        return polled ;
    }

    public synchronized void offer(Integer e) throws InterruptedException, IOException {
    	//Add value to the end of the file
        Files.write(fileStorePath, String.format("%s%n", e).getBytes(), StandardOpenOption.APPEND) ;
        //Shift values from file to queue until it reaches its capacity
        shiftFromFileToQueue();
        // notify the thread that is waiting for its object
        notify();
    }

}