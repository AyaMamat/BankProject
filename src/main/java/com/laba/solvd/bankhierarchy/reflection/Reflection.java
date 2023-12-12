package com.laba.solvd.bankhierarchy.reflection;

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
            Class myCustomer = Class.forName("com.laba.solvd.bankhierarchy.people.Customer");

            Class customerClass = myCustomer.getClass();
            LOGGER.info("Customer class name");
            LOGGER.info("<<<<CLASS NAME>>>>>>" + customerClass.getName() + "-->>" + Modifier.toString(customerClass.getModifiers()));

            LOGGER.info("<<<<<<<<<<FIELDS>>>>>>>>>>>>>");
            List<Field> customerFields = Arrays.asList(customerClass.getClass().getDeclaredFields());
            customerFields.stream()
                    .forEach(field -> LOGGER.info(field.getName() + " -->> " + Modifier.toString(field.getModifiers())));

            LOGGER.info("<<<<<<<<<<METHODS>>>>>>>>>>>>>>");
            List<Method> customerMethods = Arrays.asList(customerClass.getClass().getDeclaredMethods());
            customerMethods.stream()
                    .forEach(method -> LOGGER.info(method.getName() + " -->> " + Modifier.toString(method.getModifiers())));

            LOGGER.info("<<<<<<<<<<CONSTRUCTORS>>>>>>>>>>>>>>");
            List<Constructor> customerConstructor = Arrays.asList(customerClass.getClass().getDeclaredConstructors());
            customerConstructor.stream()
                    .forEach(method -> LOGGER.info(method.getName() + " -->> " + Modifier.toString(method.getModifiers())));

        } catch (Exception e) {
            LOGGER.error("Error has occurred");
        }
    }
}
