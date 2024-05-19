package com.commic.v1.services.search;

import com.commic.v1.dto.DataListResponse;
import com.commic.v1.dto.responses.BookResponseDTO;
import com.commic.v1.dto.responses.CategoryResponseDTO;
import com.commic.v1.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ISearchServices {
    DataListResponse<BookResponseDTO> getBook(String containName, Integer categoryId, Pageable pageable);

    DataListResponse<BookResponseDTO> getRankBy(String type, Pageable pageable);

    DataListResponse<BookResponseDTO> getRankBy(String type, Integer categoryId, Pageable pageable);

//    Lấy danh sách thể loại
    List<CategoryResponseDTO> getCategory();

//    Lấy danh sách truyện theo ngày ra mắt mới nhất của chapter đầu tiên
    DataListResponse<BookResponseDTO> getComicByPublishDate(Pageable pageable);

    List<BookResponseDTO> getAllBook(Sort pageable);

    BookResponseDTO getBookById(Integer id);
}
