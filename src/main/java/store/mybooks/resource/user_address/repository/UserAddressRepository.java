package store.mybooks.resource.user_address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.mybooks.resource.user_address.entity.UserAddress;

/**
 * packageName    : store.mybooks.resource.user_address.repository
 * fileName       : UserAddressRepository
 * author         : masiljangajji
 * date           : 2/13/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/13/24        masiljangajji       최초 생성
 */
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
}