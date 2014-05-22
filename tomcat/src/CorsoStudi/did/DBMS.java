/**        DBMS.java        */
package did;

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
	//Recupera le principali info su tutti i corsi di studi
	private String css = "SELECT id, Codice, Nome FROM corsostudi ORDER BY Nome";
	//Recupera tutte le informazioni di un particolare corso di studi
	private String cs = "SELECT id,nome,codice,abbreviazione,durataanni,sede,informativa FROM corsostudi WHERE id=?";
	
	//Recupera il/i dipartimenti di un particolare corso di studi
	private String csd = "SELECT DISTINCT d.nome FROM dipart d INNER JOIN corsoindipart csd ON (d.id=csd.id_dipart) WHERE csd.id_corsostudi=?";
	
	private String iicaa = "SELECT DISTINCT * FROM inserogato ie JOIN insegn ins on (ins.id = ie.id_insegn) " +
			"													 LEFT JOIN discriminante discr ON (discr.id = ie.id_discriminante)" +
			"													 WHERE ie.id_corsostudi=? AND ie.annoaccademico=? ORDER BY nomeins";
    private String aacs = "SELECT DISTINCT annoaccademico FROM inserogato ie WHERE id_corsostudi=? ORDER BY annoaccademico DESC";
	//Query per il recupero delle facoltà di appartenenza di un dato corso di studio
	private String csf = "SELECT DISTINCT f.nome FROM (facolta f INNER JOIN corsoinfacolta csf ON f.id=csf.id_facolta) WHERE csf.id_corsostudi=?";
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

	//Metodi per la creazione di un bean a partire dal record attuale del ResultSet dato come parametro
	private CorsoStudiBean makeCorsoStudiBean(ResultSet rs) throws SQLException {
		CorsoStudiBean bean = new CorsoStudiBean();
		bean.setId(rs.getInt("id"));
		bean.setNomeCorsoStudi(rs.getString("Nome"));
		bean.setCodice(rs.getString("Codice"));
		bean.setAbbreviazione(rs.getString("Abbreviazione"));
		bean.setDurataanni(rs.getInt("Durataanni"));
		bean.setSede(rs.getString("Sede"));
		bean.setInformativa(rs.getString("Informativa"));
		return bean;
    }

	private CorsoStudiBean makeCSBean(ResultSet rs) throws SQLException {
		CorsoStudiBean bean = new CorsoStudiBean();
		bean.setId(rs.getInt("id"));
		bean.setNomeCorsoStudi(rs.getString("Nome"));
		bean.setCodice(rs.getString("Codice"));
		return bean;
    }

	//Metodo per il recupero delle informazioni del corso di studi con l'id specificato
	public Vector getCorsoStudi(int id) {
		// Dichiarazione delle variabili necessarie
		Connection con = null;		
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();	
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			pstmt = con.prepareStatement(cs); 
			pstmt.clearParameters();
			//Imposto i parametri della query
			pstmt.setInt(1, id); 
			//Eseguo la query
			rs=pstmt.executeQuery(); 
			// Memorizzo il risultato dell'interrogazione in Vector di Bean
			while(rs.next())
				result.add(makeCorsoStudiBean(rs));
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
	
	//Metodo per il recupero delle principali informazioni di tutti i corsi di studi
	public Vector getCorsiStudi() {
		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			stmt = con.createStatement();
			// Eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(css);
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add(makeCSBean(rs));
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
	
	//Metodo per il recupero del/i dipartimenti di appartenenza del corso di studi con l'id specificato
	public Vector getFacoltaCorso(int id) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(csf); 
			pstmt.clearParameters();
			pstmt.setInt(1, id); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			while(rs.next())
				result.add(rs.getString("Nome"));
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
//Metodo per il recupero della/e facoltà di appartenenza del corso di studi con l'id specificato
	public Vector getDipartCorso(int id) {
		// Dichiarazione delle variabili
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			pstmt = con.prepareStatement(csd); 
			pstmt.clearParameters();
			pstmt.setInt(1, id); 
			rs=pstmt.executeQuery(); 		
			
			// Memorizzo il risultato dell'interrogazione nel Bean
			while(rs.next())
				result.add(rs.getString("Nome"));
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
	public Vector getInsegnamentiErogati(int idCorso, String aa){
		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		PreparedStatement pstmt = null;
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			
			pstmt = con.prepareStatement(iicaa); 
			pstmt.clearParameters();
			pstmt.setInt(1, idCorso); 
			pstmt.setString(2, aa); 
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
	public Vector<String> getAAErogazioni(int idCorso){
		// Dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Vector result = new Vector();
		PreparedStatement pstmt = null;
		try {
			// Tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// Connessione riuscita, ottengo l'oggetto per l'esecuzione dell'interrogazione.
			
			pstmt = con.prepareStatement(aacs); 
			pstmt.clearParameters();
			pstmt.setInt(1, idCorso); 
			rs=pstmt.executeQuery(); 	
			
			// Memorizzo il risultato dell'interrogazione nel Vector
			while(rs.next())
				result.add(rs.getString("annoaccademico")); 
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
		bean.setMutuato(rs.getString("mutuato"));
		bean.setAnnoaccademico(rs.getString("annoaccademico"));
		bean.setCrediti(rs.getString("crediti"));
		bean.setNomemodulo(rs.getString("nomemodulo"));
		bean.setDiscriminantemodulo(rs.getString("discriminantemodulo"));
		bean.setNomeunita(rs.getString("nomeunita"));
		bean.setHamoduli(rs.getString("hamoduli"));
		bean.setHaunita(rs.getString("haunita"));
		bean.setDescrizione(rs.getString("descrizione"));
		
		return bean;
	}
	
}
