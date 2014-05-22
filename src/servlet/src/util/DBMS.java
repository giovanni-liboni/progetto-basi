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
	
    /** URL per la connessione alla base di dati e' formato dai seguenti componenti:
     * <protocollo>://<host del server>/<nome base di dati>.
     */
    private String url = "jdbc:postgresql://dbserver.sci.univr.it/did2014";
    
	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
    private String driver = "org.postgresql.Driver";
    
    /** DEFINIZIONE DELLE QUERY */
    String ricercaVoli = " SELECT datapartenza, codicevolo, volo.partenza, volo.arrivo, durata, orapartenza, tipoaereo " +
    					 " FROM tratta JOIN volo on ( tratta.partenza = volo.partenza AND tratta.arrivo = volo.arrivo )" +
    					 " WHERE datapartenza=? AND tratta.partenza=? AND tratta.arrivo=? ORDER BY orapartenza;";
    String datiVolo = " SELECT v.*, tr.durata, tr.distanza" + 
    				  " FROM tratta tr JOIN volo v ON ( v.partenza = tr.partenza AND v.arrivo = tr.arrivo) " +
    				  " WHERE v.codicevolo=?";
    String datiPasseggeroLogin = " SELECT nome,cognome,nazionalita,documento " +
    				  			 " FROM passeggero " +
    				  			 " WHERE passeggero.login=?";
    
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
	public Vector<VoloBean> getRicercaVolo( Date datapartenza, String partenza, String arrivo ) 
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
			pstmt.setDate(1, datapartenza);
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
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public Vector<VoloBean> getVolo( String codicevolo ) 
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
			pstmt = con.prepareStatement(datiVolo);
			pstmt.clearParameters();
			pstmt.setString(1, codicevolo);
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( makeVoloBean(rs) );
			
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
}
