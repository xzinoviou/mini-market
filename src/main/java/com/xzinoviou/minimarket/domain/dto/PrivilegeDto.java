package com.xzinoviou.minimarket.domain.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author : Xenofon Zinoviou
 */
@Data
@Builder
public class PrivilegeDto {

  private Long id;

  private String type;
}
