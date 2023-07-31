package com.quanpham.demo.enums;

public interface StatusTicketEnum {
    enum StatusTicket {
        INITIAL(1), PENDING(2), COMPLETED(3), CANCEL(4), UPDATED(5);

        private final int value;

        StatusTicket(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            switch (this) {

                case INITIAL:
                    return "Khởi tạo ticket";
                case PENDING:
                    return "pending ticket";
                case COMPLETED:
                    return "Hoàn thành ticket";
                case CANCEL:
                    return "Hủy ticket";
                case UPDATED:
                    return "Cập nhật ticket ticket";

                default:
                    break;
            }
            return null;
        }

    }
}
