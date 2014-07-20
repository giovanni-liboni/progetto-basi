package database;
/**        DBMS.java        */
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.*;
import org.hibernate.engine.jdbc.BinaryStream;

import util.HibernateUtil;
import bean.Biglietto;
import bean.BigliettoId;
import bean.Passeggero;
import bean.Prenotazione;
import bean.Volo;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {
	/**
	 * @param date Data di partenza del volo
	 * @param partenza Aeroporto di partenza del volo
	 * @param arrivo Aeroporto di arrivo del volo
	 * @return Lista dei voli che soddisfano i requisiti della ricerca
	 */
	public ArrayList<Volo> getRicercaVolo( Date date, String partenza, String arrivo ) 
	{
		ArrayList<Volo> result = new ArrayList<Volo>();

		String ricercaVoli = " SELECT volo.* FROM tratta JOIN volo on ( tratta.partenza = volo.partenza AND tratta.arrivo = volo.arrivo ) WHERE datapartenza=(:datapartenza) AND tratta.partenza ilike (:partenza) AND tratta.arrivo ilike (:arrivo) ORDER BY orapartenza;";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 

		Query query = session.createSQLQuery(ricercaVoli).addEntity(Volo.class);
		query.setDate("datapartenza", date);
		query.setString("partenza", partenza);
		query.setString("arrivo", arrivo);

		List<Volo> l = query.list();

		Iterator<Volo> itr = l.iterator();

		while( itr.hasNext()) {
			Volo dip = itr.next();
			
			dip.getTratta();
			dip.getTratta().getId();
			dip.getTratta().getDurata();
			dip.getTratta().getDistanza();
			dip.getTratta().getId().getPartenza();
			dip.getTratta().getId().getArrivo();

			result.add(dip);
		}

		tx.commit();
		session.close();
		return result;
	}
	/**
	 * Ricerca un singolo volo a partire dal suo codice
	 * @param codicevolo Codice del volo da trovare
	 * @return Bean del volo, altrimenti ritorna null se non esiste
	 */
	public Volo getVolo( String codicevolo ) 
	{			
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction(); 
		Volo res = ( Volo ) session.get( Volo.class, codicevolo );
		res.getTratta().getDurata();
		res.getTratta().getId().getArrivo();

		tx.commit();
		session.close();
		return res;
	}
	/**
	 * Ricerca il passeggero a partire dal suo documento 
	 * @param documento Il documento del passeggero da ricercare
	 * @return Bean del passeggero, null se non esiste all'interno del DB
	 */
	public Passeggero getPasseggero( String documento ) 
	{
		Passeggero result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( Passeggero ) session.get( Passeggero.class, documento );
		result.getCognome();

		tx.commit();
		session.close();

		return result;
	}
	/**
	 * Ricerca una specifica prenotazione a partire dal suo id
	 * @param id Id da ricercare
	 * @return Bean della prenotazione, se non esiste ritorna null
	 */
	public Prenotazione getPrenotazione( String id ) 
	{
		Prenotazione result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( Prenotazione ) session.get( Prenotazione.class, Integer.parseInt(id) );
		result.getPasseggero().getDocumento();
		result.getVolo().getTratta().getDurata();
		result.getVolo().getTratta().getId().getArrivo();
		result.getVolo().getOrapartenza();

		tx.commit();
		session.close();

		return result;
	}
	/**
	 * Restituisce il passeggero a partire dal suo username
	 * @param username Username da ricercare
	 * @return Bean del passeggero trovato, se non esiste ritorna null
	 */
	public Passeggero getPasseggeroFromLogin( String username ) 
	{
		String datiPasseggeroLogin = " SELECT * " +
				" FROM passeggero " +
				" WHERE passeggero.login=(:login)";
		
		Passeggero result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 

		Query query = session.createSQLQuery(datiPasseggeroLogin).addEntity(Passeggero.class);
		query.setString("login", username);

		
		result = (Passeggero) query.uniqueResult();
		tx.commit();
		session.close();

		return result;
	}
	/**
	 * Ritorna tutte le città di partenza all'interno del database
	 * @return Liste delle città di partenza
	 */
	public ArrayList<String> getPartenze() 
	{
		String q = " SELECT DISTINCT partenza FROM tratta;";

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		
		ArrayList<String> result = ( ArrayList<String> ) session.createSQLQuery(q).list();
		tx.commit();
		session.close();
		
		return result;
	}
	/**
	 * Ritorna tutti gli aeroporti di arrivo presenti nella base di dati
	 * @return Lista degli aeroporti di arrivo
	 */
	public ArrayList<String> getArrivi() 
	{
		String q = " SELECT DISTINCT arrivo FROM tratta;";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		ArrayList<String> result = ( ArrayList<String> ) session.createSQLQuery(q).list();
		tx.commit();
		session.close();
		return result;
	}
	/**
	 * Controlla se esiste un utente con l'username specificato
	 * @param username Username del passeggero da ricercare
	 * @param password Password
	 * @return True se esiste una corrispondenza, false altrimenti.
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean isLogin( String username, String password ) throws NoSuchAlgorithmException 
	{
		boolean login = false;	
		Passeggero res = getPasseggeroFromLogin(username);
		if( res!=null && res.getPassword().compareTo(sha1( password ) ) == 0 )
			login=true;
		return login;
	}
	/**
	 * Inserisce un nuovo passeggero nel DB
	 * @param nome Nome 
	 * @param cognome Cognome
	 * @param nazione Nazione
	 * @param documento Numero del documento
	 * @param username Username
	 * @param password Password
	 * @param tessera Boolean per sapere se ha la tessera o meno
	 * @return True se l'operazione è andata a buon fine, false altrimenti.
	 */
	public boolean newPasseggero(String nome, String cognome, String nazione, String documento, String username, String password, boolean tessera)
	{
		boolean status = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Passeggero p = new Passeggero();
		p.setNome(nome);
		p.setCognome(cognome);
		p.setNazionalita(nazione);
		p.setLogin(username);
		try {
			p.setPassword( sha1(password) );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		p.setDocumento(documento);
		p.setTessera(tessera);

		session.save(p);
		session.getTransaction().commit();
		return status;
	}
	/**
	 * Aggiunge un nuovo biglietto al DB
	 * @param passeggero Bean del passeggero
	 * @param volo Bean del volo
	 * @param prenotazione Bean della prenotazione 
	 * @param prezzo prezzo Costo del biglietto
	 * @return True se l'operazione è andata a buon fine, false altrimenti.
	 */
	public boolean newBiglietto( Passeggero passeggero, Volo volo, Prenotazione prenotazione, BigDecimal prezzo )
	{
		boolean status = true;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Biglietto b = new Biglietto();
		b.setPasseggero(passeggero);
		b.setVolo(volo);
		b.setPrenotazione(prenotazione);
		b.setPrezzo(prezzo);
		b.setId(new BigliettoId(volo.getCodicevolo(), passeggero.getDocumento()));

		session.save(b);
		session.getTransaction().commit();
		session.close();
		return status;
	}
	/**
	 * Aggiunge un nuovo biglietto al DB
	 * @param prenotazione Bean della prenotazione
	 * @param prezzo Costo del biglietto
	 * @return True se l'operazione è andata a buon fine, false altrimenti.
	 */
	public boolean newBiglietto( Prenotazione prenotazione, BigDecimal prezzo )
	{
		boolean status = true;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Biglietto b = new Biglietto();
		b.setPasseggero(prenotazione.getPasseggero());
		b.setVolo(prenotazione.getVolo());
		b.setPrenotazione(prenotazione);
		b.setPrezzo(prezzo);
		b.setId(new BigliettoId(prenotazione.getVolo().getCodicevolo(), prenotazione.getPasseggero().getDocumento()));

		session.save(b);
		session.getTransaction().commit();
		session.close();
		return status;
	}
	/**
	 * Aggiunge una nuova prenotazione al DB
	 * @param volo Bean del volo
	 * @param passeggero Bean del passeggero
	 * @return Ritorna true se è stata salvata correttamente
	 */
	public boolean newPrenotazione(Volo volo , Passeggero passeggero )
	{	
		/*
		 * Controllo che non esista una prenotazione per lo stesso volo e lo stesso passeggero
		 * Se ritorna true allora esiste una prenotazione per lo stesso volo e lo stesso passeggero
		 */
		if ( checkPrenotazione( passeggero,volo ) )
		{
			return false;
		}
		else{
			Session session = null;
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Prenotazione p = new Prenotazione();
			p.setPasseggero(passeggero);
			p.setVolo(volo);
			session.save(p);
			session.getTransaction().commit();
			session.close();
			return true;
		}
	}
	/**
	 * Prenotazioni di un passeggero
	 * @param documento Documento del passeggero
	 * @return Lista delle prenotazioni associate al passeggero
	 */
	public ArrayList<Prenotazione> getPrenotazioni( String documento ) 
	{
	    String prenotazioni = "select * from prenotazione where documento=(:documento) and not exists ( select * from biglietto where prenotazione.id=biglietto.id_prenotazione)";

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(prenotazioni).addEntity(Prenotazione.class);
        q.setString("documento", documento);
        
        List l = q.list();
                
        ArrayList<Prenotazione> result = new ArrayList<Prenotazione>();
        
		Iterator<Prenotazione> itr = l.iterator();

		while( itr.hasNext()) {
			Prenotazione pb = itr.next();
			pb.getVolo().getTratta().getId().getPartenza();
			result.add(pb);
		}
        
        tx.commit();
        session.close();
        
		return result;
	}
	/**
	 * Ricerca i biglietti associati ad un passeggero
	 * @param documento Identificatico del passaggero
	 * @return Lista dei biglietti associati al passeggero
	 */
	public ArrayList<Biglietto> getBiglietti( String documento ) 
	{
	    String biglietti = "SELECT * FROM biglietto b WHERE b.documento=(:documento)";

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(biglietti).addEntity(Biglietto.class);
        q.setString("documento", documento);        
        List<Biglietto> l = q.list();
        
        ArrayList<Biglietto> result = new ArrayList<Biglietto>();
        
		Iterator<Biglietto> itr = l.iterator();

		while( itr.hasNext()) {
			Biglietto pb = itr.next();
			pb.getVolo().getDatapartenza();
			pb.getVolo().getTratta().getId().getPartenza();
			result.add(pb);
		}
		
        tx.commit();
        session.close();
		return result;
	}
	/**
	 * Metodo per ricercare le città di arrivo a partire da una città di partenza
	 * @param partenza Città di partenza
	 * @return Lista delle città di arrivo
	 */
	public ArrayList<String> getArrivi( String partenza ) 
	{
		String biglietti = "SELECT DISTINCT arrivo FROM tratta t WHERE t.partenza=(:partenza) ORDER BY arrivo";

		ArrayList<String> result = new ArrayList<String>();

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		Query q = session.createSQLQuery(biglietti);
		q.setString("partenza", partenza);        
		result  = ( ArrayList<String> ) q.list();

		tx.commit();
		session.close();
		return result;
	}
	/**
	 * Metodo per ricercare le città di partenza a partire da una città di arrivo
	 * @param arrivo Città di arrivo
	 * @return Lista delle città di partenza
	 */
	public ArrayList<String> getPartenze ( String arrivo ) 
	{
		String biglietti = "SELECT DISTINCT partenza FROM tratta t WHERE t.arrivo=(:arrivo) ORDER BY partenza";
		ArrayList<String> result = new ArrayList<String>();

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		Query q = session.createSQLQuery(biglietti);
		q.setString("arrivo", arrivo);    
		
		result  = ( ArrayList<String> ) q.list();

		tx.commit();
		session.close();
		return result;
	}
	
	public boolean checkUsername( String username ) 
	{
	    String checkusername = "select * from passeggero where login=(:username)";
	    boolean result = true;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(checkusername).addEntity(Passeggero.class);
        q.setString("username", username);
        
		Iterator<Passeggero> itr = q.list().iterator();

		if ( itr.hasNext() )
			result = false;
        
        tx.commit();
        session.close();
        
		return result;
	}
	public boolean checkDocumento( String documento ) 
	{
	    String checkusername = "select * from passeggero where documento=(:documento)";
	    boolean result = true;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(checkusername).addEntity(Passeggero.class);
        q.setString("documento", documento);
        
		Iterator<Passeggero> itr = q.list().iterator();

		if ( itr.hasNext() )
			result = false;
        
        tx.commit();
        session.close();
        
		return result;
	}
	public boolean checkPrenotazione( Passeggero pass, Volo volo ) 
	{
	    String checkusername = "select * from prenotazione where documento=(:documento) AND codicevolo=(:codicevolo)";
	    boolean result = false;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(checkusername).addEntity(Prenotazione.class);
        q.setString("documento", pass.getDocumento() );
        q.setString("codicevolo", volo.getCodicevolo());
        
	
		if ( q.list().size() > 0 )
			result = true;
        
        tx.commit();
        session.close();
        
		return result;
	}
	public void addPictureToPasseggero ( Passeggero passeggero, File f)
	{
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		try {
			passeggero.setPicture( Files.readAllBytes(f.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		session.update(passeggero);
		session.getTransaction().commit();
	}
	
    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}