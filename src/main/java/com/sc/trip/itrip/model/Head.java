package com.sc.trip.itrip.model;


import lombok.Data;

import java.io.Serializable;
@Data
public class Head implements Serializable {
    private String CloudSessionID;
    private String requestTime;
    private String respTime;
    private String usdTime;
    private Integer respCode;
    private String respMsg;

}