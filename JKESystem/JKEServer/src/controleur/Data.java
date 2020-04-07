/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;
import modele.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import java.util.*;
/**
 *
 * @author Satellite
 */
public class Data
{
  Session session1 = null;
    Transaction tx=null;
    public ArrayList<Agence> liste_ag=null;
    public ArrayList<Client> liste_cl=null;
    public ArrayList<Compte> liste_cp=null;
    public ArrayList<Operation> liste_op=null;

    public void debutTransaction()
    {
        session1=HibernateUtil.currentSession();
        tx=session1.beginTransaction();

    }
    public void finTransaction()
    {
        tx.commit();
        HibernateUtil.closeSession();
    }
    //AGENCE
    //Ajout
    public void AjoutAgence(Agence user)
    {
        debutTransaction();
        session1.save(user);
        finTransaction();
    }
    //Mise a jour
    public void ModifierAgence(Agence user)
    {
        debutTransaction();
        session1.update(user);
          finTransaction();
    }
//lister
    public ArrayList<Agence> ListeAgence()
    {
        debutTransaction();
        liste_ag=(ArrayList<Agence>)session1.createQuery("from Agence u order by u.id").list();
        finTransaction();
        return liste_ag;

    }
    //Rechercher
    public Agence RechercheAgence(int id)
    {
      debutTransaction();
       Agence user=null;
       user=(Agence)session1.get(Agence.class,new Integer(id));
       finTransaction();
        return user;

    }
    
    //CLIENT
    //Ajout
    public void AjoutClient(Client user)
    {
        debutTransaction();
        session1.save(user);
        finTransaction();
    }
    //Mise a jour
    public void ModifierClient(Client user)
    {
        debutTransaction();
        session1.update(user);
          finTransaction();
    }
//lister
    public ArrayList<Client> ListeClient()
    {
        debutTransaction();
        liste_cl=(ArrayList<Client>)session1.createQuery("from Client u order by u.id").list();
        finTransaction();
        return liste_cl;

    }
    //Rechercher
    public Client RechercheClient(int id)
    {
      debutTransaction();
       Client user=null;
       user=(Client)session1.get(Client.class,new Integer(id));
       finTransaction();
        return user;

    }
    //Recherche client apr agence
    public ArrayList<Client> ListeClientParAgence(int codeag)
    {
    	Client c=null;
    	debutTransaction();
        ArrayList<Client> listec=(ArrayList<Client>)session1.createQuery("from Client u order by u.id").list();
        ArrayList<Client> listebon = new ArrayList<Client>();
        finTransaction();
        for(int i=0; i<listec.size(); i++)
        {
        	c=listec.get(i);
        	if(c.getAgence().getCodeag()==codeag)
        	{
        		listebon.add(c);
        	}
        }
        return listebon;
        
    }
    

     //COMPTE
    //Ajout
    public void AjoutCompte(Compte user)
    {
        debutTransaction();
        session1.save(user);
        finTransaction();
    }
    //Mise a jour
    public void ModifierCompte(Compte user)
    {
        debutTransaction();
        session1.update(user);
          finTransaction();
    }
//lister
    public ArrayList<Compte> ListeCompte()
    {
        debutTransaction();
        liste_cp=(ArrayList<Compte>)session1.createQuery("from Compte u order by u.id").list();
        finTransaction();
        return liste_cp;

    }
    //Rechercher
    public Compte RechercheCompte(String id)
    {
      debutTransaction();
       Compte user=null;
       user=(Compte)session1.get(Compte.class,new String(id));
       finTransaction();
        return user;

    }
    //Recherche Compte apr agence
    public ArrayList<Compte> ListeCompteClient(int codecli)
    {
    	Compte c=null;
    	debutTransaction();
        ArrayList<Compte> listec=(ArrayList<Compte>)session1.createQuery("from Compte u order by u.id").list();
        
        finTransaction();
        for(int i=0; i<listec.size(); i++)
        {
        	c=listec.get(i);
        	if(c.getClient().getCodecli()!=codecli)
        	{
        		listec.remove(i);
        	}
        }
        return listec;
        
    }

//OPERATION
    //Ajout
    public void AjoutOperation(Operation user)
    {
        debutTransaction();
        session1.save(user);
        finTransaction();
    }
    //Mise a jour
    public void ModifierOperation(Operation user)
    {
        debutTransaction();
        session1.update(user);
          finTransaction();
    }
//lister
    public ArrayList<Operation> ListeOperation()
    {
        debutTransaction();
        liste_op=(ArrayList<Operation>)session1.createQuery("from Operation u order by u.id").list();
        finTransaction();
        return liste_op;

    }
 
    //Recherche Operation compte
    public ArrayList<Operation> RechercheOperation(String codecpt)
    {
    	Operation o=null;
    	debutTransaction();
        ArrayList<Operation> listec=(ArrayList<Operation>)session1.createQuery("from Operation u order by u.id").list();
        ArrayList<Operation> listeop = new ArrayList<Operation>();
        finTransaction();
        for(int i=0; i<listec.size(); i++)
        {
        	o=listec.get(i);
        	if(o.getCompte().getCodecpt().equals(codecpt))
        	{
        		
        		listeop.add(o);
        	}
        }
        return listeop;
        
    }
    

}


