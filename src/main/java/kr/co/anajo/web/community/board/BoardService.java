package kr.co.anajo.web.community.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public void create(String id, String name) {
		Board board = new Board();
		board.setId(id);
		board.setName(name);
		this.boardRepository.insert(board);
	}

	public Board read(String id) {
		return this.boardRepository.findOne(id);
	}
	
	public List<Board> readAll() {
		return this.boardRepository.findAll();
	}

	public void update(String id, String name) {
		Board board = this.read(id);
		board.setName(name);
		this.boardRepository.save(board);
	}

	public void delete(String id) {
		this.boardRepository.delete(id);
	}

}
