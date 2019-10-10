package br.com.ctis.hackathon.util;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PageDict implements Serializable {

    @NotNull
    @Schema(description = "Número da página atual", required = true)
    private int pageNumber;

    @NotNull
    @Schema(description = "Tamanho da página atual", required = true)
    private int pageSize;

    @Schema(description = "Busca atual", required = true)
    private String searchName;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
