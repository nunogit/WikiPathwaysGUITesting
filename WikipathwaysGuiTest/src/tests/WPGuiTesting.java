package tests;

import static org.junit.Assert.*;

import java.util.List;



import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class WPGuiTesting {

	private WebDriver driver;
	
	
	
	/**
	 * Based on http://developers.pathvisio.org/wiki/BrowsePathwayTestProtocol
	 * improvements
	 *  Step 4 we can go through all pathways
	 *  Step 7 we can open a random pathway?
	 */
	@Test
	public void browsePathwayTestProtocol() {
		
		
		//this.driver = new FirefoxDriver();
		this.driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_38);
		//((HtmlUnitDriver) this.driver).setJavascriptEnabled(true);

		//Step 1: open page		
		driver.get("http://www.wikipathways.org");
		
		//Step 2: click browse
		WebElement browseIcon = driver.findElement(By.xpath("/html/body/div/div[1]/div/div/table/tbody/tr/td[1]/div[2]/table[1]/tbody/tr[1]/td[5]/div/div/a/img"));
		browseIcon.click();
		
		//Step 3: pick species
		WebElement selectSpecies = driver.findElement(By.xpath("//*[@id=\"nsselect\"]/tbody/tr/td[2]/select/option[6]"));
		selectSpecies.click();
		
		/*
		List<WebElement> selectSpeciesList = driver.findElements(By.xpath("//*[@id=\"nsselect\"]/tbody/tr/td[2]/select/option[*]"));
		
		for(WebElement selectSpeciesItem : selectSpeciesList){
			selectSpeciesItem.click();
		}*/
		
		//Step 4: pick all species
		selectSpecies = driver.findElement(By.xpath("//*[@id=\"nsselect\"]/tbody/tr/td[2]/select/option[31]"));
		selectSpecies.click();
		checkTextAndAssert("Abscisic Acid Biosynthesis"); //improve choice, should not be a pathway text
		
		//Step 5: pick collection - all tags
		selectSpecies = driver.findElement(By.xpath("//*[@id=\"nsselect\"]/tbody/tr/td[4]/select/option[4]"));
		selectSpecies.click();
		checkTextAndAssert("1,2-Dichloroethane"); //improve choice, should not be a pathway text
		
		//Step 6: pick view - List mode
		selectSpecies = driver.findElement(By.xpath("//*[@id=\"nsselect\"]/tbody/tr/td[6]/select/option[1]"));
		selectSpecies.click();
		checkTextAndAssert("next 300");
		
		//Step7: open pathway
		selectSpecies = driver.findElement(By.linkText("Abscisic Acid Biosynthesis (At)"));
		selectSpecies.click();
		checkTextAndAssert("Abscisic Acid Biosynthesis (Arabidopsis thaliana)"); //check pathway title
		checkTextAndAssert("Ontology Tags"); // check body > default titles
		checkTextAndAssert("Changed xrefs"); // check body > content
				
		assertTrue(true);
		//fail("Not yet implemented");
		//return true;
	}
	
	private void checkTextAndAssert(String text){
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		assertTrue("Text '"+text+"' not found!", list.size() > 0);
	}

}
