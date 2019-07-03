package pages;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Payment {
	
	
	

		
		
		WebDriver driver = null;
		Actions builder = null;

		@FindBy(xpath = "//span[text()='Add to cart']") WebElement addToCart;
		@FindBy(xpath = "//span[text()='Checkout']") WebElement chkout;
		@FindBy(xpath = "//span[text()='Checkout as Guest']") WebElement asAGuest;
		@FindBy(xpath = "//input[@id='NewAddress_FirstName']") WebElement firstName;
		@FindBy(xpath = "//input[@id='NewAddress_LastName']") WebElement lastName;
		@FindBy(xpath = "//input[@id='NewAddress_Address1']") WebElement address;
		@FindBy(xpath = "//input[@id='NewAddress_City']") WebElement city;
		@FindBy(xpath = "//input[@id='NewAddress_ZipPostalCode']") WebElement zipcode;
		@FindBy(xpath = "//select[@id='NewAddress_CountryId']") WebElement country;
		@FindBy(xpath = "//input[@id='NewAddress_Email']") WebElement email;
		@FindBy(xpath = "//input[@id='NewAddress_PhoneNumber']") WebElement phoneNo;
		@FindBy(xpath = "//button/span[text()='Next']") WebElement nxtButton;
		@FindBy(xpath = "//button/span[text()='Ship to this address']") WebElement shipAddr;
		@FindBy(xpath = "//input[@id='shippingoption_1']") WebElement byGroundRBtn;
		
		public Payment(WebDriver driver) {
			this.driver = driver;
			builder = new Actions(driver);
			PageFactory.initElements(driver, this);
		}
		
		public boolean forPayment() {
			try {
				builder.moveToElement(addToCart).click().build().perform();
			}
			catch(NoSuchElementException e) {
				return true;
			}
			return false;
		}
		
		public void checkoutProcess() {
			chkout.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			builder.moveToElement(asAGuest).click().build().perform();
		}
		//
		public void sendDetails(int i,String data) {
			switch(i) {
				case 1 : firstName.sendKeys(data); break;
				case 2 : lastName.sendKeys(data); break;
				case 3 : address.sendKeys(data); break;
				case 4 : city.sendKeys(data); break;
				case 5 : zipcode.sendKeys(data); break;
				case 6:  Select slt =   new Select(country); slt.selectByVisibleText(data); break;
				case 7 : email.sendKeys(data); break;
				case 8 : phoneNo.sendKeys(data); break;
				default:
			}
		}
		//
		public void toNext() {
			builder.moveToElement(nxtButton).click().build().perform();
		}
		//
		public void confirmShipping() {
			builder.moveToElement(shipAddr).click().build().perform();
			builder.moveToElement(byGroundRBtn).click().build().perform();
		}
		
		//get current URL
		public String toGetCurtURL() {
			return driver.getCurrentUrl();
		}
	}



