//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GyungMaeGo.jpashop.domain.Item;

import lombok.Generated;

public enum Role {
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "사용자");

    private final String key;
    private final String title;

    @Generated
    public String getKey() {
        return this.key;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    private Role(final String key, final String title) {
        this.key = key;
        this.title = title;
    }
}
