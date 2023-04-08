package domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class baseEntity {
    private String createdBy;
}
