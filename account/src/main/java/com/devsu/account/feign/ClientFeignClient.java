package com.devsu.account.feign;

import com.devsu.account.model.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "client")
public interface ClientFeignClient {
    @GetMapping("/api/client/{identification}")
    ResponseEntity<Optional<ClientDto>> findClientByIdentification(@PathVariable String identification);
}
