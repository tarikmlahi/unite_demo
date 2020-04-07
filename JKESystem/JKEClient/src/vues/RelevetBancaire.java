package vues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import modele.AutreMethode;
import modele.Masocket;
import modele.Operation;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelevetBancaire extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1,lnum,lsoldt,lsenst;
	private JTextField chsoldt;
	private JPanel pan1,pan2,pan3;
	private JButton qt;
	private JTable table;
	private Vector <String> colonnes ;
	private Vector <String> tuple;
	private Vector <Vector>  lignes;
	ArrayList<Operation> liste = null;
	private int total=0;
	private int totalcredit=0;
	private int toaldebit=0;

	public RelevetBancaire(final String numcpt)
	{
		lnum=new JLabel("Numéro: "+numcpt);
		lnum.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lnum.setForeground(Color.red);
		l1=new JLabel("RELEVET BAINCAIRE DU COMPTE "+numcpt);
		final String titre="RELEVET BAINCAIRE DU COMPTE "+numcpt;
		l1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		l1.setForeground(Color.blue);
		lsoldt=new JLabel("TOTAL");
		lsenst=new JLabel("CREDITEUR");
		chsoldt=new JTextField(10);
		chsoldt.setFont(new Font("Segoe UI", Font.BOLD, 12));
		chsoldt.setEditable(false);
		chsoldt.setBackground(new Color(255, 255, 204));
		chsoldt.setForeground(Color.RED);
		chsoldt.setText("0000");
		pan1=new JPanel();
		pan1.setBackground(Color.white);
		pan1.add(l1);
		pan1.add(lnum);
		
		pan2=new JPanel();
		colonnes = new Vector<String>();
		colonnes.add("N° opération");
		colonnes.add("Libellé");
		colonnes.add("Sens");
		colonnes.add("Montant");
		colonnes.add("Date");
		
		lignes = new Vector <Vector>();
		Masocket soc=new Masocket();
		
		try {
			
			Masocket.getOos().writeObject("relevet");
			Masocket.getOos().flush();
			
			Masocket.getOos().writeObject(String.valueOf(numcpt));
	    	Masocket.getOos().flush();
	    	
	        ArrayList<Operation> liste=(ArrayList<Operation>)Masocket.getOis().readObject();
		
		for (int i=0;i<liste.size();i++)
		{
			tuple= new Vector <String>();
			Operation  c = liste.get(i);
			tuple.add(String.valueOf(c.getNumop()));
			tuple.add(c.getLibop());
			tuple.add(c.getSensop());
			tuple.add(String.valueOf(c.getMontantop()));
			
			AutreMethode at=new AutreMethode();
			tuple.add(at.AngToFr(c.getDateop()));
			lignes.add(tuple);
			if(c.getSensop()=="CR")
			{
				totalcredit=totalcredit+c.getMontantop();
			}else
			{
				toaldebit=toaldebit+c.getMontantop();
			}
			
			total=total+c.getMontantop();
		}
		
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		chsoldt.setText(total+"");
		//verification du sense 
		
		if(toaldebit>totalcredit)
		{
			lsenst.setText("SOLDE: DEBITEUR");
		}else
		{
			lsenst.setText("SOLDE: CREDITEUR");
		}
		SpringLayout sl_pan2 = new SpringLayout();
		pan2.setLayout(sl_pan2);
		table = new JTable(lignes,colonnes);
		table.setBackground(Color.white);
		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane(table);
		sl_pan2.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, pan2);
		sl_pan2.putConstraint(SpringLayout.SOUTH, scrollPane, 211, SpringLayout.NORTH, pan2);
		sl_pan2.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, pan2);
		pan2.add(scrollPane);
		pan2.setBackground(Color.white);
		qt= new JButton("Quitter");
		
		pan3=new JPanel();
		pan3.setBackground(Color.white);
		pan3.add(lsoldt);
		pan3.add(chsoldt);
		pan3.add(lsenst);
		
		JButton btnImprimerLeRelevet = new JButton("Imprimer");
		btnImprimerLeRelevet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//CREATION DU FICHIER PDF
				try {
				Document document = new Document();
				PdfWriter.getInstance(document,
				new FileOutputStream("Relevet.pdf"));
				document.open();
				PdfPTable table = new PdfPTable(5);
				PdfPCell cell = new PdfPCell(new Paragraph(titre));
				cell.setColspan(5);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 120));
				cell.setPadding(10.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("OPERATION"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 140));
				cell.setPadding(2.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("LIBELLE"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 140));
				cell.setPadding(5.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("SENSE"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 140));
				cell.setPadding(5.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("MONTANT"));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 255));
				cell.setPadding(5.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph("DATE "));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 140));
				cell.setPadding(5.0f);
				table.addCell(cell);
				
				
				
				
				Masocket.getOos().writeObject("relevet");
				Masocket.getOos().flush();
				
				Masocket.getOos().writeObject(String.valueOf(numcpt));
		    	Masocket.getOos().flush();
		    	
		        ArrayList<Operation> liste=(ArrayList<Operation>)Masocket.getOis().readObject();
		        
				for (int i=0;i<liste.size();i++)
				{
					
					Operation  c = liste.get(i);
					table.addCell(String.valueOf(c.getNumop()));
					table.addCell(c.getLibop());
					table.addCell(c.getSensop());
					table.addCell(String.valueOf(c.getMontantop()));
					
					AutreMethode at=new AutreMethode();
					table.addCell(at.AngToFr(c.getDateop()));
					
					if(c.getSensop()=="CR")
					{
						totalcredit=totalcredit+c.getMontantop();
					}else
					{
						toaldebit=toaldebit+c.getMontantop();
					}
					
					total=total+c.getMontantop();
				}
				
				cell = new PdfPCell(new Paragraph("TOTAL"));
				cell.setColspan(3);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(10.0f);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Paragraph(total+""));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new BaseColor(193,255, 255));
				cell.setPadding(5.0f);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(""));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPadding(5.0f);
				table.addCell(cell);
				
					document.add(table);
					document.close();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				//FIN CREATION
				try {
					java.awt.Desktop.getDesktop().open(new File("Relevet.pdf"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		pan3.add(btnImprimerLeRelevet);
		pan3.add(qt);
		
		getContentPane().add(pan1,BorderLayout.NORTH);
		getContentPane().add(pan2,BorderLayout.CENTER);
		getContentPane().add(pan3,BorderLayout.SOUTH);
		
		setBounds(200,80,533,310);
		setResizable(false);
		setTitle("Relevet bancaire");
		
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
