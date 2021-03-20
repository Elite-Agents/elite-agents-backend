package com.elite.agents.server.controller;

import com.elite.agents.server.common.controller.AbstractCrudController;
import com.elite.agents.server.common.view.factory.AbstractViewFactory;
import com.elite.agents.server.model.device.Device;
import com.elite.agents.server.request.DeviceEditRequest;
import com.elite.agents.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController extends AbstractCrudController<Device, String, Device, DeviceEditRequest> {

    @Autowired
    public DeviceController(
            DeviceService deviceService) {
        super(deviceService, new AbstractViewFactory<Device, Device>() {
            // TODO
            @Override
            public Device buildView(Device entity) {
                return entity;
            }
        }, Device.class);
    }
}
