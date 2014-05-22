package wget;
/*
 * A simple Java class to provide functionality similar to Wget.
 *
 * Note: Could also strip out all of the html w/ jtidy.
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JGet
{

  public static void main(String[] args)
  {

	Map<String, String> map = new HashMap<String, String>();
	
	
//	map.put("Alghero", "8.3222037,40.5579814");
//	map.put("Alicante", "-0.4809945,38.3452100");
//	map.put("Amburgo", "9.9915860,53.5538130");
//	
//	map.put("Atene", "23.7166470,37.9791800");
//	map.put("Barcellona", "2.1699187,41.3879170");
//	map.put("Bari", "16.8683784,41.1259998");
//	map.put("Berlino", "13.4113999,52.5234051");
//	map.put("Birmingham", "-1.8935920,52.4829614");
//	map.put("Bordeaux", "-0.5791800,44.8377890");
//	map.put("Bratislava", "17.1073105,48.1483765");
//	map.put("Breslavia", "17.0385376,51.1078852");
//	map.put("Brindisi", "17.9389909,40.6361753");
//	map.put("Bruxelles", "4.3517103,50.8503396");
//	map.put("Bucarest", "26.0973670,44.4377110");
//	map.put("Budapest", "19.0407578,47.4984056");
//	map.put("Cagliari", "9.1093239,39.2154086");
//	map.put("Catania", "15.0878345,37.5024825");
//	map.put("Colonia", "6.9599115,50.9406645");
//	map.put("Copenaghen", "12.5830460,55.6934030");
//	map.put("Cracovia", "19.9449799,50.0646501");
//	map.put("Crotone", "17.1271465,39.0806728");
//	map.put("Danzica", "18.6466384,54.3520252");
//	map.put("Dublino", "-6.2674937,53.3441040");
//	map.put("Edinburgo", "-3.1875359,55.9501755");
//	map.put("Girona", "2.8236999,41.9817957");
//	map.put("Glasgow", "-4.2572227,55.8656274");
//	map.put("Hanover", "9.7356861,52.3720683");
//	
//	map.put("Istanbul", "28.9769600,41.0052700");
//	map.put("Lamezia Terme", "16.3168187,38.9736410");
//	map.put("Lampedusa", "12.6098169,35.5011153");
//	map.put("Lanzarote", "-13.5899733,29.0468535");
//	map.put("Lappeenranta", "28.1869908,61.0584483");
//	map.put("Las Palmas de Gran Canaria", "-15.4300065,28.1248227");
//	map.put("Lione", "4.8356590,45.7640430");
//	map.put("Lisbona", "-9.1356321,38.7069320");
//	map.put("Liverpool", "-2.9778383,53.4107766");
//	map.put("Lourdes", "-0.0457260,43.0914630");
//	map.put("Lussemburgo", "6.1295830,49.8152730");
//	map.put("Madrid", "-3.7003454,40.4166909");
//	map.put("Malaga", "-4.4200163,36.7196484");
//	map.put("Manchester", "-2.2343765,53.4807125");
//	map.put("Marsiglia", "5.3697800,43.2964820");
//	
//	map.put("Mosca", "37.6176330,55.7557860");
//	map.put("Mykonos", "25.3285500,37.4463890");
//	map.put("Olbia", "9.4868537,40.9225127");
//	map.put("Oporto", "-8.6102426,41.1499680");
//	map.put("Oslo", "10.7522454,59.9138688");
//	map.put("Palermo", "13.3614059,38.1156400");
//	map.put("Pescara", "14.2140946,42.4648263");
//	map.put("Praga", "14.4204598,50.0878114");
//	map.put("Rodi", "28.2270070,36.4432350");
//
//	map.put("Rotterdam", "4.4817760,51.9242160");
//	map.put("Salerno", "14.7659122,40.6779567");
//	map.put("Salonicco", "22.9446070,40.6393500");
//	map.put("San Giacomo di Compostella", "-8.5463034,42.8804471");
//	map.put("San Paolo", "-46.6388182,-23.5489433");
//	map.put("San Pietroburgo", "30.3157850,59.9390390");
//	map.put("Santander", "-3.8079336,43.4609602");
//	map.put("Southampton", "-1.4032340,50.9049660");
//	map.put("Stoccarda", "9.1807688,48.7771056");
//	map.put("Stoccolma", "18.0644881,59.3327881");
//	map.put("Tenerife", "-16.5770463,28.0464178");
//	map.put("Tolosa", "1.4442090,43.6046520");
//	map.put("Trapani", "12.5146338,38.0185881");
//	map.put("Trieste", "13.7784072,45.6536295");
//	map.put("Valenza", "-0.3768049,39.4702393");
//	map.put("Varsavia", "21.0122287,52.2296756");
//	map.put("Vienne", "16.3738189,48.2081743");
	
	map.put("Verona", "10.9917623,45.4383278");
	map.put("Roma", "12.4942486,41.8905198");
//	map.put("Amsterdam", "4.8922222,52.3730556");
	map.put("Monaco", "11.5801863,48.1391265");
	map.put("Londra", "-0.1262362,51.5001524");
	map.put("Ibiza", "1.4323778,38.9088566");
	map.put("Palma di Maiorca", "2.6499660,39.5695059");
	map.put("Parigi", "2.3522219,48.8566140");


    URL u;
    InputStream is = null;
    DataInputStream dis;
    String s,url=null;
    String par = null, arr = null, dist = null;
    int o = 0;
    boolean legal = true;
    
	WriteToFile wtf = new WriteToFile();
	
	ArrayList<String> partenza = new ArrayList<String>();
	partenza.add("Roma");
//	partenza.add("Amsterdam");
	partenza.add("Ibiza");
	partenza.add("Verona");
	partenza.add("Palma di Maiorca");
	partenza.add("Parigi");
	partenza.add("Londra");
	partenza.add("Monaco");
	
    ArrayList<String> arrivo = new ArrayList<String>();
    
    Iterator it_ = map.entrySet().iterator();
     
    while (it_.hasNext()) {
        Map.Entry pairs = (Map.Entry)it_.next();
        arrivo.add((String) pairs.getKey());
    }

    for( String part : partenza){
	  	for ( String dest: arrivo)
	  	{

	        url="http://www.ore-di-volo.com/index.asp?partenza=" + part +"&arrivo=" + map.get(dest) + "&cmdsub=Calcola";
	        url= url.replaceAll(" ", "+");
	        
		    try
		    {
		      u = new URL(url);
		      is = u.openStream();
		      dis = new DataInputStream(new BufferedInputStream(is));
		      while ((s = dis.readLine()) != null)
		      {
		    	  if ( s.contains("</title>") )
		    		  {
		    		  	
		    		  	s = s.replaceAll("<title>Durata Volo ", "");
		    		  	s = s.replaceAll("</title>", "");
		    		  	String cit[] = s.split("-");
		    		  	if( cit[1].charAt(0) == ' ' )
		    		  			cit[1]=cit[1].replaceFirst(" ", "");
		    		  	if(cit[1].contains("err")) 
		    		  		break;
		    		  	if( cit[1].contains(","))
		    		  		{
		    		  			String m[] = cit[1].split(",");
		    		  			cit[1] = m[0];
		    		  		}
		    		  	par = cit[0];
		    		  	arr = cit[1];
		    		  }
		    	  else if ( s.contains("var contentString"))
		    	  {
		    		  String a[] = s.split(" ");
		    		  for ( int i = 0; i< a.length; i++)
		    		  {
		    			  if( a[i].compareTo("Miglia") == 0)
		    			  	  dist = a[i+1].replaceFirst("</li><br>\";", "");
		    			  if( a[i].compareTo("Volo") == 0)
		    			  {
		    				  String ore_ = a[i+2].replaceAll("<strong>", "");
		    				  String minuti_ = a[i+3].replaceFirst("</strong></li><li>Coordinate", "");
		    				  ore_ = ore_.replace("h", "");
		    				  minuti_ = minuti_.replace("m", "");
		    				  try{
		    				  o = Integer.parseInt(ore_)*60;
		    				  if( !minuti_.contains( "</strong></li><li>Coordinate" ))
		    					  o+=Integer.parseInt(minuti_);
		    				  
		    				  legal = true;
		    				  }
		    				  catch( NumberFormatException e){
		    					  legal=false;
		    				  }
		    			  }
		    		  }
		    		  if( legal )
		    		  {
		    			  String toWrite = "INSERT INTO tratta ( partenza, arrivo, distanza, durata ) VALUES ('" + par + "','" + arr + "','" + dist + "'," + o + ");\n";
			    		  wtf.writeToFile(toWrite);
			    		  System.out.println(toWrite);
		    		  }

		    	  }
		      }
		    }
		    catch (MalformedURLException mue)
		    {
		      System.err.println("Ouch - a MalformedURLException happened.");
		      mue.printStackTrace();
		    }
		    catch (IOException ioe)
		    {
		      System.err.println("Oops- an IOException happened.");
		      ioe.printStackTrace();
		    }
		    finally
		    {
		      try
		      {
		        is.close();
		      }
		      catch (IOException ioe)
		      {
		      }
		    }
	    }// fine while
    }
    wtf.close();

  }

}
