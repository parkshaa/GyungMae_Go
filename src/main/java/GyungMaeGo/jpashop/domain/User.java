//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(
        name = "test_user"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String email;
    private String picture;
    private String role;
    private String provider;

    public User(String name, String email, String picture, String role, String provider) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.provider = provider;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role;
    }

    @Generated
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public String getPicture() {
        return this.picture;
    }

    @Generated
    public String getRole() {
        return this.role;
    }

    @Generated
    public String getProvider() {
        return this.provider;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setPicture(final String picture) {
        this.picture = picture;
    }

    @Generated
    public void setRole(final String role) {
        this.role = role;
    }

    @Generated
    public void setProvider(final String provider) {
        this.provider = provider;
    }

    @Generated
    public User() {
    }

    @Generated
    public static class UserBuilder {
        @Generated
        private String name;
        @Generated
        private String email;
        @Generated
        private String picture;
        @Generated
        private String role;
        @Generated
        private String provider;

        @Generated
        UserBuilder() {
        }

        @Generated
        public UserBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Generated
        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        @Generated
        public UserBuilder picture(final String picture) {
            this.picture = picture;
            return this;
        }

        @Generated
        public UserBuilder role(final String role) {
            this.role = role;
            return this;
        }

        @Generated
        public UserBuilder provider(final String provider) {
            this.provider = provider;
            return this;
        }

        @Generated
        public User build() {
            return new User(this.name, this.email, this.picture, this.role, this.provider);
        }

        @Generated
        public String toString() {
            return "User.UserBuilder(name=" + this.name + ", email=" + this.email + ", picture=" + this.picture + ", role=" + this.role + ", provider=" + this.provider + ")";
        }
    }
}
