package store.mybooks.resource.category.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.mybooks.resource.category.dto.request.CategoryCreateRequest;
import store.mybooks.resource.category.dto.response.CategoryGetResponse;
import store.mybooks.resource.category.entity.Category;

/**
 * packageName    : store.mybooks.resource.category.repository
 * fileName       : CategoryRepositoryTest
 * author         : damho
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24          damho-lee          최초 생성
 */
@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    Category actualParentCategory;
    Category actualChildCategory;

    @BeforeEach
    @Transactional
    void setup() {
        CategoryCreateRequest parentCategoryCreateRequest = new CategoryCreateRequest(null, "firstCategory");
        Category parentCategory = new Category(parentCategoryCreateRequest);
        actualParentCategory = categoryRepository.save(parentCategory);

        CategoryCreateRequest childCategoryCreateRequest = new CategoryCreateRequest(parentCategory, "secondCategory");
        Category childCategory = new Category(childCategoryCreateRequest);
        actualChildCategory = categoryRepository.save(childCategory);

    }

    @Test
    @Transactional
    void givenCategory_whenFindHighestCategoryList_thenReturnHighestCategoryGetResponseList() {
        List<CategoryGetResponse> highestCategoryList = categoryRepository.findAllByParentCategoryIsNull();
        assertThat(highestCategoryList).hasSize(1);

        CategoryGetResponse parentActual = highestCategoryList.get(0);
        assertThat(parentActual.getName()).isEqualTo("firstCategory");
        assertThat(parentActual.getParentCategory()).isNull();
    }

    @Test
    @Transactional
    void givenCategory_whenFindCategoryListByParentCategoryId_thenReturnCategoryGetResponseList() {
        List<CategoryGetResponse> childCategoryList =
                categoryRepository.findAllByParentCategory_Id(actualParentCategory.getId());

        assertThat(childCategoryList).isNotNull();
        assertThat(childCategoryList).hasSize(1);
        assertThat(childCategoryList.get(0).getName()).isEqualTo("secondCategory");
        assertThat(childCategoryList.get(0).getParentCategory().getId()).isEqualTo(actualParentCategory.getId());
    }
}