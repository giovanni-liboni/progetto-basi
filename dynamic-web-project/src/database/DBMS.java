package database;
/**        DBMS.java        */
import java.sql.Date;
import java.util.*;
import org.hibernate.*;
import bean.BigliettoBean;
import bean.BigliettoId;
import bean.HibernateUtil;
import bean.PasseggeroBean;
import bean.PrenotazioneBean;
import bean.VoloBean;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
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
			dip.getTratta().getDurata();

			result.add(dip);
		}

		tx.commit();
		session.close();
		return result;
	}
	//Metodo per ricercare un singolo volo
	public VoloBean getVolo( String codicevolo ) 
	{	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		VoloBean res = ( VoloBean ) session.get( VoloBean.class, codicevolo );
		res.getTratta();

		tx.commit();
		session.close();
		return res;
	}
	//Metodo per ricercare un singolo volo
	public PasseggeroBean getPasseggero( String documento ) 
	{
		PasseggeroBean result = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( PasseggeroBean ) session.get( PasseggeroBean.class, documento );

		tx.commit();
		session.close();

		return result;
	}
	public PrenotazioneBean getPrenotazione( String id ) 
	{
		PrenotazioneBean result = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		result = ( PrenotazioneBean ) session.get( PrenotazioneBean.class, Integer.parseInt(id) );
		result.getPasseggero();
		result.getPasseggero().getDocumento();
		result.getVolo();

		tx.commit();
		session.close();

		return result;
	}
	//Metodo per ricercare un singolo volo
	public PasseggeroBean getPasseggeroFromLogin( String username ) 
	{
		String datiPasseggeroLogin = " SELECT * " +
				" FROM passeggero " +
				" WHERE passeggero.login=(:login)";
		PasseggeroBean result = null;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 

		Query query = session.createSQLQuery(datiPasseggeroLogin).addEntity(PasseggeroBean.class);
		query.setString("login", username);

		result = (PasseggeroBean) query.list().get(0);
		tx.commit();
		session.close();

		return result;
	}

	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public ArrayList<String> getPartenze() 
	{
		String q = " SELECT DISTINCT partenza FROM tratta;";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		ArrayList<String> result = ( ArrayList<String> ) session.createSQLQuery(q).list();
		tx.commit();
		session.close();
		return result;
	}
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
	public boolean isLogin( String username, String password ) 
	{
		boolean login = false;	
		PasseggeroBean res = getPasseggeroFromLogin(username);
		if( res!=null && res.getPassword().compareTo(password) == 0 )
			login=true;
		return login;
	}
	public boolean newPasseggero(String nome, String cognome, String nazione, String documento, String username, String password, boolean tessera)
	{
		boolean status = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PasseggeroBean p = new PasseggeroBean();
		p.setNome(nome);
		p.setCognome(cognome);
		p.setNazionalita(nazione);
		p.setLogin(username);
		p.setPassword(password);
		p.setDocumento(documento);
		p.setTessera(tessera);

		session.save(p);
		session.getTransaction().commit();
		return status;
	}
	public boolean newBiglietto( PasseggeroBean passeggero, VoloBean volo, PrenotazioneBean prenotazione, float prezzo )
	{
		boolean status = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
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
	public boolean newPrenotazione(VoloBean volo , PasseggeroBean passeggero )
	{
		boolean status = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PrenotazioneBean p = new PrenotazioneBean();
		p.setPasseggero(passeggero);
		p.setVolo(volo);

		session.save(p);
		session.getTransaction().commit();
		session.close();
		return status;
	}
	//Metodo per ricercare un singolo volo
	public ArrayList<PrenotazioneBean> getPrenotazioni( String documento ) 
	{
	    String prenotazioni = "SELECT * FROM prenotazione p WHERE p.documento=(:documento)";

		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(prenotazioni).addEntity(PrenotazioneBean.class);
        q.setString("documento", documento);
                
        ArrayList<PrenotazioneBean> result = (ArrayList<PrenotazioneBean>) q.list();
        
        tx.commit();
        session.close();
        
		return result;
	}
	//Metodo per ricercare un singolo volo
	public ArrayList<BigliettoBean> getBiglietti( String documento ) 
	{
	    String biglietti = "SELECT * FROM biglietto b WHERE b.documento=(:documento)";

		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Query q = session.createSQLQuery(biglietti).addEntity(BigliettoBean.class);
        q.setString("documento", documento);
        ArrayList<BigliettoBean> result = (ArrayList<BigliettoBean>) q.list();
        
        tx.commit();
        session.close();
		return result;
	}
}