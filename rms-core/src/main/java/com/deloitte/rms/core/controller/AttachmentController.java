package com.deloitte.rms.core.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.core.service.AttachmentService;

@RestController
@RequestMapping("/rest/secured/document")
public class AttachmentController {
	@Autowired AttachmentService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void download(@PathVariable(name="id") String id, HttpServletResponse response) {
		Attachment attachment = service.findById(id);
		try {
			response.setContentType("application/msword");            
			response.setHeader("Content-disposition", "attachment; filename="+ attachment.getName()+"."+attachment.getExtn());
			response.getOutputStream().write(attachment.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/reference/{referenceId}", method = RequestMethod.GET)
	public Collection<Attachment> getAttachmentsByRef(@PathVariable(name="referenceId") Long referenceId) {
		return service.findByReferenceId(referenceId);
	}
}
