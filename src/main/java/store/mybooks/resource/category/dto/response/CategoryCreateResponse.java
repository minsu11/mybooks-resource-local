package store.mybooks.resource.category.dto.response;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : store.mybooks.resource.category.dto.response
 * fileName       : CategoryCreateResponse
 * author         : damho-lee
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24          damho-lee          최초 생성
 */
@Getter
@Builder
public class CategoryCreateResponse {
    private CategoryCreateResponse parentCategory;
    private String name;
}
