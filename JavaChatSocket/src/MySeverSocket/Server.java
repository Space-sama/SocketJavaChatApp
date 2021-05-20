package MySeverSocket;

import java.util.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server extends Thread{
	
	public static List<Conversation> ListClient=new ArrayList<Conversation>();
	public  int nbrClient=1;
	    @Override
	    public void run() {

	        try {
	            System.out.println("server :  "+ Arrays.toString(InetAddress.getAllByName("localhost")));

	            ServerSocket sv = null;
	            sv = new ServerSocket(111);

	        System.out.println("Server ISGA IP:  "+InetAddress.getAllByName("localhost")+"Listening on :"+ 1111);
	        System.out.println("Our process is running ... ");
	       while(true){
	        Socket ss = sv.accept();


	        Conversation thclient=new Conversation(ss,nbrClient++);
	       this.ListClient.add(thclient);
	       thclient.start();
	       }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * @param args the command line arguments
	     */

	    public static void main(String[] args) throws IOException {

	        
	        new Server().start();
	        
	        
	        
	    }

}
