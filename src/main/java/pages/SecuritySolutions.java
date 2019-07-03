package pages;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import interfaces.Webelements1;

public class SecuritySolutions implements Webelements1 {
	
	
	
			
			
			WebDriver driver = null;
			Actions builder = null; 
			
			@FindBy(xpath = "//select[@name='artlist-action-pagesize']") WebElement sltPerPage;
			@FindBys(@FindBy(xpath = "//h3/a/span")) public List<WebElement> elements;
			
			public SecuritySolutions(WebDriver driver) {
				this.driver = driver;
				builder = new Actions(driver);
				PageFactory.initElements(driver, this);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			
			public void toSelect() {
				Select  slt =   new Select(sltPerPage);
				slt.selectByValue("120");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
			public List<WebElement> toSendList(){
				return  elements;
			}
			
			public int totalElements() {
				return elements.size();
			}
			
			public boolean eachProduct(int i) {
				String forPass = "//h3/a/span[text()='" + elements.get(i).getText() + "']";
				try {
					builder.moveToElement(driver.findElement(By.xpath(forPass))).click().build().perform();
				}
				catch(NoSuchElementException e) {
					System.out.println("Vaccum Cleaner Product Skipped");
					return true;
				}
				return false;
			}
		}

		




