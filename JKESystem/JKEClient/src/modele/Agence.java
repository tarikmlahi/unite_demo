package modele;

// Generated 2 juin 2012 10:49:45 by Hibernate Tools 3.2.4.GA
//Master branch update !

//V2
import java.util.HashSet;
import java.util.Set;

/**
 * Agence generated by hbm2java
 */
public class Agence implements java.io.Serializable {

	private Integer codeag;
	private String nomag;
	private String adresseag;
	private Set clients = new HashSet(0);

	public Agence() {
	}

	public Agence(String nomag, String adresseag) {
		this.nomag = nomag;
		this.adresseag = adresseag;
	}

	public Agence(String nomag, String adresseag, Set clients) {
		this.nomag = nomag;
		this.adresseag = adresseag;
		this.clients = clients;
	}

	public Integer getCodeag() {
		return this.codeag;
	}

	public void setCodeag(Integer codeag) {
		this.codeag = codeag;
	}

	public String getNomag() {
		return this.nomag;
	}

	public void setNomag(String nomag) {
		this.nomag = nomag;
	}

	public String getAdresseag() {
		return this.adresseag;
	}

	public void setAdresseag(String adresseag) {
		this.adresseag = adresseag;
	}

	public Set getClients() {
		return this.clients;
	}

	public void setClients(Set clients) {
		this.clients = clients;
	}

}
