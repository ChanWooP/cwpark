package change.company.cwpark.data.entity;

import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime reg_date;

    @CreatedBy
    @Column(updatable = false)
    private String reg_id;

    @LastModifiedDate
    private LocalDateTime upd_date;

    @LastModifiedBy
    private String upd_id;
}