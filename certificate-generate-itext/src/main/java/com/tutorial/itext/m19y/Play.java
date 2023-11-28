//package com.tutorial.itext.m19y;
//
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.border.Border;
//import com.itextpdf.layout.element.Cell;
//import com.itextpdf.layout.element.Paragraph;
//import com.itextpdf.layout.element.Table;
//import com.itextpdf.layout.property.TextAlignment;
//;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Play {
//  public static void main(String[] args) throws IOException {
//
//    String tempPDF = "template.pdf";
//    String resultPDF = "grid-output.pdf";
//
//    PdfReader reader = new PdfReader(tempPDF);
//    PdfWriter writer = new PdfWriter(resultPDF);
//
//    PdfDocument document = new PdfDocument(reader, writer);
//
//            float threeColumn = 285f;
//    float [] productTblWidth = {25f, 344f, 172f};
//    Paragraph major = new Paragraph("Pendidikan Teknologi Informasi")
//            .setTextAlignment(TextAlignment.LEFT)
//            .setFontSize(12)
//            .setFixedPosition(120,650,500);
//
//    Table product = new Table(productTblWidth);
//
//          List<Product> products = new ArrayList<>();
//        products.add(new Product("Apple Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung: Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung:", 11, 5000f));
//        products.add(new Product("Mango", 1, 3000000000f));
//        products.add(new Product("Apple Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung: Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung:", 7, 100f));
//        products.add(new Product("Papaya", 10, 8000f));
//        products.add(new Product("Blueberry", 6, 500f));
//        products.add(new Product("Burger", 5, 13000f));
//        products.add(new Product("Pizza", 2, 14500f));
//
//        float totalCombine = 0f;
//        for (Product data : products) {
//            float total = data.getPrice() * data.getQuantity();
//            totalCombine += total;
//            product.addCell(setText("1"));
//            product.addCell(setText(data.getName(), 11));
////            product.addCell(setText(String.valueOf(data.getQuantity()), 11).setTextAlignment(TextAlignment.CENTER));
//            product.addCell(setText(String.valueOf(data.getPrice()), 11).setTextAlignment(TextAlignment.RIGHT));
//        }
//    // create a doc
//    Document doc = new Document(document);
//    doc.add(write("21916060", TextAlignment.LEFT, 12, 120, 695, 500));
//    doc.add(write("Otong Surotong", TextAlignment.LEFT, 12, 120, 673, 500));
//    doc.add(major);
//    // Menentukan tinggi konten
//    float tinggiKonten = calculateContentHeight(products, 20); // Implementasi metode ini tergantung pada struktur data dan konten yang Anda tambahkan
//
//// Menghitung koordinat y baru berdasarkan tinggi konten
//    int tinggiHalaman = 800; // Gantilah dengan tinggi halaman sesuai kebutuhan Anda
//    int y_fixedPosition = 390;
//    int y_fixedPosition_baru = tinggiHalaman - y_fixedPosition - (int) tinggiKonten;
//
//// Mengatur fixedPosition baru
//    doc.add(product.setFixedPosition(36, y_fixedPosition_baru, 500));
//
//    doc.close();
//  }
//
//  public static Paragraph write(String text, TextAlignment alignment, float size, float x, float y, float width){
//    return new Paragraph(text)
//            .setTextAlignment(alignment)
//            .setFontSize(size)
//            .setFixedPosition(x,y,width);
//  }
//
//  public static  Cell setTextBold(String data){
//        return new Cell().add(data).setBorder(Border.NO_BORDER).setBold();
//    }
//    public static  Cell setTextBold(String data, float size){
//        return new Cell().add(data).setBorder(Border.NO_BORDER).setBold().setFontSize(size);
//    }
//    public static  Cell setText(String data, float size){
//        return new Cell().add(data).setFontSize(size);
//    }
//    public static  Cell setText(String data){
//        return new Cell().add(data);
//    }
//
//  private static float calculateContentHeight(List<Product> products, int tinggiPerBaris) {
//    int jumlahBaris = products.size();
//    return jumlahBaris * tinggiPerBaris;
//  }
//}
