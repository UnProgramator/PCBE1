package msgsrv;

import msgsrv.Client.TopicAddClient;
import msgsrv.Client.TopicGetClient;
import msgsrv.Server.Server;

public class Main {

    public static void main(String [] args) throws InterruptedException {
        Server server = new Server();

        server.start();

        TopicGetClient topicGetClient1 = new TopicGetClient(server);
        TopicAddClient topicAddClient1 = new TopicAddClient(server);

        TopicGetClient topicGetClient2 = new TopicGetClient(server);
        TopicAddClient topicAddClient2 = new TopicAddClient(server);

        topicAddClient1.start();
        topicAddClient2.start();

        topicGetClient1.start();
        topicGetClient2.start();

        server.join();

    }
}
