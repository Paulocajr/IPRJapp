package com.iprjappteste.data;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;
import android.content.ContentValues;
import com.iprjappteste.data.Curso;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase; 
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
	 
    // Versão do Banco de Dados
    private static final int DATABASE_VERSION = 3;
    // Nome do Banco
    private static final String DATABASE_NAME = "IprjApp";
    
    // Nome das Tabelas
    private static final String TABLE_CURSOS = "curso";
    private static final String TABLE_ATIVIDADES = "atividade";
    private static final String TABLE_PERIODOS = "periodo";
    private static final String TABLE_USUARIO = "usuario";
    private static final String TABLE_AVISOS_CAIP = "aviso";
    private static final String TABLE_AVISOS_PROF = "aviso_prof";


    // Nome das Colunas das Tabelas
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_FACEBOOK = "id_facebook";
    private static final String KEY_CORPO = "id_corpo";
    private static final String KEY_TIME = "id_time";
    private static final String KEY_NUMERO = "numero_per";
    private static final String KEY_USER = "user";


   // Colunas
    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_FACEBOOK,KEY_CORPO,KEY_TIME,KEY_NUMERO,KEY_USER};
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL para criação de tabelas
    	
    	String CREATE_PERIODO_TABLE = "CREATE TABLE periodo ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " + 
                "title TEXT ,"+
                "numero_per TEXT)";
    	
    	
    	String CREATE_USUARIO_TABLE = "CREATE TABLE usuario ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " + 
                "title TEXT , "+
                "id_facebook TEXT )";
 
    	
    	String CREATE_CURSO_TABLE = "CREATE TABLE curso ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
                "title TEXT)";
    	
    	String CREATE_ATIVIDADE_TABLE = "CREATE TABLE atividade ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " + 
                "title TEXT ,"+
                "id_corpo TEXT ,"+
                "id_time TEXT)";
    	

    	String CREATE_AVISOS_CAIP_TABLE = "CREATE TABLE aviso ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " + 
                "title TEXT ,"+
                "id_corpo TEXT ,"+
                "id_time TEXT)";
    	
     	String CREATE_AVISOS_PROF_TABLE = "CREATE TABLE aviso_prof ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " + 
                "title TEXT ,"+
                "id_corpo TEXT ,"+
                "user TEXT)";
    	
    	
        // criando tabelas
    	
        db.execSQL(CREATE_CURSO_TABLE);
        db.execSQL(CREATE_ATIVIDADE_TABLE);
        db.execSQL(CREATE_PERIODO_TABLE);
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_AVISOS_CAIP_TABLE);
        db.execSQL(CREATE_AVISOS_PROF_TABLE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	
        // Dropando tabelas antigas se já existirem
    	
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_CURSOS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ATIVIDADES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PERIODOS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AVISOS_CAIP);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_AVISOS_PROF);
        
 
      
        this.onCreate(db);
    }
   
    
    public void addCurso(Curso curso){
       
     Log.d("addCurso", curso.toString()); 

       // 1. obtendo acesso à leitura no Banco
       SQLiteDatabase db = this.getWritableDatabase();

       // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, curso.getTitle()); // get title 
       // values.put(KEY_AUTHOR, curso.getAuthor()); // get author

           // 3. insert
          db.insert(TABLE_CURSOS, // table
          null, //nullColumnHack
          values); // key/value -> keys = column names/ values = column values

      // 4. close
         db.close(); 
       }
    
    public void addUsuario(Usuario usuario){
        //for logging
     Log.d("addUsuario", usuario.toString()); 
   
       // 1. obtendo acesso à leitura no Banco
       SQLiteDatabase db = this.getWritableDatabase();

       // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, usuario.getTitle()); // get title 
        values.put(KEY_FACEBOOK, usuario.getId_Facebook()); // get author

          // 3. insert
          db.insert(TABLE_USUARIO, // table
          null, //nullColumnHack
          values); // key/value -> keys = column names/ values = column values

      // 4. close
         db.close(); 
       }
    
    public void addPeriodo(Periodos periodo){
    	
    	Log.d("addPeriodo", periodo.toString()); 

        SQLiteDatabase db = this.getWritableDatabase();
         
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, periodo.getTitle());
        values.put(KEY_NUMERO, periodo.getNum_per());
          
        // Inserindo linha
        db.insert(TABLE_PERIODOS, null, values);
        db.close(); // Closing database connection
    }
    
    public List<Periodos>getAllPeriodos(){
    	
        List<Periodos> periodos = new LinkedList<Periodos>();
         
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_PERIODOS +" LIMIT 0, 2" ;
        // " LIMIT 0, 11"
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Periodos periodo = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	periodo = new Periodos();
            	
            	
            	 periodo.setId(Integer.parseInt(cursor.getString(0)));
                 periodo.setTitle(cursor.getString(1));
                 periodo.setNum_per(cursor.getString(2));
                 periodos.add(periodo);
              
               
            } while (cursor.moveToNext());
        }
         
        Log.d("getAllPeriodos()", periodos.toString());
        // fechando conexão
       cursor.close();
       db.close();
         
        // retornando tabelas
        return periodos;
    }
    
