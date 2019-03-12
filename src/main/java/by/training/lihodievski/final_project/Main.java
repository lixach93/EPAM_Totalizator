package by.training.lihodievski.final_project;


import by.training.lihodievski.final_project.connection.exception.ConnectionPoolException;
import by.training.lihodievski.final_project.service.exception.ServiceException;
import by.training.lihodievski.final_project.util.BCryptUtil;

public class Main {

    public static void main(String[] args) throws ServiceException, ConnectionPoolException {

        String hash = BCryptUtil.hashString ("300sA");
        System.out.println (hash);
        String hash1 = BCryptUtil.hashString ("300sA");
        System.out.println (hash1);
        String hash2 = BCryptUtil.hashString ("300sA");
        System.out.println (hash2);

        System.out.println (BCryptUtil.isValidHash ("300sA",hash));
        System.out.println (BCryptUtil.isValidHash ("300sA",hash1));
        System.out.println (BCryptUtil.isValidHash ("300sA",hash2));

    }
}


