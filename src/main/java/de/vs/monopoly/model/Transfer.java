package de.vs.monopoly.model;

public class Transfer {
	public String from;
	public String to;
	public String reason;
	public String event;
	
	public Transfer(String from, String to, String reason, String event) throws Exception{
		if(from.isEmpty() || to.isEmpty()){
			throw new Exception("from or to is Empty or null.");
		}
		this.from = from;
		this.to= to;
		this.reason = reason;
		this.event = event;
	}
}
