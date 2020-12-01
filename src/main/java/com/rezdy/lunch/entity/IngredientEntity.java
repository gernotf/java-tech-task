package com.rezdy.lunch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "ingredient")
public class IngredientEntity {

    @Id
    @Column(name = "TITLE")
    private String title;

    @Column(name = "BEST_BEFORE")
    private LocalDate bestBefore;

    @Column(name = "USE_BY")
    private LocalDate useBy;

    public String getTitle() {
        return title;
    }

    public IngredientEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public IngredientEntity setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
        return this;
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public IngredientEntity setUseBy(LocalDate useBy) {
        this.useBy = useBy;
        return this;
    }
}
