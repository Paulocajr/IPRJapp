package com.iprjappteste.data;

/*
  * Periodos.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pela criação da entidade Periodo no banco de dados
  * Todos os direitos reservados.
 */

public class Periodos {
	
//************Declaração de Variáveis**********

    public int id;
    public String title;
    public String numero_per;
    
//Objeto Periodo    
    public Periodos(){}
 
    public Periodos(String title, String numero_per) {
        super();
        this.title = title;
        this.id = id;
        this.numero_per = numero_per;
      
    }
 
   
 
    @Override
    public String toString() {
        return "Periodos [id=" + id + ", title=" + title +", numero_per=" +numero_per+"]";
    }


//Métodos get e set usados para retornar e setar os valores

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title  = title;
	}
	
	public void setNum_per(String numero_per) {
		// TODO Auto-generated method stub
		this.numero_per = numero_per;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public String getNum_per() {
		// TODO Auto-generated method stub
		return this.numero_per;
	}


	public Periodos get(int listcounter) {
		// TODO Auto-generated method stub
		return null;
	}



}

