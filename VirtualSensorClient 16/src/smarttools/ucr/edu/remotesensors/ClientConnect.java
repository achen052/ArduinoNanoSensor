package smarttools.ucr.edu.remotesensors;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import smarttools.ucr.edu.remotesensors.common.Datagram;

public class ClientConnect
{

	/** IP Address of the server */
	private String serverIpAddress;

	/** Port the server is listening to */
	private int serverPort;

	/** Socket connection to the server */
	private Socket serverSocket;

	/** 
	 * Creates a connection to the server that will remain open until this class' close() function is called
	 * @param serverIpAddress - the IP address of the server
	 * @param serverPort - the port number the server should be listening to 
	 */
	public ClientConnect(String serverIpAddress, int serverPort){
		this.serverIpAddress = serverIpAddress;
		this.serverPort = serverPort;

		try{
			InetAddress serverAddr = InetAddress.getByName(this.serverIpAddress);
			this.serverSocket = new Socket(serverAddr, this.serverPort);
			serverSocket.setTcpNoDelay(true);
		} catch(Exception e){
		}
	}

	/** 
	 * 
	 * Sends a single datagram objects payload to the already connected server.
	 * @param d - the datagram to be sent to the server
	 * @return -1 if there is an error and 1 if successful
	 *  
	 */
	public int send(Datagram d) {

		/** Create the message to be sent */
		String message = d.createMessage();

		try{

			/** Write the data to the socket */
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.serverSocket.getOutputStream())), true);
			out.println(message);
			
		} catch (Exception e) {
			return -1;
		}
		
		return 1;
	}
	
	/**
	 * Close the socket to the server.
	 */
	public void close(){
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
