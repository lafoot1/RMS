package com.deloitte.rms.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.api.Referer;
import com.deloitte.rms.api.Referral;
import com.deloitte.rms.api.Requisition;
import com.deloitte.rms.api.Skill;
import com.deloitte.rms.api.type.FunctionType;
import com.deloitte.rms.api.type.PersonType;
import com.deloitte.rms.api.type.ServiceLineType;
import com.deloitte.rms.api.type.SpecialityType;

@Component
public class ExcelUtil {
	
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(new File("C:\\Users\\prasengupta\\Desktop\\TestData.xlsx"));
		ExcelUtil excelUtil = new ExcelUtil();
		excelUtil.readReferrals(fis);
	}
	
	public List<Referral> readReferrals(InputStream is) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int firstRow = sheet.getFirstRowNum();
		DataFormatter fmt = new DataFormatter();
		//int rowCount = sheet.getPhysicalNumberOfRows();
		List<Referral> referrals = new ArrayList<>(sheet.getLastRowNum() - firstRow - 1);
		
		for (int rowIndex = firstRow+1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row != null) {
			    Requisition requisition = new Requisition();
			    requisition.setFunction(FunctionType.getByCode(row.getCell(0).getStringCellValue()));
			    requisition.setSpeciality(SpecialityType.getByCode(row.getCell(1).getStringCellValue()));
			    requisition.setServiceLine(ServiceLineType.getByDesc(row.getCell(2).getStringCellValue()));
			    requisition.setRecruiterName(row.getCell(13).getStringCellValue());
			    requisition.setRecruiterAsstName(row.getCell(14).getStringCellValue());
			    requisition.setRequisitionNo(row.getCell(15).getStringCellValue());
			    requisition.setTitle(row.getCell(16).getStringCellValue());
			   
			    Candidate candidate = new Candidate();
			    candidate.setRmsId((long)row.getCell(7).getNumericCellValue());
			    candidate.setFirstName(row.getCell(8).getStringCellValue());
			    candidate.setLastName(row.getCell(9).getStringCellValue());
			    candidate.setEmail(row.getCell(10).getStringCellValue());
			    candidate.setCurrentLocation(row.getCell(11).getStringCellValue());
			    
			    String[] skills = row.getCell(25).getStringCellValue().split("&");
			    List<Skill> skillSet = new ArrayList<>();
			    for (String s : skills) {
			    	Skill skill = new Skill();
			    	skill.setCode(s);
			    	skillSet.add(skill);
			    }
			    
			    candidate.setSkillSet(skillSet);
			    candidate.setExperience((int)(row.getCell(26).getNumericCellValue()));
			    candidate.setType(PersonType.CANDATE);
			    
			    candidate.setMobile(fmt.formatCellValue(row.getCell(27)));
			    
			    Referer referer = new Referer();
			    referer.setEmpNo(Long.parseLong((fmt.formatCellValue(row.getCell(17)))));
			    String[] names = row.getCell(18).getStringCellValue().split(",");
			    referer.setFirstName(names[0].trim());
			    referer.setLastName(names[1].trim());
			    referer.setEmail(row.getCell(19).getStringCellValue());
			    referer.setType(PersonType.REFERER);
			 
			    Referral referral = new Referral();
			    referral.setReferrer(referer);
			    referral.setRequisition(requisition);
			    referral.setCandidate(candidate);
			    referral.setSourceType(row.getCell(22).getStringCellValue());
			    referral.setApplicationSource(row.getCell(23).getStringCellValue());
			    referral.setRmsStatus(row.getCell(29).getStringCellValue());
			    referral.setComments(row.getCell(30).getStringCellValue());
			    referral.setAdditionalComments(row.getCell(31).getStringCellValue());
			    referral.setSharedWith(row.getCell(32).getStringCellValue());
			    referral.setCorrespondence(row.getCell(33).getStringCellValue());
			    
			    referrals.add(referral);
			}
		}
		
		workbook.close();
		return referrals;
	}
}
