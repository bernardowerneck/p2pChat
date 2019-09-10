package com.gplenty.p2pChat;

import java.io.PrintWriter;
import java.util.Scanner;


@SuppressWarnings("unused")
public class Sender implements Runnable
{
	private Scanner scanner = new Scanner(System.in);
	private PrintWriter out;
	private String nome;
	private User user;
	
	public Sender(PrintWriter out, String nome, User user)
	{
		this.out = out;
		this.nome = nome;
		this.user = user;
	}
	
	@Override
	public void run() {
		while(true)
		{
			out.println("[" + nome + "] " + scanner.nextLine());
		}
	}
	
	public void sendMessage(String message)
	{
		out.println(message);
	}
	
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}
