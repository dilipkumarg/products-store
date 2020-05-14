package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class LoginCommand implements ShellCommand {

    private final SecurityService securityService;

    public LoginCommand(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public String getHelpText() {
        return "logins the given user into system.";
    }

    @Override
    public int getMinRequiredArgs() {
        return 1;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        securityService.login(args.get(0));
        ps.println(MessageFormat.format("{0} Logged in successfully.", args.get(0)));
    }
}
