package com.appsrise.spamfilter;

import java.io.Serializable;

/**
 * SMSObject
 * 
 * SMSObject Class contains info about a SMS message
 * 
 * version: 1.0
 */
public class SmsInfo implements Serializable{
	private static final long serialVersionUID = 2676793567924336339L;

	public SmsInfo() {
		super();
	}
	
	int sms_pk;
	int _id;
	int thread_id;
	String address;  //contains the phone number
	String person;
	long date;
	String protocol;
	int read;
	int status;
	int type;        // 1 Received, 2 Sent
	String reply_path_present;
	String subject;
	String body;
	String service_center;
	int locked;
	int error_code;
	int backup;
	int hidden;
	
	public int getSms_pk() {
		return sms_pk;
	}
	
	public void setSms_pk(int sms_pk) {
		this.sms_pk = sms_pk;
	}
	
	public int getId() {
		return _id;
	}
	
	public void setId(int _id) {
		this._id = _id;
	}
	
	public int getThread_id() {
		return thread_id;
	}
	
	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public long getDate() {
		return date;
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public int getRead() {
		return read;
	}
	
	public void setRead(int read) {
		this.read = read;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getReply_path_present() {
		return reply_path_present;
	}
	
	public void setReply_path_present(String reply_path_present) {
		this.reply_path_present = reply_path_present;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getService_center() {
		return service_center;
	}
	
	public void setService_center(String service_center) {
		this.service_center = service_center;
	}
	
	public int getLocked() {
		return locked;
	}
	
	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	public int getError_code() {
		return error_code;
	}
	
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public int getBackup() {
		return backup;
	}

	public void setBackup(int backup) {
		this.backup = backup;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
}