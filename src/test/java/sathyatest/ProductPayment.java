package sathyatest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Parameters;
import pages.Payment;
import pages.HomePage;
import utilities.ExcelRead;

import pages.HomeAppliances;





public class ProductPayment {

	
	

		
	
	
		
		
		HomePage HPObj = null;
		HomeAppliances vc = null;
		WebDriver driver = null;
		Payment vcp = null;
		
		int j = 1;
		
		@BeforeClass
		 public void setUp() {
			 System.setProperty("webdriver.chrome.driver","F:\\Karthick\\Driver\\chromedriver.exe");
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			 HPObj = new HomePage(driver);
			 PageFactory.initElements(driver,HomePage.class);
		  }
		
		 @DataProvider
		 public static Object[][] billingInfo() throws Exception {
			 return ExcelRead.getTableArray();
		 }
		 
		 @BeforeMethod
		 @Parameters({"baseURL"})
		 public void bMtdGnrlMtd(String bURL){
			 driver.get(bURL);
		 }
		
		 @Test(dataProvider = "billingInfo")
		  public void paymentProcess(String fNme, String lNme, String addr, String city, String pCode, String country, String email, String phnNo){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Payment Process for Vaccum Cleaner");
			SoftAssert sAstObj= new SoftAssert();
			HPObj.toClick(1);
			HPObj.toClick(2);
			vc = new HomeAppliances(driver);
			vcp = new Payment(driver);
			for(int i = 0; i < vc.totalElements(); i++) {
				if(vc.eachProduct(i)||vcp.forPayment()) {
					System.out.println("No Add to Cart");
					driver.navigate().back();
					continue;
				}
				break;
			}
			vcp.checkoutProcess();
			vcp.sendDetails(j++,fNme);
			vcp.sendDetails(j++,lNme);
			vcp.sendDetails(j++,addr);
			vcp.sendDetails(j++,city);
			vcp.sendDetails(j++,pCode);
			vcp.sendDetails(j++,country);
			vcp.sendDetails(j++,email);
			vcp.sendDetails(j, phnNo);
			vcp.toNext();
			vcp.confirmShipping();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			vcp.toNext();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println(vcp.toGetCurtURL());
			sAstObj.assertTrue(!"https://www.sathya.in/Checkout/PaymentMethod".equals(vcp.toGetCurtURL()),"Payment Page Reached");
			vcp.toNext();
			sAstObj.assertTrue(!"https://www.sathya.in/Checkout/Confirm".equals(vcp.toGetCurtURL()),"Confirmation Page Reached");
			System.out.println(vcp.toGetCurtURL());
		  }
		
		@AfterClass
		public void toCloseBrowser() {
			driver.close();
		}
	}		
