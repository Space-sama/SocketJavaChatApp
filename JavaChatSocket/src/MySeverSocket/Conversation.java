package MySeverSocket;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Conversation extends Thread{

	Socket Sc;
    int idClient;
    String psdname;

    public Conversation(Socket s,int idC) {
        this.Sc=s;
        this.idClient= idC;
    }
    
    void SendMsg(String str) throws IOException {
        OutputStream os=Sc.getOutputStream();
        PrintWriter oss=new PrintWriter(os,true);
        oss.println(" "+str);

    }

    void BraodCastMsg(List<Conversation> l,String str,String psd) throws IOException {
        for (Conversation c:l){
if(c.idClient!=this.idClient)
{
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();


    c.SendMsg("<"+psd+"> : ("+dtf.format(now)+" ) "+ str);
}


        }

    }

    @Override
    public void run() {

        System.out.println("New socket connection to: " + Sc.getRemoteSocketAddress());
        InputStream is= null;
        try {
            is = Sc.getInputStream();

            InputStreamReader isc= new InputStreamReader(is);
            BufferedReader iss=new BufferedReader(isc);



            OutputStream os=Sc.getOutputStream();
            PrintWriter oss=new PrintWriter(os,true);
            //os.flush();
            oss.println("Hello :"+Sc.getRemoteSocketAddress());
            oss.println("Vous etes le client numero :"+this.idClient);
            oss.println("Votre Pseudo svp :");

            this.psdname  = iss.readLine();
            oss.println("Bonjour  :"+this.psdname);
            while( is.read()!=-1) {
                String str = iss.readLine();
                BraodCastMsg(Server.ListClient,str,this.psdname);
                System.out.println("Received data from client: " + str);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
