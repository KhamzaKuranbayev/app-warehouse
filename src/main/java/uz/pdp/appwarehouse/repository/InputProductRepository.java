package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.dtos.DailyTotal;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.util.Date;
import java.util.List;


@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    @Query(value = "select sum(ip.amount) as totalAmount, sum(ip.price) as totalPrice from input_product ip " +
            "left join input i on ip.input_id = i.id where i.date between :date and now()", nativeQuery = true)
    DailyTotal findDailyInput(Date date);

    @Query(value = "select sum(op.amount) as totalAmount, sum(op.price) as totalPrice from output_product op " +
            "left join output o on op.output_id = o.id where o.date between :date and now() order by op.id desc limit 10", nativeQuery = true)
    DailyTotal findDailyOutput(Date date);


    @Query(value = "select * from input_product where expire_status=0 and expire_date between now() and :after10", nativeQuery = true)
    List<InputProduct> getWarningProducts(Date after10);


    @Query(value = "select count() from input_product where expire_status=1", nativeQuery = true)
    List<InputProduct> getAllWarningProducts();


}
