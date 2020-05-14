package com.dilipkumarg.gmp.productsstore.commands;

import com.dilipkumarg.gmp.productsstore.models.Role;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class AdminUserAvailability implements Availability {

    private final SecurityService securityService;

    public AdminUserAvailability(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public boolean isAvailable() {
        return securityService.isUserLoggedIn() && securityService.getLoggedInUser().getRole() == Role.ADMIN;
    }

    @Override
    public String getHelpText() {
        return "Authenticated ADMIN user required!!";
    }
}
