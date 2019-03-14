import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Ebay
{
    private WebDriver driver;
    @BeforeMethod
    public void SetUp(){
        Reporter.log("===Start TestCase===",true);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pwasilewski\\Documents\\chromedriver\\Nowy folder\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.ebay.pl/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void CorrectNavigateToEbayWeb(){
        Reporter.log("====Verify correct navigation to Ebay Web=========",true);
        //get and print title of the page
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is : " +pageTitle);
        //assertion of page tittle
        Assert.assertTrue(pageTitle.contains("eBay"));
        Reporter.log("===Ebay webSite correctly open",true);
    }
    @Test(priority = 2)
    public void WeryficationPossibleToSearchSubjectWrite()throws InterruptedException{
        Reporter.log("===Verify \n" +
                "whether the user can enter the subject searched for===",true);
        //set webdriver wait for method
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //assertion of WebEdit field which is field to entered date
        boolean flag = driver.findElement(By.name("_nkw")).isDisplayed();
        Assert.assertTrue(flag);
        Reporter.log("===WebElement for searching is displayed===",true);
        //locate and entered "iphone" in field of searching
        WebElement LookingForEdit=driver.findElement(By.name("_nkw"));
        LookingForEdit.sendKeys("iphone");
        Thread.sleep(1000);
        //get value (after entered"iphone") from WebEdit field and veryfication is displayed correct
        String SearchingSubject = LookingForEdit.getAttribute("value");
        System.out.println(SearchingSubject);
        if(SearchingSubject.equals("iphone"))
        {
            System.out.println("Searching subject is correct write");
            Reporter.log("=TestCase: WeryficationPossibleToSearchSubjectWrite - Passed=", true);
        }
        else
        {
            System.out.println("Searching subject does not exist ");
        }


    }
    @Test(priority = 3)
    public void verificationOfTheCorrectnessOfTheSearch(){
        //set webdriver wait for method
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //locate and entered "iphone" in field of searching
        WebElement LookingForEdit=driver.findElement(By.name("_nkw"));
        LookingForEdit.sendKeys("iphone");
        //locate and click the Lookfor Button
        WebElement LookForBtn = driver.findElement(By.id("gh-btn"));
        LookForBtn.click();
        //set list of elements which are results of searching - names of iphone; get and print size
        List<WebElement> resultsOfFinding=driver.findElements(By.className("vip"));
        int FindingElements = resultsOfFinding.size();
        System.out.println(FindingElements);
        //Loop for get names of iphones
        for (int i = 0; i < resultsOfFinding.size(); i++) {
            //toLowerCase==>all names have small letters
            String names = resultsOfFinding.get(i).getText().toLowerCase();
            System.out.println(names);
//condiition if to print all names contains "iphone"
            if (resultsOfFinding.get(i).getText().toLowerCase().contains("iphone"))
            {
                System.out.println("Wyszukiwanie poprawne");
            }
            //count and print how many times loop runs
            String NumberOfLoops = String.valueOf(i);
            System.out.println(NumberOfLoops);
        }
    }

    @Test (priority = 4)
    public void GetNamesAndPrices() {
        //set webdriver wait for method
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //locate field to edit i enter name of subject
        WebElement LookingForEdit = driver.findElement(By.name("_nkw"));
        LookingForEdit.sendKeys("iphone");
        //locate and click LookFor Btn
        WebElement LookForBtn = driver.findElement(By.id("gh-btn"));
        LookForBtn.click();
        //set list of elements which are results of searching - names of iphone
        List<WebElement> resultsOfFinding = driver.findElements(By.className("vip"));
        int FindingElements = resultsOfFinding.size();
        System.out.println(FindingElements);
        //locate parent of elements which are prices
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("CenterPanelInner")));
        WebElement ParentOfPrices = driver.findElement(By.id("CenterPanelInner"));
        //Set list of elements which are results of searching - prices
        List<WebElement> Prices = ParentOfPrices.findElements(By.xpath("//li[@class='lvprice prc']"));
        int Allprices = Prices.size();
        System.out.println(Allprices);
        //loop for printing names and prices which are results of searching
        for (int i = 0 ; i < resultsOfFinding.size()&& i<Prices.size(); i++)
        {
            String names = resultsOfFinding.get(i).getText();
            System.out.println(names);
            String Amount = Prices.get(i).getText();
            System.out.println(Amount);
        }
    }
    @Test(priority = 5)
    public void set_And_unSet_Filters(){
        //set webdriver wait for method
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //locate field to edit i enter name of subject
        WebElement LookingForEdit = driver.findElement(By.name("_nkw"));
        LookingForEdit.sendKeys("iphone");
        //locate and click LookFor Btn
        WebElement LookForBtn = driver.findElement(By.id("gh-btn"));
        LookForBtn.click();
        //click menu with  more filters
        WebElement MoreOptions = driver.findElement(By.id("Refine"));
        MoreOptions.click();
        //locate element which is confirm that menu filters is correct open
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("filapp")));
        String Filters = driver.findElement(By.id("filapp")).getText();
        System.out.println(Filters);
        //assertion which is confirm that menu filters is correct open
        Assert.assertTrue(Filters.contains("Wybrane filtry"));
        //set list of list of elements(checkboxes) visible in the filter of the advertisement;assertion of their quantity
        List<WebElement> filterChckboxes = driver.findElements(By.className("txt"));
        int count = filterChckboxes.size();
        System.out.println(count);
        Assert.assertEquals(4,filterChckboxes.size());
        //get title from chceckboxes and click one of them==>condition if
        for(WebElement checkbox : filterChckboxes)
        {
            System.out.println(checkbox.getAttribute("title"));
            if (checkbox.getAttribute("title").equals("Ogłoszenia"))
            {
                checkbox.click();
            }
        }
        //locate and click in the filter State(Stan)
        WebElement State = driver.findElement(By.id("LH_ItemCondition"));
        State.click();
        //get text from filter state and assertion of text
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("null_content")));
        WebElement Statetxt = driver.findElement(By.className("null_content"));
        String text = Statetxt.getText();
        System.out.println(text);
        //assertion of displayed text
        Assert.assertTrue(text.contains("Brak dostępnych filtrów"));
        //click on the prices filter and set min and max prices
        WebElement Price = driver.findElement(By.id("LH_Price"));
        Price.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("e1-6")));
        WebElement MinPrice = driver.findElement(By.id("e1-6"));
        MinPrice.sendKeys("300");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("e1-7")));
        WebElement MaxPrice = driver.findElement(By.id("e1-7"));
        MaxPrice.sendKeys("4000");
        //click on the locations filter and set it
        WebElement Location = driver.findElement(By.id("LH_PrefLoc"));
        Location.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LH_Distance")));
        WebElement SetLocation = driver.findElement(By.name("LH_Distance"));
        SetLocation.click();
        WebElement Distance = driver.findElement(By.name("_sadis"));
        Distance.click();
        WebElement Km = driver.findElement(By.xpath("//*[@id=\"c_LH_PrefLoc\"]/div/div[2" +
                "]/div[5]/span[1]/select/option[10]"));
        Km.click();
        //click on the seller filter and set it
        WebElement Seller = driver.findElement(By.id("LH_FromSellers"));
        Seller.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ForSellers_fss_1")));
        WebElement Sellerchckbox = driver.findElement(By.id("ForSellers_fss_1"));
        Sellerchckbox.click();
        WebElement BestSeller = driver.findElement(By.id("seller_LH_TopRatedSellers"));
        BestSeller.click();
        //click on the just show filter
        WebElement ShowOnly = driver.findElement(By.id("LH_ShowOnly"));
        ShowOnly.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("c_LH_ShowOnly")));
