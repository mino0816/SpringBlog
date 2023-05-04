package com.mion.blog.Controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mion.blog.config.auth.PrincipalDetail;
import com.mion.blog.dto.ReplySaveRequestDto;
import com.mion.blog.dto.ResponseDto;
import com.mion.blog.model.Board;
import com.mion.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	BoardService boardService;
	
 
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
			
			boardService.boardSave(board, principal.getUser());
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	//글 삭제하기
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.boardDelete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	//글 수정하기
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		boardService.boardUpdate(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	
	//댓글 작성하기
	//데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	//
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.댓글쓰기(replySaveRequestDto);
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	//댓글 삭제하기  주소를 받기위해 만듬
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
