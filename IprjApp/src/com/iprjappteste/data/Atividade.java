package com.iprjappteste.data;


/*
  * Atividade.java
  * Versão: <v2.0>
  * Data de Criação : 10/09/2014
  * Copyright (C) 2014 Paulo cabral
  * Instituto Politécnico do Estado do Rio de Janeiro
  * IPRJ - http://www.iprj.uerj.br
  * Classe responsável pela criação da entidade Atividade no banco de dados
  * Todos os direitos reservados.
 */
public class Atividade {

//*********************Declaração de Variáveis********************//	
	
    public int id;
    public String title;
    public String id_corpo;
    public String id_time;
    public Atividade(){}
 
//Objeto Atividade 
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

//Métodos get e set usados para retornar o valores

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
