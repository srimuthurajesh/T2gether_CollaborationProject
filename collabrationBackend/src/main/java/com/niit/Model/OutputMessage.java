package com.niit.Model;

import java.util.Date;

public class OutputMessage extends Message {

	 private Date time;
	    
	    public OutputMessage(Message original, Date time, String username) {
	        super(original.getId(), original.getMessage());
	        this.time = time;
	    }
	    
	    public Date getTime() {
	        return time;
	    }
	    
	    public void setTime(Date time) {
	        this.time = time;
	    }
	
}
