package com.iprjappteste.data;

public class Atividade {
    public int id;
    public String title;
    public String id_corpo;
    public String id_time;
  
 
    public Atividade(){}
 
    public Atividade(String title, String id_corpo) {
        super();
        this.title = title;
        this.id = id;
        this.id_corpo = id_corpo;
        this.id_time = id_time;
      
    }
 
   
 
    @Override
    public String toString() {
        return "Atividade [id=" + id + ", title=" + title +", id_corpo = " + id_corpo + "]";
    }


	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title  = title;
	}
	
	public void setCorpo(String id_corpo) {
		// TODO Auto-generated method stub
		this.id_corpo  = id_corpo;
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
	
	
	public String get_Corpo() {
		// TODO Auto-generated method stub
		return this.id_corpo;
	}


	public String getId_Time() {
		// TODO Auto-generated method stub
		return this.id_time;
	}
	
}
