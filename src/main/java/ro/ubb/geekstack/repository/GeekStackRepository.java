package ro.ubb.geekstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.geekstack.models.BaseEntity;

import java.io.Serializable;

public interface GeekStackRepository<T extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<T, ID> {

}