package com.iprjappteste.data;

/*
  * Curso.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pela criação da entidade Curso no banco de dados
  * Todos os direitos reservados.
 */


public class Curso {
	
//**************Declaração de Variáveis*************	 
    public int id;
    public String title;
    public Curso(){}

//Objeto Curso

    public Curso(String title) {
        super();
        this.title = title;
        this.id = id;
       
    }
 
    
 
    @Override
    public String toString() {
        return "Curso [id=" + id + ", title=" + title +"]";
    }


//Métodos get e set usados para retornar o valores

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
