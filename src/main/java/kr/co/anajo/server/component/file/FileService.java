package kr.co.anajo.server.component.file;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileService.class);

	private static final String ROOT_DIRECTORY = "/home/anajo/file";

	public void indexingDirectory() {
		FileIndexer walk = new FileIndexer();
		EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		try {
			Files.walkFileTree(Paths.get(ROOT_DIRECTORY), opts, Integer.MAX_VALUE, walk);
		} catch (IOException ioe) {
			logger.error("indexing error", ioe);
		}
	}
}
