package com.laba.solvd.bankhierarchy.reflection;

import com.laba.solvd.bankhierarchy.people.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

public class Reflection {
    private static final Logger LOGGER = LogManager.getLogger(Reflection.class);

    public static void main(String[] args) {
        reflection();
    }

    public static void reflection() {

        try {
            Customer customer = new Customer("Kayla Kobe", "1234 Quentin Rd", "123 455 6664");

            LOGGER.info("<<<<<<<<<<FIELDS>>>>>>>>>>>>>");
            List<Field> customerFields = Arrays.asList(customer.getClass().getDeclaredFields());
            customerFields.stream()
                    .forEach(field -> LOGGER.info(field.getName() + " -->> " + Modifier.toString(field.getModifiers())));

            LOGGER.info("<<<<<<<<<<METHODS>>>>>>>>>>>>>>");
            List<Method> customerMethods = Arrays.asList(customer.getClass().getDeclaredMethods());
            customerMethods.stream()
                    .forEach(method -> LOGGER.info(method.getName() + " -->> " + Modifier.toString(method.getModifiers())));

            LOGGER.info("<<<<<<<<<<CONSTRUCTORS>>>>>>>>>>>>>>");
            List<Constructor> customerConstructors = Arrays.asList(customer.getClass().getDeclaredConstructors());
            customerConstructors.stream()
                    .forEach(constructor -> LOGGER.info(constructor.getName() + " -->> " + Modifier.toString(constructor.getModifiers())));

        } catch (Exception e) {
            LOGGER.error("Error has occurred");
        }
    }
}
