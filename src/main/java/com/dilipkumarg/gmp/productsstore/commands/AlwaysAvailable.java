package com.dilipkumarg.gmp.productsstore.commands;

public class AlwaysAvailable implements Availability {

    private AlwaysAvailable() {
    }

    private static class AlwaysAvailableHolder {
        private static final AlwaysAvailable INSTANCE = new AlwaysAvailable();
    }

    public static AlwaysAvailable getInstance() {
        return AlwaysAvailableHolder.INSTANCE;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public String getHelpText() {
        return "available!!";
    }
}
