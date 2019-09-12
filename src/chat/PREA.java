package chat;

public class PREA 
{
	public final static String accept = "_ACCEPT";
	public final static String acceptFirst = "_ACCEPT_FIRST";
	public final static String updateListen = "_UPDATE_LISTEN";
	public final static String updateSendRequest = "_UPDATE_SEND_REQUEST";
	public final static String sendName = "_SEND_NAME";
	public final static String requestJoin = "_REQUEST_JOIN";
	public final static String requestUpdate = "_REQUEST_UPDATE";
	
	public static boolean isProtocol(String message)
	{
		if(message.startsWith(accept))
			return true;
		if(message.startsWith(acceptFirst))
			return true;
		if(message.startsWith(sendName))
			return true;
		if(message.startsWith(updateListen))
			return true;
		if(message.startsWith(updateSendRequest))
			return true;
		if(message.startsWith(requestJoin))
			return true;
		if(message.startsWith(requestUpdate))
			return true;
		return false;
	}
}
