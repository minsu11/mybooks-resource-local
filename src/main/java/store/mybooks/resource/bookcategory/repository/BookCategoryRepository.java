package store.mybooks.resource.bookcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.mybooks.resource.bookcategory.entity.BookCategory;

/**
 * packageName    : store.mybooks.resource.book_category.repository
 * fileName       : BookCategoryRepository
 * author         : damho-lee
 * date           : 2/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/22/24          damho-lee          최초 생성
 */
public interface BookCategoryRepository extends JpaRepository<BookCategory, BookCategory.Pk> {
    boolean existsByPk_BookId(Long bookId);

    void deleteByPk_BookId(Long bookId);
}