package com.iprjappteste.data;


/*
  * Usuario.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pela criação da entidade Usuário no banco de dados
  * Todos os direitos reservados.
 */

public class Usuario {
	
//Declaração de Varriáveis
	   public int id;
	   public String id_facebook;
	   public String title;
	   public Usuario(){}
	   
//Objeto Usuario	   
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

//Métodos get e set usados para retornar e setar os valores
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

