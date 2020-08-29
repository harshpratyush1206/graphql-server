package org.oaknorth.graphql.server.entity.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

  @CreatedBy
  @JsonIgnore
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @CreatedDate
  @JsonIgnore
  @Column(name = "created_on", updatable = false)
  private LocalDateTime createdOn;

  @LastModifiedBy
  @JsonIgnore
  @Column(name = "last_modified_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @JsonIgnore
  @Column(name = "last_modified_on")
  private LocalDateTime lastModifiedOn;

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public LocalDateTime getLastModifiedOn() {
    return lastModifiedOn;
  }

  public void setLastModifiedOn(LocalDateTime lastModifiedOn) {
    this.lastModifiedOn = lastModifiedOn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Auditable auditable = (Auditable) o;
    return Objects.equals(createdBy, auditable.createdBy)
        && Objects.equals(createdOn, auditable.createdOn)
        && Objects.equals(lastModifiedBy, auditable.lastModifiedBy)
        && Objects.equals(lastModifiedOn, auditable.lastModifiedOn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdBy, createdOn, lastModifiedBy, lastModifiedOn);
  }
}
