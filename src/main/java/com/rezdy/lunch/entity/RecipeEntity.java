package com.rezdy.lunch.entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @Column(name = "TITLE")
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe"),
            inverseJoinColumns = @JoinColumn(name = "ingredient"))
    private Set<IngredientEntity> ingredients;
    
    public String getTitle() {
        return title;
    }

    public RecipeEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Set<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public RecipeEntity setIngredients(Set<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
