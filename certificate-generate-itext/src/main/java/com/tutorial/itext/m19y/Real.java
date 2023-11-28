package com.tutorial.itext.m19y;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.layout.property.UnitValue.createPointValue;

public class Real {
  public static void main(String[] args) throws FileNotFoundException, MalformedURLException {

    // Setup for page size A4
    final float wFull = 595f;
    final float hFull = 842f;
    final float container = 570f;

    // file destination
    String output = "output-report.pdf";

    PdfWriter writer = new PdfWriter(output);
    PdfDocument pdfDocument = new PdfDocument(writer);
    pdfDocument.setDefaultPageSize(PageSize.A4);


    // HEADER
    Table header = new Table(new float[]{
            percentPerWidth(container, 1.0f / 12),
            percentPerWidth(container, 8.0f / 12),
            percentPerWidth(container, 3.0f / 12)
    });

    // header title
    Table headerTitle = new Table(new float[]{percentPerWidth(container, 9.0f / 12)});

    headerTitle.addCell(setTextBold("UNIVERSITAS MUHAMMADIYAH KENDARI", 13f)
            .setPadding(5f)
            .setCharacterSpacing(1.5f)
            .setTextAlignment(TextAlignment.CENTER)
    );
    headerTitle.addCell(setTextBold("LEMBAGA PENGKAJIAN DAN PENGAMALAN AIK", 10f)
            .setTextAlignment(TextAlignment.CENTER)
            .setCharacterSpacing(1.5f)
    );
    // -------------

    // header detail
    Table headerDetail = new Table(new float[]{percentPerWidth(container, 11f / 12)});
    headerDetail.addCell(setText("Jl. KH. Ahmad Dahlan No.10 Kendari", 5f));

    headerDetail.addCell(setText("Tlp : 08533661912", 5f));
    headerDetail.addCell(setText("Email : aik@gmail.com", 5f));
    headerDetail.addCell(setText("Website : lppaik.netlify", 5f));
    // -------------


    // header
    header.addCell(new Cell().add(image("umk1.png")
                    .setWidth(55f)
                    .setHeight(50f))
            .setBorder(Border.NO_BORDER)
    );
    header.addCell(new Cell().add(headerTitle).setBorder(Border.NO_BORDER));
    header.addCell(new Cell().add(headerDetail).setBorder(Border.NO_BORDER).setBorderLeft(border(3, Color.BLACK)));
    // -------------


    // core
    Table core = new Table(new float[]{wFull});

    /*
    core.addCell(setTextBold("Daftar kehadiran", 10)
            .setTextAlignment(TextAlignment.CENTER)
            .setCharacterSpacing(1.2f));
    */

    core.addCell(setText("Lembaga Pengkajian dan Pengamalan Al-Islam dan Kemuhammadiyahan" +
            " menyatakan bahwa berdasarkan hasil laporan kehadiran, mahasiswa tersebut di bawah ini:").setTextAlignment(TextAlignment.JUSTIFIED));

    Table userDetail = new Table(new float[]{percentPerWidth(container, 8.0f / 12)});

    String name = "Otong Surotong";
    String id = "21919191";
    String major = "Pendidikan Teknologi Informasi";

    userDetail.addCell(setText("Nama \t\t\t\t\t\t : " + name));
    userDetail.addCell(setText("Stanbuk \t\t\t\t\t : " + id));
    userDetail.addCell(setText("Program Studi\t\t\t: " + major));

    core.addCell(new Cell().add(userDetail.setMarginTop(20).setMarginBottom(20).setMarginLeft(20)).setBorder(Border.NO_BORDER));
    core.addCell(setText("Dinyatakan telah mengikuti kegiatan - kegiatan sebagai berikut:"));
    // -------------

    float [] productTblWidth = {20f, 381f, 125f};

    Table product = new Table(productTblWidth);

    List<Product> products = new ArrayList<>();
    products.add(new Product("Apple", 11, 5000f));
    products.add(new Product("Mango", 1, 3000000000f));
    products.add(new Product("Orange", 7, 100f));
    products.add(new Product("Papaya", 10, 8000f));
    products.add(new Product("Blueberry", 6, 500f));
    products.add(new Product("Burger", 5, 13000f));
    products.add(new Product("Pizza", 2, 14500f));

    float totalCombine = 0f;

    int targetSize = 10; // Jumlah kolom yang diinginkan

    // list header
    product.addCell(tableHead("No", 9).setTextAlignment(TextAlignment.CENTER));
    product.addCell(tableHead("Judul Kegiatan", 9).setTextAlignment(TextAlignment.CENTER));
    product.addCell(tableHead("Tanggal", 9).setTextAlignment(TextAlignment.CENTER));

    // list content
    for (int i = 0; i < targetSize; i++) {
      int num = i + 1;
      if (i < products.size()) {
        Product data = products.get(i);
        float total = data.getPrice() * data.getQuantity();
        totalCombine += total;
        product.addCell(tableData(String.valueOf(num)).setTextAlignment(TextAlignment.CENTER));
        product.addCell(tableData(data.getName(), 11).setTextAlignment(TextAlignment.CENTER));
        product.addCell(tableData(String.valueOf(data.getPrice()), 11).setTextAlignment(TextAlignment.CENTER));
      } else {
        // Tambahkan kolom kosong
        product.addCell(tableData(String.valueOf(num)).setTextAlignment(TextAlignment.CENTER));
        product.addCell(tableData("")); // Kolom kosong dengan lebar 11
        product.addCell(tableData("")); // Kolom kosong dengan lebar 11
      }
    }
    // -------------

    // signature
    String ketua = "Fabio Diggiantonio, S.Pd., M.T";
    String idKetua = "10100201010";
    String signatureIMG = "signature";
    Table signature = new Table(new float[]{container});

    signature.addCell(setText("Kendari, 27 agustus 2023")
            .setTextAlignment(TextAlignment.RIGHT));

    signature.addCell(setTextBold("Kepala Lppaik", 11)
            .setTextAlignment(TextAlignment.RIGHT).setPaddingBottom(45));


    Text underlinedText = new Text(ketua).setUnderline(1, -2);
    Paragraph paragraph = new Paragraph(underlinedText)
            .setTextAlignment(TextAlignment.RIGHT)
            .setFontSize(12)
            .setCharacterSpacing(0.5f)
            .setMarginBottom(5);

    signature.addCell(new Cell().add(paragraph.setTextAlignment(TextAlignment.RIGHT))
            .setBorder(Border.NO_BORDER));

    signature.addCell(setText("NIDN." + idKetua).setTextAlignment(TextAlignment.RIGHT).setPaddingTop(-10f));


    signature.addCell(new Cell().add(image("signature.png")
            .scaleAbsolute(210, 100)
            .setFixedPosition(380, 60))
            .setBorder(Border.NO_BORDER)
    );



    // -------------

    // document
    Document document = new Document(pdfDocument);
    document.add(header);
    document.add(underline(container, 0.5f , Color.GRAY).setMarginTop(10));
    document.add(core.setMarginTop(40));
    document.add(product.setMarginTop(10));
    document.add(signature.setMarginTop(20));


    document.close();

    System.out.println("Completed");

  }

