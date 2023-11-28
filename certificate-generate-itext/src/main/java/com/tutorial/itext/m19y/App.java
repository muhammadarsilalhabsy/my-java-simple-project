package com.tutorial.itext.m19y;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws IOException {


//        String path = "sertifikat.pdf";
//        String dest = "sertifikat-out.pdf";
//
//        PdfReader reader = new PdfReader(path);
//        PdfWriter writer = new PdfWriter(dest);
//
//        PdfDocument pdfDocument = new PdfDocument(reader, writer);

        // variable
        float threeColumn = 190f;

        String path = "ok.pdf";

        PdfWriter writer = new PdfWriter(path);

        PdfDocument pdfDocument = new PdfDocument(writer);
        // ukuran kertas
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDocument);

        // create two column
        float right = 285f;
        float left = right + 150f;
        float[] header = {left + 10, right - 10};

        Table tableHeader = new Table(header);

        Table tableNestedRight = new Table(new float[]{right/2, right/2});
        tableNestedRight.addCell(setTextBold("Invoice No\t:"));
        tableNestedRight.addCell(setText("08533642"));
        tableNestedRight.addCell(setTextBold("Invoice Date :"));
        tableNestedRight.addCell(setText("02/Sep/2023"));

        tableHeader.addCell(setTextBold("Invoice", 20f));
        tableHeader.addCell(new Cell().add(tableNestedRight).setBorder(Border.NO_BORDER));

        // create a line for divide between header and content
        float[] fullWidth = {threeColumn * 3};

        Border grayBorder = new SolidBorder(Color.GRAY, 1f);
        Table borderDivideHeaderAndContent = new Table(fullWidth);
        borderDivideHeaderAndContent.setBorder(grayBorder);

        // create 2 cloumn (left: Billing information, right: shipping information)
        float oneColumn = 285f ;
        float[] body = {oneColumn + 50f, oneColumn - 50f};

        Table tableBodyTitle = new Table(body);
        tableBodyTitle.addCell(setTextBold("Bling Information", 12));
        tableBodyTitle.addCell(setTextBold("Shipping Information",12));

        Table tableBody = new Table(body);

        Table leftBody = new Table(new float[]{oneColumn + 50f});
        Table rightBody = new Table(new float[]{oneColumn - 50f});

        // left body====
        leftBody.addCell(setTextBold("Company", 11));
        leftBody.addCell(setText("Muhammad Arsil Alhabsy", 11));
        leftBody.addCell(setTextBold("Name", 11));
        leftBody.addCell(setText("Otong", 11));
        leftBody.addCell(setTextBold("Address", 11));
        leftBody.addCell(setText("Jln Laode Kasim,. Lrg. Robert Robertson,\n 1234 NW Bobcat Lane, St. Robert, MO 65584-5678", 11));
        leftBody.addCell(setTextBold("Email", 11));
        leftBody.addCell(setText("Otong@gamil.com", 11));
        // ====


        // right body====
        rightBody.addCell(setTextBold("Name", 11));
        rightBody.addCell(setText("Arsil", 11));
        rightBody.addCell(setTextBold("Address", 11));
        rightBody.addCell(setText("Robert Robertson, 1234 NW Bobcat Lane, St. Robert, MO 65584-5678", 11));
        // ====

        tableBody.addCell(new Cell().add(leftBody).setBorder(Border.NO_BORDER));
        tableBody.addCell(new Cell().add(rightBody).setBorder(Border.NO_BORDER));


        // ====Another border
        Border grayBorderDashed = new DashedBorder(Color.GRAY, 2f);
        Table borderDivideContentAndProducts = new Table(fullWidth);
        borderDivideContentAndProducts.setBorder(grayBorderDashed);
        //

        // table with 3 column
        float [] productTblWidth = {threeColumn, threeColumn, threeColumn};
        Table productTitle = new Table(productTblWidth);

        productTitle.addCell(setTextBold("Description", 11).setFontColor(Color.WHITE));
        productTitle.addCell(setTextBold("Quantity", 11).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER));
        productTitle.addCell(setTextBold("Price", 11).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
        productTitle.setBackgroundColor(Color.GRAY);

        Table product = new Table(productTblWidth);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung: Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung:Apple", 11, 5000f));
        products.add(new Product("Mango", 1, 3000f));
        products.add(new Product("Apple Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung: Untuk menghitung 1/3 dari 516, Anda dapat mengalikan 516 dengan 1/3 atau membaginya dengan 3. Mari kita hitung:", 7, 100f));
        products.add(new Product("Papaya", 10, 8000f));
        products.add(new Product("Blueberry", 6, 500f));
        products.add(new Product("Burger", 5, 13000f));
        products.add(new Product("Pizza", 2, 14500f));

        float totalCombine = 0f;
        for (Product data : products) {
            float total = data.getPrice() * data.getQuantity();
            totalCombine += total;
            product.addCell(setText(data.getName(), 11));
            product.addCell(setText(String.valueOf(data.getQuantity()), 11).setTextAlignment(TextAlignment.CENTER));
            product.addCell(setText(String.valueOf(data.getPrice()), 11).setTextAlignment(TextAlignment.RIGHT));
        }
        // =========

        // ====Another border
        Border grayBorderDashedTotal = new DashedBorder(Color.GRAY, 1f);
        Table leftBorderTotal = new Table(new float[]{oneColumn - 20 , oneColumn + 20});
        Table borderDivideProductListAndTotal = new Table(new float[]{oneColumn + 20});
        borderDivideProductListAndTotal.setBorder(grayBorderDashedTotal);
        leftBorderTotal.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        leftBorderTotal.addCell(new Cell().add(borderDivideProductListAndTotal).setBorder(Border.NO_BORDER));
        //

        // total ----
        Table productTotal = new Table(productTblWidth);
        productTotal.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        productTotal.addCell(setText("Total", 11).setTextAlignment(TextAlignment.CENTER));
        productTotal.addCell(setText(String.valueOf(totalCombine), 11).setTextAlignment(TextAlignment.RIGHT));
        // ----


        // Display into document
        document.add(tableHeader);
        document.add(new Paragraph("\n"));
        document.add(borderDivideHeaderAndContent);
        document.add(new Paragraph("\n"));
        document.add(tableBodyTitle.setMarginBottom(10f));
        document.add(tableBody);
        document.add(borderDivideContentAndProducts);
        document.add(new Paragraph("Products").setBold().setMarginTop(20f));
        document.add(productTitle);
        document.add(product);
        document.add(leftBorderTotal.setMarginBottom(10f));
        document.add(productTotal.setMarginBottom(5f));
        document.add(borderDivideHeaderAndContent);

        document.close();

        System.out.println("Selesai!");
    }

    public static  Cell setTextBold(String data){
        return new Cell().add(data).setBorder(Border.NO_BORDER).setBold();
    }
    public static  Cell setTextBold(String data, float size){
        return new Cell().add(data).setBorder(Border.NO_BORDER).setBold().setFontSize(size);
    }
    public static  Cell setText(String data, float size){
        return new Cell().add(data).setBorder(Border.NO_BORDER).setFontSize(size);
    }
    public static  Cell setText(String data){
        return new Cell().add(data).setBorder(Border.NO_BORDER);
    }
}
