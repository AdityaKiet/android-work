package ainaa.acup.javaLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ainaa.acup.data.GlobalData;
import android.util.Log;

public class StartConnection extends Thread {

	private String ip;
	private int port = 8000;
	public StartConnection(String ip){
		
		this.ip = ip.substring(1);
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Log.d("ipsdjf", this.ip);
			Log.d(" starting connection", "start connection doen");
			(((GlobalData) GlobalData.getContext())).setSocket(new Socket(this.ip, port));
			Log.d("connection", "start connection doen");
			(((GlobalData) GlobalData.getContext())).setObjectIn(new ObjectInputStream((((GlobalData) GlobalData.getContext()).getSocket().getInputStream())));
			(((GlobalData) GlobalData.getContext())).setObjectOut(new ObjectOutputStream((((GlobalData) GlobalData.getContext()).getSocket().getOutputStream())));
			
			(((GlobalData) GlobalData.getContext())).getObjectOut().writeObject(new String("authenticate"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
