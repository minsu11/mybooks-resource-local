package store.mybooks.resource.return_name_rule.dto.mapper;

import org.mapstruct.Mapper;
import store.mybooks.resource.return_name_rule.dto.response.ReturnNameRuleResponse;
import store.mybooks.resource.return_name_rule.entity.ReturnNameRule;

/**
 * packageName    : store.mybooks.resource.return_name_rule.dto.mapper<br>
 * fileName       : ReturnNameRuleMapper<br>
 * author         : minsu11<br>
 * date           : 2/20/24<br>
 * description    : 반품 규정 명 규칙 엔티티를 mapStruct를 사용하여 ReturnNameRuleResponse로 반환<br>
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/20/24        minsu11       최초 생성<br>
 */
@Mapper(componentModel = "spring")
public interface ReturnNameRuleMapper {
    ReturnNameRuleResponse mapToReturnNameRuleResponse(ReturnNameRule returnNameRule);
}
