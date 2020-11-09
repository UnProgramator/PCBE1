package msgsrv.Client;

import java.util.ArrayList;
import java.util.List;



public class ClientIDMgr {

	
	public List<Integer> idList = new ArrayList<Integer>();
	private static volatile ClientIDMgr idMgr= null;
	Object o;

	private ClientIDMgr() {};

	public static ClientIDMgr getIntance() {
		if(idMgr == null) {
			synchronized (ClientIDMgr.class) {
				if(idMgr == null) {
					idMgr= new ClientIDMgr();
						return idMgr; }
			
			}
		}	
		return idMgr;				
	}


	public void register(int myID){
		synchronized(o){
			idList.add(myID);
		}
	}


	public int getID(int myID) {
		int i;
		do { 
			synchronized(o){// optional?
				i = idList.get((int)Math.random()%idList.size());
			}
		}while(i==myID);
		return i;
	}


}
