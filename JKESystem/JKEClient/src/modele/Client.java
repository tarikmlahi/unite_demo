package modele;

// Generated 2 juin 2012 10:49:45 by Hibernate Tools 3.2.4.GA
//test for wi 195

import java.util.HashSet;
import java.util.Set;

/**
 * Client generated by hbm2java
 */
public class Client implements java.io.Serializable {

	private Integer codecli;
	private Agence agence;
	private String nomcli;
	private String prenomcli;
	private String adressecli;
	private Set comptes = new HashSet(0);

	public Client() {
	}

	public Client(Agence agence, String nomcli, String prenomcli,
			String adressecli) {
		this.agence = agence;
		this.nomcli = nomcli;
		this.prenomcli = prenomcli;
		this.adressecli = adressecli;
	}

	public Client(Agence agence, String nomcli, String prenomcli,
			String adressecli, Set comptes) {
		this.agence = agence;
		this.nomcli = nomcli;
		this.prenomcli = prenomcli;
		this.adressecli = adressecli;
		this.comptes = comptes;
	}

	public Integer getCodecli() {
		return this.codecli;
	}

	public void setCodecli(Integer codecli) {
		this.codecli = codecli;
	}

	public Agence getAgence() {
		return this.agence;
	}

	public void setAgence(Agence agence) {
		this.agence = agence;
	}

	public String getNomcli() {
		return this.nomcli;
	}

	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}

	public String getPrenomcli() {
		return this.prenomcli;
	}

	public void setPrenomcli(String prenomcli) {
		this.prenomcli = prenomcli;
	}

	public String getAdressecli() {
		return this.adressecli;
	}

	public void setAdressecli(String adressecli) {
		this.adressecli = adressecli;
	}

	public Set getComptes() {
		return this.comptes;
	}

	public void setComptes(Set comptes) {
		this.comptes = comptes;
	}

}
