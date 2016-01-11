package kr.co.anajo.web.drive;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping("/file/read")
	@ResponseBody
	public byte[] get(String id) throws Exception {
		File file = this.fileService.get(id);
		Path path = Paths.get(file.getPath());
		return Files.readAllBytes(path);
	}

	@RequestMapping("/file/upload")
	public String uploadNormal(MultipartFile file, String path) throws Exception {
		if (file.isEmpty()) {
			throw new FileNotFoundException("empty file");
		}

		return this.fileService.upload(file, path);
	}

	@RequestMapping("/file/upload/temp")
	public String uploadTemp(MultipartFile file, String path) throws Exception {
		if (file.isEmpty()) {
			throw new FileNotFoundException("empty file");
		}

		return this.fileService.upload(file, path, true);
	}

}