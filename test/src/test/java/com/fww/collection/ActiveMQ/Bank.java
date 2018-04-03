package com.fww.collection.ActiveMQ;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Data
@Accessors(chain = true)
public class Bank implements Serializable {
    private Integer id;

    private String number;

    private Date gmtCreate;

    private Date gmtModified;

}