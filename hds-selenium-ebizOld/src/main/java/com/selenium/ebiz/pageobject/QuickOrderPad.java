package com.selenium.ebiz.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.ebiz.baseclass.BaseClass;
import com.selenium.ebiz.tools.CommonFunctions;
import com.selenium.ebiz.tools.WaitTool;

public class QuickOrderPad extends BaseClass{
	WebDriver driver;
	public QuickOrderPad(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.visibilityOf((quickOrderPadModal)));
	}

	//Quick Order form 
	@FindBy(xpath = "//div[@class='header-quick-order activated']")
	WebElement quickOrderPadModal;

	//Header HD Logo
	@FindBy(xpath = "//div[@class='header__main']//div[@id='ci_espot__HdsHeaderLogoESpot']")
	WebElement headerHDLogoImage;

	//All Part number text box in quick order form
	/*@FindBy(xpath = "//input[starts-with(@id,'partNumber_')]")    
    List<WebElement> AllpartNumbersEdit;*/
	
	@FindBy(xpath="//div[starts-with(@id,'WC_QuickOrderForm_div_11')]/div[@class='parts__part-row']/div[2]/input")
	//@FindBy(xpath="//div[starts-with(@id,'WC_QuickOrderForm_div_11')]/div[@class='parts__part-row']/div//input[@data-hds-tag='header-quick-order__qty-field']")
	List<WebElement> firstSixpartNumberTextBox;

	/*@FindBy(xpath = "//div[starts-with(@id,'WC_QuickOrderForm_div_11')]/div[@class='parts__add-more']/div[@class='parts__part-row']//input[@type='text']") */
	@FindBy(xpath="//div[@class='header-quick-order__parts']//div[@class='parts__add-more']/..//input[@data-hds-tag='header-quick-order__partnumber-field']")
	List<WebElement> lastSixpartNumberTextBox;

	@FindBy(xpath = "//div[starts-with(@id,'WC_QuickOrderForm_div_11')]/div[@class='parts__part-row']/div/section/input")    
	List<WebElement> firstSixquantityBox;

	@FindBy(xpath = "//div[@class='header-quick-order__parts']//div[@class='parts__add-more']/..//input[@data-hds-tag='header-quick-order__qty-field']")    
	List<WebElement> lastSixquantityBox;

	@FindBy(xpath = "//div[@class='header-quick-order__footer']//button[contains(text(),'Add to cart')]")
	WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='quick-order__checkout']")
	WebElement checkoutButton;
	
	@FindBy(xpath = "//div[@class='quotes-add__new-input']//input[@class='jq-quote-add--input']")
	WebElement quoteNameEdit;

	@FindBy(xpath = "//div[@class='header-quick-order__footer']//a[@data-hds-tag='quick-order__add-more-rows']")
	WebElement addMoreRowsButton;

	@FindBy(xpath = "//div[starts-with(@id,'WC_QuickOrderForm_div_11')][1]/div[@class='parts__part-row']//input[@type='text']")
	WebElement firstpartNumberTextBox;

	@FindBy(xpath = "//div[starts-with(@id,'WC_QuickOrderForm_div_11')][1]/div[@class='parts__part-row']//input[@name='quantity_1']")
	WebElement firstQuantityTextBox;
	
	@FindBy(xpath="//a[contains(text(),'ADD TO QUOTE')]")  
	WebElement addToQuoteButton;
	
	@FindBy(xpath = "//div[@class='tabs__bar']//a[@class='tabs__tab jq-tabs--toggle']")
	WebElement newTab;

	@FindBy(xpath = "//div[@class='quote-add__footer']//button[@id='createNewQuoteBtn']")
	WebElement createQuoteButton;
	
	@FindBy(xpath = "//div[@id='msgpopup_content_wrapper']")
	WebElement quoteSuccessPopup;
	
	@FindBy(xpath = "//div[@class='close_icon']")
	WebElement closeQuoteSuccessPopupIcon;
	
	@FindBy(xpath="//div[@class='quickordermodal activated']")
	WebElement quickOrderSection;
	
	@FindBy(xpath = "//div[@id='ShopCartDisplay']//h1[contains(text(),'SHOPPING CART')]")
	WebElement shoppingCartPage;
	
	/**
	 * @return True if quick order display default six rows otherwise False
	 */
	public boolean isDisplayedDefaultSixRowsInQuickOrderPad() 
	{
		System.out.println("6partNumberTextBox" + firstSixpartNumberTextBox.size());
		System.out.println("6quantityBox" + firstSixquantityBox.size());
		if(firstSixpartNumberTextBox.size() == 6 && firstSixquantityBox.size() == 6) {
			return true;	
		}
		else {
			return false;	
		}
	}

	/**
	 *  Method to click on HDLogo in any of the Pages
	 */
	public HomePage clickHeaderHDLogoImage() throws InterruptedException {
		headerHDLogoImage.click();
		return new HomePage(driver);
	} 

	/**
	 * click on Add More Rows
	 * @return the driver to Quick order page
	 */
	/*public QuickOrderPage clickAddMoreRows() 
	{
		addMoreRowsButton.click();
		return new QuickOrderPage();

	}*/
	
	public boolean isDisplayedAddMoreRowsFirstTime() 
	{
		return addMoreRowsButton.isDisplayed();

	}
	
	public void clickAddMoreRowsFirstTime() 
	{
		CommonFunctions.scrolltoViewElement(addMoreRowsButton, driver);
		addMoreRowsButton.click();
	}

	public QuickOrderPage clickAddMoreRowsSecondTime() 
	{
		addMoreRowsButton.click();
		return new QuickOrderPage(driver);

	}

	/**
	 * @return True if quick order display Twelve rows when user clicks on Add More rows first time otherwise False
	 */
	public boolean isDisplayedTwelveRowsInQuickOrderPad()
	{
		if(firstSixpartNumberTextBox.size() == 6 && firstSixquantityBox.size() == 6 && lastSixpartNumberTextBox.size() == 6 && lastSixquantityBox.size() == 6)
		{
			return true;	
		}
		else {
			return false;	
		}
	}

	public void fillFirstSixPartNumberTextBox(String partNumber1,String partNumber2,String partNumber3,String partNumber4, String partNumber5,String partNumber6)
	{
		String partnumber[] = {partNumber1,partNumber2,partNumber3,partNumber4,partNumber5,partNumber6};
		int i=0;
		for(WebElement we : firstSixpartNumberTextBox)
		{
			we.clear();
			we.sendKeys(partnumber[i]);
			i++;
		}

	}

	public void fillFirstSixQuantityTextBox(String quantity1,String quantity2,String quantity3,String quantity4, String quantity5,String quantity6)
	{
		String quantity[] = {quantity1,quantity2,quantity3,quantity4,quantity5,quantity6};
		int i=0;
		for(WebElement we : firstSixquantityBox)
		{
			we.clear();
			we.sendKeys(quantity[i]);
			i++;
		}

	}

	public void fillFirstPartNumberTextBox(String partNumber1) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		wait.until(ExpectedConditions.visibilityOf((firstpartNumberTextBox)));
		firstpartNumberTextBox.click();
		Thread.sleep(2000);
		firstpartNumberTextBox.sendKeys(partNumber1);	
	}

	public void fillFirstQuantityTextBox(String quantity1)
	{
		WaitTool.waitForElement(driver, firstQuantityTextBox);
		CommonFunctions.scrolltoViewElement(firstQuantityTextBox, driver);
		firstQuantityTextBox.clear();
		firstQuantityTextBox.sendKeys(quantity1);	

	}

	public ShoppingCartPage clickOnAddToCartButton() throws Exception{
		WaitTool.waitForElement(driver, addToCartButton);
		CommonFunctions.scrolltoViewElement(addToCartButton, driver);
		addToCartButton.click();
		return new ShoppingCartPage(driver);
	}

	/**
	 * @name isDisplayedquickOrderSection
	 * @description Method to verify the presence of quick order section after clicking.
	 * @return String value
	 */
	public boolean isDisplayedquickOrderSection(){
		return CommonFunctions.isElementPresent(quickOrderSection);
	}

	public void clickCheckoutButton()
	{
		CommonFunctions.scrolltoViewElement(checkoutButton, driver);
		checkoutButton.click();

	}

	public boolean shoppingCartPageDispalyed() {
		return CommonFunctions.isElementPresent(shoppingCartPage);

	}
	
	public void clickAddToQuoteButton()
	{
		CommonFunctions.scrolltoViewElement(addToQuoteButton, driver);
		addToQuoteButton.click();
	}
	
	public Boolean isAddToQuoteButtonDisplayed()
	{
		return addToQuoteButton.isDisplayed();
	}
	
	public boolean isDisplayedNewTab(){
		return newTab.isDisplayed();
	}
	
	public void clickNewTab(){
		newTab.click();
	}
		
	public void enterQuoteName(String quote){
		quoteNameEdit.sendKeys(quote);
	}
	
	public boolean isDisplayedCreateQuoteButton(){
		return createQuoteButton.isDisplayed();
	}
	
	public void clickCreateQuoteButton(){
		createQuoteButton.click();
	}
	
	public Boolean isDisplayedQuoteSuccessPopup()
	{
		return quoteSuccessPopup.isDisplayed();
	}
	
	public HomePage clickCloseQuoteSuccessPopupIcon(){
		closeQuoteSuccessPopupIcon.click();
		return new HomePage(driver);
	}
}

