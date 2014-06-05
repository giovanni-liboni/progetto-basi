package util;
/**        DBMS.java        */
import java.sql.Date;
import java.util.*;

import org.hibernate.*;
import org.hibernate.criterion.*;

/**
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 */
public class DBMS {
	    
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
    
    String controllaPassword = "SELECT login FROM passeggero WHERE login=? AND password=?;";
    String newPasseggero = " INSERT INTO Passeggero (nome,cognome,documento,nazionalita,login,password,tessera) VALUES(?,?,?,?,?,?,?)";
    String newBiglietto = " INSERT INTO Biglietto (documento, codicevolo, prezzo, dataemissione ,id_prenotazione ) VALUES ( ?,?,?, current_date,? )" ;
    String newPrenotazione = " INSERT INTO Prenotazione ( documento, codicevolo, datarichiesta,orarichiesta) VALUES (?,?, current_date, current_time )";    
   
    // ritorna le prentazioni per bigliettiPage
    
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public Vector<VoloBean> getRicercaVolo( Date date, String partenza, String arrivo ) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        
        tx.commit();
        session.close();
		Vector<VoloBean> result = null;
		return result;
    }
	//Metodo per ricercare un singolo volo
	public VoloBean getVolo( String codicevolo ) 
	{
		VoloBean result = null;
		return result;
    }
	//Metodo per ricercare un singolo volo
	public PasseggeroBean getPasseggero( String username ) 
	{
		PasseggeroBean result = null;

		return result;
    }
	//Metodo per ricercare i voli a partire dalla data di partenza, dal luogo di partenza e dal luogo di arrivo
	public Vector<String> getPartenze() 
	{
		Vector<String> result = new Vector<String>();
		return result;
    }
	public Vector<String> getArrivi() 
	{
		String arrivi = " SELECT DISTINCT arrivo FROM tratta;";
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
//        Vector<String> result = session.createSQLQuery(arrivi).addEntity(arg0)
        tx.commit();
        session.close();
		return null;
    }
	public boolean isLogin( String username, String password ) 
	{
		boolean login = false;
		return login;
    }
	public void newPasseggero(String nome, String cognome, String nazione, String documento, String username, String password, boolean tessera)
	{
	}
	public void newBiglietto( String documento, String codicevolo, float prezzo , int id_prenotazione )
	{

	}
	public boolean newPrenotazione(String codicevolo, String documento )
	{
		boolean status = true;
		return status;
	}
	//Metodo per ricercare un singolo volo
	public Vector<InfoPrenotazioneBean> getPrenotazioni( String documento ) 
	{
	    String prenotazioni = "SELECT partenza,arrivo,documento,volo.codicevolo,datarichiesta,orarichiesta,datapartenza,orapartenza FROM prenotazione p JOIN volo ON volo.codicevolo=p.codicevolo WHERE p.documento=?";

		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Vector<InfoPrenotazioneBean> result = (Vector<InfoPrenotazioneBean>) session.createSQLQuery(prenotazioni).addEntity(InfoPrenotazioneBean.class).list();
        tx.commit();
        session.close();
        
		return result;
    }
	//Metodo per ricercare un singolo volo
	public Vector<InfoBigliettoBean> getBiglietti( String documento ) 
	{
	    String biglietti = "SELECT volo.codicevolo,dataemissione,prezzo,partenza,arrivo,orapartenza,datapartenza FROM biglietto b JOIN volo ON volo.codicevolo = b.codicevolo WHERE b.documento=?";

		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction(); 
        Vector<InfoBigliettoBean> result = (Vector<InfoBigliettoBean>) session.createSQLQuery(biglietti).addEntity(InfoBigliettoBean.class).list();
        tx.commit();
        session.close();
		return result;
    }
}