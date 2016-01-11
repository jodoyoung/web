package kr.co.anajo.web.drive;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.anajo.web.SessionContext;

@Service
public class ImageService {

	private static final Logger log = LoggerFactory.getLogger(ImageService.class);

	/** 사진 최상위 폴더 경로 */
	private static final String PICTURE_ROOT_PATH = "/opt/data/picture/";

	/** 썸네일 최상위 폴더 경로 */
	private static final String THUMBNAIL_ROOT_PATH = "/opt/data/thumbnail/";

	/**
	 * 사용자 사진 최상위 폴더 경로 조회
	 * 
	 * @return path
	 */
	private String getUserPictureRootPath() {
		return PICTURE_ROOT_PATH + SessionContext.getCurrentUser().getId();
	}

	/**
	 * 사용자 썸네일 최상위 폴더 경로 조회
	 * 
	 * @return path
	 */
	private String getUserThumbnailRootPath() {
		return THUMBNAIL_ROOT_PATH + SessionContext.getCurrentUser().getId();
	}

	/**
	 * 최상위 경로에 존재하는 폴더/파일 목록 반환
	 * 
	 * @return directories list
	 * @return file list
	 * @throws IOException
	 */
	public Map<String, Object> getDirectoryItems() throws IOException {
		return this.getDirectoryItems("");
	}

	/**
	 * 해당 경로에 존재하는 폴더/파일 목록 반환
	 * <p>
	 * 경로는 '/'로 구분(ex. 20140805/test.jpg)
	 * </p>
	 * 
	 * @param directoryPath
	 * @return directories list
	 * @return file list
	 * @throws IOException
	 */
	public Map<String, Object> getDirectoryItems(String directoryPath) throws IOException {
		Path dirPath = Paths.get(this.getUserPictureRootPath() + directoryPath);

		if (Files.notExists(dirPath, LinkOption.NOFOLLOW_LINKS)) {
			log.warn("Picture Directory Not Found. Path: {}", dirPath.toString());
			return null;
		}

		File directory = dirPath.toFile();
		File[] items = directory.listFiles();
		Arrays.sort(items); // 파일명 정렬

		List<PictureModel> directories = new ArrayList<PictureModel>();
		List<PictureModel> thumbnails = new ArrayList<PictureModel>();
		List<PictureModel> videos = new ArrayList<PictureModel>();

		PictureModel entry = null;
		for (File item : items) {
			entry = new PictureModel(item.getName(), directoryPath + "/" + item.getName());
			if (item.isDirectory()) {
				directories.add(entry);
			} else if (item.isFile()) {
				Path source = Paths.get(item.getPath());
				String contentType = Files.probeContentType(source);
				if (contentType != null) {
					String type = contentType.split("/")[0];
					if (type.equals("image")) {
						thumbnails.add(entry);
					} else if (type.equals("video")) {
						videos.add(entry);
					}
				}
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("directories", directories);
		model.put("thumbnails", thumbnails);
		model.put("videos", videos);
		model.put("directoryCount", directories.size());
		model.put("thumbnailCount", thumbnails.size());
		model.put("videoCount", videos.size());
		return model;
	}

	/**
	 * 썸네일 이미지 조회
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public Path getThumbnailImage(String path) throws IOException {
		Path thumbnailPath = Paths.get(this.getUserThumbnailRootPath() + path);

		if (Files.notExists(thumbnailPath)) { // 썸네일이 존재하지 않으면 생성한다.
			Path thumbnailDirectory = thumbnailPath.getParent();
			if (Files.notExists(thumbnailDirectory)) { // 폴더가 존재하지 않으면 생성한다.
				Files.createDirectories(thumbnailDirectory);
			}

			Path imagePath = this.getImagePath(path);
			File imageFile = imagePath.toFile();
			BufferedImage image = ImageIO.read(imageFile);
			BufferedImage resizeImage = Scalr.resize(image, 150);
			ImageIO.write(resizeImage, "jpg", thumbnailPath.toFile());
		}

		return thumbnailPath;
	}

	/**
	 * 사진 이미지 조회
	 * 
	 * @param path
	 * @return image path
	 */
	public Path getImagePath(String path) {
		return Paths.get(this.getUserPictureRootPath() + path);
	}

}
