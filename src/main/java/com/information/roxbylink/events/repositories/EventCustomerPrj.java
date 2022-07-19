package com.information.roxbylink.events.repositories;

import com.information.roxbylink.events.models.Customer;
import com.information.roxbylink.events.models.Event;
import com.information.roxbylink.events.models.EventFacility;

public interface EventCustomerPrj {
    Customer getCustomer();
    Event getEventInfo();
    EventFacility getEventFacility();
}
