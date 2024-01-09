package SAGroup.webShop.repository;

import SAGroup.webShop.model.CheckoutHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutHistoryRepo extends JpaRepository<CheckoutHistory, Long> {

    List<CheckoutHistory> findByUser(String user);
}
