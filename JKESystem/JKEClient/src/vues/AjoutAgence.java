package vues;

import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;

import javax.swing.*;

import modele.*;

import javax.swing.border.LineBorder;

import org.hibernate.mapping.Set;

public class AjoutAgence extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnom_ag,ladresse_ag;
	private JTextField chnom_ag;
	private JTextArea chadresse_ag;
	private JPanel pan1,pan2,pan3;
	private JButton qt,ajout;
		
	
	//constructeur
	public AjoutAgence( )
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("FORMULAIRE D'AJOUT D'UN AGENCE");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ladresse_ag=new JLabel("Adresse agence");
		ladresse_ag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnom_ag=new JLabel("Nom agence");
		lnom_ag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chnom_ag=new JTextField(30);
		chadresse_ag = new JTextArea(30,60);
		chadresse_ag.setLineWrap(true);
		chadresse_ag.setBorder(UIManager.getBorder("FormattedTextField.border"));
		ajout=new JButton("Ajouter ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.textHighlightText));
		pan2.setForeground(SystemColor.scrollbar);
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.NORTH, ladresse_ag, 3, SpringLayout.NORTH, chadresse_ag);
		sl_pan2.putConstraint(SpringLayout.WEST, ladresse_ag, 0, SpringLayout.WEST, lnom_ag);
		sl_pan2.putConstraint(SpringLayout.EAST, ladresse_ag, -6, SpringLayout.WEST, chadresse_ag);
		sl_pan2.putConstraint(SpringLayout.WEST, lnom_ag, 26, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnom_ag, -25, SpringLayout.WEST, chnom_ag);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnom_ag, 9, SpringLayout.NORTH, chnom_ag);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnom_ag, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chnom_ag, 136, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, chnom_ag, -45, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chadresse_ag, 49, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chadresse_ag, 136, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnom_ag, -5, SpringLayout.NORTH, chadresse_ag);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chadresse_ag, -10, SpringLayout.SOUTH, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, chadresse_ag, -10, SpringLayout.EAST, pan2);
		pan2.setLayout(sl_pan2);
		pan2.add(lnom_ag);
		pan2.add(chnom_ag);
		pan2.add(ladresse_ag);
		pan2.add(chadresse_ag);
		pan3.add(ajout);
		pan3.add(qt);
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		
		

		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,395,189); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Ajout agence"); //titre de la fenfetre
		
		//ajout des ecouteurs sur les bouton
		qt.addActionListener(this);
		ajout.addActionListener(this);
		
		//
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Masocket soc=new Masocket();
		if (e.getSource()==ajout)
		{
			try
			{
				
				Masocket.getOos().writeObject("ajout_ag");
				Masocket.getOos().flush();
        	
		    	   Agence a=new Agence();
		    	   a.setNomag(chnom_ag.getText());
		    	   a.setAdresseag(chadresse_ag.getText());
		    	   Masocket.getOos().writeObject(a);
		    	   Masocket.getOos().flush();
		    	   
		    	   JOptionPane.showMessageDialog(null, "Opération d'ajout réussie");
		    	   chnom_ag.setText("");
		    	   chadresse_ag.setText("");
			}catch (Exception ex) {
				
				
			}
		    	   
		    	
		}
		
		if (e.getSource()==qt)
			{
			dispose();
			}
		
	}
}
