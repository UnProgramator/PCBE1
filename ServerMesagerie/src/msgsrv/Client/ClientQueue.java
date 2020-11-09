package msgsrv.Client;
import msgsrv.Server.QueueMessage;
import msgsrv.Server.Server; 


public class ClientQueue extends Thread{
	
	
	private Server sv;
	private int idClient;
	private ClientIDMgr idMgr=ClientIDMgr.getIntance();
	private QueueMessage qm;
	private QueueMessage toSent;
	private boolean sendMessage = true;
	
	public ClientQueue(int id, Server sv)  {
		
		this.sv=sv;
		idClient=id;
		idMgr.register(idClient);
		if(id<0)
		{
			throw new IllegalArgumentException("Only positive IDs");
		}
		
		
	}
	
	public void run() {
		toSent = null;
		int crt=0;
		while(true) {	
			if(sendMessage) {
				if(toSent == null) {
					toSent = new QueueMessage();
					toSent.body = "newMessage " + crt + " from " + idClient;
					toSent.destinatar = idMgr.getID(idClient);
				}
				
				if(sv.addQueue(toSent)) {
					toSent=null;
				}
			}
			else {
				int id = idMgr.getID(idClient);
				qm=sv.getFromQueue(id);
				if(qm!=null)
				{
					System.out.println("Message to client with id"+ id+ ": " +qm.body);
				}
			}
			sendMessage=Math.random() < 0.5;
			try {	
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
