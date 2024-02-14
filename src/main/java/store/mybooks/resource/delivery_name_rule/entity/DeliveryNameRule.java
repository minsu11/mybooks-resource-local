package store.mybooks.resource.delivery_name_rule.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * packageName    : store.mybooks.resource.delivery_name_rule
 * fileName       : DeliveryNameRule
 * author         : Fiat_lux
 * date           : 2/14/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/14/24        Fiat_lux       최초 생성
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "delivery_name_rule")
public class DeliveryNameRule {

    @Id
    @Column(name = "delivery_name_rule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delivery_name")
    private String name;

    @Column(name = "delivery_name_created_at")
    private LocalDate createdAt;

}
