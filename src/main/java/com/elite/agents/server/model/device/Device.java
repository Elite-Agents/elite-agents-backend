package com.elite.agents.server.model.device;

import com.elite.agents.server.common.model.AuditedPersistedObject;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
public class Device implements AuditedPersistedObject<String> {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;

    private String name;
    private String description;
}
