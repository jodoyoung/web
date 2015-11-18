package kr.co.anajo.server.component.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 특정 디렉토리 하위로 탐색하며 DB 자원으로 인덱싱.
 * 
 * @author jodoyoung
 *
 */
public class FileIndexer implements FileVisitor<Path> {

	private static final Logger logger = LoggerFactory.getLogger(FileIndexer.class);

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		logger.debug("visit file > {}", file);
		return null;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
