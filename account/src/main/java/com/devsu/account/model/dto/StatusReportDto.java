package com.devsu.account.model.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class StatusReportDto implements Serializable {

    private String name;

    List<AccountDto> accounts;

    public StatusReportDto(){}

    public StatusReportDto(String name, List<AccountDto> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "StatusReportDto{" +
                "name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    @Serial
    private static final long serialVersionUID = 4161570579183018982L;
}
