package com.projeto.restaurant.dto;

import java.io.Serializable;
import java.time.Instant;

public class ReservationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long ClientId;
    private Long TableId;
    private Instant moment;
    private Integer partySize;

    public ReservationDTO() {
    }

    public ReservationDTO(Long clientId, Long tableId, Instant moment, Integer partySize) {
        ClientId = clientId;
        TableId = tableId;
        this.moment = moment;
        this.partySize = partySize;
    }

    public Long getClientId() {
        return ClientId;
    }

    public void setClientId(Long clientId) {
        ClientId = clientId;
    }

    public Long getTableId() {
        return TableId;
    }

    public void setTableId(Long tableId) {
        TableId = tableId;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }
}