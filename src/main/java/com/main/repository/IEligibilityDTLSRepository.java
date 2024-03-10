package com.main.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.main.entity.EligibilityDtls;

public interface IEligibilityDTLSRepository extends JpaRepository<EligibilityDtls, Integer> 
{
	  @Query("select distinct(planName) from EligibilityDtls")
      public List<String> findPlanNames();
	  
	  @Query("select distinct(planStatus) from EligibilityDtls")
      public List<String> findPlanStatus();
	  
	  /*@Query("FROM EligibilityDtls where recordStatus='A'")
      public List<EligibilityDtls> searchAllWithNoParams();
	  
		@Query("FROM EligibilityDtls where recordStatus='A' and planName=:planName")
		public List<EligibilityDtls> searchByName(@Param("planName")String planName);
		
		@Query("FROM EligibilityDtls where recordStatus='A' and planStatus=:status")
		public List<EligibilityDtls> searchByPlanStatus(@Param("status")String status);
		
		@Query("FROM EligibilityDtls where recordStatus='A' and planStatus=:planStatus and planName=:planName")
		public List<EligibilityDtls> searchByPlanNameAndPlanStatus(@Param("planStatus")String status, @Param("planName")String planName);
		
		@Query("FROM EligibilityDtls where recordStatus='A' and planStartDate=:startDate")
		public List<EligibilityDtls> searchByPlanStartDate(@Param("startDate") LocalDate startDate);
		
		@Query("FROM EligibilityDtls where recordStatus='A' and planEndDate=:endDate")
		public List<EligibilityDtls> searchByPlanEndDate(@Param("endDate") LocalDate endDate);*/
}
