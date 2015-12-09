package de.vs.monopoly.model;

public class Service {

	public String name;
	public String description;
	public String url;
	public String servicename;
	public String status;
	public Service(String name, String des, String servicename, String status, String url) {
			this.name = name;
			this.description = des;
			this.url = url;
			this.servicename = servicename;
			this.status = status;
	}

}
