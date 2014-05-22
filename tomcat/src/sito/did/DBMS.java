package did;
/**        DBMS.java        */
import java.sql.*;
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

    
    
    
	//definizione delle Query 
	
    private String csdip =  "SELECT tabAll.* ,tabCS.NumeroInsAttivi ,tabCS.creditiTot FROM (  ( "
                           +"         SELECT distinct cs.* "
                           +"         FROM corsostudi cs JOIN corsoindipart cid ON ( cs.id = cid.id_corsostudi) "
                           +"                             JOIN inserogato ins ON ins.id_corsostudi = cs.id "
                           +"         WHERE "
                           +"                 id_tipocs IN ( 5,14,20,25,23) "
                           +"          AND cid.id_dipart = ? "
                           +"          AND modulo = 0 AND annoaccademico = '2013/2014' "
                           +"     )  "
                           +"     UNION "
                           +"     ( "
                           +"         SELECT distinct cs.* "
                           +"         FROM corsostudi cs JOIN corsoindipart cid ON ( cs.id = cid.id_corsostudi) "
                           +"                            LEFT JOIN CicloCS ccs ON ( cs.id = ccs.id_cicloincs_corsostudi ) "
                           +"         WHERE "
                           +"           anno='2014' "
                           +"           AND id_tipocs = 14 "
                           +"           AND cid.id_dipart = ? "
                           +"     ) "
                           +" ) AS tabAll LEFT JOIN ( "
                           +"         SELECT distinct cs.ID, COUNT( ins.id ) AS NumeroInsAttivi, SUM ( ins.crediti ) AS creditiTot "
                           +"         FROM corsostudi cs JOIN corsoindipart cid ON ( cs.id = cid.id_corsostudi) "
                           +"                            JOIN inserogato ins ON ins.id_corsostudi = cs.id       "
                           +"         WHERE                                                                     "
                           +"           id_tipocs IN ( 5,14,20,25,23)                                           "
                           +"           AND modulo = 0 AND annoaccademico = '2013/2014' AND cid.id_dipart = ?   "
                           +"         GROUP BY cs.id                                                            "
                           +" ) as tabCS ON ( tabAll.id = tabCS.id );                                           " ;

	private String nomeDip = " SELECT nome FROM dipart where id=?;";
    private String nomiDip = " SELECT DISTINCT id,nome FROM dipart WHERE datadisattivazione IS NULL ORDER BY nome; ";
    
    private String nomeDirettore = "SELECT p.nome, p.cognome FROM persona p JOIN dipart ON p.id = dipart.id_direttore WHERE dipart.id = 30;";
    
	private String iicaa = "SELECT DISTINCT ie.id_insegn as id , ins.nomeins, ie.crediti, ins.codiceins, ie.hamoduli, ie.nomemodulo, ie.modulo  FROM inserogato ie JOIN insegn ins ON ins.id = ie.id_insegn " +
			"													 WHERE ie.id_corsostudi=? AND ie.annoaccademico='2013/2014' AND ie.modulo >= 0 ORDER BY ie.id_insegn, ins.nomeins, ie.modulo ";
	
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
	
	//Metodo per il recupero delle principali informazioni di tutti i corsi di studi
	public Vector<CorsoStudiBean> getCorsiDip( int idDip ) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<CorsoStudiBean> result = new Vector<CorsoStudiBean>();
		
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(csdip);
			pstmt.clearParameters();
			pstmt.setInt(1, idDip); 
			pstmt.setInt(2, idDip);
			pstmt.setInt(3, idDip);
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( makeCSBean(rs) );
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	
	//Metodi per la creazione di un bean a partire dal record attuale del ResultSet dato come parametro
	private CorsoStudiBean makeCSBean(ResultSet rs) throws SQLException {
		CorsoStudiBean bean = new CorsoStudiBean();
		
		bean.setId(rs.getInt("id"));
		bean.setNomeCorsoStudi(rs.getString("Nome"));
		bean.setCodice(rs.getString("Codice"));
		bean.setAbbreviazione(rs.getString("Abbreviazione"));
		bean.setDurataanni(rs.getInt("Durataanni"));
		bean.setSede(rs.getString("Sede"));
		bean.setInformativa(rs.getString("Informativa"));
		bean.setId_tipocs(rs.getInt("id_tipocs"));

        int value = rs.getInt("NumeroInsAttivi");
        if ( ! rs.wasNull() ) 
		    bean.setNumeroInsAttivi(value);

        value = rs.getInt("creditiTot");
        if ( ! rs.wasNull() ) 
		    bean.setCreditiTot(value);
		
		return bean;
    }
	public String getNomeDip( int id ){
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
			
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(nomeDip);
			pstmt.clearParameters();
			pstmt.setInt(1, id); 
			
			// Eseguo l'interrogazione desiderata
			rs = pstmt.executeQuery();
			if ( rs.next() )
				result = rs.getString("nome");
			else
				result = "Nessun dipartimento trovato";
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	public InfoDipBean getDirettore(){
		// Dichiarazione delle variabili
		Connection con = null;
		InfoDipBean bean = new InfoDipBean();
		Statement stmt = null;
		ResultSet rs = null;
		String result = null;
			
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			// Eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(nomeDirettore);
			if ( rs.next() )
				{
					bean.setNome(rs.getString("nome")) ;
					bean.setCognome(rs.getString("cognome")) ;
				}
			else
				result = "Nessun dipartimento trovato";
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return bean;
	}

	//Metodo per il recupero delle principali informazioni di tutti i corsi di studi
	public Vector<Integer> getNomiDipart( ) {
		// Dichiarazione delle variabili
		Connection con = null;
Statement stmt = null;
		ResultSet rs = null;
		Vector<Integer> result = new Vector<Integer>();
		
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
                stmt = con.createStatement();
			// Eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(nomiDip);
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add( rs.getInt("id") );
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
    }
	
public Vector<InsErogatoBean> getInsegnamentiErogati(int idCorso){
		
		// Dichiarazione delle variabili
		Connection con = null;
		ResultSet rs = null;
		Vector<InsErogatoBean> result = new Vector<InsErogatoBean>();
		PreparedStatement pstmt = null;
		try {
			
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			
			pstmt = con.prepareStatement(iicaa); 
			pstmt.clearParameters();
			pstmt.setInt(1, idCorso); 
			rs=pstmt.executeQuery(); 	
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add(makeInsErogatoBean(rs)); 
			
		} catch(SQLException sqle) {                /* Catturo le eventuali eccezioni! */
			sqle.printStackTrace();
		} finally {                                 /* Alla fine chiudo la connessione. */
			try {
				con.close();
			} catch(SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	private InsErogatoBean makeInsErogatoBean(ResultSet rs) throws SQLException{
		InsErogatoBean bean = new InsErogatoBean();
		
		bean.setNomeins(rs.getString("nomeins"));
		bean.setCodiceins(rs.getString("codiceins"));
		bean.setCrediti(rs.getString("crediti"));
		bean.setNomemodulo(rs.getString("nomemodulo"));
		bean.setHamoduli(rs.getString("hamoduli"));
		bean.setModulo(rs.getString("modulo"));
		bean.setIdInsegn(rs.getInt("id"));
		
		return bean;
	}
}
