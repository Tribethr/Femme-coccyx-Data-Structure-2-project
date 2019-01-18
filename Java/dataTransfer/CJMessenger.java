package dataTransfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class CJMessenger {

   private final int PORT = 8086;
   private DataOutputStream writer;
   private DataInputStream reader;
   private Socket socket;
   
   public void doConnection() {
	   try {
		   socket = new Socket("localhost", PORT);
		} catch (IOException e) {
			System.out.println("Can't create the conection.");
		}
	    try {
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Cant get client io streams");
		}
   }
   
   public void sendData(int pData) {
	   try {   
		   writer.write(new byte[] {(byte)pData,(byte)(pData >>> 8),(byte)(pData >>> 16),(byte)(pData >>>24)});
	   } catch (IOException e) {
		   System.out.println("Can't send data to client.");
		   System.out.println(e.getMessage());
	   }	   
   }
   
   public int waitForData() {
	   try {
		   byte[] data = new byte[4];
		   for(int i = 3; i>=0; i--) {
			   data[i] = reader.readByte();
		   }
		   ByteBuffer buffer = ByteBuffer.wrap(data);
		   return buffer.getInt();
	   } catch (IOException e) {
		   System.out.println("Can't get info from client.");
		   return -1;
	   }	   
   }
}