package com.veryworks.fcmsender;

public class Msg {
	String to;
	Data data;
	Notification notification;
	
	public Msg(){
		data = new Data();
		notification = new Notification();
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Notification getNotification() {
		return notification;
	}
	public void setNotification(Notification notification) {
		this.notification = notification;
	}
	
	class Data {
		String point;
		public String getPoint() {
			return point;
		}
		public void setPoint(String point) {
			this.point = point;
		}
	}
	
	class Notification{
		String title;
		String body;
		String click_action;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getClick_action() {
			return click_action;
		}
		public void setClick_action(String click_action) {
			this.click_action = click_action;
		}
		
	}
}
