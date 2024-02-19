package store.mybooks.resource.user.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import store.mybooks.resource.user_grade.entity.UserGrade;
import store.mybooks.resource.user_status.entity.UserStatus;

/**
 * packageName    : store.mybooks.resource.user.dto.response
 * fileName       : UserGetResponse
 * author         : masiljangajji
 * date           : 2/16/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/16/24        masiljangajji       최초 생성
 */


public interface UserGetResponse {

    String getUserGradeName();

    String getUserStatusId();

    String getName();

    String getPhoneNumber();

    String getEmail();

    LocalDate getBirth();

    LocalDateTime getCreatedAt();

    LocalDateTime getLatestLogin();

    LocalDate getGradeChangedDate();

}