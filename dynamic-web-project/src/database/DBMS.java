package database;
/**        DBMS.java        */
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.*;

import org.hibernate.*;

import util.HibernateUtil;
import bean.BigliettoBean;
import bean.BigliettoId;
import bean.PasseggeroBean;
import bean.PrenotazioneBean;
import bean.VoloBean;

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
	public ArrayList<VoloBean> getRicercaVolo( Date date, String partenza, String arrivo ) 
	{
		ArrayList<VoloBean> result = new ArrayList<VoloBean>();

		String ricercaVoli = " SELECT volo.* FROM tratta JOIN volo on ( tratta.partenza = volo.partenza AND tratta.arrivo = volo.arrivo ) WHERE datapartenza=(:datapartenza) AND tratta.partenza ilike (:partenza) AND tratta.arrivo ilike (:arrivo) ORDER BY orapartenza;";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 

		Query query = session.createSQLQuery(ricercaVoli).addEntity(VoloBean.class);
		query.setDate("datapartenza", date);
		query.setString("partenza", partenza);
		query.setString("arrivo", arrivo);

		List<VoloBean> l = query.list();

		Iterator<VoloBean> itr = l.iterator();

		while( itr.hasNext()) {
			VoloBean dip = itr.next();
			
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
	public VoloBean getVolo( String codicevolo ) 
	{			
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction tx = session.beginTransaction(); 
		VoloBean res = ( VoloBean ) session.get( VoloBean.class, codicevolo );
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
	public PasseggeroBean getPasseggero( String documento ) 
	{
		PasseggeroBean result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( PasseggeroBean ) session.get( PasseggeroBean.class, documento );

		tx.commit();
		session.close();

		return result;
	}
	/**
	 * Ricerca una specifica prenotazione a partire dal suo id
	 * @param id Id da ricercare
	 * @return Bean della prenotazione, se non esiste ritorna null
	 */
	public PrenotazioneBean getPrenotazione( String id ) 
	{
		PrenotazioneBean result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( PrenotazioneBean ) session.get( PrenotazioneBean.class, Integer.parseInt(id) );
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
	public PasseggeroBean getPasseggeroFromLogin( String username ) 
	{
		String datiPasseggeroLogin = " SELECT * " +
				" FROM passeggero " +
				" WHERE passeggero.login=(:login)";
		
		PasseggeroBean result = null;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 

		Query query = session.createSQLQuery(datiPasseggeroLogin).addEntity(PasseggeroBean.class);
		query.setString("login", username);

		
		result = (PasseggeroBean) query.uniqueResult();
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
		PasseggeroBean res = getPasseggeroFromLogin(username);
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
	 * @return Passeggero Ritorna il passeggero creato
	 */
	public PasseggeroBean newPasseggero(String nome, String cognome, String nazione, String documento, String username, String password, boolean tessera)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PasseggeroBean p = new PasseggeroBean();
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
		return p;
	}
	/**
	 * Aggiunge un nuovo biglietto al DB
	 * @param passeggero Bean del passeggero
	 * @param volo Bean del volo
	 * @param prenotazione Bean della prenotazione 
	 * @param prezzo prezzo Costo del biglietto
	 * @return True se l'operazione è andata a buon fine, false altrimenti.
	 */
	public boolean newBiglietto( PasseggeroBean passeggero, VoloBean volo, PrenotazioneBean prenotazione, BigDecimal prezzo )
	{
		boolean status = true;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		BigliettoBean b = new BigliettoBean();
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
	public boolean newBiglietto( PrenotazioneBean prenotazione, BigDecimal prezzo )
	{
		boolean status = true;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		BigliettoBean b = new BigliettoBean();
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
	public boolean newPrenotazione(VoloBean volo , PasseggeroBean passeggero )
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
			PrenotazioneBean p = new PrenotazioneBean();
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
	public ArrayList<PrenotazioneBean> getPrenotazioni( String documento ) 
	{
	    String prenotazioni = "SELECT * FROM prenotazione WHERE documento=(:documento) AND NOT EXISTS ( SELECT * FROM biglietto WHERE prenotazione.id=biglietto.id_prenotazione)";

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(prenotazioni).addEntity(PrenotazioneBean.class);
        q.setString("documento", documento);
        
        List l = q.list();
                
        ArrayList<PrenotazioneBean> result = new ArrayList<PrenotazioneBean>();
        
		Iterator<PrenotazioneBean> itr = l.iterator();

		while( itr.hasNext()) {
			PrenotazioneBean pb = itr.next();
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
	public ArrayList<BigliettoBean> getBiglietti( String documento ) 
	{
	    String biglietti = "SELECT * FROM biglietto b WHERE b.documento=(:documento)";

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(biglietti).addEntity(BigliettoBean.class);
        q.setString("documento", documento);        
        List<BigliettoBean> l = q.list();
        
        ArrayList<BigliettoBean> result = new ArrayList<BigliettoBean>();
        
		Iterator<BigliettoBean> itr = l.iterator();

		while( itr.hasNext()) {
			BigliettoBean pb = itr.next();
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
        Query q = session.createSQLQuery(checkusername).addEntity(PasseggeroBean.class);
        q.setString("username", username);
        
		Iterator<PasseggeroBean> itr = q.list().iterator();

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
        Query q = session.createSQLQuery(checkusername).addEntity(PasseggeroBean.class);
        q.setString("documento", documento);
        
		Iterator<PasseggeroBean> itr = q.list().iterator();

		if ( itr.hasNext() )
			result = false;
        
        tx.commit();
        session.close();
        
		return result;
	}
	public boolean checkPrenotazione( PasseggeroBean pass, VoloBean volo ) 
	{
	    String checkusername = "select * from prenotazione where documento=(:documento) AND codicevolo=(:codicevolo)";
	    boolean result = false;

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(checkusername).addEntity(PrenotazioneBean.class);
        q.setString("documento", pass.getDocumento() );
        q.setString("codicevolo", volo.getCodicevolo());
        
	
		if ( q.list().size() > 0 )
			result = true;
        
        tx.commit();
        session.close();
        
		return result;
	}
	public void addPictureToPasseggero ( PasseggeroBean passeggero, byte[] imageInByte )
	{
		
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		passeggero.setPicture( imageInByte );

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