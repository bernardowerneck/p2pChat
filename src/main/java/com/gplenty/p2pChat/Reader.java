package com.gplenty.p2pChat;

import java.io.PrintWriter;
import java.util.Scanner;


public class Reader implements Runnable
{
	private Scanner in;
	private PrintWriter out;
	
	private User user;
	private String ignore;
	
	public Reader(Scanner in, PrintWriter out, User user, String ignore)
	{
		this.in = in;
		this.out = out;
		this.user = user;
		this.ignore = ignore;
	}

	@Override
	public void run() {
		String message;
		while(true)
		{
			message = in.nextLine();
			if(!PREA.isProtocol(message))
			{
				System.out.println(message);
				if(!message.startsWith("[" + ignore + "]"))
					out.println(message);
			}
		}
	}
	
	public void setIn(Scanner in) {
		this.in = in;
	}
	
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	
	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}
}
