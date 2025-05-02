package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import baseClass.BaseClass;

public class HomePage extends BaseClass{
	
	public HomePage() {
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(xpath="//div[@type='text']") WebElement txtuser ;
	@FindBy(xpath="//div[contains(@class, 'text')]") WebElement txtpass ;
	@FindBy(xpath="//input[starts-with(@type, 'text')]") WebElement  sub;
	@FindBy(xpath="//div//span[normalize-space()='text']") WebElement dropdown ;
	@FindBy(xpath="//div[text()='ban']") WebElement dd ;
	@FindBy(xpath="//spec[@type='name' and @id='text]") List<WebElement> ell ;
	
	
	
	
	public void signup(String name, String pass) {
		txtuser.sendKeys(name);
		txtpass.sendKeys(pass);
		sub.click();
		Select slt =new Select(dropdown);
		slt.selectByVisibleText("indian");
		slt.selectByValue("almm123");
		slt.selectByIndex(4);
		
		List<WebElement> opt=slt.getOptions();
		
		for(int i=0; i<opt.size(); i++) {
			String txt=opt.get(i).getText();
			if(txt.contains("indian")|| txt.equalsIgnoreCase("asian")) {
				opt.get(i).click();
			}
		}
		
		
	}
	
	public void verifyTitle() {
		Assert.assertEquals(driver.getTitle(), "store");
		Assert.assertTrue(dd.getText().contains(prop.getProperty("username")));
	}
	
	

}
