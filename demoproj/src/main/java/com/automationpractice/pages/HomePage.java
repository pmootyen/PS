package com.automationpractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.commons.Configuration;
import com.selenium.commons.ReadExcel;

public class HomePage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(className = "login")
	private WebElement signInLink;

	@FindBy(className = "logout")
	private WebElement logoutLink;

	@FindBy(className = "logo")
	private WebElement homeLogo;

	@FindBy(id = "search_query_top")
	private WebElement searchTextBox;

	@FindBy(name = "submit_search")
	private WebElement searchIcon;

	public HomePage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void tapSignin() {
		signInLink.click();
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));

	}

	public void validateHome() {
		Assert.assertEquals(driver.getTitle(), read.readData("Home_Title"));
	}

	public void logoutOfAPP() {
		logoutLink.click();
	}

	public void navigateToHome() {
		homeLogo.click();
	}

	public void searchProduct(String productName,String productResult,String price ) {
		searchTextBox.clear();
		searchTextBox.sendKeys(productName);
		searchIcon.click();
		WebElement e=driver.findElement(By.xpath("//img[@title='"+productName+"']"));
		Actions a= new Actions(driver);
		a.moveToElement(e).build().perform();
		driver.findElement(By.xpath("//h5[@itemprop='name']//a[contains(text(),'"+productResult+"')]")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='left-block']//div[@class='content_price']//span[contains(text(),'"+price+"')]")).isDisplayed();
		
		
		
	}

}
