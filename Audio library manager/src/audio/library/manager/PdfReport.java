package audio.library.manager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tifui
 */
import audio.library.manager.AudioLibraryManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocCalculation;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.configuration.AdhocSort;
import net.sf.dynamicreports.adhoc.configuration.AdhocSubtotal;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.adhoc.AdhocCustomizerReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
public class PdfReport {

        File file;
        List<File> favorites_list;
	public PdfReport(File file,List<File> files_Fav) throws DRException, IOException {
		
                this.file=file;
                favorites_list=files_Fav;
                build();
	}
       
        
        public void setFileReport(File file)
        {
            this.file=file;
        }

	public void build() throws DRException, IOException {
		AdhocConfiguration configuration = new AdhocConfiguration();
		AdhocReport report = new AdhocReport();
		configuration.setReport(report);

		//columns
		AdhocColumn column = new AdhocColumn();
		column.setName("nr");
		report.addColumn(column);
		column = new AdhocColumn();
		column.setName("song_name");
		report.addColumn(column);
                column = new AdhocColumn();
		column.setName("dest");
		report.addColumn(column);
		

	     try(OutputStream output=new FileOutputStream(file)) 
             {
			JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(), new ReportCustomizer());
			reportBuilder.setDataSource(createDataSource());
	 		reportBuilder.toPdf(output);
             } 
	}

	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("nr", "song_name", "dest");
		for (int i = 0; i < favorites_list.size(); i++) {
			dataSource.add( i+1, favorites_list.get(i).getName(), favorites_list.get(i).toString());
		}		
		return dataSource;
	}
        /*
  	public static void main(String[] args) {
		new AdhocCustomizerReport();
	}
        */
        
      
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
			report.title(Templates.createTitleComponent("List Favorites Song"));
			//a fixed page footer that user cannot change, this customization is not stored in the xml file
			report.pageFooter(Templates.footerComponent);
		}

		/**
		 * This method returns a field type from a given field name.
		 */
		@Override
		protected DRIDataType<?, ?> getFieldType(String name) {
			if (name.equals("nr")) {
				return type.integerType();
			}
			if (name.equals("song_name")) {
				return type.stringType();
			}
			if (name.equals("dest")) {
				return type.stringType();
			}
		
			return super.getFieldType(name);
		}

		/**
		 * If a user doesn’t specify a column title, getColumnTitle is evaluated and the return value is used as a column title.
		 */
		@Override
		protected String getFieldLabel(String name) {
			if (name.equals("nr")) {
				return "Nr.";
			}
			if (name.equals("song_name")) {
				return "Song Name";
			}
			if (name.equals("dest")) {
				return "File Destination";
			}
			return name;
		}

	}
}