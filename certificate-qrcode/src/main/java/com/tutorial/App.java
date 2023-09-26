package com.tutorial;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
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
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException, WriterException {

        String dataQR = "https://skilvul.com/";
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
        Paragraph prodiMhs = new Paragraph("Program Studi Ilmu Goib")
                .setFont(fontReg).setFixedPosition(170, 290, 500).setTextAlignment(TextAlignment.CENTER).setFontSize(15f);;
        Paragraph rektorName = new Paragraph("Treatan Muslim, S.PI., M.P")
                .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(85, 85, 300);
        Paragraph rektorNIDN = new Paragraph("333Xxxxxxxx")
                .setFont(fontReg).setBold().setFixedPosition(195, 60, 120);
        Paragraph kepalaName = new Paragraph("Dr. Coki Pardede, S.Pd.i., M.Ag")
                .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(465, 85, 300);
        Paragraph kepalaNIDN = new Paragraph("666Xxxxxxxx")
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

        String address = "courses";
        String qrLoc = "./gambar/qr.jpg";

        String qrResult = "https://www.youtube.com/watch?v=R5_6Eihny_c&ab_channel=Qorygore";
        BitMatrix matrix = new MultiFormatWriter()
                .encode(qrResult, BarcodeFormat.QR_CODE, 250, 250);

        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(qrLoc));

        ImageData qrImage = ImageDataFactory.create("./gambar/qr.jpg");
        Image qrAcutualImage = new Image(qrImage);
        qrAcutualImage.scaleAbsolute(100, 100);
        qrAcutualImage.setFixedPosition(371, 135);


        doc.add(mhsName);
        doc.add(prodiMhs);
        doc.add(rektorName);
        doc.add(rektorNIDN);
        doc.add(kepalaName);
        doc.add(kepalaNIDN);
        doc.add(tanggalIslam);
        doc.add(tanggalMasehi);
        doc.add(image);
        doc.add(qrAcutualImage);
//    doc.add(temp);

        doc.close();
    }
}
