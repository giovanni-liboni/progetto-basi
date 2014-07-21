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



CREATE OR REPLACE FUNCTION update_time_biglietto()
RETURNS TRIGGER AS '
BEGIN 
	IF NEW.dataemissione IS NULL THEN
		NEW.dataemissione := current_date;
	END IF;
	RETURN NEW;
END' LANGUAGE 'plpgsql';



CREATE OR REPLACE FUNCTION update_numvoli()
RETURNS TRIGGER AS '
BEGIN 
	IF NEW.dataemissione IS NULL THEN
		NEW.dataemissione := current_date;
	END IF;
	RETURN NEW;
END' LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION update_tessera() RETURNS trigger AS $$
BEGIN 
  UPDATE passeggero
  SET numvoli = (SELECT count(*) 
		  FROM volo JOIN biglietto ON biglietto.codicevolo = volo.codicevolo 
			    JOIN tratta ON (volo.partenza = tratta.partenza 
					    AND volo.arrivo = tratta.arrivo )
		 WHERE passeggero.tessera=true 
		      AND passeggero.documento = biglietto.documento
		 GROUP BY passeggero.documento ),
      miglia = (SELECT sum(distanza) 
		FROM volo JOIN biglietto ON biglietto.codicevolo = volo.codicevolo 
			  JOIN tratta ON (volo.partenza = tratta.partenza AND volo.arrivo = tratta.arrivo )
		WHERE passeggero.tessera=true AND passeggero.documento = biglietto.documento
		GROUP BY passeggero.documento )
  FROM biglietto
  WHERE biglietto.dataemissione >= ( SELECT date('now') - interval '3 year') 
				      AND NEW.documento = passeggero.documento;
  RETURN NEW;

END;
$$ LANGUAGE 'plpgsql';
