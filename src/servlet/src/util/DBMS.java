package util;
/**        DBMS.java        */
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {
	
	//Dati di identificazione dell'utente (da personalizzare)
    private String user = "userlab01";
    private String passwd = "uno8M";
    private String server = "localhost";
//    private String server = "dbserver.scienze.univr.it";
	
    /** URL per la connessione alla base di dati e' formato dai seguenti componenti:
     * <protocollo>://<host del server>/<nome base di dati>.
     */
    private String url = "jdbc:postgresql://"+ server +"/dblab01";
    
	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
    private String driver = "org.postgresql.Driver";
    
    /** DEFINIZIONE DELLE QUERY */
    String ricercaVoli = " SELECT datapartenza, codicevolo, volo.partenza, volo.arrivo, durata, orapartenza, tipoaereo " +
    					 " FROM tratta JOIN volo on ( tratta.partenza = volo.partenza AND tratta.arrivo = volo.arrivo )" +
    					 " WHERE datapartenza=? AND tratta.partenza ilike ? AND tratta.arrivo ilike ? ORDER BY orapartenza;";
    String datiVolo = " SELECT v.*, tr.durata, tr.distanza" + 
    				  " FROM tratta tr JOIN volo v ON ( v.partenza = tr.partenza AND v.arrivo = tr.arrivo) " +
    				  " WHERE v.codicevolo=?";
    String datiPasseggeroLogin = " SELECT nome,cognome,nazionalita,documento,tessera,numvoli,miglia,login " +
    				  			 " FROM passeggero " +
    				  			 " WHERE passeggero.login=?";
    String partenze = " SELECT DISTINCT partenza FROM tratta;";
    String arrivi = " SELECT DISTINCT arrivo FROM tratta;";
    String controllaPassword = "SELECT login FROM passeggero WHERE login=? AND password=?;";
    String newPasseggero = " INSERT INTO Passeggero (nome,cognome,documento,nazionalita,login,password,tessera) VALUES(?,?,?,?,?,?,?)";
    String newBiglietto = " INSERT INTO Biglietto (documento, codicevolo, prezzo, dataemissione ,id_prenotazione ) VALUES ( ?,?,?, current_date,? )" ;
    String newPrenotazione = " INSERT INTO Prenotazione ( documento, codicevolo, datarichiesta,orarichiesta) VALUES (?,?, current_date, current_time )";    
    
    
    
    /**
     * Costruttore della classe. Carica i driver da utilizzare per la
     * connessione alla base di dati.
     *
     * @throws ClassNotFoundException Eccezione generata nel caso in cui
     *         i driver per la connessione non siano trovati nel CLASSPATH.
     */
    public DBMS() throws ClassNotFoundException {
		Class.forName(driver);
    }
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public Vector<VoloBean> getRicercaVolo( Date date, String partenza, String arrivo ) 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		Vector<VoloBean> result = new Vector<VoloBean>();
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(ricercaVoli);
			pstmt.clearParameters();
			pstmt.setDate(1, date);
			pstmt.setString(2, partenza);
			pstmt.setString(3, arrivo);
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( makeRicercaVoloBean(rs) );
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	private VoloBean makeRicercaVoloBean( ResultSet rs ) throws SQLException
	{
		VoloBean bean = new VoloBean();
		bean.setCodicevolo(rs.getString("codicevolo"));
		bean.setDurata(rs.getInt("durata"));
		bean.setDatapartenza(rs.getDate("datapartenza"));
		bean.setOrapartenza(rs.getTime("orapartenza"));
		bean.setArrivo(rs.getString("arrivo"));
		bean.setPartenza(rs.getString("partenza"));
		bean.setTipoaereo(rs.getString("tipoaereo"));
		
		return bean;
	}
	//Metodo per ricercare un singolo volo
	public VoloBean getVolo( String codicevolo ) 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		VoloBean result = null;
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(datiVolo);
			pstmt.clearParameters();
			pstmt.setString(1, codicevolo);
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			if( rs.next() )
				result = makeVoloBean(rs);
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	private VoloBean makeVoloBean( ResultSet rs ) throws SQLException
	{
		VoloBean bean = new VoloBean();
		bean.setCodicevolo(rs.getString("codicevolo"));
		bean.setDurata(rs.getInt("durata"));
		bean.setDatapartenza(rs.getDate("datapartenza"));
		bean.setOrapartenza(rs.getTime("orapartenza"));
		bean.setArrivo(rs.getString("arrivo"));
		bean.setPartenza(rs.getString("partenza"));
		bean.setTipoaereo(rs.getString("tipoaereo"));
		bean.setDistanza(rs.getInt("distanza"));
		bean.setCapienza(rs.getInt("capienza"));
		
		return bean;
	}
	//Metodo per ricercare un singolo volo
	public PasseggeroBean getPasseggero( String username ) 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		PasseggeroBean result = null;
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(datiPasseggeroLogin);
			pstmt.clearParameters();
			pstmt.setString(1, username);
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			if( rs.next() )
				result = makePasseggeroBean(rs);
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	private PasseggeroBean makePasseggeroBean( ResultSet rs ) throws SQLException
	{
		PasseggeroBean bean = new PasseggeroBean();
		bean.setNome(rs.getString("nome"));
		bean.setCognome(rs.getString("cognome"));
		bean.setDocumento(rs.getString("documento"));
		bean.setNazionalita(rs.getString("nazionalita"));
		bean.setTessera(rs.getBoolean("tessera"));
		bean.setNumvoli(rs.getInt("numvoli"));
		bean.setMiglia(rs.getInt("miglia"));
		bean.setLogin(rs.getString("login"));
		
		return bean;
	}
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public Vector<String> getPartenze() 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		Vector<String> result = new Vector<String>();
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			stmt = con.createStatement();
			
			// Eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(partenze);
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( rs.getString("partenza") );
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	public Vector<String> getArrivi() 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		Vector<String> result = new Vector<String>();
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			stmt = con.createStatement();
			
			// Eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(arrivi);
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( rs.getString("arrivo") );
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	public boolean isLogin( String username, String password ) 
	{
		
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Ci possono essere dei voli corrispondenti e non solo uno solo
		boolean login = false;
		
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(controllaPassword);
			pstmt.clearParameters();
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			if( rs.next() )
			{
				if( rs.getString("login").compareTo(username) == 0)
					login = true;
			}
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
		return login;
    }
	public void newPasseggero(String nome, String cognome, String nazione, String documento, String username, String password, boolean tessera)
	{
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(newPasseggero);
			pstmt.clearParameters();
			pstmt.setString(1, nome);
			pstmt.setString(2, cognome);
			pstmt.setString(3, documento);
			pstmt.setString(4, nazione);
			pstmt.setString(5, username);
			pstmt.setString(6, password);
			pstmt.setBoolean(7, tessera);
			// Eseguo l'interrogazione desiderata
			pstmt.executeUpdate();
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
	}
	public void newBiglietto( String documento, String codicevolo, float prezzo , int id_prenotazione )
	{
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(newBiglietto);
			pstmt.clearParameters();
			pstmt.setString(1, documento);
			pstmt.setString(2, codicevolo);
			pstmt.setFloat(3, prezzo);
			pstmt.setInt(4, id_prenotazione);

			// Eseguo l'interrogazione desiderata
			pstmt.executeUpdate();
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
	}
	public void newPrenotazione(String codicevolo, String documento )
	{
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try 
		{
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(newPrenotazione);
			pstmt.clearParameters();
			pstmt.setString(1, documento);
			pstmt.setString(2, codicevolo);

			// Eseguo l'interrogazione desiderata
			pstmt.executeUpdate();
			
		} 
		catch(SQLException sqle) 
		{                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} 
		finally 
		{                                 /* Alla fine chiudo la connessione. */
			try 
			{
				con.close();
			} 
			catch(SQLException sqle1) 
			{
				sqle1.printStackTrace();
			}
		}
	}
}
