package volo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

// classe per creare i voli
public class Main {
	/*
	 * Codice univoco
	 * Data
	 * Ora
	 * Tipo aereo
	 * Capienza
	 */
	
	
	public static void main(String[] args){
		
		
		int MIN_ENTRY_PER_VOLO = 30;
		int MAX_ENTRY_PER_VOLO = 30;
		int MAX_ENTRY_TOTALI   = 5000;
		int MAX_VOLI_GIORNALIERI = 2;
		int MIN_VOLI_GIORNALIERI = 1;
		int MESE = 11;
		
		Map<String, Integer> aerei = new LinkedHashMap<String, Integer>();
		
		aerei.put("ATR 72-200", 66); // max 1600 miglia
		aerei.put("ATR 72-500", 66);
		aerei.put("Embraer ERJ 145LR", 48);
		aerei.put("Embraer 170-100LR", 72);
		aerei.put("Airbus A318-100", 131);
		aerei.put("Airbus A319-100", 142);
		aerei.put("Airbus A319-100", 126);
		
		aerei.put("Airbus A321-100", 187);//3740
		aerei.put("Airbus A320-200", 153);
		aerei.put("Boeing 737-800 Next Generation", 189);
		aerei.put("McDonnell Douglas MD-82", 164);
		aerei.put("Airbus A320-200", 178);
		aerei.put("Airbus A321-200", 212);
		aerei.put("Airbus A330-200", 208);
		aerei.put("Airbus 330-200", 230);
		
		aerei.put("Boeing 777-200ER", 291);//8726
		aerei.put("Airbus A340-300", 275);
		aerei.put("Airbus A380-800", 516);
		aerei.put("Boeing 747-400", 432);
		aerei.put("Boeing 777-200ER", 309);
		aerei.put("Boeing 777-300ER", 472);
		aerei.put("Airbus A330-200", 278);
		aerei.put("Airbus A340-300", 267);
		aerei.put("Airbus A340-500", 258);
		aerei.put("Airbus A380-800", 517);
		aerei.put("Boeing 777-200", 346);
		aerei.put("Boeing 777-200ER", 274);
		aerei.put("Boeing 777-200LR", 266);
		aerei.put("Boeing 777-300", 364);
		aerei.put("Boeing 777-300ER", 442);
		
		ArrayList<String> nomeAereo = new ArrayList<>(); 
	    Iterator it_ = aerei.entrySet().iterator();
	    while (it_.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it_.next();
	        nomeAereo.add((String) pairs.getKey());
	    }
			WriteToFile wtf = null ;
		    
	    for( int w = 2010; w <= 2014; w++ )
	        {
	        	
	        	
	        	for( int k = 0; k < 12 ; k++ )
	        	{
	        	
			
	//		String user = "giovanni";
	//		String passwd = "ciaociao1992";
	//		String url = "jdbc:postgresql://192.168.0.10/dbuser01";
			String user = "userlab01";
			String passwd = "uno8M";
			String url = "jdbc:postgresql://localhost/dblab01";
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
	
			int distanza, id_tratta, cap_sel;
			String aereo_sel,codicevolo=null;
			int pos_aereo;
			RandomString rdm = new RandomString(8);
			int c=0;
			
			
			try {
				Class.forName("org.postgresql.Driver");
			}
			catch (ClassNotFoundException cnfe) {
				System.out.println("Driver jdbc non trovato: " + cnfe.getMessage());
			}
			try{
				con = DriverManager.getConnection(url, user, passwd);
				stmt = con.createStatement();
				
			    // ottengo dal database la lista delle tratte e assegno un aereo
			    // a seconda della distanza da percorrere
			    
				
				rs = stmt.executeQuery("SELECT partenza, arrivo, distanza from tratta;");
				while (rs.next())
				{
					distanza = rs.getInt("distanza");
					String partenza = rs.getString("partenza");
					String arrivo = rs.getString("arrivo");
					
			        GregorianCalendar gc = new GregorianCalendar();
			        gc.set(gc.YEAR, w);
			    	gc.set(gc.MONTH, k );

							for ( int i = 1; i <= gc.getActualMaximum(gc.DAY_OF_MONTH) ; i++ )
							{
			
						        gc.set(gc.DAY_OF_MONTH, i);
						        Date date = new Date(gc.getTimeInMillis());
						        wtf = new WriteToFile(w+i);
						        
								for ( int j = 0; j < randBetween(MIN_VOLI_GIORNALIERI, MAX_VOLI_GIORNALIERI) ; j++ )
								{
									if ( distanza < 1600 )
									{
										pos_aereo = (int) (0 + (Math.random() * (6 - 0)));
									}
									else if( distanza >= 1600 && distanza < 3700)
									{
										pos_aereo = (int) (6 + (Math.random() * (14 - 6)));
									}
									else 
									{
										pos_aereo = (int) (14 + (Math.random() * ( ( aerei.size() - 1) - 14)));
									}
									cap_sel = aerei.get(nomeAereo.get(pos_aereo));
									aereo_sel = nomeAereo.get(pos_aereo);
									
							        int ora = randBetween(7, 22);
							        int minuti = randBetween(0, 59);
							        Time time = new Time(ora, minuti, 0);
							        
							        codicevolo = rdm.nextString();
							        
							        if ( c < MAX_ENTRY_TOTALI )					
							        	wtf.writeToFile("INSERT INTO volo (codicevolo,datapartenza,orapartenza,tipoaereo,capienza, partenza, arrivo) VALUES ('" + codicevolo  +"','"+ date+ "','" + time+"','"+aereo_sel+"','"+cap_sel+"','" + partenza +"','"+arrivo+"');");
							        
							        c++;
								}
								wtf.close();
							}
							 
			        	}
			        }
				}
				
				//Chiudo la connessione
				con.close();
			}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
	}
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
