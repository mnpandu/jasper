package dev.simplesolution.pdf.demo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperPdfGenerator {

    public static void main(String[] args) {
        try {
            // Sample data with additional fields
            List<EmployeeNew> employees = List.of(
                new EmployeeNew(101, "Alice Smith", "HR", 75000.00, "123 Main St, Boston, MA"),
                new EmployeeNew(102, "Bob Johnson", "IT", 85000.00, "456 Pine Ave, New York, NY"),
                new EmployeeNew(103, "Charlie Brown", "Finance", 80000.00, "789 Oak Blvd, Chicago, IL"),
                new EmployeeNew(104, "David Miller", "Marketing", 72000.00, "321 Elm St, San Francisco, CA"),
                new EmployeeNew(105, "Emma Davis", "HR", 65000.00, "654 Cedar Ln, Seattle, WA")
            );

            // Load JRXML file from classpath
            // Try multiple ways to load the JRXML file
            InputStream jrxmlStream = null;
            
            // Option 1: Try from absolute project path (for development)
            try {
                String currentDir = System.getProperty("user.dir");
                String filePath = currentDir + "/src/main/resources/report-layout.jrxml";
                System.out.println("Looking for file at: " + filePath);
                jrxmlStream = new java.io.FileInputStream(filePath);
            } catch (Exception e) {
                System.out.println("Could not load from absolute path: " + e.getMessage());
            }
            
            // Option 2: Try relative path if Option 1 fails
            if (jrxmlStream == null) {
                try {
                    jrxmlStream = new java.io.FileInputStream("src/main/resources/report-layout.jrxml");
                    System.out.println("Loaded from relative path");
                } catch (Exception e) {
                    System.out.println("Could not load from relative path: " + e.getMessage());
                }
            }
            
            // Option 3: Try classpath loading if previous options fail
            if (jrxmlStream == null) {
                // Try various classpath patterns
                String[] paths = {"/report-layout.jrxml", "report-layout.jrxml", 
                                 "/templates/report-layout.jrxml"};
                
                for (String path : paths) {
                    jrxmlStream = JasperPdfGenerator.class.getResourceAsStream(path);
                    if (jrxmlStream != null) {
                        System.out.println("Found file in classpath at: " + path);
                        break;
                    }
                }
            }
            
            if (jrxmlStream == null) {
                throw new IllegalStateException("Could not find the report-layout.jrxml file. Please check the file location.");
            }
            
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlStream);

            // Parameters for the report (if needed)
            Map<String, Object> params = new HashMap<>();
            
            // Create data source from the employee list
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            
            // Export to PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "EmployeeMultiTableReport.pdf");

            System.out.println("✅ PDF generated: EmployeeMultiTableReport.pdf");
        } catch (Exception e) {
            System.err.println("Failed to generate PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
