package msgsrv.Client;
import msgsrv.Server.QueueMessage;
import msgsrv.Server.Server; 


public class ClientQueue extends Thread{
	
	
	private Server sv;
	private int idClient;
	private ClientIDMgr idMgr=ClientIDMgr.getIntance();
	private QueueMessage qm;
	
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
		while(true) {
			
		int id;
		id=idMgr.getID(idClient);
		if(id >= 0)
		{
			qm=sv.getFromQueue(id);
			
		}	
		if(qm!=null)
		{
			System.out.println("Message to client with id"+ id+ ": " +qm.body);
		}
		try {	
				
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
