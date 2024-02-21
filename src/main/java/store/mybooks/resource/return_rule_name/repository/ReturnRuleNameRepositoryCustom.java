package store.mybooks.resource.return_rule_name.repository;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;
import store.mybooks.resource.return_rule_name.dto.response.ReturnRuleNameResponse;

/**
 * packageName    : store.mybooks.resource.return_rule_name.repository<br>
 * fileName       : ReturnRuleNameRepositoryCustom<br>
 * author         : minsu11<br>
 * date           : 2/21/24<br>
 * description    :
 * ===========================================================<br>
 * DATE              AUTHOR             NOTE<br>
 * -----------------------------------------------------------<br>
 * 2/21/24        minsu11       최초 생성<br>
 */
@NoRepositoryBean
public interface ReturnRuleNameRepositoryCustom {
    List<ReturnRuleNameResponse> getReturnRuleNameList();
}
