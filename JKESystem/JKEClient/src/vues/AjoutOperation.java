package vues;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import modele.*;

public class AjoutOperation extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnumcpt,llibop,lsensop,lmontantop;
	private JTextField chnumcpt,chlibop,chmontant;
	private JComboBox chsens;
	private JPanel pan1,pan2,pan3;
	private JButton qt,ajout;
	private JTextField chl;
	
	//constructeur
	public AjoutOperation(String codecpt, String codecl)
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("FORMULAIRE D'OPERATION");
		lmontantop=new JLabel("Montant de l'operation");
		lnumcpt=new JLabel("Numéro de compte");
		llibop=new JLabel("Libelle operation");
		lsensop=new JLabel("Sense de l'operation");
		
		chl=new JTextField(10);
		chl.setVisible(false);
		chl.setText(codecl);
		chnumcpt=new JTextField(30);
		chnumcpt.setText(codecpt);
		chnumcpt.setEnabled(false);
		chmontant=new JTextField(30);
		chlibop=new JTextField(30);
		chsens = new JComboBox();
		chsens.addItem("DB");
		chsens.addItem("CR");
		
		ajout=new JButton("Enregistrer ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		
		//alignement panaux
		pan2.setLayout(new GridLayout(6,2));
		
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		pan2.add(lnumcpt);
		pan2.add(chnumcpt);
		pan2.add(llibop);
		pan2.add(chlibop);
		pan2.add(lsensop);
		pan2.add(chsens);
		pan2.add(lmontantop);
		pan2.add(chmontant);
		pan3.add(ajout);
		pan3.add(qt);
		
		
		//ajout des panaux sur la fenetre
		add(pan1,BorderLayout.NORTH);
		add(pan2,BorderLayout.CENTER);
		add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,400,260); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Ajout opération"); //titre de la fenfetre
		
		//ajout des ecouteurs sur les bouton
		qt.addActionListener(this);
		ajout.addActionListener(this);
		
		//
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Compte cp=new Compte();
		Operation o=new Operation();
		Masocket soc=new Masocket();
		if(e.getSource()==ajout)
		{
			
			try
			{
			Masocket.getOos().writeObject("recherche_num_cp");
			Masocket.getOos().flush();
			
			Masocket.getOos().writeObject(chnumcpt.getText());
	    	Masocket.getOos().flush();
	    	
	    	cp=(Compte)Masocket.getOis().readObject();
	    	
			
			int montantop=Integer.parseInt(chmontant.getText());
			int montantcpt=0;
			if((chsens.getSelectedItem().toString())=="CR")
			{	
				//on ajoute le montant de l'operation au montant du compte
				montantcpt=cp.getSoldcpt()+montantop;
				
			}else
			{
				montantcpt=cp.getSoldcpt()-montantop;
			}
			
			Masocket.getOos().writeObject("ajout_op");
			Masocket.getOos().flush();
			
			o.setLibop(chlibop.getText());
			o.setSensop(chsens.getSelectedItem().toString());
			o.setMontantop(Integer.parseInt(chmontant.getText()));
			cp.setCodecpt(chnumcpt.getText());
			o.setCompte(cp);	
		
			
			String format = "yyyy-MM-dd H:mm:ss"; 
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
			java.util.Date date = new java.util.Date(); 
			o.setDateop(formater.parse(formater.format( date )));
			
			Masocket.getOos().writeObject(o);
	    	Masocket.getOos().flush();
			
	    	Masocket.getOos().writeObject("modif_solde");
			Masocket.getOos().flush();
			
			Set oper=new HashSet();
			oper.add(o);
	    	Compte cpt=new Compte();
	    	cpt.setOperations(oper);
	    	cpt.setCodecpt(chnumcpt.getText());
	    	cpt.setSoldcpt(montantcpt);
	    	cpt.setLibcpt(cp.getLibcpt());
	    	cpt.setSenscpt(cp.getSenscpt());
	    	Client cl=new Client();
	    	cl.setCodecli(Integer.parseInt(chl.getText()));
	    	cpt.setClient(cl);
	    	
	    	Masocket.getOos().writeObject(cpt);
	    	Masocket.getOos().flush();
	 
			JOptionPane.showMessageDialog(null, "Opération enregistré sous le  numéro de compte"+chnumcpt.getText());
			}catch (Exception ex) {
				
				
			}
		
			dispose();
		}
		
		
		if (e.getSource()==qt)
			{
			new ListeCompte();
			dispose();
			}
		
		
	}
	
}
