package com.deloitte.rms.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Component;

import com.deloitte.rms.api.Attachment;
import com.deloitte.rms.api.type.ContentType;

@Component
public class UnzipUtil {

	public static void main(String[] args) throws Exception {
		UnzipUtil util = new UnzipUtil();
		DocumentParserUtil documentParserUtil = new DocumentParserUtil();
		InputStream is = new FileInputStream(new File("C:\\Users\\prasengupta\\Desktop\\Referral Profiles.zip"));
		List<Attachment> files = util.unzip(is);
		for (Attachment file : files) {
			//System.out.println(file.getName()+"\t\t\t"+file.getContentType());
			//System.out.println(new String(file.getContent()));
			//break;
			System.out.println(file.getName());
			//documentParserUtil.updateCandidateRecords(file, new Candidate());
		}
	}

	public List<Attachment> unzip(InputStream is) throws IOException {
		ZipInputStream zis = new ZipInputStream(is);
		List<Attachment> files = new ArrayList<>();
		ZipEntry ze;
		while ((ze = zis.getNextEntry()) != null) {
			if (ze.isDirectory()) {
				continue;
			}

			byte[] buf = new byte[1024];
			int read = -1;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while ((read = zis.read(buf)) != -1) {
				os.write(buf, 0, read);
			}
			Attachment fileContent = new Attachment();

			String fileName = ze.getName().replaceAll("^([^\\/]*)([\\/])*(.+)$", "$3");
			String extn = fileName.substring(fileName.lastIndexOf(".")+1);
			fileName = fileName.substring(0, fileName.lastIndexOf("."));

			fileContent.setName(fileName);
			fileContent.setContent(os.toByteArray());
			fileContent.setExtn(extn);
			String contentType = URLConnection.guessContentTypeFromName(fileContent.getName());
			contentType = contentType != null ? contentType : URLConnection.guessContentTypeFromStream(zis);
			contentType = contentType == null ? extn : contentType;
			fileContent.setContentType(ContentType.getByType(contentType));
			os.close();
			files.add(fileContent);
		}
		zis.closeEntry();
		zis.close();

		return files;
	}
}
