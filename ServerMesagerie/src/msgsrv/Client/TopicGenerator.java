package msgsrv.Client;

import java.util.ArrayList;
import java.util.List;

public class TopicGenerator {

    public static List<String> topic = new ArrayList<>(){
        {
            add("Topic1");
            add("Topic2");
            add("Topic3");
            add("Topic4");
        }
    };

    public static String get()
    {
        int index = (int)(Math.random() * topic.size());
        return topic.get(index);
    }
}
