package com.mion.blog.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mion.blog.dto.ReplySaveRequestDto;
import com.mion.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying //인설트시 필요
	@Query(value = "INSERT INTO reply  (userId, boardId, content,createDate) values(?, ?, ?, now())", nativeQuery = true)
	int msave(int userId, int boardId, String content);
}
