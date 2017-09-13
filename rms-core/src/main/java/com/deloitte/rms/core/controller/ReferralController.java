package com.deloitte.rms.core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.PageResponse;
import com.deloitte.rms.api.Referral;
import com.deloitte.rms.api.SearchRequest;
import com.deloitte.rms.core.exception.RmsException;
import com.deloitte.rms.core.service.ReferralService;
import com.deloitte.rms.core.util.ExcelUtil;
import com.deloitte.rms.core.util.UnzipUtil;

@RestController
@RequestMapping("/rest/secured/referral")
public class ReferralController {
	@Autowired ReferralService rmsService;
	@Autowired ExcelUtil excelUtil;
	@Autowired UnzipUtil unzipUtil;

	@ExceptionHandler(RmsException.class)
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public List<Referral> upload(@RequestPart("exports") MultipartFile multipartFile,
			@RequestPart("resume") MultipartFile multipartResume) {
		List<Referral> fails = new ArrayList<>();
		try {
			List<Referral> referrals = excelUtil.readReferrals(multipartFile.getInputStream());
			List<Attachment> files = unzipUtil.unzip(multipartResume.getInputStream());
			fails = rmsService.saveReferral(referrals, files);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fails;
	}

	@RequestMapping(method = RequestMethod.POST)
	public PageResponse<Referral> getReferrals(@RequestBody SearchRequest search) {
		return rmsService.getReferrals(search);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Referral getReferrals(@PathVariable Long id) {
		return rmsService.getReferralDetail(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Referral update(@PathVariable Long id, @RequestBody Referral referral) {
		return rmsService.update(referral);
	}
}
