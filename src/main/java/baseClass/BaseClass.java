package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	
	public static Properties prop;
	public static WebDriver driver;
	public static Logger logger;
	
	
	@BeforeClass
	public void setup() {
		
		logger =LogManager.getLogger(this.getClass());
		
		if(driver==null) {
			try {
				prop=new Properties();
				FileInputStream fis= new FileInputStream("C:\\Users\\chinm\\OneDrive\\Desktop\\mainPractice\\zVguard\\src\\main\\resources\\configProperties\\config.properties");
				prop.load(fis);
				
			} catch( FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String browser= prop.getProperty("browser");
			
			if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				
				ChromeOptions opt=new ChromeOptions();
				opt.addArguments("--start-Maximized");
				driver=new ChromeDriver(opt);
			}
			else if(browser.equalsIgnoreCase("edeg")) {
				WebDriverManager.edgedriver().setup();
				EdgeOptions opt=new EdgeOptions();
				opt.addArguments("--incognito");
				opt.addArguments("--headless");
				driver=new EdgeDriver(opt);
			} else {
				throw new RuntimeException("invalidBrowser"+ browser);
			}
			
			
			driver.get(prop.getProperty("url"));
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		
		
	}
	
	@AfterClass
	public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}
	
	
	public String captureScreen(String ss) throws IOException{
		String timestamp=new SimpleDateFormat("dd.MM.yyyy.hh.mm.ss").format(new Date());
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);

		String fileDrop=System.getProperty("user.dir")+"\\screenshots\\"+ss+timestamp+".png";
		File trg=new File(fileDrop);
		
		FileUtils.copyFile(src, trg);
		return (fileDrop);
	}
	
	
	
	
}
