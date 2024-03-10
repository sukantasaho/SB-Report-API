package com.main.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.poi.hpsf.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lowagie.text.DocumentException;
import com.main.dto.ResponseDTO;
import com.main.dynamicSearch.SearchRequest;
import com.main.entity.EligibilityDtls;
import com.main.service.IEligibleService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/report-api")
public class ReportRestController 
{
    @Autowired
	private IEligibleService service;
	 
    @GetMapping("/getAllPlanNames")
	public ResponseEntity<List<String>> getUniquePlanNames() {
		       
		 return    new ResponseEntity<List<String>>(service.getUniquePlanNames(), HttpStatus.OK);
	}
	@GetMapping("/getAllPlanStatus")
	public ResponseEntity<List<String>> getUniquePlanStatus() {
	 
		return new ResponseEntity<List<String>>(service.getUniquePlanStatus(), HttpStatus.OK);
	}
	@PostMapping("/saveEligibility")
	public ResponseEntity<Boolean> addEligibility(@RequestBody EligibilityDtls dtls)
	{
		boolean b = service.addEligibility(dtls);
		
		return new ResponseEntity<Boolean>(b, HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<ResponseDTO>> search(@RequestBody SearchRequest search)
	{
		return new ResponseEntity<List<ResponseDTO>>(service.search(search), HttpStatus.OK);
	}
	@GetMapping("/pdfGenerator")
	public ResponseEntity<ByteArrayResource> generatePdf(HttpServletResponse response) throws DocumentException, IOException 
	{
		//1st logic generate pdf and directly downloaded if you want this change return type void and uncomment
		/*response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		service.generatePDF(response);	*/
		
		//2nd logic to generate pdf with view and download return type ByteArrayResource
		ByteArrayResource resource =	service.generatePDF(response);
		
		 return ResponseEntity.ok()
                 .contentType(MediaType.APPLICATION_PDF)
                 .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=data.pdf")
                 .body(resource);
	}
	@GetMapping("/excelGenerator")
	public void generateExcel(HttpServletResponse response) throws DocumentException, IOException 
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}

}
