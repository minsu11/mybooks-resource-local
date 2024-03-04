package store.mybooks.resource.book_tag.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import store.mybooks.resource.book_tag.dto.request.BookTagCreateRequest;
import store.mybooks.resource.book_tag.service.BookTagService;

/**
 * packageName    : store.mybooks.resource.book_tag.controller
 * fileName       : BookTagRestControllerTest
 * author         : damho-lee
 * date           : 2/29/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/29/24          damho-lee          최초 생성
 */
@WebMvcTest(BookTagRestController.class)
@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
class BookTagRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookTagService bookTagService;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("createBookTag 테스트")
    void givenBookTagCreateRequest_whenCreateBookTag_thenReturnStatusCodeOk() throws Exception {
        List<Integer> tagIdList = new ArrayList<>();
        tagIdList.add(1);
        tagIdList.add(2);
        tagIdList.add(3);
        BookTagCreateRequest bookTagCreateRequest = new BookTagCreateRequest(1L, tagIdList);
        String content = objectMapper.writeValueAsString(bookTagCreateRequest);
        doNothing().when(bookTagService).createBookTag(any());
        mockMvc.perform(post("/api/book-tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andDo(document("book_tag-create",
                        requestFields(
                                fieldWithPath("bookId").description("도서 ID"),
                                fieldWithPath("tagIdList").description("태그 ID 리스트")
                        )));
        verify(bookTagService, times(1)).createBookTag(any());
    }

    @Test
    @DisplayName("deleteBookTag")
    void givenBookId_whenDeleteBookTag_thenReturnStatusCodeOk() throws Exception {
        doNothing().when(bookTagService).deleteBookTag(anyLong());
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/api/book-tag/{bookId}", 1))
                .andExpect(status().isOk())
                .andDo(document("book_tag-delete",
                        pathParameters(
                                parameterWithName("bookId").description("도서 ID")
                        )));

        verify(bookTagService, times(1)).deleteBookTag(1L);
    }
}