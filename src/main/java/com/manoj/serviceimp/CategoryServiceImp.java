package com.manoj.serviceimp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manoj.entity.Book;
import com.manoj.entity.Category;
import com.manoj.entity.IssueBook;
import com.manoj.exception.CustomResourceNotFoundException;
import com.manoj.exception.ForeignKeyConstraintException;
import com.manoj.exception.ResourceDuplicateException;
import com.manoj.mapper.CategoryMapper;
import com.manoj.payload.CategoryDto;
import com.manoj.repository.BookRepo;
import com.manoj.repository.CategoryRepo;
import com.manoj.repository.IssueBookRepo;
import com.manoj.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

	private CategoryRepo categoryRepo;
	private BookRepo bookRepo;
	private IssueBookRepo issueBookRepo;
	private CategoryMapper categoryMapper;
	private String uploadImagesDir;

	public CategoryServiceImp(CategoryRepo categoryRepo, BookRepo bookRepo, IssueBookRepo issueBookRepo,
			CategoryMapper categoryMapper, @Value("${project.upload.images}") String uploadImagesDir) {
		super();
		this.categoryRepo = categoryRepo;
		this.bookRepo = bookRepo;
		this.issueBookRepo = issueBookRepo;
		this.categoryMapper = categoryMapper;
		this.uploadImagesDir = uploadImagesDir;
	}

	@Override
	public List<CategoryDto> getAllCategory(Integer pageSize, Integer pageNumber) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Category> pageCategories = categoryRepo.findAll(p);
		List<CategoryDto> categories = categoryMapper.categoryEntityListToCategoryDtoList(pageCategories.getContent());
		if (categories.isEmpty())
			throw new CustomResourceNotFoundException("categories list is empty!");
		return categories;
	}

	@Override
	public CategoryDto getCategory(Long id) {
		Category categoryEnt = categoryRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("category with id " + id + " does't exist!"));
		return categoryMapper.categoryEntityToCategoryDto(categoryEnt);
	}

	@Override
	public CategoryDto saveCategory(CategoryDto c) {
		Optional<Category> categoryExist = categoryRepo.findByCategoryName(c.getCategoryName());
		if (categoryExist.isPresent())
			throw new ResourceDuplicateException(c.getCategoryName() + " category already exist!");
		return categoryMapper
				.categoryEntityToCategoryDto(categoryRepo.save(categoryMapper.categoryDtoToCategoryEntity(c)));
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
		Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(
				() -> new CustomResourceNotFoundException("category with id " + categoryId + " does't exist!"));

		savedCategory.setShortName(categoryDto.getShortName());

		return categoryMapper.categoryEntityToCategoryDto(categoryRepo.save(savedCategory));
	}

	@Override
	public CategoryDto updateCategoryName(CategoryDto categoryDto, Long categoryId) {
		Category savedCategory = categoryRepo.findById(categoryId).orElseThrow(
				() -> new CustomResourceNotFoundException("category with id " + categoryId + " does't exist!"));

		Optional<Category> categoryExist = categoryRepo.findByCategoryName(categoryDto.getCategoryName());
		if (categoryExist.isPresent())
			throw new ResourceDuplicateException(categoryDto.getCategoryName() + " category already exist!");

		savedCategory.setCategoryName(categoryDto.getCategoryName());

		return categoryMapper.categoryEntityToCategoryDto(categoryRepo.save(savedCategory));
	}

	@Override
	public void deleteCategory(Long id) throws IOException {
		Category savedCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new CustomResourceNotFoundException("category with id " + id + " does't exist!"));

		Set<Book> books = bookRepo.findByCategory(savedCategory);
		books.forEach((book) -> {
			Optional<IssueBook> issueBook = issueBookRepo.findByBook(book);
			if (issueBook.isPresent()) {
				throw new ForeignKeyConstraintException("can't delete this category!",
						"book with book id " + issueBook.get().getBook().getId()
								+ " with this category is issued to student with id "
								+ issueBook.get().getStudent().getId());
			}
		});

		if (!books.isEmpty()) {
			for (Book book : books) {
				if (book.getImage() != null)
					Files.delete(Paths.get(uploadImagesDir + File.separator + book.getImage()));
				bookRepo.delete(book);
			}
		}

		categoryRepo.delete(savedCategory);
	}

}
