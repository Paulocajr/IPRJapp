package com.iprjappteste.data;



public class Avisos_Prof {
    public int id;
    public String title;
    public String user;
    public String id_time;
  
 
    public Avisos_Prof(){}
 
    public Avisos_Prof(String title, String user) {
        super();
        this.title = title;
        this.id = id;
        this.user = user;
        this.id_time = id_time;
      
    }
 
   
 
    @Override
    public String toString() {
        return "Avisos [id=" + id + ", title=" + title +", id_corpo = " + user + "]";
    }


	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title  = title;
	}
	
	public void setUser(String user) {
		// TODO Auto-generated method stub
		this.user  = user;
	}
	
	public void setTime(String id_time) {
		// TODO Auto-generated method stub
		this.id_time  = id_time;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
		
		
	}
	
	
	public String get_User() {
		// TODO Auto-generated method stub
		return this.user;
	}


	public String getId_Time() {
		// TODO Auto-generated method stub
		return this.id_time;
	}
	
}

