package ru.online.shop.config;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomEntityManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persi");

    public static javax.persistence.EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
