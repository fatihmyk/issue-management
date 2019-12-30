package com.fatihmayuk.issuemanagement.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

//@Data  @Getter ve @Setter ile aynı işi yapar.
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(name = "updated_by", length = 50)
    private String updateBy;

    @Column(name = "status")
    private Boolean status;

}
