package com.commic.v1.services.chapter;

import com.commic.v1.dto.responses.ChapterContentRespone;
import com.commic.v1.entities.Chapter;
import com.commic.v1.entities.ChapterContent;
import com.commic.v1.mapper.ChapterMapper;
import com.commic.v1.repositories.IChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterContentServiceImpl implements IChapterContentService {
    @Autowired
    private IChapterRepository chapterRepository;
    @Autowired
    private ChapterMapper chapterMapper;
    @Override
    public List<ChapterContentRespone> getChapterContent(Integer id) {
        List<ChapterContent> chapters = chapterRepository.findByChapterId(id);

        if (chapters.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No chapters found for id: " + id);
        }

        return chapters.stream()
                .map(chapterMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}