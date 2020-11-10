package msgsrv.Client;
import msgsrv.Server.QueueMessage;
import msgsrv.Server.Server; 


public class ClientQueue extends Thread{
	
	
	private Server sv;
	private int idClient;
	private ClientIDMgr idMgr=ClientIDMgr.getIntance();
	private QueueMessage qm;
	private QueueMessage toSent = null;
	private boolean sendMessage = true;
	
	public ClientQueue(int id, Server sv)  {
		if(id<0)
		{
			throw new IllegalArgumentException("Only positive IDs");
		}
		this.sv=sv;
		idClient=id;
		idMgr.register(idClient);
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
					crt++;
				}
				
				if(sv.addQueue(toSent)) {
					toSent=null;
				}
			}
			else {
				qm=sv.getFromQueue(idClient);
				if(qm!=null)
				{
					System.out.println("Message to client with id " + idClient + " : \"" + qm.body + "\"");
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
