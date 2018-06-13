import java.net.*; 
import java.io.*; 
 

public class clientetcp {

	clientetcp(){
		
	}
	
	java.net.Socket crear() throws UnknownHostException, IOException {
            System.out.println("YES1");
	   String ip = "192.168.0.6";
	   int port = 8889;
	   java.net.Socket socket = null;
	
		socket = new java.net.Socket(ip,port);

	return socket;
	   

}
	  void cerrar(java.net.Socket socket) throws IOException {
	    	try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			
		}
	    }
	  
	  
    void enviar(java.net.Socket socket ) throws IOException {
    	String msg = "Esto es una prueba";
    	OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
    	PrintWriter pw = new PrintWriter(out, true);
		   pw.print(msg);
		   pw.flush();
		   out.write(msg);
		   recibir2(socket);
		   
		  
    	
    }
    void enviarXML(java.net.Socket socket, String msg  ) throws IOException {
    	
    	OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
    	PrintWriter pw = new PrintWriter(out, true);
		   pw.print(msg);
		   pw.flush();
		   out.write(msg);
		   //recibir(socket);
		
    }
    void recibir2(java.net.Socket socket) throws IOException   {
    	InputStreamReader in = new InputStreamReader(socket.getInputStream());
    	BufferedReader br = new BufferedReader(in);
		   char[] buffer = new char[573];
		   int count = br.read(buffer, 0, 573);
		   String reply = new String(buffer, 0, count);
		   System.out.println(reply);
		   socket.shutdownOutput();
		   
		   
		 
		  /* cerrar(socket);
		   String ip = "localhost";
		   int port = 8888;
		   java.net.Socket socket2 = null;
		   socket2 = new java.net.Socket(ip,port);
		  // enviarXML(socket2, reply);*/
    	
    }
  
    
    
     void recibir(java.net.Socket socket) throws IOException   {
        	InputStreamReader in = new InputStreamReader(socket.getInputStream());
        	BufferedReader br = new BufferedReader(in);
    		   char[] buffer = new char[573];
    		   int count = br.read(buffer, 0, 573);
    		   String reply = new String(buffer, 0, count);
    		   //System.out.println(br.read(buffer, 0, 573));
    		   
    		   socket.shutdownOutput();
    		   
    		   
    	 		   
        	
        }
    	
    }

