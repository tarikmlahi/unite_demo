/**
 * Package et importaiton
 */
package vues;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import modele.Masocket;
/**
 * @author Mohamed
 *
 */
public  class Accueil extends JFrame implements ActionListener
{
	
	
	/**
	 * @param Declaration des parametre
	 */
	private JMenuBar Menuprincipal;
	private JMenu mfich, magence,mclient,mcompte,mapropos,sep1,sep2,sep3,sep4;//les menus
	private JMenuItem quitter, aprop;//les item du menu fichier
	private JMenuItem creer_ag,lister_ag;//les item du menu agence
	private JMenuItem liste_cli,liste_cli_ag;//les item du menu client
	private JMenuItem liste_cpt,liste_cpt_cli,relevet_cpt;//les item du menu client
	private JLabel luse, lblEtatDuServeur ;
	private JPanel pan;
	
	
	
	/**
	 * @param args
	 */
	public Accueil()
	{	
		Masocket soc=new Masocket();
		String serv=soc.getAdrserver();
		setResizable(false);
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		//Création du Menu principale
		Menuprincipal = new JMenuBar() ;
		setJMenuBar(Menuprincipal);
		sep1=new JMenu("|");
		sep2=new JMenu("|");
		sep3=new JMenu("|");
		sep4=new JMenu("|");
		sep1.setForeground(Color.GRAY);
		sep2.setForeground(Color.GRAY);
		sep3.setForeground(Color.GRAY);
		mfich=new JMenu("Fichier");
		magence=new JMenu("Agence");
		mclient=new JMenu("Client");
		mcompte=new JMenu("Compte");
		mapropos=new JMenu("?");
		Menuprincipal.add(mfich);
		Menuprincipal.add(sep1);
		Menuprincipal.add(magence);
		Menuprincipal.add(sep2);
		Menuprincipal.add(mclient);
		Menuprincipal.add(sep3);
		Menuprincipal.add(mcompte);
		Menuprincipal.add(sep4);
		Menuprincipal.add(mapropos);
		//sous menu fichier
		quitter=new JMenuItem ("Quitter") ;
		aprop=new JMenuItem("Apropos");
		mfich.add(quitter);
		mapropos.add(aprop);
		//sousmenu agence
		creer_ag=new JMenuItem ("Créer un agence") ;
		lister_ag=new JMenuItem ("Liste des agences") ;
		magence.add(creer_ag);
		magence.add(lister_ag);
		//sousmenu client
		liste_cli=new JMenuItem ("Liste des clients") ;
		liste_cli_ag=new JMenuItem ("Liste des clients par agence") ;
		mclient.add(liste_cli);
		mclient.add(liste_cli_ag);
		
		//sousmenu compte
		liste_cpt=new JMenuItem ("Liste des comptes") ;
		liste_cpt_cli=new JMenuItem ("Liste des comptes d'un client") ;
		relevet_cpt=new JMenuItem ("Relevet compte bancaire") ;
		mcompte.add(liste_cpt);
		mcompte.add(liste_cpt_cli);
		mcompte.add(relevet_cpt);
		
		luse=new JLabel("PREJET DE JAVA MISE EN PLACE D'UN INTRANET");
		luse.setForeground(Color.GRAY);
		pan=new JPanel();
		pan.setBackground(Color.WHITE);
		pan.add(luse);
		
		
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, pan, 160, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, pan, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, pan, 488, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		
		getContentPane().add(pan);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Masocket soc=new Masocket();
				try
				{
				 Masocket.getOos().writeObject("fin");
				 Masocket.getOos().flush();
				dispose();
				System.exit(0);
			    }
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				};
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnQuitter, -6, SpringLayout.NORTH, pan);
		springLayout.putConstraint(SpringLayout.EAST, btnQuitter, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnQuitter);
		
		JPanel panetat = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panetat, -23, SpringLayout.NORTH, pan);
		springLayout.putConstraint(SpringLayout.WEST, panetat, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panetat, -8, SpringLayout.NORTH, pan);
		springLayout.putConstraint(SpringLayout.EAST, panetat, 34, SpringLayout.WEST, getContentPane());
		panetat.setBackground(Color.RED);
		getContentPane().add(panetat);
		
		lblEtatDuServeur = new JLabel("Etat du serveur");
		lblEtatDuServeur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String adr=JOptionPane.showInputDialog(null, "Veuillez saisir l'dresse du serveur");
				Masocket soc=new Masocket();
				soc.setAdrserver(adr);
				new Accueil();
				dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, lblEtatDuServeur, 14, SpringLayout.EAST, panetat);
		springLayout.putConstraint(SpringLayout.SOUTH, lblEtatDuServeur, -6, SpringLayout.NORTH, pan);
		getContentPane().add(lblEtatDuServeur);
		
		
		//ajout des ecouteurs sur les sous menus
		quitter.addActionListener(this);
		creer_ag.addActionListener(this);
		lister_ag.addActionListener(this);
		liste_cli.addActionListener(this);
		liste_cli_ag.addActionListener(this);
		liste_cpt.addActionListener(this);
		liste_cpt_cli.addActionListener(this);
		relevet_cpt.addActionListener(this);
		aprop.addActionListener(this);
		
		setBounds(200,200,500,240);
		setTitle("Bienvenue");
		setVisible(true);
		try
		{
			Masocket.setSocket(new Socket(serv,8000));
		     Masocket.setIs(Masocket.getSocket().getInputStream());
		     Masocket.setOs(Masocket.getSocket().getOutputStream());
		     Masocket.setOos(new ObjectOutputStream(Masocket.getOs()));
		     Masocket.setOis(new ObjectInputStream(Masocket.getIs()));
		     panetat.setBackground(new Color(0, 153, 0));
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			panetat.setBackground(Color.RED);
			lblEtatDuServeur.setText("Cliquez ici pour changer de serveur");
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
		if (e.getSource()==creer_ag)
		{
		 new AjoutAgence();
		}
		
		if (e.getSource()==lister_ag)
		{
			
		 new ListeAgence();
		}
		if (e.getSource()==liste_cli)
		{
			
		 new ListeClient();
		}
		
		if (e.getSource()==liste_cli_ag)
		{
			
		 new SelectionAgence();
		}
		if (e.getSource()==liste_cpt)
		{
			
		 new ListeCompte();
		}
		if(e.getSource()==liste_cpt_cli)
		{
			
			new RechercheClient();
		
		}
		if(e.getSource()==relevet_cpt)
		{
			new RechercheCompte();
			
		}
		
		if(e.getSource()==aprop)
		{
			new Apropos();
		}
		if (e.getSource()==quitter)
			{
			
			System.exit(0);
			
		   
		   }
		 
		
	}
	public static void main(String[] args) {
		new Accueil();
	}
}
