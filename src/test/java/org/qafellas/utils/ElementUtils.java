package org.qafellas.utils;

import com.beust.ah.A;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

public class ElementUtils {
    public Page page;

    public ElementUtils(Page page){
        this.page = page;
    }

    public void uploadFile(String fileName){
        page.locator("#singleFileInput").setInputFiles(Paths.get("src/test/java/org/qafellas/data/" +fileName));
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Upload Single File")).click();

    }
}
