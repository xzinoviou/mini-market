package com.xzinoviou.minimarket.domain.jpa;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;

import com.xzinoviou.minimarket.domain.enumeration.RoleType;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "users")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ROLE_TYPE")
  @Enumerated(value = EnumType.STRING)
  private RoleType type;

  @ManyToMany
  @JoinTable(name = "ROLES_PRIVILEGES",
      joinColumns = @JoinColumn(
          name = "ROLE_ID",
          referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_ROLES_PRIVILEGES_ROLE")),
      inverseJoinColumns = @JoinColumn(
          name = "PRIVILEGE_ID",
          referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "PRIVILEGE")))
  private Collection<Privilege> privileges;

  @ManyToMany(mappedBy = "roles", fetch = LAZY, cascade = {PERSIST, DETACH, MERGE, REFRESH})
  private Collection<User> users;
}