//locate Element which is the parent of sub-filters just show
        WebElement ParentElement = driver.findElement(By.id("c_LH_ShowOnly"));
        //set List of elements which are sub-filters of just show
        List<WebElement> ShowOnlyPossibilties = ParentElement.findElements(By.className("txt"));
        //get size of sub-filters
        int countPossibilties = ShowOnlyPossibilties.size();
        System.out.println(countPossibilties);
        //assert of quantity sub-filters
        Assert.assertEquals(7,ShowOnlyPossibilties.size());
        //loop for click in sub-filter which fulfill condition if
        for (WebElement Possibility : ShowOnlyPossibilties){
            System.out.println(Possibility.getAttribute("title"));
            if (Possibility.getAttribute("title").equals("Akceptowane płatności PayPal")) {
                Possibility.click();
            }
        }
        //set strings which are expected an actual results of settings filter;assertion
        WebElement NumberOfFilterSet = driver.findElement(By.id("filter"));
        String nOf = NumberOfFilterSet.getText();
        System.out.println(nOf);
        String ExpectedNoF = NumberOfFilterSet.getText();
        Assert.assertEquals(nOf,ExpectedNoF);
//set list of elements which are sellected filters
        List<WebElement> ChoosenFilters = driver.findElements(By.className("rempills"));
        //get size of selected filters
        int AllFilters = ChoosenFilters.size();
        System.out.println(AllFilters);
        //assertion==>between selected filters and displayed(quantity);loop for click all selected filters
        Assert.assertEquals(5,ChoosenFilters.size());
        for (int i = 0 ; i < ChoosenFilters.size(); i++ ){
            ChoosenFilters.get(i).click();
        }
        //set strings which are expected an actual results of settings filter after deleting filters;assertion
        String nOfAfterDelete = NumberOfFilterSet.getText();
        System.out.println(nOfAfterDelete);
        String ExpectednOfAfterDelete = NumberOfFilterSet.getText();
        Assert.assertEquals(nOfAfterDelete,ExpectednOfAfterDelete);
    }

    @Test(priority = 6)
    public void printFirstNamesAndPrice() {
        //set webdriver wait for method
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        //locate field to edit i enter name of subject
        WebElement LookingForEdit = driver.findElement(By.name("_nkw"));
        LookingForEdit.sendKeys("iphone");
        //locate and click LookFor Btn
        WebElement LookForBtn = driver.findElement(By.id("gh-btn"));
        LookForBtn.click();
        List<WebElement> resultsOfFinding = driver.findElements(By.className("vip"));
        String firstName = resultsOfFinding.get(0).getText();
        System.out.println(firstName);
        List<WebElement> Prices = driver.findElements(By.xpath("//li[@class='lvprice prc']"));
        String firstPrice = Prices.get(0).getText();
        System.out.println(firstPrice);
    }
    @Test(priority = 7)
    public void setLanguageOfWebsite()throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //locate and click the cookie btn
        WebElement CookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        CookieBtn.click();
        Actions action = new Actions(driver);
        WebElement language = driver.findElement(By.xpath("//a[@aria-controls='gf-f']"));
        String setLanguage = language.getText();
        System.out.println("Language is already set " +setLanguage);
        action.moveToElement(language);
        action.perform();
        List<WebElement> listOfLanguage = driver.findElements(By.xpath("//li[@role='menuitem']"));
        int allLanguage = listOfLanguage.size();
        System.out.println(allLanguage);
        try {
            for (WebElement language1 : listOfLanguage) {
                System.out.println(language1.getText());
                if (language1.getText().equals("Szwecja")) {
                    language1.click();
                    WebElement language2 = driver.findElement(By.xpath("//a[@aria-controls='gf-f']"));
                    String expectedlanguageAfterSet = "Szwecja";
                    String actualLanguage = language2.getText();
                    Assert.assertEquals(actualLanguage,expectedlanguageAfterSet);
                }
            }
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("change url");
            WebElement language2 = driver.findElement(By.xpath("//a[@aria-controls='gf-f']"));
            String expectedlanguageAfterSet = "Szwecja";
            String actualLanguage = language2.getText();
            Assert.assertEquals(actualLanguage,expectedlanguageAfterSet);
        }
    }
    @Test(priority = 8)
    public void addProductToBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"gdpr-banner-accept\"]")));
        //1.locate and click the cookie btn
        WebElement cookieBtn = driver.findElement(By.xpath("//*[@id=\"gdpr-banner-accept\"]"));
        cookieBtn.click();
        //2.locate and enter product of searching
        WebElement lookingForEdit = driver.findElement(By.name("_nkw"));
        lookingForEdit.sendKeys("Playstation 4");
        //3.locate and click search button
        WebElement lookForBtn = driver.findElement(By.id("gh-btn"));
        lookForBtn.click();
        //4.locate and click more options button to set filter
        WebElement moreOptions = driver.findElement(By.id("Refine"));
        moreOptions.click();
        //5.wait and select filter "Buy Now" in more options screen
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("LH_BIN")));
        WebElement buyNow = driver.findElement(By.name("LH_BIN"));
        buyNow.click();
        //6.locate and click submit button - submit set filter
        WebElement submitFilter = driver.findElement(By.className("submit-btn"));
        submitFilter.click();
        //7.create list of product after set filter, get their quantity
        List<WebElement> resultsOfFinding = driver.findElements(By.className("vip"));
        int FindingElements = resultsOfFinding.size();
        System.out.println(FindingElements);
        //8.selecting any product from the list(random) and click in selected product
        Random getProduct = new Random();
        resultsOfFinding.get(getProduct.nextInt(50)).click();
        //9.(after8.)--->details of selecting product(after random selected(click)) and get names of the selected product
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("it-ttl")));
        WebElement nameOfProduct = driver.findElement(By.className("it-ttl"));
        String choosenProduct = nameOfProduct.getText();
        System.out.println(choosenProduct);
        /*WebElement price - the user wants to get a price in polish zloty
         *1.the main price is always visible - main price has id = "prcIsum"
         *2.if the main price is given in polish zloty there is no additional price displayed in polish zloty
         *3.if the main price is given in Euro or $ is additional price displayed - additional price has id="convbinPrice"
         *additional price is given in polish zloty
         *that's the reason using if and try and catch = first of all script waiting and finding main price, after that is
         * conditional statement if that checks the correct display of the main price and then
         * tries to display the additional price if it exists and if not, catch NoSuchElementException and get main price
         */
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("prcIsum")));
        WebElement price = driver.findElement(By.id("prcIsum"));
        if (price.isDisplayed())
        {
            try
            {
                WebElement priceInzloty = driver.findElement(By.id("convbinPrice"));
                String priceInzloty1 = priceInzloty.getText();
                System.out.println("Price in zloty " + priceInzloty1);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("binBtn_btn")));
                WebElement addToBasket = driver.findElement(By.id("binBtn_btn"));
                addToBasket.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sbin-gxo-btn")));
                WebElement buy = driver.findElement(By.id("sbin-gxo-btn"));
                buy.click();
            }
            catch (org.openqa.selenium.NoSuchElementException nse)
            {
                System.out.println("Price in zloty " + (price.getText()));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("binBtn_btn")));
                WebElement addToBesket = driver.findElement(By.id("binBtn_btn"));
                addToBesket.click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sbin-gxo-btn")));
                WebElement buy = driver.findElement(By.id("sbin-gxo-btn"));
                buy.click();
            }
        }
        else
        {
            System.out.println("nothing happens");
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cart-order']")));
        WebElement confirmation = driver.findElement(By.xpath("//div[@class='cart-order']"));
        if (confirmation.isDisplayed())
        {
            System.out.println("Confirmation succesfull");
            WebElement productInTheBasket = driver.findElement(By.xpath("//div[@class='col-xs-9 item-title']"));
            String expectedProduct = choosenProduct;
            System.out.println("Choosen product is " + expectedProduct);
            String actualProductInTheBasket = productInTheBasket.getText();
            System.out.println("Product in the basket is " + actualProductInTheBasket);
            Assert.assertEquals(actualProductInTheBasket, expectedProduct);
        }
    }




    /*@Test
    public void  writeExcel()throws Exception{
        FileInputStream files = new FileInputStream(new File("C:\\Users\\pwasilewski\\Documents\\test.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(files);
        XSSFSheet sheet = workbook.getSheetAt(0);
        String URL = sheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println(URL);
        String Subject = sheet.getRow(1).getCell(1).getStringCellValue();
        System.out.println(Subject);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Porczykwas\\IdeaProjects\\EbayTestNgPOI\\src\\test\\java" +
                "\\chromedriver_win32 (3)\\chromedriver.exe");
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
    }*/
}





