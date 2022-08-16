package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.qa.base.FrameworkBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class WebEventListenerPage extends FrameworkBase implements IReporter, ITestListener {


		public WebEventListenerPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

		private ExtentReports extent;

		public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
//			extent = new ExtentReports(outputDirectory + File.separator + "ExtentReport.html", true);
//			OR
//			extent = new ExtentReports(path + "/ExtendsReport/"+"extentsReport.html", true);

			extent = new ExtentReports(path + "/ExtendsReport" + File.separator + "extentsReport"+getTimeAndDate()+".html", true);
			for (ISuite suite : suites) {
				Map<String, ISuiteResult> result = suite.getResults();

				for (ISuiteResult r : result.values()) {
					ITestContext context = r.getTestContext();

					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				}
			}

			extent.flush();
			extent.close();
		}

		private void buildTestNodes(IResultMap tests, LogStatus status) {
			ExtentTest test;

			if (tests.size() > 0) {
				for (ITestResult result : tests.getAllResults()) {
					test = extent.startTest(result.getMethod().getMethodName());

					test.setStartedTime(getTime(result.getStartMillis()));
					test.setEndedTime(getTime(result.getEndMillis()));

					for (String group : result.getMethod().getGroups())
						test.assignCategory(group);

					if (result.getThrowable() != null) {
						test.log(status, result.getThrowable());
					} else {
						test.log(status, "Test " + status.toString().toLowerCase() + "ed");
					}

					extent.endTest(test);
				}
			}
		}

		private Date getTime(long millis) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			return calendar.getTime();
		}

		int testcount;

		public void onTestStart(ITestResult result) {
			testcount++;

		}

		public void onTestSuccess(ITestResult result) {

			System.out.println("Test Case ::"+testcount);

		}

		public void onTestFailure(ITestResult result) {

			String testCaseName = result.getMethod().getMethodName();
			captureImage(testCaseName);
		}

		public static void captureImage(String testCaseName) {

			TakesScreenshot scrshot = (TakesScreenshot) driver;
			File srcFile = scrshot.getScreenshotAs(OutputType.FILE);
			File destFile = null;
			try {
				destFile = new File(path + "/ScreenShoot/" + testCaseName+getTimeAndDate() + ".png");
			} catch (Exception e) {
			}
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void onTestSkipped(ITestResult result) {

		}
		
		
		public static String  getTimeAndDate() {
	       SimpleDateFormat format=new SimpleDateFormat("dMMMhhmm");
			
			String str=format.format(new Date());
			return str;
		}
		
		

	}

	

