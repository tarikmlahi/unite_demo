package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import modele.*;

import javax.swing.border.LineBorder;

public class ListeClientAg extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1;
	private JPanel pan1,pan2,pan3;
	private JButton qt,cree_cpt;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList<Client> liste = null;

	public ListeClientAg(int codeag, String nomag, ArrayList<Client> liste)
	{
		
		l1=new JLabel("LISTE DES CLIENTS DE L'AGENCE "+nomag.toUpperCase());
		pan1=new JPanel();
		pan1.add(l1);
		
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		colonnes = new Vector<String>();
		colonnes.add("Numéro");
		colonnes.add("Nom");
		colonnes.add("Prénom");
		colonnes.add("Adresse");
		
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		
			
		for (int i=0;i<liste.size();i++)
		{
			tuple= new Vector <String>();
			Client  c = liste.get(i);
			tuple.add(String.valueOf(c.getCodecli()));
			tuple.add(c.getNomcli());
			tuple.add(c.getPrenomcli());
			tuple.add(c.getAdressecli());
			
			
			lignes.add(tuple);
			
			
		}
		      
		SpringLayout sl_pan2 = new SpringLayout();
		pan2.setLayout(sl_pan2);
		table = new JTable(lignes,colonnes);
		
		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new LineBorder(UIManager.getColor("Table.highlight")));
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 23, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 218, SpringLayout.NORTH, pan2);
		pan2.add(scrollPane);
		cree_cpt=new JButton("Ajouter un compte ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(UIManager.getColor("Button.background"));
		pan3.add(cree_cpt);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,525,332);
		setResizable(false);
		setTitle("Liste des clients par agence");
		
		qt.addActionListener(this);
		cree_cpt.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()==cree_cpt)
		{
			int n=table.getSelectedRow();
			if(n==-1)
			{
				JOptionPane.showMessageDialog(null,"Veuillez selectionner une ligne");
			}else
			{
			 String code=(String) table.getValueAt(n, 0);
			 String nom=(String) table.getValueAt(n, 1);
			
			 new AjoutCompte(code,nom);
			 dispose();
			}
		}
			if (e.getSource()==qt)
			{
			
				dispose();
			}
		
	}
	
}
