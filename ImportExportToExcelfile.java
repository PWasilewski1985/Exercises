import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportExportToExcelfile
{
    @Test
    public void  writeExcel()throws Exception{
        FileInputStream files = new FileInputStream(new File("C:\\Users\\pwasilewski\\Documents\\test.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(files);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String URL = sheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println(URL);
        String Subject = sheet.getRow(1).getCell(1).getStringCellValue();
        System.out.println(Subject);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pwasilewski\\Documents\\chromedriver\\Nowy folder\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //pobranie i wyswietlenie tytulu strony
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is : " + pageTitle);
        //weryfikacja tytulu strony
        Assert.assertTrue(pageTitle.contains("eBay"));
        //klikniecie CookieButton
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //zlokalizowanie elementu do wpisywania i wyszukania przedmiotow; wpisanie "iphone"
        WebElement WebEdit = driver.findElement(By.name("_nkw"));
        WebEdit.sendKeys(Subject);
        WebElement LookForBtn = driver.findElement(By.id("gh-btn"));
        LookForBtn.click();
        List<WebElement> resultsOfFinding = driver.findElements(By.className("vip"));
        int FindingElements = resultsOfFinding.size();
        System.out.println(FindingElements);
        //Loop for get names of iphones
        for (int i = 0; i < resultsOfFinding.size(); i++) {
            String names = resultsOfFinding.get(i).getText();
            System.out.println(names);
        }
        File source = new File("C:\\Users\\pwasilewski\\Documents\\export.xlsx");
        FileInputStream input = new FileInputStream(source);
        XSSFWorkbook wb = new XSSFWorkbook(input);
        XSSFSheet sheet2 = wb.getSheetAt(0);
        List<WebElement> Prices = driver.findElements(By.xpath("//li[@class='lvprice prc']"));
        int allPrices = Prices.size();
        XSSFRow row =sheet2.createRow(0);
        row.createCell(0).setCellValue("namesOfproduct ");
        row.createCell(1).setCellValue("Prices");
        for(int i = 0; i < resultsOfFinding.size(); i++)
        {
            row =sheet2.createRow(i+1);
            row.createCell(0).setCellValue(resultsOfFinding.get(i).getText());
            row.createCell(1).setCellValue(Prices.get(i).getText());
        }
        FileOutputStream output = new FileOutputStream(source);
        wb.write(output);
    }
}
