package appiumAutomation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class hybridBaseClass {
	public static AndroidDriver<AndroidElement> capabilities(String device) throws MalformedURLException
	{
		File f = new File("src");
		File fs = new File(f,"ApiDemos-debug.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		if(device.equals("emulator"))
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2Android9");
		else if(device.equals("real"))
		{
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
		}
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability("autoGrantPermissions",true);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		AndroidDriver<AndroidElement> ad = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return ad;
		//AndroidUIAutomator	
		
	}

}
