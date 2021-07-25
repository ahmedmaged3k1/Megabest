package com.example.megabest.Login;

import java.util.List;

public class User {
    private String name ;
    private String number ;
    private String email ;
    private String password;
    private List<Integer> favouriteMovies ;
    private String image ;

    public User(String name, String number, String email,String password) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.password=password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getFavouriteMovies() {
        return favouriteMovies;
    }

    public void setFavouriteMovies(List<Integer> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }
}
