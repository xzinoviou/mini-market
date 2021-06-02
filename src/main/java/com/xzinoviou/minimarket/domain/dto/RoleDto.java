package com.xzinoviou.minimarket.domain.dto;

import java.util.Collection;
import lombok.Builder;
import lombok.Data;

/**
 * @author : Xenofon Zinoviou
 */
@Data
@Builder
public class RoleDto {

  private Long id;

  private String type;

  private Collection<PrivilegeDto> privileges;


}
