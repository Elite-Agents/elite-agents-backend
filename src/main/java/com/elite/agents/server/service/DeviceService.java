package com.elite.agents.server.service;

import com.elite.agents.server.repository.DeviceRepository;
import com.elite.agents.server.common.service.AbstractCrudService;
import com.elite.agents.server.model.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService extends AbstractCrudService<Device, String> {

    @Autowired
    public DeviceService(
            DeviceRepository deviceRepository) {
        super(deviceRepository, Device.class);
    }
}
