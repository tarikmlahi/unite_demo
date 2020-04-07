package modele;

import java.util.Date;

public class AutreMethode {
	public String serv;

	public String getServ() {
		return serv;
	}

	public void setServ(String serv) {
		this.serv = serv;
	}
	
	//convertisseur de date EN to Fr
	
	public String AngToFr(Date date) {
		String format = "dd/MM/yyyy H:mm:ss"; 
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		String datefr=formater.format( date );
		return datefr;
	}

}