public List<Usuario>getAllUsuario(){
    	
        List<Usuario> usuario = new LinkedList<Usuario>();
         
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_USUARIO + " WHERE "+KEY_ID+" = (select max("+KEY_ID+") from "+TABLE_USUARIO+")";
        // " LIMIT 0, 11"
      
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Usuario usuarios = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	usuarios = new Usuario();
            	
            	//periodos.add(cursor.getString(1));
            	 usuarios.setId(Integer.parseInt(cursor.getString(0)));

                 usuarios.setTitle(cursor.getString(1));
                 usuarios.setId_Facebook((cursor.getString(2)));
                 //periodos.add(cursor.getString(1));
            	 usuario.add(usuarios);
               
               // curso.setAuthor(cursor.getString(2));
  
                // Add book to books
                //periodos.add(periodo);
               
            } while (cursor.moveToNext());
        }
         
        Log.d("getUsuario()", usuario.toString());
        // fechando conexão
       cursor.close();
       db.close();
         
        // retornando tabelas
        return usuario;
    }
    
    
    public Curso getCurso(int id){
    	 
        // 1.obter acesso à leitura do BD
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. construindo query
        Cursor cursor = 
                db.query(TABLE_CURSOS, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build book object
        Curso curso = new Curso();
        
        
        curso.setId(Integer.parseInt(cursor.getString(0)));
        curso.setTitle(cursor.getString(1));
       // curso.setAuthor(cursor.getString(2));
     
        //log 
    Log.d("getCurso("+id+")", curso.toString());
     
        // 5. return book
        return curso;
    }
    public List<Curso> getAllCursos() {
        List<Curso> cursos = new LinkedList<Curso>();
  
        // 1. construindo consulta
        String query = "SELECT  * FROM " + TABLE_CURSOS +  " LIMIT 0, 1";
  
        // 2. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        Curso curso = null;
        if (cursor.moveToFirst()) {
            do {
                curso = new Curso();
                curso.setId(Integer.parseInt(cursor.getString(0)));
                curso.setTitle(cursor.getString(1));
               // curso.setAuthor(cursor.getString(2));
  
                // Add book to books
                cursos.add(curso);
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllCursos()", cursos.toString());
  
        // return books
        return cursos;
    }
    public int updateAtividade(Atividade atividade) {
   	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", atividade.getTitle()); // get title 
        //values.put("author", curso.getAuthor()); // get author
     
        // 3. atualizando linha
        int i = db.update(TABLE_ATIVIDADES, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(atividade.getId()) }); //selection args
     
        // 4. close
        db.close();
     
        return i;
     
    }
    public int updateCurso(Curso curso) {
    	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        
        ContentValues values = new ContentValues();
        values.put("title", curso.getTitle()); // get title 
        //values.put("author", curso.getAuthor()); // get author
     
        // 3. atualizando linha
        int i = db.update(TABLE_CURSOS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(curso.getId()) }); //selection args
     
        // 4. close
        db.close();
     
        return i;
     
    }
    
    public void deleteCurso() {
    	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_CURSOS,null,null);
        //db.execSQL(" delete * from " + TABLE_CURSOS);
        //db.execSQL("delete * from"+ TABLE_NAME);
        // 3. close
        db.close();
 
        //log
   // Log.d("deleteCurso", curso.toString());
 
    }

    public void deletePeriodo() {
   	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_PERIODOS,null,null);
       // db.execSQL(" delete * from " + TABLE_PERIODOS);
        //db.execSQL("delete * from"+ TABLE_NAME);
        // 3. close
        db.close();
 
        //log
   // Log.d("deleteCurso", curso.toString());
 
    }
    
    public void deleteUsuario() {
      	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_USUARIO,null,null);
        //db.execSQL(" delete * from " + TABLE_USUARIO);
        //db.execSQL("delete * from"+ TABLE_NAME);
        // 3. close
        db.close();
 
        //log
   // Log.d("deleteCurso", curso.toString());
 
    }
    
    public void addAtividade(Atividade atividade){
        //for logging
     Log.d("addAtividade", atividade.toString()); 

    // 1. obtendo acesso à leitura no Banco
       SQLiteDatabase db = this.getWritableDatabase();

       // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, atividade.getTitle()); // get title 
       values.put(KEY_CORPO, atividade.get_Corpo());
       values.put(KEY_TIME, atividade.getId_Time());// get author

          // 3. insert
          db.insert(TABLE_ATIVIDADES, // table
        		  " LIMIT 0, 2", //nullColumnHack
          values); // key/value -> keys = column names/ values = column values

      // 4. close
         db.close(); 
       }

    
    public void addAviso(Avisos_Caip aviso){
        //for logging
     Log.d("addAviso", aviso.toString()); 

    // 1. obtendo acesso à leitura no Banco
       SQLiteDatabase db = this.getWritableDatabase();

       // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, aviso.getTitle()); // get title 
       values.put(KEY_CORPO, aviso.get_User());
       values.put(KEY_TIME, aviso.getId_Time());// get author

          // 3. insert
          db.insert(TABLE_AVISOS_CAIP, // table
        		  " LIMIT 0, 2", //nullColumnHack
          values); // key/value -> keys = column names/ values = column values

      // 4. close
         db.close(); 
       }
    
    public void addAviso_Prof(Avisos_Prof aviso_prof){
        //for loggingpro
     Log.d("addAviso_Prof", aviso_prof.toString()); 

    // 1. obtendo acesso à leitura no Banco
       SQLiteDatabase db = this.getWritableDatabase();

       // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, aviso_prof.getTitle()); // get title 
       values.put(KEY_CORPO, aviso_prof.get_User());
       //values.put(KEY_TIME, aviso_prof.getId_Time());// get author

          // 3. insert
          db.insert(TABLE_AVISOS_PROF, // table
        		  " LIMIT 0, 2", //nullColumnHack
          values); // key/value -> keys = column names/ values = column values

      // 4. close
         db.close(); 
       }
    
    public Atividade getAtividade(int id){
    	 
        // 1.obter acesso à leitura do BD
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. construindo query
        Cursor cursor = 
                db.query(TABLE_ATIVIDADES, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build book object
       Atividade atividade = new Atividade();
        
        
        atividade.setId(Integer.parseInt(cursor.getString(0)));
        atividade.setTitle(cursor.getString(1));
       // curso.setAuthor(cursor.getString(2));
     
        //log 
    Log.d("getAtividade("+id+")", atividade.toString());
     
        // 5. return book
        return atividade;
    }
    
    public Avisos_Caip getAviso(int id){
   	 
        // 1.obter acesso à leitura do BD
        SQLiteDatabase db = this.getReadableDatabase();
     
        // 2. construindo query
        Cursor cursor = 
                db.query(TABLE_AVISOS_CAIP, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
     
        // 4. build book object
       Avisos_Caip aviso = new Avisos_Caip();
        
        
        aviso.setId(Integer.parseInt(cursor.getString(0)));
        aviso.setTitle(cursor.getString(1));
       // curso.setAuthor(cursor.getString(2));
     
        //log 
    Log.d("getAviso("+id+")", aviso.toString());
     
        // 5. return book
        return aviso;
    }
    
    public List<Atividade> getAllAtividades() {
    	
        List<Atividade> atividades = new LinkedList<Atividade>();
  
        // 1. construindo consulta
       
        String query = "SELECT * FROM " + TABLE_ATIVIDADES + " WHERE "+KEY_ID+" = (select max("+KEY_ID+") from "+TABLE_ATIVIDADES+")";
        // 2. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        Atividade atividade = null;
        if (cursor.moveToFirst()) {
            do {
                atividade = new Atividade();
                atividade.setId(Integer.parseInt(cursor.getString(0)));
                atividade.setTitle(cursor.getString(1));
                atividade.setCorpo(cursor.getString(2));
                atividade.setTime(cursor.getString(3));
                // Add book to books
                atividades.add(atividade);
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllAtividades()", atividades.toString());
  
        // return books
        return atividades;
    }
    
  public List<Avisos_Caip> getAllAvisos() {
    	
        List<Avisos_Caip> avisos = new LinkedList<Avisos_Caip>();
  
        // 1. construindo consulta
       
        String query = "SELECT * FROM " + TABLE_AVISOS_CAIP + " WHERE "+KEY_ID+" = (select max("+KEY_ID+") from "+TABLE_AVISOS_CAIP+")";
        // 2. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build book and add it to list
        Avisos_Caip aviso = null;
        if (cursor.moveToFirst()) {
            do {
                aviso = new Avisos_Caip();
                aviso.setId(Integer.parseInt(cursor.getString(0)));
                aviso.setTitle(cursor.getString(1));
                aviso.setUser(cursor.getString(2));
                aviso.setTime(cursor.getString(3));
                // Add book to books
                avisos.add(aviso);
            } while (cursor.moveToNext());
        }
  
        Log.d("getAllAvisos()", avisos.toString());
  
        // return books
        return avisos;
    }
    
  
public List<Avisos_Prof> getAllAvisos_prof() {
  	
      List<Avisos_Prof> avisos = new LinkedList<Avisos_Prof>();

      // 1. construindo consulta
     
      String query = "SELECT * FROM " + TABLE_AVISOS_PROF + " WHERE "+KEY_ID+" = (select max("+KEY_ID+") from "+TABLE_AVISOS_PROF+")";
      // 2. obtendo acesso à leitura no Banco
      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(query, null);

      // 3. go over each row, build book and add it to list
      Avisos_Prof aviso = null;
      if (cursor.moveToFirst()) {
          do {
              aviso = new Avisos_Prof();
              aviso.setId(Integer.parseInt(cursor.getString(0)));
              aviso.setTitle(cursor.getString(1));
              aviso.setUser(cursor.getString(2));
              aviso.setTime(cursor.getString(3));
              // Add book to books
              avisos.add(aviso);
          } while (cursor.moveToNext());
      }

      Log.d("getAllAvisos_Prof()", avisos.toString());

      // return books
      return avisos;
  }
  
    public void deleteAtividade(Atividade atividade) {
   	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_ATIVIDADES, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(atividade.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    Log.d("deleteAtividade", atividade.toString());
 
    }
    
    public void deleteAviso(Avisos_Caip aviso) {
      	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_ATIVIDADES, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(aviso.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    Log.d("deleteAviso", aviso.toString());
 
    }
    
    
    public void deleteAviso_prof(Avisos_Prof aviso_prof) {
      	 
        // 1. obtendo acesso à leitura no Banco
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_ATIVIDADES, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(aviso_prof.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
    Log.d("deleteAviso_Prof", aviso_prof.toString());
 
    }
		
	}

