package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import modele.*;

import javax.swing.border.LineBorder;
public class ListeClient extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	
	private JLabel l1;
	private JPanel pan1,pan2,pan3;
	private JButton qt,cree_cpt;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList<Client> liste = null;

	public ListeClient()
	{
		
		l1=new JLabel("LISTE DE TOUS CLIENTS ");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pan1=new JPanel();
		pan1.add(l1);
		
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(new Color(255, 255, 255)));
		colonnes = new Vector<String>();
		colonnes.add("Numéro");
		colonnes.add("Nom");
		colonnes.add("Prénom");
		colonnes.add("Adresse");
		colonnes.add("Agence");
		
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		
		try {
			
		Masocket.getOos().writeObject("liste_cl");
		Masocket.getOos().flush();
        ArrayList<Client> liste=(ArrayList<Client>)Masocket.getOis().readObject();
        
        for (int i=0;i<liste.size();i++)
		{
		
		tuple= new Vector <String>();
			Client  c = liste.get(i);
			tuple.add(String.valueOf(c.getCodecli()));
			tuple.add(c.getNomcli());
			tuple.add(c.getPrenomcli());
			tuple.add(c.getAdressecli());
			//tuple.add(String.valueOf(c.getCodeag()));
			tuple.add(c.getAgence().getNomag());
			
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
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 271, SpringLayout.NORTH, pan2);
		pan2.add(scrollPane);
		cree_cpt=new JButton("Ajouter un compte ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(SystemColor.control);
		pan3.add(cree_cpt);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,525,384);
		setResizable(false);
		setTitle("Liste des clients");
	
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
			 String nom=(String) table.getValueAt(n, 4);
			
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
