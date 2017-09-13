package com.deloitte.rms.core.service.repo.test;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.type.ContentType;
import com.deloitte.rms.persistence.mongo.entity.AttachmentDetailEntity;
import com.deloitte.rms.persistence.repo.AttachmentDetailRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoRepoTest {
	private static final Logger LOG = Logger.getLogger(MongoRepoTest.class);

	@Autowired AttachmentDetailRepository repo;

	@Test
	public void testRetrieveDocument() {
		AttachmentDetailEntity entity = new AttachmentDetailEntity();
		entity.setContentType(ContentType.WORD);
		entity.setExtn("doc");
		entity.setName("Test");
		entity.setReferenceId(101L);
		repo.save(entity);

		Collection<AttachmentDetailEntity> entities = repo.findByReferenceId(101L);
		Assert.assertTrue(!entities.isEmpty());
		AttachmentDetailEntity firstElem = entities.iterator().next();
		Assert.assertEquals("Test", firstElem.getName());
		LOG.error("Retrieved Name: "+firstElem.getName());
	}
}
