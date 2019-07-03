package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

		

public class HomePage {
		
		//// "driver" is an instance for WebDriver Class
		WebDriver driver;
		
		//"homeApp" is an instance for "Home Appliances" link
		@FindBy(xpath = "//a[normalize-space(text())='Home Appliances']") WebElement HomeApplianceslink;
		
		@FindBy(xpath = "//a[@title='Vacuum Cleaner']") WebElement HomeAppliancesProductlink;
	
		@FindBy(linkText=("Security Solutions")) WebElement SecuritySolutionslink;
		
		@FindBy(xpath ="//span[@class=\"has-count\"][text()='CCTV Camera']") WebElement SecuritySolutionsProductlink;

		
		public HomePage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
				public void toClick(int flag) {
			switch(flag) {
				case 1: HomeApplianceslink.click(); break;
				case 2: HomeAppliancesProductlink.click(); break;
				case 3: SecuritySolutionslink.click(); break;
				case 4: SecuritySolutionsProductlink.click(); break;
				
			}
		}
	}



