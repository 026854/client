package com.rabbitmq.client.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

	private String id;
	private String menu, base, core;


	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String id,String menu) {
		super();
		this.id = id;
		this.menu = menu;
	}
	public Message(String id){
		super();
		this.id = id;
	}
	public Message(String id,String menu,String base,String core) {
		super();
		this.id = id;
		this.menu = menu;
		this.base = base;
		this.core = core;

	}

}
