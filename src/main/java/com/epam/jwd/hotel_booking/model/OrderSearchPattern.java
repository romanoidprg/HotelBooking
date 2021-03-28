package com.epam.jwd.hotel_booking.model;

import com.epam.jwd.hotel_booking.model.enums.OrderStatus;

import java.sql.Date;
import java.time.LocalDate;

public class OrderSearchPattern {

    private LocalDate dateFrom = LocalDate.parse("0000-01-01");
    private LocalDate dateBefore = LocalDate.parse("9999-01-01");
    private long orderId = -1L;
    private boolean orderIdStrongMatch = false;
    private long clientId = -1L;
    private boolean clientIdStrongMatch = false;
    private String status = "";
    private String login = "";
    private boolean loginStrongMatch = false;
    private String clientName = "";
    private boolean clientNameStrongMatch = false;
    private String clientSName = "";
    private boolean clientSNameStrongMatch = false;

    private OrderSearchPattern() {
    }

    public static Builder newBuilder() {
        return new OrderSearchPattern().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder whereOrderIdStrongMatchIs(boolean match) {
            OrderSearchPattern.this.orderIdStrongMatch = match;
            return this;
        }

        public Builder whereClientIdStrongMatchIs(boolean match) {
            OrderSearchPattern.this.clientIdStrongMatch = match;
            return this;
        }

        public Builder whereLoginStrongMatchIs(boolean match) {
            OrderSearchPattern.this.loginStrongMatch = match;
            return this;
        }

        public Builder whereClientNameStrongMatchIs(boolean match) {
            OrderSearchPattern.this.clientIdStrongMatch = match;
            return this;
        }

        public Builder whereClientSNameStrongMatchIs(boolean match) {
            OrderSearchPattern.this.clientSNameStrongMatch = match;
            return this;
        }

        public Builder whereOrderIdInclude(long orderId) {
            OrderSearchPattern.this.orderId = orderId;
            return this;
        }

        public Builder whereClientIdInclude(long clientId) {
            OrderSearchPattern.this.clientId = clientId;
            return this;
        }

        public Builder whereDateFromIs(LocalDate dateFrom) {
            OrderSearchPattern.this.dateFrom = dateFrom;
            return this;
        }

        public Builder whereDateBeforeIs(LocalDate dateBefore) {
            OrderSearchPattern.this.dateBefore = dateBefore;
            return this;
        }

        public Builder whereStatusIs(String status) {
            OrderSearchPattern.this.status = status;
            return this;
        }

        public Builder whereLoginInclude(String login) {
            OrderSearchPattern.this.login = login;
            return this;
        }

        public Builder whereClientNameInclude(String name) {
            OrderSearchPattern.this.clientName = name;
            return this;
        }

        public Builder whereClientSNameInclude(String sName) {
            OrderSearchPattern.this.clientSName = sName;
            return this;
        }

        public OrderSearchPattern build() {
            return OrderSearchPattern.this;
        }
    }


    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateBefore() {
        return dateBefore;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getClientId() {
        return clientId;
    }

    public String getStatus() {
        return status;
    }

    public String getLogin() {
        return login;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSName() {
        return clientSName;

    }

    public Date getDateFromForSQL() {
        return Date.valueOf(dateFrom);
    }

    public Date getDateBeforeForSQL() {
        return Date.valueOf(dateBefore);
    }

    public String getOrderIdForSQL() {
        return (orderId == -1L) ? "%" : (orderIdStrongMatch ? Long.toString(orderId) : "%" + orderId + "%");
    }

    public String getClientIdForSql() {
        return (clientId == -1L) ? "%" : (clientIdStrongMatch ? Long.toString(clientId) : "%" + clientId + "%");
    }

    public String getStatusForSQL() {
        return (status.equals("")) ? "%" : status;
    }

    public String getLoginForSQL() {
        return (login.equals("")) ? "%" : (loginStrongMatch ? login : "%" + login + "%");
    }

    public String getClientNameForSQL() {
        return (clientName.equals("")) ? "%" : (clientNameStrongMatch ? clientName : "%" + clientName + "%");
    }

    public String getClientSNameForSQL() {
        return (clientSName.equals("")) ? "%" : (clientSNameStrongMatch ? clientSName : "%" + clientSName + "%");
    }
}
