package org.richfaces.sandbox.chart;

import static org.jboss.arquillian.graphene.Graphene.waitAjax;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.javascript.JavaScript;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import category.Failing;


@RunWith(Arquillian.class)
public class ChartTest {

	private static final String WEBAPP_PATH = "src/test/webapp";
	private static final String INDEX_PAGE = "faces/index.xhtml";

	private int seriesCount;

	@Drone
	WebDriver browser;

	@ArquillianResource
	URL deploymentUrl;

	Actions builder;

	@Deployment
	public static WebArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class,
				"this-component.jar");
		jar.merge(
				ShrinkWrap.create(GenericArchive.class)
						.as(ExplodedImporter.class)
						.importDirectory("target/classes/")
						.as(GenericArchive.class), "/", Filters.includeAll());

		WebArchive arch = ShrinkWrap
				.create(WebArchive.class)
				.addClass(ChartTest.class)
				.addClass(EventBean.class)
				.addAsWebResource(new File(WEBAPP_PATH, "index.xhtml"))
				.addAsWebInfResource(
						new StringAsset("<faces-config version=\"2.0\"/>"),
						"faces-config.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(
						Maven.resolver().loadPomFromFile("pom.xml")
								.resolve(
										"org.richfaces:richfaces:5.0.0-SNAPSHOT").withClassPathResolution(false)
								.withTransitivity().asFile())
				.addAsLibraries(jar)
				.setWebXML(new File(WEBAPP_PATH, "web.xml"));

		return arch;

	}

	@JavaScript
	ChartJs chtestjs;


	@Before
	public void setExpectedValues(){
		//eventBean.getCountries().size();
		seriesCount=4;
	}

	@Before
	public void init(){
		builder = new Actions(browser);
	}

	@RunAsClient
	@Test
	public void ChartCreated() {
		browser.get(deploymentUrl.toExternalForm());

		Assert.assertEquals("Hello World!",chtestjs.hello());

		Assert.assertNotNull("Chart should be on page.", browser.findElement(By.id("frm:chartChart")));

		Assert.assertNotNull("Plot canvas created.",browser.findElement(By.xpath("//div[@id='frm:chartChart']/canvas[@class='flot-base']")));

		Assert.assertEquals(seriesCount,chtestjs.seriesLength("frm:chart"));
	}

	@FindBy(id="clickInfo")
	WebElement clickSpan;


	@FindBy(xpath="//div[@id='frm:chartChart']/canvas[@class='flot-overlay']")
	WebElement chartCanvas;


	@RunAsClient
	@Test
	@Category(Failing.class)
	public void ClientSideClick(){
		browser.get(deploymentUrl.toExternalForm());

		Action click = builder.moveToElement(chartCanvas,
				chtestjs.pointXPos("frm:chart", 0, 0),
				chtestjs.pointYPos("frm:chart", 0, 0))
				.click().build();

		click.perform();


		//crop decimal places
		double xVal = chtestjs.pointX("frm:chart", 0, 0);
		int xValInt = (int) xVal;

		String expected =  Integer.toString(xValInt)  +
				','+ Double.toString(chtestjs.pointY("frm:chart", 0, 0));


		Assert.assertEquals(expected, clickSpan.getText());
	}

	@FindBy(id="frm:msg")
	WebElement msg;


	@RunAsClient
	@Test
	@Category(Failing.class)
	public void ServerSideClick(){
		browser.get(deploymentUrl.toExternalForm());

		String before = msg.getText();

		int x = chtestjs.pointXPos("frm:chart", 0, 0);
		int y = chtestjs.pointYPos("frm:chart", 0, 0);

		Action click = builder.moveToElement(chartCanvas,x,y)
                .click().build();


		click.perform();

		waitAjax().until().element(msg).text().not().equalTo(before);

		int seriesIndex = 0;
		int pointIndex = 0;
		//crop decimal places
		double xVal = chtestjs.pointX("frm:chart", seriesIndex, pointIndex);
		int xValInt = (int) xVal;

		String expected = "Server's speaking:Point with index "+pointIndex +
				"within series "+seriesIndex+" was clicked. Point coordinates [" +
				Integer.toString(xValInt)  +','
				+ Double.toString(chtestjs.pointY("frm:chart", 0, 0))+"]";



		Assert.assertEquals(expected, msg.getText());
	}



}