package com.dilipkumarg.gmp.productsstore.commands;

import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class UserAvailability implements Availability {
    private final SecurityService securityService;

    public UserAvailability(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean isAvailable() {
        return securityService.isUserLoggedIn();
    }

    @Override
    public String getHelpText() {
        return "Authenticated user required!!";
    }
}
