package com.main.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.RequestBody;

import com.lowagie.text.DocumentException;
import com.main.dto.ResponseDTO;
import com.main.dynamicSearch.SearchRequest;
import com.main.entity.EligibilityDtls;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IEligibleService 
{
        public List<String> getUniquePlanNames();
        public List<String> getUniquePlanStatus();
        public List<ResponseDTO> search(@RequestBody SearchRequest search);
        public void generateExcel(HttpServletResponse response) throws IOException;
        public ByteArrayResource  generatePDF(HttpServletResponse response) throws DocumentException, IOException;
        public boolean addEligibility(EligibilityDtls dtls);
}
