package com.gplenty.p2pChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class User 
{
	protected String name;
	protected Sender sender;
	protected Reader reader;
	protected Socket senderSocket;
	protected Socket readerSocket;
	protected ServerSocket listenSocket;
	
	public User(String name) throws IOException
	{
		this.name = name;
		listenSocket = new ServerSocket(59001);
	}
	
	public void beginChat() throws IOException
	{
		senderSocket = listenSocket.accept();
		System.out.println("ALGUEM SE CONECTOU");
		readerSocket = senderSocket;
		PrintWriter pw = new PrintWriter(senderSocket.getOutputStream(), true);
		Scanner scn = new Scanner(readerSocket.getInputStream());
		sender = new Sender(pw, name, this);
		String ignore = scn.nextLine().split(":")[1];
		reader = new Reader(scn, pw, this, ignore);
		sender.sendMessage(PREA.acceptFirst + ":" + name);
		
	}
	
	public void insertInChat() throws IOException
	{
		Socket socket = listenSocket.accept();
		
		Scanner scn = new Scanner(socket.getInputStream());
		String request = scn.nextLine();
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

		if(request.startsWith(PREA.requestJoin))
		{
			pw.println(PREA.accept + ":" + name + ":" + senderSocket.getInetAddress().toString().replace("/", ""));
			System.out.println(senderSocket.getInetAddress());
			System.out.println(senderSocket.getInetAddress().toString().replace("/", ""));
			
			reader.setIgnore(request.split(":")[1]);
			
			senderSocket = socket;
			sender.setOut(pw);
			
			reader.setOut(pw);
		}
		else
		{
			readerSocket = socket;
			reader.setIn(scn);
			reader.setIn(scn);
			pw.println(PREA.sendName + ":" + name);
		}
	}
	
	
	public void enterChat(String address) throws UnknownHostException, IOException
	{
		Socket socket = new Socket(address, 59001);
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		Scanner scn = new Scanner(socket.getInputStream());
		
		pw.println(PREA.requestJoin + ":" + name);
		
		readerSocket = socket;
		reader = new Reader(scn, pw, this, "oioioi");
		
		String[] message = scn.nextLine().split(":");
		if(message[0].equals(PREA.acceptFirst))
		{
			senderSocket = socket;
			sender = new Sender(pw, name, this);
			
			reader.setIgnore(message[1]);
		}
		else
		{
			socket = new Socket(message[2], 59001);
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println(PREA.requestUpdate);
			
			senderSocket = socket;
			sender = new Sender(pw, name, this);
			
			Scanner scanner = new Scanner(socket.getInputStream());
			
			reader.setIgnore(scanner.nextLine().split(":")[1]);
			reader.setOut(pw);
		}
	}
	
	public Reader getReader() {
		return reader;
	}
	
	public Sender getSender() {
		return sender;
	}
	
	public ServerSocket getListenSocket() {
		return listenSocket;
	}
}
