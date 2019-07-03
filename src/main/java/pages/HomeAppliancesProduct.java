package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import interfaces.Productsspecs;

public class HomeAppliancesProduct implements Productsspecs{
	


		

		WebDriver driver = null;
		

		public int specCount = 5;
		

		@FindBy(xpath = "//h1") public WebElement VaccumCleanerName;
		@FindBy(xpath = "//meta[@itemprop='price']") public WebElement VaccumCleanerPrice;
		@FindBy(xpath = "//span[@class='pd-saving-percent']") public WebElement VaccumCleanerDiscount;
		@FindBy(xpath = "//a[text()='Features']")public WebElement ClickFeatures;
		@FindBy(xpath = "//table/tbody/tr[1]/td[2]")public WebElement VaccumCleanerBrand;
		@FindBy(xpath = "//table/tbody/tr[2]/td[2]")public WebElement VaccumCleanerType;
		@FindBy(xpath = "//table/tbody/tr[3]/td[2]") public WebElement VaccumCleanerModelCode;
		@FindBy(xpath = "//table/tbody/tr[4]/td[2]")public WebElement VaccumCleanerCapacity;
		@FindBy(xpath = "//table/tbody/tr[5]/td[2]")public  WebElement VaccumCleanerStarRating;
		@FindBy(xpath = "//table/tbody/tr[9]/td[2]") public WebElement VaccumCleanerColour;
		
		
		public HomeAppliancesProduct(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		public int toSendSpecCount() {
			return specCount;
		}
		
		public String getDetails(int i) {
			
	try {
				
				if(i==1) return VaccumCleanerName.getText();
				else if(i==2) return VaccumCleanerPrice.getAttribute("content");
				else if(i==3) return VaccumCleanerDiscount.getText();
				else if(i==4) {
					ClickFeatures.click();
					return VaccumCleanerBrand.getText();
				}
				else if(i==5) return VaccumCleanerType.getText();
				else if(i==6) return VaccumCleanerModelCode.getText();
				else if(i==7) return VaccumCleanerCapacity.getText();
				else if(i==8) return VaccumCleanerStarRating.getText();
				else if(i==9) return VaccumCleanerColour.getText();
	}
	catch(NoSuchElementException e) {
			  return "Nil";
			}
			return null;
		}
		}
	


