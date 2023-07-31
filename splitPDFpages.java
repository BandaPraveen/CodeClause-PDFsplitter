package Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class splitPDFpages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the input PDF file: ");
        String inputFilePath = scanner.nextLine();

        System.out.print("Enter the output directory path where individual PDFs will be saved: ");
        String outputDirPath = scanner.nextLine();

        System.out.print("Enter the starting page number: ");
        int startPage = scanner.nextInt();

        System.out.print("Enter the ending page number: ");
        int endPage = scanner.nextInt();

        try {
            PDDocument document = PDDocument.load(new File(inputFilePath));
            Splitter splitter = new Splitter();

            // Set the range of pages to split 
            splitter.setStartPage(startPage);
            splitter.setEndPage(endPage);

            int pageCounter = startPage;
            for (PDDocument page : splitter.split(document)) {
                String outputFileName = outputDirPath + File.separator + "page_" + pageCounter + ".pdf";
                page.save(outputFileName);
                page.close();
                System.out.println("Page " + pageCounter + " saved to: " + outputFileName);
                pageCounter++;
                
                System.out.println("PDF's Splitted successfully");
            }
            scanner.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}
