package net.svishch.template.config.security;

/*
     Роли должны соответствовать с ролями в SQL базе!!!
     таблица "roles"
     SELECT * FROM roles
 */

public class RoleListApp {

    public final static String ROLE_DEFAULT_USER = "ROLE_USER";

    public final static String ROLE_ADMIN = "ROLE_ADMIN";
    public final static String ROLE_USER = "ROLE_USER";
    public final static String R_USER = "USER";
    public final static String ROLE_DEVELOPER = "ROLE_DEVELOPER";

    public final static String ROLE_PREFIX = "ROLE_";
}
