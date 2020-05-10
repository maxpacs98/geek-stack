package ro.ubb.geekstack.iterators;

import ro.ubb.geekstack.models.BaseEntity;

public interface IIterator<T extends BaseEntity<Long>> {

    T getCurrent();

    void next();

    Boolean isFinished();
}
