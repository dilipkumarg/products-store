package com.dilipkumarg.gmp.productsstore;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instruction {
    private String command;
    private List<String> arguments;
}
