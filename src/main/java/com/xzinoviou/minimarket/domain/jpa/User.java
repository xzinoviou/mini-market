package com.xzinoviou.minimarket.domain.jpa;

import java.time.OffsetDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * @author : Xenofon Zinoviou
 */
@Entity
@Table(name = "USER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "USERNAME", unique = true)
  private String username;

  @Column(name = "PASS")
  private String password;

  @Column(name = "EMAIL", unique = true)
  private String email;

  @Column(name = "ENABLED")
  private boolean enabled;

  @Column(name = "LOCKED")
  private boolean locked;

  @Column(name = "BIRTH_DATE")
  private OffsetDateTime birthDate;

  @Column(name = "CREATION_DATE")
  private OffsetDateTime creationDate;

  @Column(name = "UPDATE_DATE")
  private OffsetDateTime updateDate;

  @Column(name = "EXPIRED")
  private boolean expired;

  @Column(name = "TOKEN_EXPIRED")
  private boolean tokenExpired;

  @ManyToMany
  @JoinTable(
      name = "USERS_ROLES",
      joinColumns = @JoinColumn(
          name = "USER_ID",
          referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_USERS_ROLES_USER")
      ),
      inverseJoinColumns = @JoinColumn(
          name = "ROLE_ID",
          referencedColumnName = "ID",
          foreignKey = @ForeignKey(name = "FK_USERS_ROLES_ROLE")
      )
  )
  private Collection<Role> roles;

}



