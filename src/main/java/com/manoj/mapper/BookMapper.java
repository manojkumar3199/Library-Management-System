package com.manoj.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.manoj.entity.Book;
import com.manoj.payload.BookDto;

@Mapper(componentModel = "spring")
public interface BookMapper {
	BookDto bookEntityToBookDto(Book bookEntity);

	Book bookDtoToBookEntity(BookDto bookDto);

	List<BookDto> bookEntityListToBookDtoList(List<Book> bookEntityList);
}
