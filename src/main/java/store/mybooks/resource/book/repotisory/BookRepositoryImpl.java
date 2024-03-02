package store.mybooks.resource.book.repotisory;

import com.querydsl.core.types.Projections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import store.mybooks.resource.author.dto.response.AuthorGetResponse;
import store.mybooks.resource.author.entity.QAuthor;
import store.mybooks.resource.book.dto.response.BookBriefResponse;
import store.mybooks.resource.book.dto.response.BookDetailResponse;
import store.mybooks.resource.book.dto.response.BookGetResponseForCoupon;
import store.mybooks.resource.book.entity.Book;
import store.mybooks.resource.book.entity.QBook;
import store.mybooks.resource.book_author.entity.QBookAuthor;
import store.mybooks.resource.book_category.dto.response.CategoryGetResponseForQuery;
import store.mybooks.resource.book_category.entity.QBookCategory;
import store.mybooks.resource.book_status.entity.QBookStatus;
import store.mybooks.resource.book_tag.entity.QBookTag;
import store.mybooks.resource.category.dto.response.CategoryGetResponseForBookCreate;
import store.mybooks.resource.publisher.dto.response.PublisherGetResponse;
import store.mybooks.resource.publisher.entity.QPublisher;
import store.mybooks.resource.tag.dto.response.TagGetResponse;
import store.mybooks.resource.tag.entity.QTag;

/**
 * packageName    : store.mybooks.resource.book.repotisory <br/>
 * fileName       : BookRepositoryImpl<br/>
 * author         : newjaehun <br/>
 * date           : 2/24/24<br/>
 * description    :<br/>
 * ===========================================================<br/>
 * DATE              AUTHOR             NOTE<br/>
 * -----------------------------------------------------------<br/>
 * 2/24/24        newjaehun       최초 생성<br/>
 */
public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookRepositoryCustom {
    public BookRepositoryImpl() {
        super(Book.class);
    }

    QBook book = QBook.book;
    QBookStatus bookStatus = QBookStatus.bookStatus;
    QAuthor author = QAuthor.author;
    QPublisher publisher = QPublisher.publisher;
    QTag tag = QTag.tag;
    QBookAuthor bookAuthor = QBookAuthor.bookAuthor;
    QBookCategory bookCategory = QBookCategory.bookCategory;
    QBookTag bookTag = QBookTag.bookTag;


    @Override
    public BookDetailResponse getBookDetailInfo(Long id) {
        BookDetailResponse result = from(book)
                .join(book.publisher, publisher).fetchJoin()
                .where(book.id.eq(id))
                .select(Projections.constructor(BookDetailResponse.class,
                        book.id, book.name, book.publishDate,
                        book.saleCost, book.originalCost, book.discountRate, book.isPackaging, book.page, book.isbn,
                        book.stock, book.index, book.content))
                .fetchOne();

        result.setBookStatus(from(book)
                .join(book.bookStatus, bookStatus)
                .where(book.id.eq(id))
                .select(Projections.constructor(String.class, bookStatus.id))
                .fetchOne());

        result.setAuthorList(from(bookAuthor)
                .join(bookAuthor.author, author)
                .where(bookAuthor.book.id.eq(id))
                .select(Projections.constructor(AuthorGetResponse.class, author.id, author.name, author.content))
                .fetch());

        result.setPublisher(from(book)
                .join(book.publisher, publisher)
                .where(book.id.eq(id))
                .select(Projections.constructor(PublisherGetResponse.class, publisher.id, publisher.name))
                .fetchOne());


        result.setTagList(from(bookTag)
                .join(bookTag.tag, tag)
                .where(bookTag.book.id.eq(id))
                .select(Projections.constructor(TagGetResponse.class, tag.id, tag.name))
                .fetch());

        List<CategoryGetResponseForQuery> categoryList = from(bookCategory)
                .where(bookCategory.book.id.eq(id))
                .select(Projections.constructor(CategoryGetResponseForQuery.class, bookCategory.category.id,
                        bookCategory.category.name, bookCategory.category.parentCategory.id))
                .fetch();

        Map<Integer, String> categoryIdAndFullNameMap = new HashMap<>();
        for (CategoryGetResponseForQuery category : categoryList) {
            StringBuilder name = new StringBuilder(category.getName());
            Integer preParentId = category.getPreParentId();
            while (preParentId != null) {
                for (CategoryGetResponseForQuery preParentCategory : categoryList) {
                    if (preParentCategory.getId().equals(preParentId)) {
                        name.insert(0, preParentCategory.getName() + "/");
                        preParentId = preParentCategory.getPreParentId();
                        break;
                    }
                }
            }
            categoryIdAndFullNameMap.put(category.getId(), name.toString());
        }

        List<CategoryGetResponseForBookCreate> categoryFullNameList = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : categoryIdAndFullNameMap.entrySet()) {
            categoryFullNameList.add(new CategoryGetResponseForBookCreate(entry.getKey(), entry.getValue()));
        }

        result.setCategoryList(categoryFullNameList);

        return result;
    }

    @Override
    public Page<BookBriefResponse> getBookBriefInfo(Pageable pageable) {
        List<BookBriefResponse> lists = getQuerydsl().applyPagination(pageable,
                        from(book)
                                .select(Projections.constructor(BookBriefResponse.class,
                                        book.id, book.name, book.saleCost)))
                .fetch();

        long total = from(book).fetchCount();

        return new PageImpl<>(lists, pageable, total);
    }

    @Override
    public List<BookGetResponseForCoupon> getBookForCoupon() {
        return from(book)
                .select(Projections.constructor(BookGetResponseForCoupon.class, book.id, book.name))
                .where(book.bookStatus.id.in("판매중", "재고없음"))
                .fetch();
    }
}
