package com.iprjappteste.data;

public class Curso {
	 
    public int id;
    public String title;
  
 
    public Curso(){}
 
    public Curso(String title) {
        super();
        this.title = title;
        this.id = id;
       
    }
 
    
 
    @Override
    public String toString() {
        return "Curso [id=" + id + ", title=" + title +"]";
    }


	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title  = title;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAuthor(String string) {
		// TODO Auto-generated method stub
		
	}
}