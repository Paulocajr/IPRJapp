package com.iprjappteste.data;

public class Usuario {
	
	   public int id;
	   public String id_facebook;
	    public String title;
	  
	 
	    public Usuario(){}
	 
	    public Usuario(String title, String id_facebook ) {
	        super();
	        this.title = title;
	        this.id = id;
	       this.id_facebook = id_facebook;
	    }
	 
	    @Override
	    public String toString() { 
	        return "Curso [id=" + id + ", title=" + title +" ,id_facebook = "+id_facebook+"]";
	    }


		public void setId(int id) {
			// TODO Auto-generated method stub
			this.id = id;
		}
		
		public void setId_Facebook(String id_facebook) {
			// TODO Auto-generated method stub
			this.id_facebook = id_facebook;
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

		
		public String getId_Facebook() {
			// TODO Auto-generated method stub
			return this.id_facebook;
		}
		
		
	}

