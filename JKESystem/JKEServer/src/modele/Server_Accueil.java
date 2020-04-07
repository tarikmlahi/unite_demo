package modele;

import java.net.*;
import java.util.*;
import java.sql.*;
import javax.swing.*;

import modele.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

import controleur.*;


public class Server_Accueil extends JFrame implements ActionListener
{
	private JLabel l1, lstatut,ldemarre,lmode;
	private JPanel pan1,pan2,pan3,span1,span2;
	private JButton qt;
	private JTextArea zonerecep;
	

 public Server_Accueil()
 {
    
	 	l1=new JLabel("INTERFACE SERVEUR MULTICLIENT");
	 	l1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lstatut=new JLabel("STATUT: ");
		lstatut.setFont(new Font("Segoe UI", Font.BOLD, 11));
		ldemarre=new JLabel("Démarré");
		ldemarre.setFont(new Font("Segoe UI", Font.BOLD, 13));
		ldemarre.setForeground(new Color(0, 153, 0));
		lmode=new JLabel("Mode en cours d'exploitation");
		lmode.setForeground(Color.GRAY);
		pan1=new JPanel();
		span1=new JPanel();
		span2=new JPanel();
		span1.add(l1);
		span2.add(lstatut);
		span2.add(ldemarre);
		
		pan1.add(span1,BorderLayout.NORTH);
		pan1.add(span2, BorderLayout.CENTER);
		
		pan2=new JPanel();
		SpringLayout sl_pan2 = new SpringLayout();
		pan2.setLayout(sl_pan2);
		pan2.add(lmode);
		JScrollPane scrollPane = new JScrollPane();
		sl_pan2.putConstraint(SpringLayout.NORTH, lmode, 3, SpringLayout.SOUTH, scrollPane);
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 5, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 175, SpringLayout.WEST, pan2);
		pan2.add(scrollPane);
		qt= new JButton("Quitter");
		pan3=new JPanel();
		pan3.setBackground(Color.white);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		zonerecep=new JTextArea(15,40);
		zonerecep.setBorder(new LineBorder(UIManager.getColor("Table.selectionBackground")));
		zonerecep.setForeground(new Color(0, 51, 153));
		sl_pan2.putConstraint(SpringLayout.NORTH, zonerecep, 22, SpringLayout.SOUTH, lmode);
		sl_pan2.putConstraint(SpringLayout.WEST, lmode, 0, SpringLayout.WEST, zonerecep);
		sl_pan2.putConstraint(SpringLayout.WEST, zonerecep, 35, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, zonerecep, 393, SpringLayout.WEST, pan2);
		pan2.add(zonerecep);
		zonerecep.setEditable(false);
		zonerecep.setBackground(UIManager.getColor("Table.focusCellBackground"));
		zonerecep.append("  N°    |    Adresse IP  "+"\n");
		zonerecep.append(" ===================="+"\n");
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,438,436);
		setResizable(false);
		setTitle("Interface du serveur");
		qt.addActionListener(this);
		setVisible(true);
     try
    {
        ServerSocket serv = new ServerSocket(8000);
        ldemarre.setText("SERVEUR DEMARRE");
        int numclient=1;
        while(true)
        {
        	 Socket socket=serv.accept();
             
             InetAddress adr = socket.getInetAddress();
             String ip = adr.getHostAddress();
             
            zonerecep.append("  "+numclient+"      |    "+ip+"    "+"\n");
            zonerecep.append(" --------------------------------"+"\n");
            
            Service s = new Service(socket);
            s.start();
            
            numclient++;
            
        }
        
    }
    catch(IOException ex)
    {
        System.out.println(ex.getMessage());
        ldemarre.setText("SERVEUR ARRETE");
        ldemarre.setForeground(Color.red);
    }
   
   
 }
     //class interne
    class Service extends Thread
    {
        Connection con=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        Socket socket;
       // ArrayList <Personne> liste=null;
        public Service(Socket socket)
        {
            this.socket=socket;
          
        }
        @Override
		public void run()
        {
            try
            {
          
           OutputStream os=socket.getOutputStream();
           InputStream is =socket.getInputStream();
           ObjectInputStream ois = new ObjectInputStream(is);
           ObjectOutputStream oos=new ObjectOutputStream(os);
           String mode;
           do
           {
            mode =(String)ois.readObject();
           lmode.setText("Mode en cours d'expoitation!!!: "+mode);
           Data da=new Data();
             if (mode.equals("ajout_ag"))
             {
            	 Agence a = (Agence) ois.readObject();
            	 da.AjoutAgence(a);
            	 
             }
             if (mode.equals("ajout_cl"))
             {
            	 Client c = (Client) ois.readObject();
            	 da.AjoutClient(c);
            	 
             }
             if (mode.equals("ajout_cp"))
             {
            	 Compte c= (Compte) ois.readObject();
            	 da.AjoutCompte(c);
            	 
             }
             if (mode.equals("ajout_op"))
             {
            	 Operation o = (Operation) ois.readObject();
            	 da.AjoutOperation(o);
             }
             if (mode.equals("modif_solde"))
             {
            	           	 
            	 Compte cc = (Compte) ois.readObject();
            	da.ModifierCompte(cc);
            	 
             }
            
                if (mode.equals("liste_ag"))
                {
                	ArrayList<Agence> liste = new ArrayList<Agence>();
                	liste=da.ListeAgence();
                	oos.writeObject(liste);
                    oos.flush();  
                }
                
                if (mode.equals("liste_cl"))
                {
                	ArrayList<Client> liste = new ArrayList<Client>();
                	ArrayList<Client> listebon = new ArrayList<Client>();
                	liste=da.ListeClient();
                	for(int i=0; i<liste.size(); i++)
                	{
                		Client  c = liste.get(i);
                		Agence ag=da.RechercheAgence(c.getAgence().getCodeag());
                		c.setAgence(ag);
                		listebon.add(c);
                	}
                	oos.writeObject(listebon);
                    oos.flush();  
                }
                
                
                if (mode.equals("liste_cl_ag"))
                {
                	ArrayList<Client> liste = new ArrayList<Client>();
                	String codeag =(String)ois.readObject();
                	liste=da.ListeClientParAgence(Integer.parseInt(codeag));
                	oos.writeObject(liste);
                    oos.flush();  
                }
                
                if (mode.equals("liste_cp"))
                {
                	ArrayList<Compte> liste = new ArrayList<Compte>();
                	liste=da.ListeCompte();
                	oos.writeObject(liste);
                    oos.flush();  
                }
                
                if (mode.equals("liste_cp_cli"))
                {
                	ArrayList<Compte> liste = new ArrayList<Compte>();
                	String codecli =(String)ois.readObject();
                	liste=da.ListeCompteClient(Integer.parseInt(codecli));
                	oos.writeObject(liste);
                    oos.flush();  
                }
                
                if (mode.equals("recherche_num_cp"))
                {
                	String numcp = (String) ois.readObject();
                	Compte c=da.RechercheCompte(numcp);
                	oos.writeObject(c);
                    oos.flush();  
                }
                
                if (mode.equals("relevet"))
                {
                	ArrayList<Operation> liste = new ArrayList<Operation>();
                	String numcp =(String)ois.readObject();
                	
                	liste=da.RechercheOperation(numcp);
                	oos.writeObject(liste);
                    oos.flush();  
                }
                
                if (mode.equals("Recherche_ag"))
                {
                	Agence a = (Agence) ois.readObject();
                	a=da.RechercheAgence(a.getCodeag());
                	oos.writeObject(a);
                    oos.flush();  
                }
                
                if (mode.equals("Recherche_cli"))
                {
                	String codecl = (String) ois.readObject();
                	Client cl=new Client();
                	cl=da.RechercheClient(Integer.parseInt(codecl));
                	
                	if(cl!=null)
                	{	
                		
                	Agence a = new Agence();
                	a=da.RechercheAgence(cl.getAgence().getCodeag());
                	cl.setAgence(a);
                	oos.writeObject(cl);
                    oos.flush();  
                	}else
                	{  
                		
                		Client cli=new Client();
                		cli.setAdressecli(" ");
                		cli.setCodecli(0);
                		cli.setNomcli(" ");
                		cli.setPrenomcli("Client innexistant");
                		Agence a=new Agence();
                		a.setCodeag(0);
                		cli.setAgence(a);
                		oos.writeObject(cli);
                        oos.flush();
                	}
                }
                
                 if (mode.equals("fin"))
                 {
                	 lmode.setText("Connexion terminee!!! pour un client");
                      oos.flush();
                      
                     
                 }
             
           }
             while(true);
           
            }
            catch(Exception ex)
            {
                System.out.println("****"+ex.getMessage());
            }
          
            

    }
 }//fin classe interne

  @Override
public void actionPerformed(ActionEvent e)
    {
        dispose();
        System.exit(0);
    }



    public static void main(String args[])
    {
        new Server_Accueil();
    }
}
