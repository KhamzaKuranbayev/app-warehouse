package uz.pdp.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.appwarehouse.entity.InputProduct;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    @Query(value = "SELECT * FROM input_product inpt JOIN input i on i.id = inpt.input_id where date = :date", nativeQuery = true)
    List<InputProduct> getInputProductsByInputDateNative(Timestamp date);

}
