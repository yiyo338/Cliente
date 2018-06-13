package odysseyLogic;

import java.net.*; 
import java.io.*; 
 

public class clientetcp {
        private String mensajeActual;
    
	public clientetcp(){
		
	}
	
	public java.net.Socket crear() throws UnknownHostException, IOException {
            
	   String ip = "192.168.8.101";
	   int port = 8878;
	   java.net.Socket socket = null;
           socket = new java.net.Socket(ip,port);

	return socket;
	   

        }
	
	
	 public void cerrar(java.net.Socket socket) throws IOException {
	    	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			
		}
	    }
	  
	  
    public void enviar(java.net.Socket socket, String msg, int x ) throws IOException {

    	OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
    	PrintWriter pw = new PrintWriter(out, true);
        pw.print(msg);
        pw.flush();
        out.write(msg);
        if(x == 0){
            recibir2(socket);    
        }else if(x == 1){
           recibir(socket);
        }
		    
    	
    }
    public void enviarXML(java.net.Socket socket, String msg  ) throws IOException {
    	
    	OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
    	PrintWriter pw = new PrintWriter(out, true);
		   pw.print(msg);
		   pw.flush();
		   out.write(msg);
		   //recibir(socket);
		
    }
     public void recibir(java.net.Socket socket) throws IOException   {
    	InputStreamReader in = new InputStreamReader(socket.getInputStream());
    	BufferedReader br = new BufferedReader(in);
		   char[] buffer = new char[99999];
		   int count = br.read(buffer, 0, 99999);
		   String reply = new String(buffer, 0, count);
		   //System.out.println(reply);
                   this.mensajeActual = reply;
		   socket.shutdownOutput();
		   
		   
		 
		  /* cerrar(socket);
		   String ip = "localhost";
		   int port = 8888;
		   java.net.Socket socket2 = null;
		   socket2 = new java.net.Socket(ip,port);
		  // enviarXML(socket2, reply);*/
    	
    }
  
    
    
     public void recibir2(java.net.Socket socket) throws IOException   {
        	InputStreamReader in = new InputStreamReader(socket.getInputStream());
        	BufferedReader br = new BufferedReader(in);
    		   //char[] buffer = new char[99999999];
                   //int count = br.read(buffer, 0, 99999999);
                    String reply = "";
                   for(int i = 0; i < 8; i++){
                       reply += br.readLine();
                   }
    		   socket.shutdownOutput();
                   //System.out.println(reply);
                   this.mensajeActual = reply;
    		      	 		   
        	
        }

    public String getMensajeActual() {
        return mensajeActual;
    }
     
}

