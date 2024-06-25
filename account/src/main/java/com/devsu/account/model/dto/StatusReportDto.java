package com.devsu.account.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class StatusReportDto implements Serializable {

    private String name;

    List<AccountDto> accounts;


    @Serial
    private static final long serialVersionUID = 4161570579183018982L;
}
