package store.mybooks.resource.return_rule_name.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.return_name_rule.entity
 * fileName       : ReturnNameRule
 * author         : minsu11
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        minsu11       최초 생성
 */

@Getter
@Entity
@Table(name = "return_rule_name")
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_rule_name_id")
    private String id;

    @Column(name = "return_rule_name_created_date")
    private LocalDate createdDate;

}