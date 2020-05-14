package com.dilipkumarg.gmp.productsstore.commands.impl;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;

public class HelpCommand implements ShellCommand {

    private final Map<String, ShellCommand> commandMap;

    public HelpCommand(final Map<String, ShellCommand> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public String getHelpText() {
        return "Lists all the available commands";
    }

    @Override
    public void execute(final List<String> args, final PrintStream ps) {
        ps.println("----Commands----");
        for (final Map.Entry<String, ShellCommand> entry : commandMap.entrySet()) {
            final ShellCommand command = entry.getValue();

            StringBuilder sb = new StringBuilder();
            if (!command.getAvailability().isAvailable()) {
                sb.append('*');
            }
            sb.append(entry.getKey()).append("\t");
            sb.append(command.getHelpText());
            if (!command.getAvailability().isAvailable()) {
                sb.append('(').append(command.getAvailability().getHelpText()).append(')');
            }

            ps.println(sb.toString());
        }
        ps.println("quit");
    }
}
