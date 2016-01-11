package kr.co.anajo.web.drive;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.anajo.web.util.IdGenerator;

@Service
public class FileService {

	private static final String ATTACH_LOCATION = Paths.get("/opt/data").toString();

	private FileRepository fileRepository;

	public File get(String id) {
		return this.fileRepository.findOne(id);
	}

	public String upload(MultipartFile file, String path) throws Exception {
		return this.upload(file, path, false);
	}

	public String upload(MultipartFile file, String path, boolean isTemp) throws Exception {
		String id = IdGenerator.createUUID();
		String name = file.getName();
		Path fullPath = Paths.get(ATTACH_LOCATION, path, id);

		BufferedOutputStream out = null;
		InputStream in = null;
		try {
			in = file.getInputStream();
			out = new BufferedOutputStream(new FileOutputStream(fullPath.toFile()));
			IOUtils.copy(in, out);
		} catch (IOException e) {
			throw new Exception("file write failed", e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}

		File attach = new File();
		attach.setId(id);
		attach.setName(name);
		attach.setPath(fullPath.toString());
		attach.setTemp(true);

		this.fileRepository.insert(attach);

		return id;
	}

}