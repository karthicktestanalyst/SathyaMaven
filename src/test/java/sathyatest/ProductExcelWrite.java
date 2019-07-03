package sathyatest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.ExcelWrite;
import pages.HomePage;
import pages.HomeAppliances;
import pages.HomeAppliancesProduct;
import pages.SecuritySolutions;
import pages.SecuritySolutionsProduct;

import interfaces.Webelements1;
import interfaces.Productsspecs;



@Listeners(utilities.Listeners.class)
public class ProductExcelWrite {


	
			
		// "driver" is an instance for WebDriver Class
		static WebDriver driver = null;
		
		/*
		 * "HPObj" is an instance for  HomePage Class,
		 * which is a Page Object class of Home Page of
		 * base URL
		 */
		HomePage HPObj = null;
		
		//"ELObj" is an instance for ExcelWirte Class
		ExcelWrite ELObj = null;
		
		/*
		 * The "setUp", which is before class method is to make
		 * default set up like driver initialization and maximize
		 * browser, initialize ELObj & HPObj objects and initialize
		 * the PageFactory for Home page of base URL
		 * 
		 * @throws WebDriverException if WebDriver is unable to find the driver
		 */
	 @BeforeClass
	 public void setUp() {
		 System.setProperty("webdriver.chrome.driver","F:\\Karthick\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 PageFactory.initElements(driver,HomePage.class);
		 ELObj = new ExcelWrite();
		 HPObj = new HomePage(driver);
	  }
	 
	 /*
	  * The "bMtdGnrlMtd", which is before method to loaded the
	  * base URL to the browser
	  * 
	  * @param bURL - Gets the base URL from XML file
	  * 
	  * @throws TimeoutException if driver gets delayed to load base URL.
	  */
	 @BeforeMethod
	 @Parameters({"baseURL"})
	 public void bMtdGnrlMtd(String bURL){
		 driver.get(bURL);
	 }
	 
	 /*
	  * The "PageObjects" is a data provider, which provides 
	  * page objects and integers for navigation to load required 
	  * page for test method "generalMtd"
	  * 
	  * @return Object[][] - Consists of Page Objects and integers
	  */
	 @DataProvider(name = "allPageObjects")
		 public Object[][] PageObjects() {
		  return new Object[][] 
				  {
			  {new HomeAppliances(driver),new HomeAppliancesProduct(driver),1,2},
			  {new SecuritySolutions(driver),new SecuritySolutionsProduct(driver),3,4}
			  
				  };
		 }
	 
	  /*
	   * The "generalMtd" is a test method receives corresponding page object
	   * and integers for navigation from data provider "allPageObjects"
	   * loads corresponding test web page, retrieves data from corresponding test page
	   * and send it to excel writing method
	   * 
	   * @param cmdy - Is an interface object
	   * @param  prd - Is an interface object
	   * @param a - Integer is used to navigate corresponding test web page
	   * @param b - Integer is used to navigate corresponding test web page
	   */
	  @Test(dataProvider = "allPageObjects")
	  public void generalMtd(Webelements1 cmdy, Productsspecs prd, int a, int b) throws IOException {
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  HPObj.toClick(a);
		  HPObj.toClick(b);
		  cmdy.toSelect();
		  ELObj.toCreateSheet(cmdy.getClass().getSimpleName());
		  for(int i = 0; i < cmdy.totalElements(); i++) {
			  System.out.println(i);
			  List<WebElement> elt = cmdy.toSendList();
			  System.out.println(elt.get(i).getText());
			  ELObj.rowCreation(i);
			  if(cmdy.eachProduct(i)) continue;
			  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  int specCount = prd.toSendSpecCount();
			  for(int j = 0;j<specCount ;j++) 
				  ELObj.cellCreationAndWrite(j, prd.getDetails(j+1));
			  driver.navigate().back();
			  if(i==2) break;
		  }
		  ELObj.fileCreation();
	  }
	  
	  /*
	   * The "toCloseBrowser" is a After Class method,
	   * used to close browser
	   */
	  @AfterClass
		 public void toCloseBrowser() {
			  driver.close();
		 }
	}



	

