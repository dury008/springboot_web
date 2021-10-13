package org.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity클래스들이 이클래스를 상속할 경우 createdDate,modifiedDate도 칼럼으로 인식하도록함
@EntityListeners(AuditingEntityListener.class) // 이클래스에 Auditing기능을 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate //엔티티가 생성되어 저장될때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 엔티티의 값을 변경할때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;
}
