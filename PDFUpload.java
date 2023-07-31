package Test;

import java.sql.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFUpload 
{

	public static void main(String[] args) 
	{
		

        List<String> pdfFilePaths = new ArrayList<>();
        pdfFilePaths.add("E:\\pdfsplit_project\\example_PDF_1m.pdf"); 
        pdfFilePaths.add("E:\\pdfsplit_project\\file_sample.pdf"); 
        pdfFilePaths.add("E:\\pdfsplit_project\\sample.pdf"); 
		
			try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:orcl","System","akki");
			
		     // SQL query to get the last used pdf_id from the pdf_storage table
            String getMaxPdfIdSql = "SELECT MAX(pdf_id) FROM pdf_storage";

            // SQL query to insert the PDF file
            String sql = "INSERT INTO pdf_storage (pdf_id, pdf_name, pdf_content) VALUES (?, ?, ?)";

            PreparedStatement getMaxPdfIdStmt = con.prepareStatement(getMaxPdfIdSql);
            ResultSet rs = getMaxPdfIdStmt.executeQuery();
            rs.next();
            
			
			         for (int i = 0; i < pdfFilePaths.size(); i++) 
			            {
			        	 
			                String pdfFilePath = pdfFilePaths.get(i);
			                File pdfFile = new File(pdfFilePath);
			                FileInputStream fis = new FileInputStream(pdfFile);
			                
			                PreparedStatement pstmt = con.prepareStatement(sql);
			                pstmt.setInt(1, i + 1); // Using a unique ID for each PDF file, e.g., i+1 here
			                pstmt.setString(2, pdfFile.getName());
			                pstmt.setBinaryStream(3, fis, (int) pdfFile.length());
			               //pstmt.executeUpdate();

			                fis.close();
			                pstmt.close();
			            }

			            con.close();

			            System.out.println("PDF files uploaded successfully.");
			            
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
			    }
			}


	


