package com.main.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.main.converter.ConverterUtils;
import com.main.dto.ResponseDTO;
import com.main.dynamicSearch.SearchRequest;
import com.main.entity.EligibilityDtls;
import com.main.repository.IEligibilityDTLSRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service("eService")
public class EligibilityImp implements IEligibleService {

	@Autowired
	private IEligibilityDTLSRepository repo;
	
	@Override
	public List<String> getUniquePlanNames() {
		       
		  List<String> planNamesList =  repo.findPlanNames();
		  System.out.println(planNamesList);
		  return planNamesList;
	}

	@Override
	public List<String> getUniquePlanStatus() {
	 
		List<String> planStatusList =  repo.findPlanStatus();
		System.out.println(planStatusList);
		return planStatusList;
	}

	@Override
	public List<ResponseDTO> search(SearchRequest search) {
		 
		
		/*if(search.getPlanName()!=null && search.getPlanStatus()==null && search.getPlanStartDate()==null && search.getPlanEndDate()==null)
	 
			return ConverterUtils.convertDBOListToDTOList(repo.searchByName(search.getPlanName()));
	 
		else if(search.getPlanName()==null && search.getPlanStatus()!=null && search.getPlanStartDate()==null && search.getPlanEndDate()==null)
	 
			return ConverterUtils.convertDBOListToDTOList(repo.searchByPlanStatus(search.getPlanStatus()));
	 
		else if(search.getPlanName()!=null && search.getPlanStatus()!=null && search.getPlanStartDate()==null && search.getPlanEndDate()==null)
 
			return ConverterUtils.convertDBOListToDTOList(repo.searchByPlanNameAndPlanStatus(search.getPlanStatus(), search.getPlanName()));
	 
		else if(search.getPlanName()==null && search.getPlanStatus()==null && search.getPlanStartDate()!=null && search.getPlanEndDate()==null)
			 
			return ConverterUtils.convertDBOListToDTOList(repo.searchByPlanStartDate(search.getPlanStartDate()));
		
		else if(search.getPlanName()==null && search.getPlanStatus()==null && search.getPlanStartDate()==null && search.getPlanEndDate()!=null)
			 
			return ConverterUtils.convertDBOListToDTOList(repo.searchByPlanEndDate(search.getPlanEndDate()));*/
	     
		EligibilityDtls queryBuilder = new EligibilityDtls();
		
		if(search.getPlanName()!=null && !search.getPlanName().equals(""))
		{
			queryBuilder.setPlanName(search.getPlanName());
		}
		if(search.getPlanStatus()!=null && !search.getPlanStatus().equals(""))
		{
			queryBuilder.setPlanStatus(search.getPlanStatus());
		}
		if(search.getPlanStartDate()!=null)
		{
			queryBuilder.setPlanStartDate(search.getPlanStartDate());
		}
		if(search.getPlanEndDate()!=null)
		{
			queryBuilder.setPlanEndDate(search.getPlanEndDate());
		}
		
		Example<EligibilityDtls> example = Example.of(queryBuilder);
		
		  List<EligibilityDtls> dboList =  repo.findAll(example);
		  
		  return ConverterUtils.convertDBOListToDTOList(dboList);
		 
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		 
		List<EligibilityDtls> list = repo.findAll();
		List<ResponseDTO> resList = ConverterUtils.convertDBOListToDTOList(list);
        Workbook workbook = new HSSFWorkbook();
         HSSFSheet sheet = (HSSFSheet) workbook.createSheet();
         HSSFRow headerRow = sheet.createRow(0);
         headerRow.createCell(0).setCellValue("S. No");
         headerRow.createCell(1).setCellValue("Name");
         headerRow.createCell(2).setCellValue("Email");
         headerRow.createCell(3).setCellValue("Mobile");
         headerRow.createCell(4).setCellValue("Gender");
         headerRow.createCell(5).setCellValue("SSN");
         
          int i = 1;
          for(ResponseDTO res : resList) 
          {
        	   
        	   HSSFRow dataRow = sheet.createRow(i);
        	   dataRow.createCell(0).setCellValue(i);
        	   dataRow.createCell(1).setCellValue(res.getName());
        	   dataRow.createCell(2).setCellValue(res.getEmail());
        	   dataRow.createCell(3).setCellValue(res.getMobile());
        	   dataRow.createCell(4).setCellValue(String.valueOf(res.getGender()));
        	   dataRow.createCell(5).setCellValue(res.getSsn());
        	   i++;
           }
           ServletOutputStream outputStream = response.getOutputStream();
           workbook.write(outputStream);
           workbook.close();
           outputStream.close();
	}

