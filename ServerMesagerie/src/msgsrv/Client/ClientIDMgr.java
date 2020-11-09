package msgsrv.Client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClientIDMgr {

	
	public List<Integer> idList = new ArrayList<Integer>();
	private static volatile ClientIDMgr idMgr= null;

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
			idList.add(myID);
		}


	public int getID(int myID) {
		for(Integer i: idList)
		{
			if(i == myID)
				return i;
		}
	return -1;	
		}


}
