package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.models.Role;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.services.UserService;

public class SignupCommand implements ShellCommand {

    private final UserService userService;

    public SignupCommand(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getHelpText() {
        return "Registers the given user into system. Input Format: userId, name, address, dob and role (optional)";
    }

    @Override
    public int getMinRequiredArgs() {
        return 4;
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        final User user = User.builder()
                .id(args.get(0))
                .name(args.get(1))
                .address(args.get(2))
                .dob(LocalDate.parse(args.get(3)))
                .build();
        if (args.size() == 5) {
            user.setRole(Role.valueOf(args.get(4)));
        }
        this.userService.register(user);
        ps.println(MessageFormat.format("User:{0} registered successfully", user.getId()));
    }
}
