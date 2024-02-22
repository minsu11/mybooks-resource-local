package store.mybooks.resource.delivery_rule.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.mybooks.resource.delivery_rule_name.entity.DeliveryRuleName;


/**
 * packageName    : store.mybooks.resource.delivery_rule.dto
 * fileName       : DeliveryRuleResponse
 * author         : Fiat_lux
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24        Fiat_lux       최초 생성
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRuleResponse {

    private Integer id;

    private String companyName;

    private Integer cost;

    private Integer ruleCost;

    private LocalDate createdDate;

    private Integer isAvailable;
}