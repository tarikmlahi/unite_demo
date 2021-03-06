package modele;

// Generated 2 juin 2012 10:49:45 by Hibernate Tools 3.2.4.GA
// demo unite for git
import java.util.Date;
//nouvelle demo
/**
 * Operation generated by hbm2java
 */
public class Operation implements java.io.Serializable {

	private Integer numop;
	private Compte compte;
	private String libop;
	private String sensop;
	private int montantop;
	private Date dateop;

	public Operation() {
	}

	public Operation(Compte compte, String libop, String sensop, int montantop,
			Date dateop) {
		this.compte = compte;
		this.libop = libop;
		this.sensop = sensop;
		this.montantop = montantop;
		this.dateop = dateop;
	}

	public Integer getNumop() {
		return this.numop;
	}

	public void setNumop(Integer numop) {
		this.numop = numop;
	}

	public Compte getCompte() {
		return this.compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public String getLibop() {
		return this.libop;
	}

	public void setLibop(String libop) {
		this.libop = libop;
	}

	public String getSensop() {
		return this.sensop;
	}

	public void setSensop(String sensop) {
		this.sensop = sensop;
	}

	public int getMontantop() {
		return this.montantop;
	}

	public void setMontantop(int montantop) {
		this.montantop = montantop;
	}

	public Date getDateop() {
		return this.dateop;
	}

	public void setDateop(Date dateop) {
		this.dateop = dateop;
	}

}
