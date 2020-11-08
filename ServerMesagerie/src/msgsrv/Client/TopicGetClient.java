package msgsrv.Client;

import msgsrv.Server.Server;

import java.util.LinkedList;

public class TopicGetClient extends Thread{

    Server server;

    public TopicGetClient(Server server)
    {
        this.server = server;
    }

    public void run()
    {
        while(true) {
            try {
                Thread.sleep((long)(Math.random()*2000 + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String myTopic = TopicGenerator.get();

            LinkedList<String> result = server.getTopic(myTopic);

            if (result != null) {
                for (String mesaj : result) {
                    System.out.println(mesaj);
                }
            }

        }
    }
}
