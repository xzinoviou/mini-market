package com.xzinoviou.minimarket.domain.jpa;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;

import com.xzinoviou.minimarket.domain.enumeration.PrivilegeType;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Xenofon Zinoviou
 */
@Entity
@Table(name = "PRIVILEGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "roles")
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "PRIVILEGE_TYPE")
  @Enumerated(value = EnumType.STRING)
  private PrivilegeType type;

  @ManyToMany(mappedBy = "privileges", fetch = LAZY, cascade = {PERSIST, DETACH, MERGE, REFRESH})
  private Collection<Role> roles;
}