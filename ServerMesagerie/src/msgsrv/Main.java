package msgsrv;

import java.util.ArrayList;

import msgsrv.Client.ClientQueue;
import msgsrv.Client.TopicAddClient;
import msgsrv.Client.TopicGetClient;
import msgsrv.Server.Server;

public class Main {

    public static void main(String [] args) throws InterruptedException {
    	
    	ArrayList<Thread> threads = new ArrayList<Thread>();
    	
        Server server = new Server();

        server.start();

        threads.add(new TopicGetClient(server));
        threads.add(new TopicGetClient(server));
        
        threads.add(new TopicAddClient(server));
        threads.add(new TopicAddClient(server));
        threads.add(new TopicAddClient(server));
        threads.add(new TopicAddClient(server));
        
        for(int i=1; i<=6; i++)
        	threads.add(new ClientQueue(i,server));
        


        for(Thread t : threads)
        	t.start();

        server.join();
        for(Thread t : threads)
        	t.join();
        

    }
}
