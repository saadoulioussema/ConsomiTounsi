package tn.esprit.spring;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import tn.esprit.spring.entity.Facture;;

public class GeneratePdfReport {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream FactureReport(Facture f) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(110);
            table.setWidths(new int[]{8,8,8,8,8,8});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA);

            PdfPCell hcell;
            
            hcell = new PdfPCell(new Phrase("Produits",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("PrixUnitaire",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Quantite",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("PrixTotal",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            
            
            hcell = new PdfPCell(new Phrase("PrixLivraison",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("PrixAPayer",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            
                PdfPCell cell;


                String s = new String("");
                for(int i=0;i<f.getPanierDetail().getProductlines().size();i++) {
                	s += f.getPanierDetail().getProductlines().get(i).getProduit().getName()+"\n"+"\n";
                }
                	cell = new PdfPCell(new Phrase(s));
                    cell.setPaddingLeft(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                
                    String s1 = new String("");
                    for(int i=0;i<f.getPanierDetail().getProductlines().size();i++) {
                    	s1 += String.valueOf(f.getPanierDetail().getProductlines().get(i).getProduit().getPrice())+"\n"+"\n";
                    }
                    cell = new PdfPCell(new Phrase(s1));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);
                
                    String s2 = new String("");
                    for(int i=0;i<f.getPanierDetail().getProductlines().size();i++) {
                    	s2 += String.valueOf(f.getPanierDetail().getProductlines().get(i).getQuantité())+"\n"+"\n";
                    }
                    cell = new PdfPCell(new Phrase(s2));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);
                    
                
                    String s3 = new String("");
                    for(int i=0;i<f.getPanierDetail().getProductlines().size();i++) {
                    	s3 += String.valueOf((f.getPanierDetail().getProductlines().get(i).getQuantité())*(f.getPanierDetail().getProductlines().get(i).getProduit().getPrice()))+"\n"+"\n";
                    }
                    cell = new PdfPCell(new Phrase(s3));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);	

                
                cell = new PdfPCell(new Phrase(String.valueOf(f.getFraisDeliv())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingLeft(0);
                table.addCell(cell);
                
                float prix = (f.getPanierDetail().getPrixApayer())+(f.getFraisDeliv());
                cell = new PdfPCell(new Phrase(String.valueOf(prix)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingLeft(0);
                table.addCell(cell);
            
                //Paragraph elements = new Paragraph();
            PdfWriter.getInstance(document, out);
            document.open();
            Paragraph p = new Paragraph("Facture n=:"+f.getIdFacture()+" "+"dans la date:" + f.getPanierDetail().getDatePanier().toString());
            p.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p);
            Paragraph p1 = new Paragraph("------------------------------------------");
                    p1.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p1);
                    Paragraph p3 = new Paragraph("------------------------------------------");
                    p3.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p3);
                    
                    Paragraph p8 = new Paragraph("Nom et Prenom d'Utilisateur:"+f.getPanierDetail().getUser().getUsername());
                    p8.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p8);
                    Paragraph p6 = new Paragraph("------------------------------------------");
                    p6.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p6);
                    Paragraph p7 = new Paragraph("------------------------------------------");
                    p7.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p7);
            
            document.add(table);
            Paragraph p2 = new Paragraph("------------------------------------------");
            p2.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p2);
            Paragraph p4 = new Paragraph("------------------------------------------");
            p4.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p4);
            Paragraph p5 = new Paragraph("#CONSOMMI TOUNSI 619");
            p5.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p5);
            
            document.close();
            
            
        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
