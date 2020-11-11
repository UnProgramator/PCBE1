package msgsrv.Server;

import java.util.LinkedList;

public class Server extends Thread {
	private volatile LinkedList<TopicMessage> topics;
	
	final int maxTime;
	
	private volatile LinkedList<QueueMessage> queue=new LinkedList<QueueMessage>();
	private final int queuelimit;
	
	public Server() {
		this(100, 100);
	}
	
	public Server(int maxTime, int queuelimit) {
		topics = new LinkedList<TopicMessage>();
		this.maxTime=maxTime;
		this.queuelimit=queuelimit;
	}
	
	public void run() {
		while(true) {
			this.verifyTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
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
	public boolean addQueue(QueueMessage msg)
	{
		synchronized(queue) {
			if(this.queue.size() < this.queuelimit)
			{
				this.queue.add(msg);
				return true;
			}else 
				return false;	 
		}
	}
	

	
	public QueueMessage getFromQueue(int clientID)
	{
		synchronized(queue) {
			if(queue.size() > 0){
				if(clientID==queue.getFirst().destinatar)
					return queue.remove(0);
			}
		}
		return null;	
	}
	
	
}
