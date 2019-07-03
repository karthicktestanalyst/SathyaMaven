package pages;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import interfaces.Productsspecs;

public class SecuritySolutionsProduct implements Productsspecs {
	
	
	
		


			

			WebDriver driver = null;
			

			public int specCount = 5;
			

			@FindBy(xpath = "//h1") public WebElement CCTVName;
			@FindBy(xpath = "//meta[@itemprop='price']") public WebElement CCTVPrice;
			@FindBy(xpath = "//span[@class='pd-saving-percent']") public WebElement CCTVDiscount;
			@FindBy(xpath = "//a[text()='Features']")public WebElement CCTVFeatures;
			@FindBy(xpath = "//table/tbody/tr[1]/td[2]")public WebElement CCTVBrand;
			@FindBy(xpath = "//table/tbody/tr[2]/td[2]")public WebElement CCTVType;
			
			
			public SecuritySolutionsProduct(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
			}
			
			public int toSendSpecCount() {
				return specCount;
			}
			
			public String getDetails(int i) {
				
		try {
					
					if(i==1) return CCTVName.getText();
					else if(i==2) return CCTVPrice.getAttribute("content");
					else if(i==3) return CCTVDiscount.getText();
					else if(i==4) {
						CCTVFeatures.click();
						return CCTVBrand.getText();
					}
					else if(i==5) return CCTVType.getText();
							}
		catch(NoSuchElementException e) {
				  return "Nil";
				}
				return null;
			}
		}





