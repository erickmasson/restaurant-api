package com.projeto.restaurant.dto;

import java.io.Serializable;
import java.time.Instant;

public class ReservationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long clientId;
    private Long tableId;
    private Instant moment;
    private Integer partySize;

    public ReservationDTO() {
    }

    public ReservationDTO(Long clientId, Long tableId, Instant moment, Integer partySize) {
        this.clientId = clientId;
        this.tableId = tableId;
        this.moment = moment;
        this.partySize = partySize;
    }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getTableId() { return tableId; }
    public void setTableId(Long tableId) { this.tableId = tableId; }

    public Instant getMoment() { return moment; }
    public void setMoment(Instant moment) { this.moment = moment; }

    public Integer getPartySize() { return partySize; }
    public void setPartySize(Integer partySize) { this.partySize = partySize; }
}