package vues;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modele.*;

import javax.swing.border.LineBorder;

public class AjoutClient extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lcodeag,lnomag,lnomcli,lprenomcli,ladressecli;
	private JTextField chcodeag,chnomag,chnomcli;
	private JTextArea chadressecli;
	private JPanel pan1,pan2,pan3;
	private JButton qt,ajout,rt;
	private JTextField chprenomcli;
	
	//constructeur
	public AjoutClient(String code, String nom)
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("FORMULAIRE D'AJOUT D'UN CLIENT");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		ladressecli=new JLabel("Adresse client");
		ladressecli.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lcodeag=new JLabel("Code gence");
		lcodeag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnomag=new JLabel("Nom agence");
		lnomag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnomcli=new JLabel("Nom client");
		lnomcli.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lprenomcli=new JLabel("Prénom client");
		lprenomcli.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		chcodeag=new JTextField(30);
		chcodeag.setText(code);
		chcodeag.setEnabled(false);
		chnomag=new JTextField(30);
		chnomag.setText(nom);
		chnomag.setEnabled(false);
		chnomcli=new JTextField(30);
		chnomcli.setSize(new Dimension(0, 30));
		chadressecli = new JTextArea(30,60);
		chadressecli.setLineWrap(true);
		chadressecli.setBorder(UIManager.getBorder("TextField.border"));
		rt=new JButton("Changer agence");
		ajout=new JButton("Ajouter ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(SystemColor.window));
		
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.WEST, chnomcli, 203, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, lnomcli, 29, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnomcli, -6, SpringLayout.WEST, chnomcli);
		sl_pan2.putConstraint(SpringLayout.WEST, lnomag, 29, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lnomag, -6, SpringLayout.WEST, chnomag);
		sl_pan2.putConstraint(SpringLayout.NORTH, chcodeag, 33, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chcodeag, 6, SpringLayout.EAST, lcodeag);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chcodeag, -6, SpringLayout.NORTH, chnomag);
		sl_pan2.putConstraint(SpringLayout.EAST, chcodeag, -26, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lcodeag, 2, SpringLayout.NORTH, chcodeag);
		sl_pan2.putConstraint(SpringLayout.WEST, lcodeag, 0, SpringLayout.WEST, lnomag);
		sl_pan2.putConstraint(SpringLayout.EAST, lcodeag, -227, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnomag, 2, SpringLayout.NORTH, chnomag);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnomcli, 93, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lprenomcli, 7, SpringLayout.SOUTH, lnomcli);
		sl_pan2.putConstraint(SpringLayout.EAST, chnomcli, -26, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnomcli, 1, SpringLayout.NORTH, chnomcli);
		sl_pan2.putConstraint(SpringLayout.WEST, chnomag, 203, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, chnomag, -26, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnomag, 63, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnomag, -6, SpringLayout.NORTH, chnomcli);
		sl_pan2.putConstraint(SpringLayout.WEST, chadressecli, 203, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, chadressecli, -26, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chadressecli, 147, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, lprenomcli, 29, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lprenomcli, -229, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chadressecli, -10, SpringLayout.SOUTH, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, ladressecli, 8, SpringLayout.SOUTH, lprenomcli);
		sl_pan2.putConstraint(SpringLayout.WEST, ladressecli, 29, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, ladressecli, -33, SpringLayout.SOUTH, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, ladressecli, -6, SpringLayout.WEST, chadressecli);
		pan2.setLayout(sl_pan2);
		pan2.add(lcodeag);
		pan2.add(chcodeag);
		pan2.add(lnomag);
		pan2.add(chnomag);
		pan2.add(lnomcli);
		pan2.add(chnomcli);
		pan2.add(lprenomcli);
		pan2.add(ladressecli);
		pan2.add(chadressecli);
		pan3.add(ajout);
		pan3.add(qt);
		pan3.add(rt);
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		
		chprenomcli = new JTextField();
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnomcli, -6, SpringLayout.NORTH, chprenomcli);
		sl_pan2.putConstraint(SpringLayout.NORTH, chprenomcli, 120, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chprenomcli, 8, SpringLayout.EAST, lprenomcli);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chprenomcli, -6, SpringLayout.NORTH, chadressecli);
		sl_pan2.putConstraint(SpringLayout.EAST, chprenomcli, -27, SpringLayout.EAST, pan2);
		pan2.add(chprenomcli);
		chprenomcli.setColumns(10);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,432,293); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Ajout client"); //titre de la fenfetre
		
		//ajout des ecouteurs sur les bouton
		qt.addActionListener(this);
		ajout.addActionListener(this);
		rt.addActionListener(this);
		
		//
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Client c=new Client();
		
		if(e.getSource()==ajout)
		{
			Masocket soc=new Masocket();		
			try
			{
				
				Masocket.getOos().writeObject("ajout_cl");
				Masocket.getOos().flush();
				
				c.setNomcli(chnomcli.getText());
				c.setPrenomcli(chprenomcli.getText());
				c.setAdressecli(chadressecli.getText());
				Agence ag=new Agence();
				ag.setCodeag(Integer.parseInt(chcodeag.getText()));
		    	c.setAgence(ag);
		    	  
		    	Masocket.getOos().writeObject(c);
		    	Masocket.getOos().flush();
		    	   
		    	   JOptionPane.showMessageDialog(null, "Opération d'ajout réussie");
		    	   chnomcli.setText("");
		    	   chprenomcli.setText("");
		    	   chadressecli.setText("");
			}catch (Exception ex) {
				
				
			}
			
		}
		
		
		if (e.getSource()==qt)
			{
			
			dispose();
			}
		
		if (e.getSource()==rt)
		{
		new ListeAgence();
		dispose();
		}
		
	}
}
