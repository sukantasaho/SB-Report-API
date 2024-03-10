package com.main.converter;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.main.dto.ResponseDTO;
import com.main.entity.EligibilityDtls;

public class ConverterUtils {

	public static List<ResponseDTO>  convertDBOListToDTOList(List<EligibilityDtls> dboList)
	{
		List<ResponseDTO> dtoList = null;
    	if(!dboList.isEmpty() && dboList!=null)
    	{ 
    		  dtoList = dboList.stream()
                    .map(dbo->convertDBOToDTO(dbo))
                    .collect(Collectors.toList()); 
    	}
    	
    	return dtoList;
	}
	
	public static ResponseDTO convertDBOToDTO(EligibilityDtls dbo)
    {
    	 ResponseDTO  dto = new ResponseDTO();
	     BeanUtils.copyProperties(dbo, dto);
		return dto;
    }
}
