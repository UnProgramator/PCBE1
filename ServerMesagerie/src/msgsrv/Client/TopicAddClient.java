package msgsrv.Client;

import msgsrv.Server.Server;
import msgsrv.Server.TopicMessage;

import java.util.Random;

public class TopicAddClient extends Thread{

    Server server;
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public TopicAddClient(Server server)
    {
        this.server = server;
    }

    StringBuilder sb;

    public void run()
    {
        while (true) {
            sb = new StringBuilder();
            try {
                Thread.sleep((long)(Math.random()*2000 + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TopicMessage topicMessage = new TopicMessage();
            Random random = new Random();

            for (int i = 0; i < 15; i++) {
                int index = random.nextInt(alphabet.length());

                char randomChar = alphabet.charAt(index);

                sb.append(randomChar);
            }

            topicMessage.body = sb.toString();
            topicMessage.time = (int) ((Math.random() + 1) * 10);
            topicMessage.topic = TopicGenerator.get();

            server.addTopic(topicMessage);

        }
    }
}
