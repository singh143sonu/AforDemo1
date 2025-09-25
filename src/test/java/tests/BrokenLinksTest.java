package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.LinkValidator;

import java.util.List;

public class BrokenLinksTest extends BaseTest {

    @Test
    public void checkBrokenLinks() {
        driver.get("https://afor.co.nz");

        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");

            if (url == null || url.isEmpty()) {
                System.out.println("Skipping empty or null link");
                continue;
            }

            boolean broken = LinkValidator.isLinkBroken(url);
            if (broken) {
                System.out.println("❌ Broken Link: " + url);
            } else {
                System.out.println("✅ Working Link: " + url);
            }
        }
    }
}
