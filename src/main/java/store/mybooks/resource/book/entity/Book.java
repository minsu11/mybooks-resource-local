package store.mybooks.resource.book.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.mybooks.resource.book_status.entity.BookStatus;
import store.mybooks.resource.publisher.entity.Publisher;

/**
 * packageName    : store.mybooks.resource.book.entity
 * fileName       : Book
 * author         : newjaehun
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        newjaehun       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_status_id")
    private BookStatus bookStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "book_name")
    private String name;

    @Column(name = "book_isbn")
    private String isbn;

    @Column(name = "book_publish_date")
    private LocalDate publishDate;

    @Column(name = "book_page")
    private Integer page;

    @Column(name = "book_index")
    private String index;

    @Column(name = "book_content")
    private String content;

    @Column(name = "book_original_cost")
    private Integer originalCost;

    @Column(name = "book_sale_cost")
    private Integer saleCost;

    @Column(name = "book_discount_rate")
    private Integer discountRate;

    @Column(name = "book_stock")
    private Integer stock;

    @Column(name = "book_view_count")
    private Integer viewCount;

    @Column(name = "is_packaging")
    private Boolean isPackaging;

    @Column(name = "book_created_date")
    private LocalDate createdDate;


    /**
     * Instantiates a new Book.
     *
     * @param bookStatus   the book status
     * @param publisher    the publisher
     * @param name         the name
     * @param isbn         the isbn
     * @param publishDate  the publishDate
     * @param page         the page
     * @param index        the index
     * @param content      the content
     * @param originalCost the original cost
     * @param saleCost     the sale cost
     * @param discountRate the discount rate
     * @param stock        the stock
     * @param isPackaging  the is packaging
     */
    public Book(BookStatus bookStatus, Publisher publisher, String name, String isbn, LocalDate publishDate,
                Integer page, String index, String content, Integer originalCost, Integer saleCost,
                Integer discountRate, Integer stock, Boolean isPackaging) {
        this.bookStatus = bookStatus;
        this.publisher = publisher;
        this.name = name;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.page = page;
        this.index = index;
        this.content = content;
        this.originalCost = originalCost;
        this.saleCost = saleCost;
        this.discountRate = discountRate;
        this.stock = stock;
        this.viewCount = 0;
        this.isPackaging = isPackaging;
        this.createdDate = LocalDate.now();
    }

    /**
     * methodName : setModifyRequest
     * author : newjaehun
     * description : 도서 객체를 수정하는 메서드.
     *
     * @param bookStatus   bookstatus
     * @param saleCost     saleCost
     * @param discountRate discountRate
     * @param stock        stock
     * @param isPackaging  isPackaging
     */
    public void setModifyRequest(BookStatus bookStatus, Integer saleCost, Integer discountRate, Integer stock,
                                 Boolean isPackaging) {
        this.bookStatus = bookStatus;
        this.saleCost = saleCost;
        this.discountRate = discountRate;
        this.stock = stock;
        this.isPackaging = isPackaging;

    }
}
