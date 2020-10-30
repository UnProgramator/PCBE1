package msgsrv.Server;

import java.util.LinkedList;

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
		while(true) {
			this.verifyTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Serverul de topicuri
	public void addTopic(TopicMessage msg) {
		synchronized(topics) {
			if(msg.time > maxTime)
				msg.time = maxTime;
			topics.add(msg);
		}
	}
	
	public LinkedList<String> getTopic(String topic){
		LinkedList<String> retVal = new LinkedList<String>();
		synchronized(topics) {
			for(TopicMessage top : topics) {
				if(top.topic.equals(topic))
					retVal.add(top.body);
			}
		}
		return retVal;
	}
	
	private void verifyTime() {
		synchronized(topics) {
			int i=0;
			while(i<topics.size()) {
				TopicMessage tm = topics.get(i);
				tm.time--;
				if(tm.time<=0) {
					topics.remove(i);
				}
				else {
					i++;
				}
			}
		}
	}
	
	//serverul de mesage
}
