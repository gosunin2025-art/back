package com.app.ggshop.v1.repository.Board;


import com.app.ggshop.v1.dto.board.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardDAO {

//    //    추가
//    public void save(BoardDTO boardDTO) {
//        boardtMapper.insert(boardDTO);
//    }
//
//    //    목록
//    public List<BoardDTO> findAll(Criteria criteria, Search search){
//        return postMapper.selectAll(criteria, search);
//    }
//
//    //    전체 개수
//    public int findTotal(Search search){
//        return postMapper.selectTotal(search);
//    }
//
//    //    조회
//    public Optional<BoardDTO> findById(Long id){
//        return postMapper.selectById(id);
//    }
//
//    //    수정
//    public void setPost(boardVO postVO){
//        postMapper.update(postVO);
//    }
//
//    //    삭제
//    public void delete(Long id){
//        postMapper.delete(id);
//    }
}
