package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;


import modele.*;

import javax.swing.border.LineBorder;

public class SelectionAgence extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnom_ag;
	private JComboBox chnom_ag;
	private JPanel pan1,pan2,pan3;
	private JButton qt,affiche;
	ArrayList <Agence> liste = null;
	
	//constructeur
	public SelectionAgence()
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("VEUILLEZ SELECTIONNER UN AGENCE");
		lnom_ag=new JLabel("Nom agence");
		lnom_ag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		chnom_ag=new JComboBox();
		
		
Masocket soc=new Masocket();
		
		try {
			
		Masocket.getOos().writeObject("liste_ag");
		Masocket.getOos().flush();
        ArrayList<Agence> liste=(ArrayList<Agence>)Masocket.getOis().readObject();
		
	        for (int i=0;i<liste.size();i++)
			{
			Agence  a = liste.get(i);
			chnom_ag.addItem(a.getCodeag()+" "+a.getNomag());
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		affiche=new JButton("Afficher la liste");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(UIManager.getColor("Button.highlight")));
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.WEST, chnom_ag, 5, SpringLayout.EAST, lnom_ag);
		sl_pan2.putConstraint(SpringLayout.EAST, chnom_ag, -22, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, lnom_ag, 24, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnom_ag, 17, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnom_ag, -20, SpringLayout.SOUTH, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnom_ag, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, lnom_ag, 44, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnom_ag, 159, SpringLayout.WEST, pan2);
		pan2.setLayout(sl_pan2);
		pan2.add(lnom_ag);
		pan2.add(chnom_ag);
		pan3.add(affiche);
		pan3.add(qt);
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,351,150); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Selectionner un agence"); //titre de la fenfetre
		
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
			StringTokenizer st=new StringTokenizer(chnom_ag.getSelectedItem().toString());
			int code= Integer.parseInt(st.nextToken());
			String nomag=(chnom_ag.getSelectedItem().toString()).substring(String.valueOf(code).length()+1);
			
				try {
					Masocket.getOos().writeObject("liste_cl_ag");
				
				Masocket.getOos().flush();
				
				Masocket.getOos().writeObject(String.valueOf(code));
		    	Masocket.getOos().flush();
		    	
		        ArrayList<Client> liste=(ArrayList<Client>)Masocket.getOis().readObject();
		        if(liste.size()==0)
		        {
		       JOptionPane.showMessageDialog(null, "Cet agence n'a pas de client");

		        }else
		        {
		        	new ListeClientAg(code ,nomag, liste);
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
