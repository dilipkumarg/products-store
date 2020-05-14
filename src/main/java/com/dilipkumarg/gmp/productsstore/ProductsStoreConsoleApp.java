package com.dilipkumarg.gmp.productsstore;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.services.SecurityService;

public class ProductsStoreConsoleApp {

    private final Pattern delimitorPattern = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");


    public void execute(InputStream inputStream, PrintStream outStream) {
        ObjectFactory factory = new ObjectFactory();
        CommandRegistry registry = new CommandRegistry(factory);

        Scanner scanner = new Scanner(inputStream);
        while (true) {
            outStream.print("\n" + getPrompt(factory.getSecurityService()));
            final Instruction instruction = parse(scanner.nextLine());
            if(instruction.getCommand().equalsIgnoreCase("quit")) {
                outStream.print("bye..");
                break;
            }
            try {
                final ShellCommand command = registry.getCommand(instruction.getCommand());
                if (command.getAvailability().isAvailable()) {
                    if (command.getMinRequiredArgs() <= instruction.getArguments().size()) {
                        command.execute(instruction.getArguments(), outStream);
                    } else {
                        outStream.print("Needed min args:" + command.getMinRequiredArgs() + ", " + command
                                .getHelpText());
                    }
                } else {
                    outStream.print("Command not available, " + command.getAvailability().getHelpText());
                }

            } catch (Exception e) {
                outStream.print("Error::" + e.getMessage());
            }
        }

    }

    private Instruction parse(String line) {
        final Matcher matcher = delimitorPattern.matcher(line);
        List<String> args = new ArrayList<>();
        String command = null;
        while (matcher.find()) {
            if (command == null) {
                command = matcher.group();
            } else {
                args.add(matcher.group());
            }
        }
        return new Instruction(command, args);
    }

    private String getPrompt(SecurityService securityService) {
        if (securityService.isUserLoggedIn()) {
            return securityService.getLoggedInUser().getId() + ">";
        } else {
            return "guest>";
        }
    }


    public static void main(String[] args) {
        ProductsStoreConsoleApp app = new ProductsStoreConsoleApp();
        app.execute(System.in, System.out);
    }
}
