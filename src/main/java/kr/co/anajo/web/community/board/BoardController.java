package kr.co.anajo.web.community.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/board/create")
	public String create(String id, String name) {
		this.boardService.create(id, name);
		return "success";
	}

	@RequestMapping("/board/read")
	public Board read(String id) {
		return this.boardService.read(id);
	}

	@RequestMapping("/board/list")
	public List<Board> list() {
		return this.boardService.readAll();
	}

	@RequestMapping("/board/update")
	public String update(String id, String name) {
		this.boardService.update(id, name);
		return "success";
	}
}