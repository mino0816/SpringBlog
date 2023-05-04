package com.mion.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mion.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{


}
