package managers;

import pages.*;

public class PageManager {

    private static PageManager pageManager = null;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public <T extends BasePage> T getPage(Class<T> page) {
        try {
            return (T) page.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }

}
