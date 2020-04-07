package vues;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


import modele.*;
import javax.swing.border.LineBorder;

public class RechercheCompte extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnumcpt;
	private JTextField chnumcpt;
	private JPanel pan1,pan2,pan3;
	private JButton qt,rechrche;
	ArrayList<Operation> liste = null;
	
	//constructeur
	public RechercheCompte()
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("VEUILLEZ SAISIR UN NUMERO DE COMPTE");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lnumcpt=new JLabel("Numéro de compte");
		chnumcpt=new JTextField(30);
		rechrche=new JButton("Rechercher");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.text));
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.NORTH, lnumcpt, 6, SpringLayout.NORTH, chnumcpt);
		sl_pan2.putConstraint(SpringLayout.WEST, lnumcpt, 23, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnumcpt, -6, SpringLayout.WEST, chnumcpt);
		sl_pan2.putConstraint(SpringLayout.WEST, chnumcpt, 164, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, chnumcpt, -23, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnumcpt, 7, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnumcpt, -12, SpringLayout.SOUTH, pan2);
		pan2.setLayout(sl_pan2);
		pan2.add(lnumcpt);
		pan2.add(chnumcpt);
		pan3.add(rechrche);
		pan3.add(qt);
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,352,132); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Recherche compte"); //titre de la fenfetre
		
		//ajout des ecouteurs sur les bouton
		qt.addActionListener(this);
		rechrche.addActionListener(this);
		
		//
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==rechrche)
		{
			
			new RelevetBancaire(chnumcpt.getText());
			dispose();
		}
		
		
		if (e.getSource()==qt)
			{
			
			dispose();
			}
		
	}
	
}
