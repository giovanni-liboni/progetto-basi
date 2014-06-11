﻿CREATE TABLE tratta(
	partenza	VARCHAR(100) NOT NULL,
	arrivo		VARCHAR(100) NOT NULL,
	durata		INTEGER NOT NULL,
	distanza	FLOAT NOT NULL DEFAULT '0.0',
	PRIMARY KEY ( partenza, arrivo ),
	UNIQUE ( partenza, arrivo )
);
CREATE TABLE volo(
	codicevolo	VARCHAR(10) NOT NULL,
	partenza	VARCHAR(100) NOT NULL,
	arrivo		VARCHAR(100) NOT NULL,
	datapartenza	DATE NOT NULL,
	orapartenza	TIME NOT NULL,
	tipoaereo	VARCHAR(50) NOT NULL ,
	capienza	INTEGER NOT NULL,
	PRIMARY KEY( codicevolo ),
	UNIQUE ( codicevolo ),
	FOREIGN KEY( partenza, arrivo )
			REFERENCES tratta( partenza, arrivo )
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
CREATE TABLE passeggero(
	login		VARCHAR(100) NOT NULL,
	password	VARCHAR(100) NOT NULL,
	nazionalita	VARCHAR(100) NOT  NULL,
	nome		VARCHAR(100) NOT NULL,
	cognome		VARCHAR(100) NOT NULL,
	documento	VARCHAR(50) NOT NULL,

	numvoli		INTEGER DEFAULT 0,
	miglia		INTEGER DEFAULT 0,
	tessera		BOOLEAN DEFAULT FALSE,
	PRIMARY KEY( documento ),
	UNIQUE ( login, documento )
);
CREATE TABLE prenotazione(
	id		SERIAL,
	codicevolo	VARCHAR(10) NOT NULL
			REFERENCES volo( codicevolo )
				ON UPDATE CASCADE
				ON DELETE CASCADE,
	documento	VARCHAR(50) NOT NULL
			REFERENCES passeggero( documento )
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	datarichiesta	DATE,
	orarichiesta	TIME,
	PRIMARY KEY( id ),
	UNIQUE ( id )
);
CREATE TABLE biglietto(
	id		SERIAL,
	codicevolo	VARCHAR(10) NOT NULL
			REFERENCES volo( codicevolo )
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	documento	VARCHAR(50) NOT NULL
			REFERENCES passeggero( documento )
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	id_prenotazione INTEGER NOT NULL
				REFERENCES prenotazione( id )
				ON UPDATE CASCADE
				ON DELETE CASCADE,
	dataemissione	DATE NOT NULL,
	prezzo		FLOAT NOT NULL,

	PRIMARY KEY( codicevolo, documento ),
	UNIQUE ( codicevolo, documento, id )
);

CREATE TABLE posto(
	lettera		CHAR NOT NULL,
	numero		INTEGER DEFAULT 0,
	PRIMARY KEY ( lettera, numero ),
	UNIQUE ( lettera, numero )
);
CREATE TABLE imbarco(
	lettera		CHAR NOT NULL,
	numero		INTEGER DEFAULT 0,
	imbarcato	BOOLEAN DEFAULT FALSE,
	-- PRIMARY KEY DEL BIGLIETTO
	codicevolo	VARCHAR(10) NOT NULL,
	documento	VARCHAR(50) NOT NULL,
	-- IDENTIFICATO DAL BIGLIETTO E DAL POSTO
	PRIMARY KEY ( codicevolo, documento, lettera, numero),

	FOREIGN KEY( lettera, numero )
			REFERENCES posto( lettera,numero )
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	FOREIGN KEY( codicevolo, documento )
			REFERENCES biglietto( codicevolo,documento )
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
CREATE OR REPLACE FUNCTION update_time_richiesta()
RETURNS TRIGGER AS '
BEGIN 
	IF NEW.datarichiesta IS NULL THEN
		NEW.datarichiesta := current_date;
	END IF;
	IF NEW.orarichiesta IS NULL THEN 
		NEW.orarichiesta := current_time;
    END IF;
	RETURN NEW;
END' LANGUAGE 'plpgsql';

CREATE TRIGGER TIME_RICHIESTA BEFORE INSERT ON PRENOTAZIONE
FOR EACH ROW EXECUTE PROCEDURE update_time_richiesta();
