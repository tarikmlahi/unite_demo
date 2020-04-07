package vues;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


import modele.*;

import javax.swing.border.LineBorder;

public class AjoutCompte extends JFrame implements ActionListener
{
	/**
	 * @param Declaration des paramtere
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lcodecli,lnomag,lnumcpt,llibcpt,lsenscpt,lsoldecpt;
	private JTextField chcodcli,chnomag,chnumcpt,chlibcpt,chsolde;
	private JComboBox chsens;
	private JPanel pan1,pan2,pan3;
	private JButton qt,ajout;
	
	//constructeur
	public AjoutCompte(String code, String nom)
	{
		//declaration des interface libelle et champ de saisie
		l1=new JLabel("FORMULAIRE D'AJOUT D'UN COMPTE");
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lsoldecpt=new JLabel("Solde initial");
		lsoldecpt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lcodecli=new JLabel("Code client");
		lcodecli.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnomag=new JLabel("Nom agence client");
		lnomag.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lnumcpt=new JLabel("Numéro de compte");
		lnumcpt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		llibcpt=new JLabel("Libelle compte");
		llibcpt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lsenscpt=new JLabel("Sense du compte");
		lsenscpt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		chcodcli=new JTextField(30);
		chcodcli.setEnabled(false);
		chcodcli.setText(code);
		chcodcli.setBackground(UIManager.getColor("Button.light"));
		chnomag=new JTextField(30);
		chnomag.setBackground(UIManager.getColor("Button.light"));
		chnomag.setText(nom);
		chnomag.setEnabled(false);
		chnumcpt=new JTextField(30);
		chnumcpt.setEditable(false);
		chnumcpt.setBackground(Color.white);
		chsolde=new JTextField(30);
		chlibcpt=new JTextField(30);
		chsens = new JComboBox();
		chsens.setBackground(Color.white);
		chsens.addItem("DB");
		chsens.addItem("CR");
		
		ajout=new JButton("Ajouter ");
		qt= new JButton("Quitter");
		pan3=new JPanel();
		
		//les panaux
		pan1=new JPanel();
		pan2=new JPanel();
		pan2.setBorder(new LineBorder(Color.WHITE));
		
		//ajout d'un ecouteur sur le champ numero de compte
		chnumcpt.addMouseListener(new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			//génération du numero de compte aleatoirement dés qu'on clique sur le champ de saisi
			int random = (int) (Math.random()*100000000);
			chnumcpt.setText(random+"");
		}
		@Override
		public void mouseEntered(MouseEvent e) {	}
		@Override
		public void mouseExited(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
	});
		//ajout des elements  libelle et champ de saisie sur les panaux
		pan1.add(l1);
		SpringLayout sl_pan2 = new SpringLayout();
		sl_pan2.putConstraint(SpringLayout.NORTH, lsoldecpt, 3, SpringLayout.NORTH, chsolde);
		sl_pan2.putConstraint(SpringLayout.WEST, lsoldecpt, 33, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lsoldecpt, 0, SpringLayout.EAST, lcodecli);
		sl_pan2.putConstraint(SpringLayout.NORTH, lsenscpt, 2, SpringLayout.NORTH, chsens);
		sl_pan2.putConstraint(SpringLayout.WEST, lsenscpt, 0, SpringLayout.WEST, lcodecli);
		sl_pan2.putConstraint(SpringLayout.EAST, lsenscpt, -2, SpringLayout.WEST, chsens);
		sl_pan2.putConstraint(SpringLayout.NORTH, chlibcpt, 105, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chlibcpt, -8, SpringLayout.NORTH, chsens);
		sl_pan2.putConstraint(SpringLayout.EAST, chsens, 0, SpringLayout.EAST, chcodcli);
		sl_pan2.putConstraint(SpringLayout.WEST, llibcpt, 33, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, lnumcpt, 33, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, lcodecli, 33, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, lcodecli, 0, SpringLayout.WEST, chcodcli);
		sl_pan2.putConstraint(SpringLayout.WEST, lnomag, 33, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lcodecli, 4, SpringLayout.NORTH, chcodcli);
		sl_pan2.putConstraint(SpringLayout.WEST, chsolde, 197, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chsens, 197, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chlibcpt, 197, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chnumcpt, 197, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chnomag, 197, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, llibcpt, 0, SpringLayout.WEST, chlibcpt);
		sl_pan2.putConstraint(SpringLayout.EAST, lnumcpt, 0, SpringLayout.WEST, chnumcpt);
		sl_pan2.putConstraint(SpringLayout.EAST, lnomag, 0, SpringLayout.WEST, chnomag);
		sl_pan2.putConstraint(SpringLayout.EAST, chnomag, -40, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnomag, 4, SpringLayout.NORTH, chnomag);
		sl_pan2.putConstraint(SpringLayout.EAST, chnumcpt, -40, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, lnumcpt, 4, SpringLayout.NORTH, chnumcpt);
		sl_pan2.putConstraint(SpringLayout.EAST, chlibcpt, -40, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, llibcpt, 4, SpringLayout.NORTH, chlibcpt);
		sl_pan2.putConstraint(SpringLayout.EAST, chsolde, -40, SpringLayout.EAST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chcodcli, 18, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, chcodcli, 0, SpringLayout.WEST, chnomag);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chcodcli, -6, SpringLayout.NORTH, chnomag);
		sl_pan2.putConstraint(SpringLayout.EAST, chcodcli, 394, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnomag, 47, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnomag, -6, SpringLayout.NORTH, chnumcpt);
		sl_pan2.putConstraint(SpringLayout.NORTH, chnumcpt, 76, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chnumcpt, -6, SpringLayout.NORTH, chlibcpt);
		sl_pan2.putConstraint(SpringLayout.NORTH, chsens, 134, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chsens, -6, SpringLayout.NORTH, chsolde);
		sl_pan2.putConstraint(SpringLayout.NORTH, chsolde, 163, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, chsolde, -10, SpringLayout.SOUTH, pan2);
		pan2.setLayout(sl_pan2);
		pan2.add(lcodecli);
		pan2.add(chcodcli);
		pan2.add(lnomag);
		pan2.add(chnomag);
		pan2.add(lnumcpt);
		pan2.add(chnumcpt);
		pan2.add(llibcpt);
		pan2.add(chlibcpt);
		pan2.add(lsenscpt);
		pan2.add(chsens);
		pan2.add(lsoldecpt);
		pan2.add(chsolde);
		pan3.add(ajout);
		pan3.add(qt);
		
		
		//ajout des panaux sur la fenetre
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		//preparation de la fenetre
		setBounds(200,200,440,290); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Ajout compte"); //titre de la fenfetre
		
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
		Compte cp=new Compte();
		Operation o=new Operation();
		
		if(e.getSource()==ajout)
		{
			try
			{
			
			Masocket.getOos().writeObject("ajout_cp");
			Masocket.getOos().flush();
			
			cp.setCodecpt(chnumcpt.getText());
			cp.setLibcpt(chlibcpt.getText());
			cp.setSenscpt(chsens.getSelectedItem().toString());
			cp.setSoldcpt(Integer.parseInt(chsolde.getText()));
			Client cl=new  Client();
			cl.setCodecli(Integer.parseInt(chcodcli.getText()));
			cp.setClient(cl);
			Masocket.getOos().writeObject(cp);
	    	Masocket.getOos().flush();
			
			
			Masocket.getOos().writeObject("ajout_op");
			Masocket.getOos().flush();
			JOptionPane.showMessageDialog(null, "Le Compte N° "+chnumcpt.getText()+" a été ajouté");
			
			o.setLibop("Ouverture de compte");
			o.setSensop(chsens.getSelectedItem().toString());
			o.setMontantop(Integer.parseInt(chsolde.getText()));
			o.setCompte(cp);
			
			String format = "yyyy-MM-dd H:mm:ss"; 
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
			java.util.Date date = new java.util.Date(); 
			
			o.setDateop(formater.parse(formater.format( date )));
			
			Masocket.getOos().writeObject(o);
	    	Masocket.getOos().flush();
	    	JOptionPane.showMessageDialog(null, "Un opération d'ouverture de compte a été effectué");
			
	    	chnumcpt.setText("");
			chlibcpt.setText("");
			chsolde.setText("");
			
			}catch (Exception ex) {
							
							
						}
			dispose();
		}
		
		
		if (e.getSource()==qt)
			{
			
			dispose();
			}
		
		
	}
}
