package com.dilipkumarg.gmp.productsstore;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dilipkumarg.gmp.productsstore.commands.AdminUserAvailability;
import com.dilipkumarg.gmp.productsstore.commands.Availability;
import com.dilipkumarg.gmp.productsstore.commands.CartAvailablity;
import com.dilipkumarg.gmp.productsstore.commands.ShellCommand;
import com.dilipkumarg.gmp.productsstore.commands.UserAvailability;
import com.dilipkumarg.gmp.productsstore.commands.impl.*;

public class CommandRegistry {

    private final Map<String, ShellCommand> commands = new LinkedHashMap<>();

    public CommandRegistry(final ObjectFactory factory) {
        Availability userAvailability = new UserAvailability(factory.getSecurityService());
        Availability adminAvailability = new AdminUserAvailability(factory.getSecurityService());
        Availability cartAvailability = new CartAvailablity(factory.getSecurityService(), factory.getCartService());

        commands.put("help", new HelpCommand(commands));

        commands.put("signup", new SignupCommand(factory.getUserService()));
        commands.put("login", new LoginCommand(factory.getSecurityService()));
        commands.put("logout", new LogoutCommand(factory.getSecurityService(), userAvailability));

        commands.put("product_add", new ProductRegisterCommand(factory.getProductService(), adminAvailability));
        commands.put("product_update", new ProductUpdateCommand(factory.getProductService(), factory.getCartService()
                , adminAvailability));
        commands.put("product_delete", new ProductDeleteCommand(factory.getProductService(), factory.getCartService()
                , adminAvailability));

        commands.put("product_list", new ProductListCommand(factory.getProductService(), userAvailability));

        commands.put("cart_add", new AddProductToCartCommand(factory.getCartService(), userAvailability));
        commands.put("cart_remove", new RemoveItemFromCartCommand(factory.getCartService(), userAvailability));
        commands.put("cart", new ShowCartCommand(factory.getCartService(), userAvailability));

        commands.put("checkout", new CheckoutCommand(factory.getCartService(), factory.getOrderService(),
                cartAvailability));
    }

    public ShellCommand getCommand(String command) {
        if (commands.containsKey(command)) {
            return commands.get(command);
        } else {
            throw new IllegalArgumentException("Invalid command:" + command);
        }
    }
}
