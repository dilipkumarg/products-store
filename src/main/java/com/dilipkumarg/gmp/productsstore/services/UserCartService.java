package com.dilipkumarg.gmp.productsstore.services;

import com.dilipkumarg.gmp.productsstore.models.OrderProductEntry;
import com.dilipkumarg.gmp.productsstore.models.Product;
import com.dilipkumarg.gmp.productsstore.models.User;
import com.dilipkumarg.gmp.productsstore.models.UserCart;

public class UserCartService implements CartService {

    private final SecurityService securityService;
    private final UserService userService;
    private final ProductService productService;

    public UserCartService(
            final SecurityService securityService,
            final UserService userService,
            final ProductService productService) {
        this.securityService = securityService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public UserCart addProduct(final Integer productId) {
        final UserCart cart = securityService.getLoggedInUser().getCart();

        final OrderProductEntry productEntry = cart.getProductEntries().compute(productId, (pId, entry) -> {
            if (entry == null) {
                final Product product = productService.getById(productId);
                return new OrderProductEntry(product);
            } else {
                entry.setQuantity(entry.getQuantity() + 1);
                return entry;
            }
        });
        cart.setTotalCost(cart.getTotalCost() + productEntry.getPrice());

        return cart;
    }

    @Override
    public UserCart removeProduct(final Integer productId) {
        final UserCart cart = securityService.getLoggedInUser().getCart();
        if (cart.getProductEntries().containsKey(productId)) {
            final OrderProductEntry entry = cart.getProductEntries().get(productId);
            entry.setQuantity(entry.getQuantity() - 1);
            cart.setTotalCost(cart.getTotalCost() - entry.getPrice());

            if (entry.getQuantity() == 0) {
                cart.getProductEntries().remove(productId);
            }
        } else {
            throw new IllegalStateException("Product:" + productId + ", not exists in the cart");
        }
        return cart;
    }

    @Override
    public UserCart clearCart() {
        final User user = securityService.getLoggedInUser();
        user.setCart(new UserCart(user));
        return user.getCart();
    }

    @Override
    public UserCart getCart() {
        return securityService.getLoggedInUser().getCart();
    }

    @Override
    public void onProductUpdated(final Product product) {
        this.userService.findAllUsers().stream()
                .map(User::getCart)
                .forEach(cart -> {
                    if (cart.getProductEntries().containsKey(product.getId())) {
                        cart.getProductEntries().get(product.getId()).setPrice(product.getPrice());
                        updateCartTotalPrice(cart);
                    }
                });
    }

    @Override
    public void onBeforeProductDeleted(final Product product) {
        this.userService.findAllUsers().stream()
                .map(User::getCart)
                .filter(cart -> cart.getProductEntries().containsKey(product.getId()))
                .forEach(cart -> {
                    cart.getProductEntries().remove(product.getId());
                    updateCartTotalPrice(cart);
                });
    }

    private void updateCartTotalPrice(final UserCart cart) {
        final Double newCost = cart.getProductEntries().values().stream()
                .map(entry -> entry.getPrice() * entry.getQuantity())
                .reduce(Double::sum)
                .orElse(0D);
        cart.setTotalCost(newCost);
    }
}
