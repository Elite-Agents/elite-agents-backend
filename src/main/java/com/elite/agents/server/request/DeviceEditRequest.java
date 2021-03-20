package com.elite.agents.server.request;

import com.elite.agents.server.common.request.EditRequest;
import com.elite.agents.server.model.device.Device;

public class DeviceEditRequest implements EditRequest<Device> {

    private String name;
    private String description;

    @Override
    public void applyTo(Device entity) {
        entity.setName(name);
        entity.setDescription(description);

    }

    @Override
    public Device createEntity() {

        return new Device();
    }
}
