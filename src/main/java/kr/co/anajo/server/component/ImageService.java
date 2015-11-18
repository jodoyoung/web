package kr.co.anajo.server.component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import kr.co.anajo.server.SessionContext;
import kr.co.anajo.server.presentation.model.PictureModel;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

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

		File directory = dirPath.toFile();
		File[] items = directory.listFiles();
		Arrays.sort(items); // 파일명 정렬

		List<PictureModel> directories = new ArrayList<PictureModel>();
		List<PictureModel> thumbnails = new ArrayList<PictureModel>();

		for (File item : items) {
			if (item.isDirectory()) {
				directories.add(new PictureModel(item.getName(), directoryPath + "/" + item.getName()));
			} else if (item.isFile()) {
				Path source = Paths.get(item.getPath());
				String contentType = Files.probeContentType(source);
				if (contentType != null) {
					String type = contentType.split("/")[0];
					if (type.equals("image")) {
						thumbnails.add(new PictureModel(item.getName(), directoryPath + "/" + item.getName()));
					}
				}
			}
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("directories", directories);
		model.put("thumbnails", thumbnails);
		model.put("directoryCount", directories.size());
		model.put("thumbnailCount", thumbnails.size());
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
