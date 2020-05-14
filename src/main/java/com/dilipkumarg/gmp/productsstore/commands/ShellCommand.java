package com.dilipkumarg.gmp.productsstore.commands;

import java.io.PrintStream;
import java.util.List;

public interface ShellCommand {

    String getHelpText();

    default Availability getAvailability() {
        return AlwaysAvailable.getInstance();
    }

    default boolean canAccept(List<String> args) {
        return true;
    }

    default int getMinRequiredArgs() {
        return 0;
    }

    void execute(List<String> args, PrintStream ps);

}
