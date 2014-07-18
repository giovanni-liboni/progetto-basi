package pass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		int MAX_PERSONE = 10;
		
		Nome nome_ = new Nome();
		Cognome cognome_ = new Cognome();
		Nazione nazione_ = new Nazione();
		RandomString rs = new RandomString(10);
		WriteToFile wtf = new WriteToFile();
		
		
		for ( int pers = 0; pers < MAX_PERSONE; pers++)
		{
			String nome = nome_.getNome().toUpperCase().replaceAll("'", "''");
			String cognome = cognome_.getCognome().toUpperCase().replaceAll("'", "''");
			String nazione = nazione_.getNazione().toUpperCase();
			String documento= rs.nextString();
						
			wtf.writeToFile("INSERT INTO passeggero( nome, cognome,nazionalita,documento,login,password) VALUES ('"+nome+"','"+cognome+"','"+nazione+"','"+documento+"','"+nome+cognome+"','"+sha1(nome)+"');" );
		}

		wtf.close();
	}
    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
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
