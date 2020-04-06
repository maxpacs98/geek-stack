package ro.ubb.geekstack.models;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue()
    private ID id;
}