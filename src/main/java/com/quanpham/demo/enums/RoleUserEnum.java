package com.quanpham.demo.enums;

public interface RoleUserEnum {
    enum RoleUser {
        USER(1), ADMIN(2);

        private final int value;

        RoleUser(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            switch (this) {

                case USER:
                    return "GDV";
                case ADMIN:
                    return "ADMIN";

                default:
                    break;
            }
            return null;
        }

    }
}
