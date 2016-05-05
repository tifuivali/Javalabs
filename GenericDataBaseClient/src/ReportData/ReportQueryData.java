/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportData;

import genericdatabaseclient.DataBaseConection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.report.AdhocReportCustomizer;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author tifuivali
 */
public class ReportQueryData implements Runnable{
    
    public static String reportDirectory="/home/tifuivali/Descărcări/Reports/";
    private String [][] data=null;
    private String [] colNames=null;
    private final String query;
    private File file=null;
    private JProgressBar progressBar=null;
    DataBaseConection conection=null;
    JFrame context=null;
    
    /**
     * Create a new ReportQueryData.
     * @param query String of query.
     * @param conection DataBase connection. 
     * @param filePath The FilePath where report will be saved. 
     * @param nameFile The name of file where report will be saved.
     * @param context Context component. 
     */
    public ReportQueryData(String query,DataBaseConection conection,String filePath,String nameFile,JFrame context) 
    {
        
       
        this.query=query;
        this.file=new File(filePath+nameFile+".pdf");
        this.context=context;
        this.conection=conection;
    }
    
    /**
     * Build the report. 
     */
    @Override
    public void run(){
        ResultSet rs;
        try {
            rs = conection.getResults(query);
            colNames=conection.getColumnsName(rs);
            data=conection.getData(rs, colNames); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(context,"Eroare Query :"+ex.getMessage(),
                                              "Query error",JOptionPane.ERROR_MESSAGE);
        }
           
        AdhocConfiguration configuration = new AdhocConfiguration();
	AdhocReport report = new AdhocReport();
	configuration.setReport(report);

        //columns
        for (String colName : colNames) {
            AdhocColumn column=new AdhocColumn();
            column.setName(colName);
            report.addColumn(column);
        }
	       updateProgressBar(10);

	     try(OutputStream output=new FileOutputStream(file)) 
             {
			JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(), (AdhocReportCustomizer) new ReportCustomizer());
                        updateProgressBar(30);
			reportBuilder.setDataSource(createDataSource());
                        updateProgressBar(60);
	 		reportBuilder.toPdf(output);
                        updateProgressBar(100);
                        progressBar.setVisible(false);
                        JOptionPane.showMessageDialog(context,"Report complete!",
                                              "Reported!",JOptionPane.WARNING_MESSAGE);
             } catch (IOException | DRException ex) {
             JOptionPane.showMessageDialog(context,"Eroare report:"+ex.getMessage(),
                                              "Report error",JOptionPane.ERROR_MESSAGE);
        } 
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource(colNames);
        for (String[] data1 : data) {
            dataSource.add((Object[]) data1);
        }
				
	return dataSource;
	}

    /**
     * @return the progressBar
     */
    public JProgressBar getProgressBar() {
        return progressBar;
    }
    
    private void updateProgressBar(int value)
    {
        if(progressBar==null)
            return;
        progressBar.setValue(value);
    }

    /**
     * @param progressBar the progressBar to set
     */
    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }
      
        
       private class ReportCustomizer extends DefaultAdhocReportCustomizer {

		/**
		 * If you want to add some fixed content to a report that is not needed to store in the xml file.
		 * For example you can add default page header, footer, default fonts,...
		 */
                @Override
		public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {
			super.customize(report, adhocReport);
			//default report values
			report.setTemplate(Templates.reportTemplate);
			report.title(Templates.createTitleComponent(query));
			//a fixed page footer that user cannot change, this customization is not stored in the xml file
			report.pageFooter(Templates.footerComponent);
		}

		/**
		 * This method returns a field type from a given field name.
		 */
		@Override
		protected DRIDataType<?, ?> getFieldType(String name) {
			return type.stringType();
		}

		/**
		 * If a user doesn’t specify a column title, getColumnTitle is evaluated and the return value is used as a column title.
		 */
		@Override
		protected String getFieldLabel(String name) {
			
			return name;
		}

	}
    
}
