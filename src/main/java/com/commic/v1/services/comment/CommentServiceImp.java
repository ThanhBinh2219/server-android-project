package com.commic.v1.services.comment;

import com.commic.v1.dto.CommentDTO;
import com.commic.v1.dto.requests.CommentCreationRequestDTO;
import com.commic.v1.dto.responses.CommentCreationResponseDTO;
import com.commic.v1.entities.Comment;
import com.commic.v1.exception.AppException;
import com.commic.v1.exception.ErrorCode;
import com.commic.v1.mapper.CommentMapper;
import com.commic.v1.repositories.IChapterRepository;
import com.commic.v1.repositories.ICommentRepository;
import com.commic.v1.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements ICommentServices {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    IChapterRepository chapterRepository;
    @Autowired
    ICommentRepository commentRepository;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentCreationResponseDTO create(CommentCreationRequestDTO requestDTO) {
        if (!userRepository.existsUserById(requestDTO.getUserId())) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        if (!chapterRepository.existsChapterById(requestDTO.getChapterId())) {
            throw new AppException(ErrorCode.CHAPTER_NOT_FOUND);
        }
        Comment comment = commentMapper.toComment(requestDTO);
        comment.setCreatedAt(new Date(System.currentTimeMillis()));
        comment.setState(CommentConst.UN_HIDE.getValue());
        comment = commentRepository.save(comment);
        CommentCreationResponseDTO responseDTO = commentMapper.toCommentCreationRequestDTO(comment);
        return responseDTO;
    }

    @Override
    public List<CommentDTO> getComment(Integer idChapter) {
        try {
            List<Comment> comments = commentRepository.findByChapterId(idChapter);
            return comments.stream()
                    .map(commentMapper::toCommentDTOs)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

}
