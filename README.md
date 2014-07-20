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

Ora il progetto punta al database dblab01p

TODO:
- Scrivere commenti
- Scrivere relazione
- Disegnare ER
- Finire sezione contatti ( da leggere sulla consegna )
- Formattare il nome e il cognome prima di inserirli nel DB, tutti in maiuscolo
- Rivedere downloadimage e uploadimage
