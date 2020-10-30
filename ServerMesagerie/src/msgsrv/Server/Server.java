package msgsrv.Server;

import java.util.LinkedList;
import java.util.List;

public class Server extends Thread {
	private LinkedList<TopicMessage> topics;
	
	int maxTime;
	
	public Server() {
		this(100);
	}
	
	public Server(int maxTime) {
		topics = new LinkedList<TopicMessage>();
		this.maxTime=maxTime;
	}
	
	public void run() {
		
	}
	
	//Serverul de topicuri
	public void addTopic(TopicMessage msg) {
		
	}
	
	public List<String> getTopic(String topic){
		
		return null;
	}
	
	private void verifyTime() {
		
	}
	
	//serverul de mesage
}
