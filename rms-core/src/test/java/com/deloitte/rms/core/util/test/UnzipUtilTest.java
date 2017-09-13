package com.deloitte.rms.core.util.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.core.util.UnzipUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UnzipUtilTest {
	@Autowired UnzipUtil unzipUtil;
	InputStream is = null;

	@Before
	public void setUp() {
		try {
			is = new FileInputStream(new File("src/test/resources/data/ReferralsTest.zip"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUnzipUtil() throws IOException {
		List<Attachment> attachments = unzipUtil.unzip(is);
		Assert.assertEquals(35, attachments.size());
	}
}
