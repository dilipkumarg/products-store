package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class LogoutCommand implements ShellCommand {

    private final SecurityService securityService;
    private final Availability availability;

    public LogoutCommand(
            final SecurityService securityService,
            final Availability availability) {
        this.securityService = securityService;
        this.availability = availability;
    }

    @Override
    public String getHelpText() {
        return "Logs out the current logged in user";
    }

    @Override
    public Availability getAvailability() {
        return availability;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final User user = securityService.getLoggedInUser();
        securityService.logout();
        ps.println(MessageFormat.format("{0} logged out successfully!", user.getId()));
    }
}
