package com.sertifiakt.demo;

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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class CertificateService {


  private final UserRepository repo;

  @Autowired
  public CertificateService(UserRepository repo) {
    this.repo = repo;
  }

  public byte[] getCertificate(CreateCertificateRequest request) throws IOException {

    User mhs = repo.findById(request.getId())
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan!"));
    User rektor = repo.findByRole("REKTOR")
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dosen tidak ditemukan!"));
    User ketuaLPPAIK = repo.findByRole("KETUA")
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dosen tidak ditemukan!"));

    String path = "assets/sertifikat.pdf";
    String dest = "assets/sertifikat-out.pdf";

    FontProgram fontProgram = FontProgramFactory.createFont("assets/KaushanScript-Regular.ttf");
    FontProgram fontku = FontProgramFactory.createFont("assets/OpenSans-VariableFont_wdth,wght.ttf");
    PdfFont font = PdfFontFactory.createFont(
            fontProgram, PdfEncodings.WINANSI, true);
    PdfFont fontReg = PdfFontFactory.createFont(
            fontku, PdfEncodings.WINANSI, true);
    PdfReader reader = new PdfReader(path);
    PdfWriter writer = new PdfWriter(dest);

    PdfDocument document = new PdfDocument(reader, writer);

    Paragraph mhsName = new Paragraph(mhs.getName())
            .setTextAlignment(TextAlignment.CENTER).setFont(font).setFontSize(40).setFixedPosition(170,310,500);
    Paragraph prodiMhs = new Paragraph("Program Studi " + mhs.getJurusan())
            .setFont(fontReg).setFixedPosition(170, 290, 500).setTextAlignment(TextAlignment.CENTER).setFontSize(15f);;
    Paragraph rektorName = new Paragraph(rektor.getName())
            .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(85, 85, 300);
    Paragraph rektorNIDN = new Paragraph(rektor.getId())
            .setFont(fontReg).setBold().setFixedPosition(195, 60, 120);
    Paragraph kepalaName = new Paragraph(ketuaLPPAIK.getName())
            .setFont(fontReg).setBold().setTextAlignment(TextAlignment.CENTER).setFixedPosition(465, 85, 300);
    Paragraph kepalaNIDN = new Paragraph(ketuaLPPAIK.getId())
            .setFont(fontReg).setBold().setFixedPosition(575, 60, 120);
    Paragraph tanggalIslam = new Paragraph("13 Rabiul Awal 1445 H")
            .setFont(fontReg).setFixedPosition(650, 234, 150);
    Paragraph tanggalMasehi = new Paragraph("13 Desember 2023 M")
            .setFont(fontReg).setFixedPosition(650, 215, 150);

    // tanda tanggan
    ImageData signature = ImageDataFactory.create("assets/signature.png");
    Image image = new Image(signature);
    image.scaleAbsolute(210, 100);
    image.setFixedPosition(120, 80);

    // create a doc
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

    doc.close();

 return Files.readAllBytes(new File(dest).toPath());
//    FileInputStream fis= new FileInputStream(new File(dest));
//    byte[] targetArray = new byte[fis.available()];
//    fis.read(targetArray);
//    return targetArray;
  }

}
