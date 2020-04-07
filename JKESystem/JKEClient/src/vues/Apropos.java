package vues;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Apropos extends JFrame 
{
	
	
	//constructeur
	public Apropos()
	{
		
		//preparation de la fenetre
		setBounds(200,200,454,173); //dimension et position
		setResizable(false); //refuser l'agrandisement de la fenetre
		setTitle("Apropos"); //titre de la fenfetre
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("PROJET DE FIN DE FORMATION POUR LE MODULE JAVA SE ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 26, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 26, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);
		
		JLabel lblRalisEnJava = new JLabel("R\u00E9alis\u00E9 en Java TCP/IP avec MySQL et Hibernate ");
		springLayout.putConstraint(SpringLayout.NORTH, lblRalisEnJava, 6, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblRalisEnJava, 0, SpringLayout.WEST, lblNewLabel);
		getContentPane().add(lblRalisEnJava);
		
		JLabel lblParMohamedHassani = new JLabel("Par Mohamed Hassani HANAFI");
		lblParMohamedHassani.setForeground(new Color(0, 51, 102));
		springLayout.putConstraint(SpringLayout.NORTH, lblParMohamedHassani, 10, SpringLayout.SOUTH, lblRalisEnJava);
		springLayout.putConstraint(SpringLayout.WEST, lblParMohamedHassani, 0, SpringLayout.WEST, lblNewLabel);
		getContentPane().add(lblParMohamedHassani);
		
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, btnFermer, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnFermer, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnFermer);
		
		JLabel lblMasterIiGnie = new JLabel("Master II g\u00E9nie logiciel     E-mail:skullbox@hotmail.fr");
		lblMasterIiGnie.setFont(new Font("Segoe UI", Font.ITALIC, 9));
		springLayout.putConstraint(SpringLayout.NORTH, lblMasterIiGnie, 6, SpringLayout.SOUTH, lblParMohamedHassani);
		springLayout.putConstraint(SpringLayout.WEST, lblMasterIiGnie, 0, SpringLayout.WEST, lblNewLabel);
		lblMasterIiGnie.setForeground(new Color(0, 51, 102));
		getContentPane().add(lblMasterIiGnie);
		
		
		//
		setVisible(true);
	}
}
