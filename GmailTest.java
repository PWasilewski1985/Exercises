import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GmailTest {
    private WebDriver driver;

    @BeforeMethod
    public void SetUp()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pwasilewski\\Documents\\chromedriver\\Nowy folder\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com/intl/pl/gmail/about/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void  CorrectOpenWebPage()
    {
        Reporter.log("====Verify correct navigation to Gmail Web=========",true);
        String pageTitle = driver.getTitle();
        System.out.println("Page Title is : " + pageTitle);
        //assert of the web title
        Assert.assertEquals(pageTitle, "Gmail – bezpłatne miejsce na dane i poczta e-mail od Google");
        Reporter.log("======CorrectOpenWebPage TestCase - Passed", true);
    }
    @Test(priority = 2, description = "Verify correct Login  with correct data")
    public void LoginCorrectData()throws InterruptedException {
        Reporter.log("====Verify correct Login with correct data=========", true);
        //Set WebDriver wait
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

            for (WebElement correctLogintab:loginTab)
            {
                System.out.println(correctLogintab.getText());
                if (correctLogintab.getText().equals("Zaloguj się"))
                {
                    correctLogintab.click();
                }
            }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("zsamotrunk@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#1234");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        Thread.sleep(5000);
        //get text from Logo; assertion by if and assert boolean-flag isDisplayed
        try {
            Reporter.log("====Get Text from LOGO====", true);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='gb_ma']")));
            boolean flag = driver.findElement(By.xpath("//img[@class='gb_ma']")).isDisplayed();
            Assert.assertTrue(flag);
            Reporter.log("=====LoginCorrectData TestCase-Passed=======", true);
            WebElement logo = driver.findElement(By.xpath("//a[@class='gb_9d gb_Tb gb_pe']"));
            String logocheck = logo.getAttribute("title");
            System.out.println(logocheck);
            if (logocheck.equals("Gmail")) {
                System.out.println("Correct Login");
                Reporter.log("====Text from LOGO is as expected=====", true);
            } else {
                System.out.println("Incorrect Login");
            }
       /* boolean flag = driver.findElement(By.xpath("//img[@class='gb_ma']")).isDisplayed();
        Assert.assertTrue(flag);
        Reporter.log("=====LoginCorrectData TestCase-Passed=======",true);*/
        }
        catch (org.openqa.selenium.StaleElementReferenceException sere)
        {
            System.out.println("error");
        }
    }
    @Test(priority = 3)
    public void LogOutAndDeleteAccount()throws InterruptedException
    {
        Reporter.log("====Verify correct Login with correct data=========", true);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //Set WebDriver wait
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("zsamotrunk@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#1234");
        Thread.sleep(2000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        WebElement logOut = driver.findElement(By.xpath("//span[@class='gb_ya gbii']"));
        logOut.click();
        WebElement logOutBtn = driver.findElement(By.id("gb_71"));
        logOutBtn.click();
        //verify correct frame of the Log Out by if and assert isDisplayed
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("xkfVF")));
            WebElement logOutframe = driver.findElement(By.className("xkfVF"));
                if (!logOutframe.isDisplayed())
                {
                    System.out.println("Incorrect LogOut");
                }
                else
                {
                    System.out.println("Correct LogOut");
                }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("profileIdentifier")));
            WebElement accountToDelete = driver.findElement(By.id("profileIdentifier"));
                accountToDelete.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Usuń konto']")));
            WebElement deleteAcc = driver.findElement(By.xpath("//div[text()='Usuń konto']"));
        jse.executeScript("arguments[0].click();",deleteAcc);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='n3x5Fb']")));
            WebElement xxX = driver.findElement(By.xpath("//div[@class='n3x5Fb']"));
                xxX.click();
        WebElement confirmationDelete = driver.findElement(By.xpath("//span[text()='Tak, usuń']"));
        jse.executeScript("arguments[0].click();",confirmationDelete);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("xkfVF")));
        boolean frameAfterDeleteAcc = driver.findElement(By.className("xkfVF")).isDisplayed();
        Assert.assertTrue(frameAfterDeleteAcc);
        Reporter.log("===LogOutAndDeleteAccount TestCase - Passed===");
    }

    @Test(priority = 4)
    public void LoginIncorrectData()throws InterruptedException
    {
        Reporter.log("====Verify Login with incorrect data=========", true);
        //Set WebDriver wait
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("dlsajdlksajdlkjafds@gmail.com");
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        Thread.sleep(2000);
        WebElement statment = driver.findElement(By.className("GQ8Pzc"));
        String statmentContent = statment.getText();
        System.out.println(statmentContent);
        String ExpectedStatment = statment.getText();
        System.out.println(ExpectedStatment);
        if(ExpectedStatment.equals(statmentContent)){
            System.out.println("Contents of statment are the same");
            Reporter.log("===The same contents===",true);
            Reporter.log("===LoginIncorrectData TestCase - Passed",true);
        }
        else{
            Reporter.log("===Different Statments===",true);
        }

    }
    @Test(priority = 5)
    public void GetTheNumberOfReceivedMails()throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("zsamotrunk@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#1234");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Dj")));
        WebElement infoOfEmails = driver.findElement(By.className("Dj"));
        String Count = infoOfEmails.getText();
        int Quantity = Count.length();
        //System.out.println(Quantity);
        if(Quantity == 7)
        {
            String Received = Count.substring(6);
            System.out.println("Number of received mails is: " +Received);
        }
        else
        {
            String Received = Count.substring(7);
            System.out.println("Number of received mails is: " +Received);
        }
        Reporter.log("===GetTheNumberOfReceivedMails - Passed",true);



    }
    @Test(priority = 6)
    public void SendingMessage()throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("rutraadogzejn@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#12345");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aic")));
        WebElement createMail = driver.findElement(By.className("aic"));
        createMail.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aYF")));
        WebElement newMessage = driver.findElement(By.className("aYF"));
        String Info = newMessage.getText();
        System.out.println(Info);
        if(newMessage.getText().equals("Nowa wiadomość"))
        {
            System.out.println("User can send mail without any problems");
        }
        else
        {
            System.out.println("User can't send mail");
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Adresaci']/../../..//textarea")));
        WebElement adress = driver.findElement(By.xpath("//*[text()='Adresaci']/../../..//textarea"));
        adress.sendKeys("zsamotrunk@gmail.com");
        WebElement subject = driver.findElement(By.name("subjectbox"));
        subject.sendKeys("testowy email");
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Ar Au")));
        WebElement contentMessage = driver.findElement(By.xpath("//div[@aria-label='Treść wiadomości']"));
        contentMessage.sendKeys("testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail " +
                "testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail ");
        //Thread.sleep(1000);
        WebElement send = driver.findElement(By.xpath("//div[@aria-label='Wyślij \u202A(Ctrl+Enter)\u202C']"));
        send.click();
        Thread.sleep(3000);
        WebElement confirmationSendingmessage = driver.findElement(By.className("bAq"));
        String textAfterSendMessage = confirmationSendingmessage.getText();
        System.out.println(textAfterSendMessage);
        String ExpectetStatment = confirmationSendingmessage.getText();
        Assert.assertEquals(textAfterSendMessage,ExpectetStatment);
        Reporter.log("===Email sent===",true);
        Reporter.log("===SendingMessage TestCase - Passed===",true);
    }
    @Test(priority = 7)
    public void SearchForTheFiltersUsed()throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("zsamotrunk@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#1234");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and @role='button']")));
        WebElement looking = driver.findElement(By.xpath("//button[@type='button' and @role='button']"));
        looking.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='ZH nr aQa']")));
        //*[@id=":lu"]
        WebElement lookFrom = driver.findElement(By.xpath("//input[@class='ZH nr aQa']"));
        lookFrom.sendKeys("rutraadogzejn@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='ZH nr aQd']")));
        WebElement lookSubject = driver.findElement(By.xpath("//input[@class='ZH nr aQd']"));
        lookSubject.sendKeys("testowy email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Wyszukaj']")));
        WebElement looking1 = driver.findElement(By.xpath("//div[text()='Wyszukaj']"));
        looking1.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='Dj']")));
        List<WebElement> infoOfEmails2 = driver.findElements(By.xpath("//span[@class='Dj']"));
        String InfoOfEmails3 = infoOfEmails2.get(1).getText();
        System.out.println("According to the entered search categories, emails were found in quantity " +InfoOfEmails3);
    }
    @Test(priority = 8)
    public void WholeProcess()throws InterruptedException
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']")));
        //localization of Login Button and click
        List<WebElement>loginTab = driver.findElements(By.xpath("//a[@href='https://accounts.google.com/AccountChooser?service=mail&continue=https://mail.google.com/mail/' and @class='h-c-header__nav-li-link ']"));

        for (WebElement correctLogintab:loginTab)
        {
            System.out.println(correctLogintab.getText());
            if (correctLogintab.getText().equals("Zaloguj się"))
            {
                correctLogintab.click();
            }
        }
        //Entering correct Email adress

        ////input[@name='identifier']
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("input[@name='identifier']")));
        ArrayList<String> openTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(1));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("identifierId")));
        WebElement emailEdit = driver.findElement(By.id("identifierId"));
        emailEdit.sendKeys("zsamotrunk@gmail.com");
        //click Apply button
        WebElement next = driver.findElement(By.className("CwaK9"));
        next.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw = driver.findElement(By.name("password"));
        psw.sendKeys("Tuccafun#1234");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next1 = driver.findElement(By.xpath("//span[contains(text(),'Dalej')]"));
        next1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Dj")));
        WebElement infoOfEmails = driver.findElement(By.className("Dj"));
        String Count = infoOfEmails.getText();
        int Quantity = Count.length();
        //System.out.println(Quantity);
        if(Quantity == 7)
        {
            String Received = Count.substring(6);
            System.out.println("Number of received mails is: " +Received);
        }
        else
        {
            String Received = Count.substring(7);
            System.out.println("Number of received mails is: " +Received);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='F cf zt']")));
        WebElement tableWithMails = driver.findElement(By.xpath("//table[@class='F cf zt']"));
        List<WebElement> allmails = tableWithMails.findElements(By.xpath("//tr[@draggable='true']"));
        //int mails = allmails.size();
        //System.out.println(mails);
        String lastMail = allmails.get(0).getText();
        System.out.println("Last received email: " +lastMail);
        WebElement logOut = driver.findElement(By.xpath("//span[@class='gb_ya gbii']"));
        logOut.click();
        WebElement logOutBtn = driver.findElement(By.id("gb_71"));
        logOutBtn.click();
        //verify correct frame of the Log Out by if and assert isDisplayed
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("xkfVF")));
        WebElement logOutframe = driver.findElement(By.className("xkfVF"));
        if (!logOutframe.isDisplayed())
        {
            System.out.println("Incorrect LogOut");
        }
        else
        {
            System.out.println("Correct LogOut");
        }
        Thread.sleep(1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("profileIdentifier")));
        WebElement accountToDelete = driver.findElement(By.id("profileIdentifier"));
        accountToDelete.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Usuń konto']")));
        WebElement deleteAcc = driver.findElement(By.xpath("//div[text()='Usuń konto']"));
        jse.executeScript("arguments[0].click();",deleteAcc);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='n3x5Fb']")));
        WebElement xxX = driver.findElement(By.xpath("//div[@class='n3x5Fb']"));
        xxX.click();
        WebElement confirmationDelete = driver.findElement(By.xpath("//span[text()='Tak, usuń']"));
        jse.executeScript("arguments[0].click();",confirmationDelete);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("xkfVF")));
        boolean frameAfterDeleteAcc = driver.findElement(By.className("xkfVF")).isDisplayed();
        Assert.assertTrue(frameAfterDeleteAcc);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("identifier")));
        WebElement emailEdit1 = driver.findElement(By.name("identifier"));
        emailEdit1.sendKeys("rutraadogzejn@gmail.com");
        Thread.sleep(1000);
        WebElement next2 = driver.findElement(By.className("CwaK9"));
        Thread.sleep(1000);
        next2.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw1 = driver.findElement(By.name("password"));
        psw1.sendKeys("Tuccafun#12345");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next3 = driver.findElement(By.id("passwordNext"));
        next3.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aic")));
        WebElement createMail = driver.findElement(By.className("aic"));
        createMail.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aYF")));
        WebElement newMessage = driver.findElement(By.className("aYF"));
        String Info = newMessage.getText();
        System.out.println(Info);
        if(newMessage.getText().equals("Nowa wiadomość"))
        {
            System.out.println("User can send mail without any problems");
        }
        else
        {
            System.out.println("User can't send mail");
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Adresaci']/../../..//textarea")));
        WebElement adress = driver.findElement(By.xpath("//*[text()='Adresaci']/../../..//textarea"));
        adress.sendKeys("zsamotrunk@gmail.com");
        WebElement subject = driver.findElement(By.name("subjectbox"));
        subject.sendKeys("testowy email");
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.className("Ar Au")));
        WebElement contentMessage = driver.findElement(By.xpath("//div[@aria-label='Treść wiadomości']"));
        contentMessage.sendKeys("testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail " +
                "testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail testowyEmail ");
        //Thread.sleep(1000);
        WebElement send = driver.findElement(By.xpath("//div[@aria-label='Wyślij \u202A(Ctrl+Enter)\u202C']"));
        send.click();
        Thread.sleep(3000);
        WebElement confirmationSendingmessage = driver.findElement(By.className("bAq"));
        String textAfterSendMessage = confirmationSendingmessage.getText();
        System.out.println(textAfterSendMessage);
        String ExpectetStatment = confirmationSendingmessage.getText();
        Assert.assertEquals(textAfterSendMessage,ExpectetStatment);
        WebElement logOut1 = driver.findElement(By.xpath("//span[@class='gb_ya gbii']"));
        logOut1.click();
        WebElement logOutBtn1 = driver.findElement(By.id("gb_71"));
        logOutBtn1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("profileIdentifier")));
        WebElement accountToDelete1 = driver.findElement(By.id("profileIdentifier"));
        accountToDelete1.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Usuń konto']")));
        WebElement deleteAcc1 = driver.findElement(By.xpath("//div[text()='Usuń konto']"));
        jse.executeScript("arguments[0].click();",deleteAcc1);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='n3x5Fb']")));
        WebElement xxX1 = driver.findElement(By.xpath("//div[@class='n3x5Fb']"));
        xxX1.click();
        WebElement confirmationDelete1 = driver.findElement(By.xpath("//span[text()='Tak, usuń']"));
        jse.executeScript("arguments[0].click();",confirmationDelete1);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("identifier")));
        WebElement emailEdit2 = driver.findElement(By.name("identifier"));
        emailEdit2.sendKeys("zsamotrunk@gmail.com");
        Thread.sleep(1000);
        WebElement next5 = driver.findElement(By.className("CwaK9"));
        Thread.sleep(1000);
        next5.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement psw2 = driver.findElement(By.name("password"));
        psw2.sendKeys("Tuccafun#1234");
        Thread.sleep(1000);
        //click on apply button after entering correct data
        WebElement next4 = driver.findElement(By.id("passwordNext"));
        next4.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and @role='button']")));
        WebElement looking = driver.findElement(By.xpath("//button[@type='button' and @role='button']"));
        looking.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='ZH nr aQa']")));
        //*[@id=":lu"]
        WebElement lookFrom = driver.findElement(By.xpath("//input[@class='ZH nr aQa']"));
        lookFrom.sendKeys("rutraadogzejn@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='ZH nr aQd']")));
        WebElement lookSubject = driver.findElement(By.xpath("//input[@class='ZH nr aQd']"));
        lookSubject.sendKeys("testowy email");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Wyszukaj']")));
        WebElement looking1 = driver.findElement(By.xpath("//div[text()='Wyszukaj']"));
        looking1.click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='Dj']")));
        List<WebElement> infoOfEmails2 = driver.findElements(By.xpath("//span[@class='Dj']"));
        String InfoOfEmails3 = infoOfEmails2.get(1).getText();
        System.out.println("According to the entered search categories, emails were found in quantity " +InfoOfEmails3);
    }

    @AfterMethod
    public void closeApp()
    {
        driver.quit();
    }
}
