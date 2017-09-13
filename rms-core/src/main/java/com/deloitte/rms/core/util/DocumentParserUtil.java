package com.deloitte.rms.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.Candidate;
import com.deloitte.rms.api.type.GenderType;

@Component
public class DocumentParserUtil {
	private static final Logger LOG = Logger.getLogger(DocumentParserUtil.class);

	public void updateCandidateRecords(Attachment attachment, final Map<String, Candidate> candidateMap) {
		String strContent;
		try {
			strContent = readDocxFile(attachment.getContent());
			List<String> emails = extractEmailIDs(strContent);
			List<String> phNumbers = extractNumber(strContent);

			String gender = strContent.toLowerCase()
					.contains("female")?"female":null;
			if (gender == null) {
				gender = strContent.matches("male")?"male":null;
			}

			String email = emails!=null&&emails.size()>0?emails.get(0):null;
			if (email != null && candidateMap.containsKey(email)) {
				Candidate candidate = candidateMap.get(email);
				candidate.setGender(GenderType.getByCode(gender));
				candidate.setMobile(phNumbers!=null&&phNumbers.size()>0?phNumbers.get(0):null);
				candidate.setEmail(emails!=null&&emails.size()>0?emails.get(0):null);
				candidate.setCurrentLocation(null);
				candidate.getAttachments().add(attachment);
			}
		} catch (InvalidFormatException | IOException e) {
			LOG.error(e.getMessage());
		}
	}

	/**
	 * This method is responsible for reading the InputStream and extracting the
	 * @param is
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public String readDocxFile(byte[] byteContent) throws IOException, InvalidFormatException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(byteContent);
		StringBuilder content = new StringBuilder();

		OPCPackage opcPackage = OPCPackage.open(bis);
		XWPFDocument docx = new XWPFDocument(opcPackage);
		List<XWPFParagraph> paragraphList = docx.getParagraphs();
		//XWPFDocument doc = new XWPFDocument(bis);
        List<XWPFTable> tables = docx.getTables();

        /*Extracting the data from the tables inside a docx file*/
        for ( XWPFTable table : tables )
        {
            for ( XWPFTableRow row : table.getRows() )
            {
                for ( XWPFTableCell cell : row.getTableCells() )
                {
                    // System.out.print(cell.getText());
                    content.append(cell.getText());
                }
                System.out.println("");
            }
        }

		/*Extracting the main content in the doc*/
		for(XWPFParagraph paragraph: paragraphList)
		{
			System.out.println(paragraph.getText());
			content.append(paragraph.getText());
		}

		/*Extracting the content from header and footer*/
		try
		{
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(bis));
			XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(xdoc);
			/* read header*/
			XWPFHeader header = policy.getDefaultHeader();
			// System.out.println(header.getText());
			if(null != header)
			{
				content.append(header.getText());
			}
			/* read footer*/
			XWPFFooter footer = policy.getDefaultFooter();
			// System.out.println(footer.getText());
			if(null !=footer)
			{
				content.append(footer.getText());
			}
		}
		catch (Exception ex)
		{
			//ex.printStackTrace();
		}
		return content.toString();
	}

	/**
	 * Check if the String contains phone number and return
	 *
	 * @param str
	 * @return
	 */
	public List<String> extractNumber(String str) {
		List<String> phNumbers = new ArrayList<String>();
		final String regex = "((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}";
		final Pattern pattern = Pattern.compile(regex);
		// str = str.replaceAll(" ", "");
		final Matcher matcher = pattern.matcher(str);

		while (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
				if (null != matcher.group(i) && "null" != matcher.group(i)) {
					if (matcher.group(i).length() >= 8) {
						phNumbers.add(matcher.group(i));
					}
				}
			}
		}

		final String regexIn = "([\\+]?[9]?[1]?[ -]?\\(?\\d{3}\\)?[ -]?\\(?\\d{3}\\)?[ -]?\\(?[ -]?\\d{4}\\)?) ";
		final Pattern patternIn = Pattern.compile(regexIn);
		str = str.replaceAll(" ", "");
		final Matcher matcherIn = patternIn.matcher(str);

		while (matcherIn.find()) {
			for (int i = 1; i <= matcherIn.groupCount(); i++) {
				if (null != matcherIn.group(i) && "null" != matcherIn.group(i)) {
					if (matcherIn.group(i).length() >= 8) {
						phNumbers.add(matcherIn.group(i));
					}
				}
			}
		}

		final Pattern patternUS = Pattern.compile(regex);
		final Matcher matcherUS = patternUS.matcher(str);

		while (matcherUS.find()) {
			for (int i = 1; i <= matcherUS.groupCount(); i++) {
				phNumbers.add(matcherUS.group(i));
			}
		}
		phNumbers = resShapeList(phNumbers);

		return phNumbers;
	}

	/**
	 * This method is responsible for removing duplicate elements and reshaping
	 * a list
	 *
	 * @param rawList
	 * @return
	 */
	private List<String> resShapeList(List<String> rawList) {
		List<String> listWithUniqueSkills = rawList.stream().distinct().collect(Collectors.toList());
		if (listWithUniqueSkills.size() < 5) {
			for (int i = listWithUniqueSkills.size(); i <= 5; i++) {
				listWithUniqueSkills.add("");
			}
		}
		List<String> freshList = new ArrayList<>();
		for (String eachSkill : listWithUniqueSkills) {
			if (!"+".equalsIgnoreCase(eachSkill)) {
				freshList.add(eachSkill);
			}
		}
		return freshList;
	}

	/**
	 * Check if the String contains valid Email ID and return
	 *
	 * @param str
	 * @return
	 */
	public List<String> extractEmailIDs(String str) {
		List<String> emailIDs = new ArrayList<>();
		boolean isEmailFound = false;
		Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(str);
		while (m.find()) {
			if(!isEmailFound)
			if (null != m.group()) {
				emailIDs.add(m.group());
				if(!emailIDs.isEmpty())
				{
					isEmailFound = true;
				}
			}
		}

		return emailIDs;
	}
}
