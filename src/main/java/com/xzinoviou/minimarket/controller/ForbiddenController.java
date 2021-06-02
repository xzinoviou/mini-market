package com.xzinoviou.minimarket.controller;

import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Xenofon Zinoviou
 */
@RestController
@RequestMapping("/forbidden")
public class ForbiddenController {

  @GetMapping(value = "/{text}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> getForbiddenMap(@PathVariable("text") String text) {
    return ResponseEntity.ok(resultMap(text));
  }

  private Map<String, String> resultMap(String value) {
    return Map.of("key", value);
  }
}
