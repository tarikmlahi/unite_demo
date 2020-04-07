package modele;

// Generated 2 juin 2012 10:49:45 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Compte generated by hbm2java
 */
public class Compte implements java.io.Serializable {

	private String codecpt;
	private Client client;
	private String libcpt;
	private String senscpt;
	private int soldcpt;
	private Set operations = new HashSet(0);

	public Compte() {
	}

	public Compte(String codecpt, Client client, String libcpt, String senscpt,
			int soldcpt) {
		this.codecpt = codecpt;
		this.client = client;
		this.libcpt = libcpt;
		this.senscpt = senscpt;
		this.soldcpt = soldcpt;
	}

	public Compte(String codecpt, Client client, String libcpt, String senscpt,
			int soldcpt, Set operations) {
		this.codecpt = codecpt;
		this.client = client;
		this.libcpt = libcpt;
		this.senscpt = senscpt;
		this.soldcpt = soldcpt;
		this.operations = operations;
	}

	public String getCodecpt() {
		return this.codecpt;
	}

	public void setCodecpt(String codecpt) {
		this.codecpt = codecpt;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getLibcpt() {
		return this.libcpt;
	}

	public void setLibcpt(String libcpt) {
		this.libcpt = libcpt;
	}

	public String getSenscpt() {
		return this.senscpt;
	}

	public void setSenscpt(String senscpt) {
		this.senscpt = senscpt;
	}

	public int getSoldcpt() {
		return this.soldcpt;
	}

	public void setSoldcpt(int soldcpt) {
		this.soldcpt = soldcpt;
	}

	public Set getOperations() {
		return this.operations;
	}

	public void setOperations(Set operations) {
		this.operations = operations;
	}

}