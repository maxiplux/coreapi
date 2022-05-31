package io.base.coreapi.utils;

public class Constans {
    public static Integer SECONDS=1000;
    public static  enum ENUMROLES{
        ROLE_ADMIN,
        ROOT,
        ROLE_USER
    }

    public enum CrudOperations {
        UPDATE,
        DELETE,
        CREATE,




    }

    public static class ApiErrors {
        public  static  String Error404="The URI requested is invalid or the resource requested, such as a user, does not exist.";
        public static String Error409="Invalid resource in system.";
        public static String Error400="Invalid request in system.";
    }
}
