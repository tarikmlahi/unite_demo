package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;

import modele.*;

import javax.swing.border.LineBorder;

public class ListeCompteClient extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lsoldt;
	private JTextField chsoldt;
	private JPanel pan1,pan2,pan3;
	private JButton qt;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList<Compte> liste = null;
	private int total=0;

	public ListeCompteClient(int codecli,String nom,String nomcl)
	{
		
		l1=new JLabel("LISTE DES COMPTES DU CLIENT "+nomcl.toUpperCase());
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pan1=new JPanel();
		pan1.add(l1);
		lsoldt=new JLabel("Montant total:");
		chsoldt=new JTextField(20);
		chsoldt.setEditable(false);
		chsoldt.setBackground(Color.WHITE);
		chsoldt.setForeground(Color.RED);
		
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.window));
		colonnes = new Vector<String>();
		colonnes.add("Numéro");
		colonnes.add("Libellé");
		colonnes.add("Solde");
		colonnes.add("Sens");
		
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		
		try {
			
			Masocket.getOos().writeObject("liste_cp_cli");
			Masocket.getOos().flush();
			
			Masocket.getOos().writeObject(String.valueOf(codecli));
	    	Masocket.getOos().flush();
	    	
	        ArrayList<Compte> liste=(ArrayList<Compte>)Masocket.getOis().readObject();
	        if(liste.size()==0)
	        {
	       JOptionPane.showMessageDialog(null, "Ce client n'a pas de compte");
	       dispose();
	        }else{
		for (int i=0;i<liste.size();i++)
		{
			tuple= new Vector <String>();
			Compte  c = liste.get(i);
			tuple.add(String.valueOf(c.getCodecpt()));
			tuple.add(c.getLibcpt());
			tuple.add(String.valueOf(c.getSoldcpt()));
			tuple.add(c.getSenscpt());
						
			lignes.add(tuple);
			total=total+c.getSoldcpt();
			
		}}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		table = new JTable(lignes,colonnes);
		chsoldt.setText(total+"");
		SpringLayout sl_pan2 = new SpringLayout();
		pan2.setLayout(sl_pan2);
		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 24, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 193, SpringLayout.NORTH, pan2);
		pan2.add(scrollPane);
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(SystemColor.control);
		pan3.add(lsoldt);
		pan3.add(chsoldt);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,525,291);
		setResizable(false);
		setTitle("Liste des compte");
		
		qt.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
			if (e.getSource()==qt)
			{
				dispose();
			}
		
	}
	
}
