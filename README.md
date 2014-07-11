Progetto Basi di Dati
=============
Progetto finale del corso di [Basi di Dati](http://www.di.univr.it/?ent=oi&codiceCs=S24&codins=12700&cs=420&discr=&discrCd=) a.a. 2013/2014 dell'[Università degli Studi di Verona](http://www.univr.it)

TO RUN:

1. Install tomcat7
2. Install postgresql
3. Create on postgresql a database
4. Launch src/script-sql scripts in this order:
    1. init.sql
    2. popola_tratta.sql
    3. popola_volo.sql
    4. popola_passeggero.sql
5. Import dynamic-web-project in Eclipse
6. Add to build path the library in src/java-sql/ext-lib
7. Build all
8. Go to localhost:8080/Name-of-eclipse-project/servlet/main

TODO:
- Scrivere commenti
- Scrivere relazione
- Disegnare ER
- Togliere "Seleziona arrivo" una vota selezionata la casella
- Database multimediale
- Hibernate con multimedia
- Possibilta' caricare foto profilo del passeggero
- Impostare metodi per caricare/scaricare foto dal db
- Possibilita' imbarco
- Finire sezione contatti ( da leggere sulla consegna )
- Creare una nuova modalità per ricercare i biglietti

DOPO TUTTO:
- Aggiungere mappa

Giovanni:
- Controllo prenotazioni duplicate
- Tessera
- Finire parte contatti ( aggiungere e centrare le informazioni )
- Aggiungere nell'area personale le informazioni del passeggero
