package by.training.lihodievski.final_project;


import by.training.lihodievski.final_project.connection.ConnectionPool;
import by.training.lihodievski.final_project.connection.ProxyConnection;
import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ConnectionPool connectionPool = ConnectionPool.getInstance ();
        try {
            connectionPool.init ();
        } catch (ConnectionPoolException e) {
            e.printStackTrace ();
        }


        try(ProxyConnection proxyConnection = connectionPool.takeConnection ()) {
            System.out.println ("tesst");
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace ();
        }

    }}


