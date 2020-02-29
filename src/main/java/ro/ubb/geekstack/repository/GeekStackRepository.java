package ro.ubb.geekstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.geekstack.models.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface GeekStackRepository<T extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<T, ID> {

}