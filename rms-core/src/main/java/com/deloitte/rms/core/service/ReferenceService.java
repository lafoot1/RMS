package com.deloitte.rms.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.rms.api.reference.PostalCode;
import com.deloitte.rms.persistence.mapper.PostalCodeMapper;
import com.deloitte.rms.persistence.repo.PostcodeRepository;

@Service
public class ReferenceService {
	@Autowired PostcodeRepository postcodeRepository;
	@Autowired PostalCodeMapper postalCodeMapper;
	
	public PostalCode getByPincode(Integer pincode) {
		return postalCodeMapper.map(postcodeRepository.findByPincode(pincode));
	}
}
