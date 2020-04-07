package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;


import modele.*;

import javax.swing.border.LineBorder;

public class RechercheClient extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnomcli;
	private JPanel pan1,pan2,pan3;
	private JButton qt,affiche;
	ArrayList<Client> liste = null;
	private JTextField chnumcli;
	
	//constructeur
	public RechercheClient()
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("VEUILLEZ SAISIR LE NUMERO DU CLIENT");
		lnomcli=new JLabel("Numéro client");
		
		
Masocket soc=new Masocket();
		
		
		affiche=new JButton("Afficher les comptes");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.text));
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.WEST, lnomcli, 21, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnomcli, -194, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnomcli, 16, SpringLayout.NORTH, pan2);
		pan2.setLayout(sl_pan2);
		pan2.add(lnomcli);
		pan3.add(affiche);
		pan3.add(qt);
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		
		chnumcli = new JTextField();
		sl_pan2.putConstraint(SpringLayout.NORTH, chnumcli, -3, SpringLayout.NORTH, lnomcli);
		sl_pan2.putConstraint(SpringLayout.WEST, chnumcli, 15, SpringLayout.EAST, lnomcli);
		sl_pan2.putConstraint(SpringLayout.EAST, chnumcli, -40, SpringLayout.EAST, pan2);
		pan2.add(chnumcli);
		chnumcli.setColumns(10);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,340,132); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Recherche Client"); //titre de la fenfetre
		
		//ajout des ecouteurs sur les bouton
		qt.addActionListener(this);
		affiche.addActionListener(this);
		
		//
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==affiche)
		{
			try {
			Masocket.getOos().writeObject("Recherche_cli");
			Masocket.getOos().flush();
			
			Masocket.getOos().writeObject(chnumcli.getText());
			Masocket.getOos().flush();
			
			Client cl=(Client)Masocket.getOis().readObject();
			if(cl.getCodecli()==0)
			{
				JOptionPane.showMessageDialog(null,"Client innexistant");
				chnumcli.setText("");
			}else
			{
			String nomag=cl.getAgence().getNomag();
			new ListeCompteClient(cl.getCodecli(),nomag,cl.getNomcli());
			dispose();
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if (e.getSource()==qt)
			{
			
			dispose();
			}
		
	}
}
