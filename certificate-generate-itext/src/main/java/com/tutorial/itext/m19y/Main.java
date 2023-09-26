package com.tutorial.itext.m19y;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {


    String path = "fix.pdf";
    String dest = "output.pdf";

    FontProgram fontProgram = FontProgramFactory.createFont("KaushanScript-Regular.ttf");
    FontProgram fontku = FontProgramFactory.createFont("OpenSans-VariableFont_wdth,wght.ttf");
    PdfFont font = PdfFontFactory.createFont(
            fontProgram, PdfEncodings.WINANSI, true);
    PdfFont fontReg = PdfFontFactory.createFont(
            fontku, PdfEncodings.WINANSI, true);
    PdfReader reader = new PdfReader(path);
    PdfWriter writer = new PdfWriter(dest);

    PdfDocument document = new PdfDocument(reader, writer);

    Paragraph mhsName = new Paragraph("Muhammad Arsil Alhbasy")
            .setTextAlignment(TextAlignment.CENTER).setFont(font).setFontSize(40).setFixedPosition(170,310,500);
    Paragraph prodiMhs = new Paragraph("Program Studi Ilmu Hukum")
            .setFont(fontReg).setFixedPosition(170, 290, 500).setTextAlignment(TextAlignment.CENTER).setFontSize(15f);;
    Paragraph rektorName = new Paragraph("Amir Mahmud, S.PI., M.P")
            .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(85, 85, 300);
    Paragraph rektorNIDN = new Paragraph("085336421911")
            .setFont(fontReg).setBold().setFixedPosition(195, 60, 120);
    Paragraph kepalaName = new Paragraph("Dr. Mualimah, S.Pd.i., M.Ag")
            .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(465, 85, 300);
    Paragraph kepalaNIDN = new Paragraph("085336421911")
            .setFont(fontReg).setBold().setFixedPosition(575, 60, 120);
    Paragraph tanggalIslam = new Paragraph("13 Rabiul Awal 1445 H")
            .setFont(fontReg).setFixedPosition(650, 234, 150);
    Paragraph tanggalMasehi = new Paragraph("13 Desember 2023 M")
            .setFont(fontReg).setFixedPosition(650, 215, 150);

    // tanda tanggan
    ImageData signature = ImageDataFactory.create("signature.png");
    Image image = new Image(signature);
    image.scaleAbsolute(210, 100);
    image.setFixedPosition(120, 80);


    Table temp = new Table(new float[]{150});
    temp.addCell(new Cell().add("Program Studi Ilmu Hukum"));
    temp.setFixedPosition(170, 290, 500).setTextAlignment(TextAlignment.CENTER).setFontSize(18f);
    Document doc = new Document(document);

    doc.add(mhsName);
    doc.add(prodiMhs);
    doc.add(rektorName);
    doc.add(rektorNIDN);
    doc.add(kepalaName);
    doc.add(kepalaNIDN);
    doc.add(tanggalIslam);
    doc.add(tanggalMasehi);
    doc.add(image);
//    doc.add(temp);

    doc.close();
  }
}