  public static Image image(String path) throws MalformedURLException {
    return new Image(ImageDataFactory.create(path));
  }

  public static Border border(float volume, Color color){

    return new SolidBorder(color, volume);
  }
  public static Table underline(float width, float volume, Color color){

    Border grayBorder = new SolidBorder(color, volume);
    Table borderDivideHeaderAndContent = new Table(new float[] {width});
    borderDivideHeaderAndContent.setBorder(grayBorder);

    return borderDivideHeaderAndContent;
  }
  public static float percentPerWidth(float current, float percent) {
    float result = current * percent;
    return Float.parseFloat(String.format("%.1f", result));
  }

  public static Cell setTextBold(String data){
    return new Cell().add(data)
            .setBorder(Border.NO_BORDER)
            .setBold();
  }
  public static  Cell setTextBold(String data, float size){
    return new Cell().add(data)
            .setBorder(Border.NO_BORDER)
            .setBold()
            .setFontSize(size);
  }
  public static  Cell setText(String data, float size){
    return new Cell().add(data)
            .setCharacterSpacing(0.8f)
            .setBorder(Border.NO_BORDER)
            .setFontSize(size);
  }
  public static  Cell setText(String data){
    return new Cell().add(data)
            .setCharacterSpacing(0.8f)
            .setBorder(Border.NO_BORDER);
  }

  public static  Cell tableHead(String data){
    return new Cell().add(data)
            .setCharacterSpacing(0.8f)
            .setBold()
            .setPadding(5);
  }
  public static  Cell tableHead(String data, float size){
    return new Cell().add(data)
            .setCharacterSpacing(0.8f)
            .setBold()
            .setPadding(5)
            .setFontSize(size);
  }
  public static  Cell tableData(String data, float size){
    return new Cell().add(data)
            .setCharacterSpacing(0.8f)
            .setPadding(5)
            .setFontSize(size);
  }
  public static  Cell tableData(String data){
    return new Cell().add(data)
            .setPadding(5)
            .setCharacterSpacing(0.8f);
  }

}
