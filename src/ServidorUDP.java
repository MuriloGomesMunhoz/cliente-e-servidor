 import java.io.IOException;
 import java.net.DatagramPacket;
 import java.net.DatagramSocket;
 import java.net.SocketException;
 import java.util.Scanner;
 import java.net.InetAddress;

 public class ServidorUDP {
	 public static void main(String[] args) {
		 String array[] = new String[3];
		 System.out.println("Servidor UDP online");
		 DatagramSocket socket = null;
		 try {
		 	socket = new DatagramSocket(7890);
		 	byte[] buffer = new byte[10000];
		 	while (true) {
		 		DatagramPacket requisicao = new DatagramPacket(buffer, buffer.length);

				socket.receive(requisicao);
			 	System.out.println("Recebi requisicao: " + requisicao.getAddress().toString());

			 	System.out.println( new String(requisicao.getData()));
			 	String mc = new String(requisicao.getData());
		 
				array = mc.split(":");
				String op = array[0];
				int a = Integer.parseInt(array[1]);
				int b = Integer.parseInt(array[2]);
		
		

				String resposta = "";

			if(op.equals("soma"))
			{
		
				resposta = "Seu resultado é: " + Integer.toString(soma(a,b))+" ";
		    	}
			if(op.equals("divisao"))
			{
				resposta = "Seu resultado é: " + Integer.toString(divisao(a,b))+" ";
			}
			if(op.equals("multiplicacao"))
			{
				resposta = "Seu resultado é: " + Integer.toString(multiplicacao(a,b))+" ";			
			}
			if(op.equals("subtracao"))
			{
				resposta = "Seu resultado é: " + Integer.toString(subtracao(a,b))+" ";
			}

			byte[] resposta2 = resposta.getBytes();
			int tam = resposta.length();
			
			System.out.println(resposta);
			DatagramPacket result = new DatagramPacket(
			      resposta2,
			      tam,
		     	      requisicao.getAddress(),
		      	      requisicao.getPort());
		
		      	socket.send(result);
		      	System.out.println("Resposta enviada para: " + requisicao.getAddress().toString() + "\n");
		 }

		 }catch (SocketException e) {
		 	System.out.println("Socket: " + e.getMessage());
		 }
		 catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		 }

	 }
	 
	 public static int soma(int a,int b){
		System.out.println("Entrei na função  soma");
    		 return a+b;
	 }
	  public static int divisao(int a, int b){
	     return a/b;
	 }
	   public static int multiplicacao(int a, int b){
	     return a*b;
	 }
	    public static int subtracao( int a, int b){
	     return a-b;
	 }
 }
    
   