	@Override
	public ByteArrayResource generatePDF(HttpServletResponse response) throws DocumentException, IOException {
		 
		/*This logic pdf will generated and download directly   return type void*/
		/*List<EligibilityDtls> list = repo.findAll();
		List<ResponseDTO> resList = ConverterUtils.convertDBOListToDTOList(list);
		
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		   // Opening the created document to modify it
			document.open();
			// Creating font
			// Setting font style and size
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setSize(20);
			font.setColor(Color.BLUE);
		   // Creating paragraph
			Paragraph paragraph = new Paragraph("Search Report", font);
			// Aligning the paragraph in document
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			// Adding the created paragraph in document
			document.add(paragraph);
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] {1.0f,3.0f,4.5f,3.5f,2.0f,3.0f});
			table.setSpacingBefore(10);
			
			// Create Table Cells for table header
			PdfPCell cell = new PdfPCell();
		
			// Setting the background color and padding
			cell.setBackgroundColor(Color.DARK_GRAY);
			cell.setPadding(5);
			//cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			// Creating font
			// Setting font style and size
			Font cellFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			cellFont1.setColor(Color.WHITE);
			// Adding headings in the created table cell/ header
			// Adding Cell to table
			cell.setPhrase(new Phrase("ID", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Name", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Email", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Mobile", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Gender", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("SSN", cellFont1));
			table.addCell(cell);
			
			// Iterating over the list of students
			for (ResponseDTO dto : resList) {
				 
				table.addCell(String.valueOf(dto.getEligibleId()));
				table.addCell(String.valueOf(dto.getName()));
				table.addCell(String.valueOf(dto.getEmail()));
				table.addCell(String.valueOf(dto.getMobile()));
				table.addCell(String.valueOf(dto.getGender()));
				table.addCell(String.valueOf(dto.getSsn()));
			}
			
			// Adding the created table to document
			document.add(table);
			// Closing the document
			document.close();
			*/
		
		// This logic will generate pdf and view pdf and can download but directly return type changing to ByteArrayResource
		List<EligibilityDtls> list = repo.findAll();
		List<ResponseDTO> resList = ConverterUtils.convertDBOListToDTOList(list);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, outputStream);
		   // Opening the created document to modify it
			document.open();
			// Creating font
			// Setting font style and size
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setSize(20);
			font.setColor(Color.BLUE);
		   // Creating paragraph
			Paragraph paragraph = new Paragraph("Search Report", font);
			// Aligning the paragraph in document
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			// Adding the created paragraph in document
			document.add(paragraph);
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100f);
			table.setWidths(new float[] {1.0f,3.0f,4.5f,3.5f,2.0f,3.0f});
			table.setSpacingBefore(10);
			
			// Create Table Cells for table header
			PdfPCell cell = new PdfPCell();
		
			// Setting the background color and padding
			cell.setBackgroundColor(Color.DARK_GRAY);
			cell.setPadding(5);
			//cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			// Creating font
			// Setting font style and size
			Font cellFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			cellFont1.setColor(Color.WHITE);
			// Adding headings in the created table cell/ header
			// Adding Cell to table
			cell.setPhrase(new Phrase("ID", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Name", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Email", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Mobile", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Gender", cellFont1));
			table.addCell(cell);
			cell.setPhrase(new Phrase("SSN", cellFont1));
			table.addCell(cell);
			
			// Iterating over the list of students
			for (ResponseDTO dto : resList) {
				 
				table.addCell(String.valueOf(dto.getEligibleId()));
				table.addCell(String.valueOf(dto.getName()));
				table.addCell(String.valueOf(dto.getEmail()));
				table.addCell(String.valueOf(dto.getMobile()));
				table.addCell(String.valueOf(dto.getGender()));
				table.addCell(String.valueOf(dto.getSsn()));
			}
			
			// Adding the created table to document
			document.add(table);
			Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			font.setSize(16);
			font.setColor(Color.BLUE);
			 
			Paragraph p1 = new Paragraph("Total Records-"+resList.size(), f1);
			document.add(p1);
			// Closing the document
			document.close();
			
			byte[] bytes = outputStream.toByteArray();
            ByteArrayResource resource = new ByteArrayResource(bytes);
            
            return resource;
			
	}
	@Override
	public boolean addEligibility(EligibilityDtls dtls) {
		EligibilityDtls e = repo.save(dtls);
		return e.getEligibleId()!=null;
	}

}
