package by.training.lihodievski.final_project.util;

public class PageUtil {

    public static int getCountPage(int countElements){
        if (countElements > Constants.PER_PAGE) {
            int countPage = countElements % Constants.PER_PAGE;
            if (countPage == 0) {
                return countElements / Constants.PER_PAGE;
            } else {
                return countElements / Constants.PER_PAGE + 1;
            }
        } else {
            return 1;
        }
    }
}
