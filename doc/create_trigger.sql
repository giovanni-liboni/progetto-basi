CREATE TRIGGER update_time_richiesta
BEFORE INSERT ON prenotazione
	FOR EACH ROW EXECUTE PROCEDURE update_time_richiesta();

CREATE TRIGGER update_tessera
AFTER INSERT OR DELETE ON biglietto
    FOR EACH ROW EXECUTE PROCEDURE update_tessera();

CREATE TRIGGER update_time_biglietto 
BEFORE INSERT ON biglietto
	FOR EACH ROW EXECUTE PROCEDURE update_time_biglietto();
