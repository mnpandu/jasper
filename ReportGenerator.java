import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.*;

public class ReportGenerator {
    public static void main(String[] args) throws JRException {
        // Sample Data
    	List<Employee> employees = Collections.singletonList(
    		    new Employee("Raja", 25, 25000)
    		);

        // Parameters (optional)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Employee Salary Report");
        parameters.put("Logo", null);  // You can load an image if needed

        // Load and compile JRXML
        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\mnpan\\MyFolder\\JAVA_CODE_BASE\\java_spring-boot_japser-report-master\\java_spring-boot_japser-report-master\\src\\main\\resources\\employee_report.jrxml");

        // Fill the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        // Export to PDF
        JasperExportManager.exportReportToPdfFile(print, "employee_report.pdf");
        System.out.println("hello");
    }
}
