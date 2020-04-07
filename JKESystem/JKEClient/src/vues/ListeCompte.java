package vues;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import modele.*;

import javax.swing.border.LineBorder;

public class ListeCompte extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1;
	private JPanel pan1,pan2,pan3;
	private JButton qt,passeop;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList<Compte> liste = null;

	public ListeCompte()
	{
		
		l1=new JLabel("LISTE DE TOUS LES COMPTES ");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		pan1=new JPanel();
		pan1.add(l1);
		
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.text));
		colonnes = new Vector<String>();
		colonnes.add("Numéro");
		colonnes.add("Libellé");
		colonnes.add("Solde");
		colonnes.add("Sens");
		colonnes.add("N° CL");
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		try {
			
		Masocket.getOos().writeObject("liste_cp");
		Masocket.getOos().flush();
        ArrayList<Compte> liste=(ArrayList<Compte>)Masocket.getOis().readObject();
		for (int i=0;i<liste.size();i++)
		{
			tuple= new Vector <String>();
			Compte  c = liste.get(i);
			tuple.add(String.valueOf(c.getCodecpt()));
			tuple.add(c.getLibcpt());
			tuple.add(String.valueOf(c.getSoldcpt()));
			tuple.add(c.getSenscpt());
			tuple.add(String.valueOf(c.getClient().getCodecli()));
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
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 21, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 24, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 256, SpringLayout.NORTH, pan2);
		pan2.add(scrollPane);
		passeop=new JButton("Passer un opération");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(SystemColor.control);
		pan3.add(passeop);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,525,364);
		setResizable(false);
		setTitle("Liste des compte");
		
		qt.addActionListener(this);
		passeop.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()==passeop)
		{
			int n=table.getSelectedRow();
			if(n==-1)
			{
				JOptionPane.showMessageDialog(null,"Veuillez selectionner une ligne");
			}else
			{
			 String code=(String) table.getValueAt(n, 0);
			 String codecl=(String) table.getValueAt(n, 4);
			 new AjoutOperation(code, codecl);
			 dispose();
			}
		}
			if (e.getSource()==qt)
			{
				dispose();
			}
		
	}
	
}
