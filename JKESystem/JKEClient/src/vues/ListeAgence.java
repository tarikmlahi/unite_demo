package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import modele.*;

import javax.swing.border.LineBorder;

public class ListeAgence extends JFrame implements ActionListener
{
	/**
	 * 
	 */

	private JLabel l1;
	private JPanel pan1,pan2,pan3;
	private JButton qt,cree_cli;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList <Agence> liste = null;

	public ListeAgence()
	{
		
		l1=new JLabel("LISTE DES AGENCES DE LA BANQUE");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pan1=new JPanel();
		pan1.add(l1);
		
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.window));
		colonnes = new Vector<String>();
		colonnes.add("Code");
		colonnes.add("Nom agence");
		colonnes.add("Adresse");
		
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		
		try {
			
		Masocket.getOos().writeObject("liste_ag");
		Masocket.getOos().flush();
        ArrayList<Agence> liste=(ArrayList<Agence>)Masocket.getOis().readObject();
				
		
		for (int i=0;i<liste.size();i++)
		{
			tuple= new Vector <String>();
			Agence  a = liste.get(i);
			tuple.add(String.valueOf(a.getCodeag()));
			tuple.add(a.getNomag());
			tuple.add(a.getAdresseag());
			
			lignes.add(tuple);
			
			
		}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		SpringLayout sl_pan2 = new SpringLayout();
		pan2.setLayout(sl_pan2);
		table = new JTable(lignes,colonnes);
		
		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 24, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 245, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, scrollPane, -24, SpringLayout.EAST, pan2);
		pan2.add(scrollPane);
		cree_cli=new JButton("Ajouter un client ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(SystemColor.control);
		pan3.add(cree_cli);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,525,361);
		setResizable(false);
		setTitle("Liste des agences");

		qt.addActionListener(this);
		cree_cli.addActionListener(this);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()==cree_cli)
		{
			int n=table.getSelectedRow();
			if(n==-1)
			{
				JOptionPane.showMessageDialog(null,"Veuillez selectionner une ligne");
			}else
			{
			 String code=(String) table.getValueAt(n, 0);
			 String nom=(String) table.getValueAt(n, 1);
			 String adr=(String) table.getValueAt(n, 2);
			
			 new AjoutClient(code,nom);
			 dispose();
			}
		}
			if (e.getSource()==qt)
			{
				dispose();
			}
		
	}
	
}
