package com.mion.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mion.blog.dto.ReplySaveRequestDto;
import com.mion.blog.model.Board;
import com.mion.blog.model.Reply;
import com.mion.blog.model.User;
import com.mion.blog.repository.BoardRepository;
import com.mion.blog.repository.ReplyRepository;
import com.mion.blog.repository.UserRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private UserRepository userRepository;

	
	//글쓰기
	@Transactional
	public void boardSave(Board board,User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	//글목록
	@Transactional(readOnly = true)
	public Page<Board> list(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패 : 해당 글을 찾을 수 없습니다.");
				});
	}
	
	//글 삭제하기
	@Transactional
	public void boardDelete(int id) {
		boardRepository.deleteById(id);
	}
	
	//글 수정하기
	@Transactional
	public void boardUpdate(int id, Board requestBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 수정 실패 : 해당 글을 찾을 수 없습니다.");
				});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}
	//댓글 작성
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
	
		replyRepository.msave(replySaveRequestDto.getUserId(), replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent());
	}
	
	//댓글 삭제
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}
}
