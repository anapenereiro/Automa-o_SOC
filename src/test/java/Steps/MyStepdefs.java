package Steps;

import Pages.Web;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class MyStepdefs {
    private WebDriver driver;
    private ExtentHtmlReporter htmlReport;
    private ExtentReports extentReport;
    private ExtentTest extentTest;


    @Before
    public void setUp(Scenario cenario) {
        if(extentReport == null){
            extentReport = new ExtentReports();
            htmlReport = new ExtentHtmlReporter("src\\test\\java\\Test\\htmlReporter.html");
            extentReport.attachReporter(htmlReport);
        }
        extentTest = extentReport.createTest(cenario.getId());

        //Chama a classe creatChrome
        driver = Web.CreateWebDriver();
    }

//------------Busca
    @Dado("que eu esteja na página do blog da SOC")
    public void queEuEstejaNaPáginaDoBlogDaSOC() {

        driver.get("https://ww2.soc.com.br/blog/");
        driver.findElement(By.id("barra-cookie")).click();

    }
    @Quando("informar a palavra chave no campo busca blog")
    public void informarAPalavraChaveNoCampoBuscaBlog() {
        driver.findElement(By.xpath("//input[@class ='form-control input-com-icone mt0']")).sendKeys("Covid-19");

    }

    @E("clico em pesquisar")
    public void clicoEmPesquisar() {
        driver.findElement(By.className("lupa-form")).click();

    }

    @Então("visualizo o resultado da pesquisa")
    public void visualizoOResultadoDaPesquisa() {

        String textoElement = driver.findElement(By.xpath("//h2[@class = 'pagetitle search']")).getText();

        Assert.assertEquals("RESULTADO DA SUA BUSCA NO BLOG: COVID-19", textoElement);
    }
//------FAP
    @E("acesso no menu superior a opção FAP")
    public void acessoNoMenuSuperiorAOpçãoFAP() {
        driver.findElement(By.xpath("//nav[@id='navegacao-superior']//li[@class='mostra-submenu']")).click();
        driver.findElement(By.xpath("//div//div[@class='popover-submenu']//ul//a[@href='/fap']")).click();
    }

    @Quando("na página da FAP procuro o formulário FAP")
    public void naPáginaDaFAPProcuroOFormulárioFAP() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("nomeEmpresa")));

    }

   @E("informo os campos do formulário")
   public void informoOsCamposDoFormulario() {
        driver.findElement(By.id("nomeEmpresa")).sendKeys("Teste Empresa");
        driver.findElement(By.id("fap")).sendKeys("5");
        driver.findElement(By.xpath("//div[@class ='selectric-wrapper selectric-wpcf7-form-control selectric-wpcf7-select selectric-wpcf7-validates-as-required selectric-form-control']")).click();
        driver.findElement(By.xpath("//div[@class ='selectric-items']//div//ul//li[@data-index=\"2\"]")).click();
        driver.findElement(By.id("projecao")).sendKeys("500000");

   }

    @Então("clico no botão Estimar valor FAP")
    public void clicoNoBotãoEstimarValorFAP() {
        driver.findElement(By.id("estimar")).click();
    }

    @E("vejo o relatório gerado")
    public void vejoORelatórioGerado() {
        String textoElement = driver.findElement(By.className("header-relatorio")).getText();

        Assert.assertEquals("ESTIMATIVA FAP DA EMPRESA", textoElement);

    }

    @After
    public void tearDown(Scenario cenario)
    {
        extentTest.log(Status.PASS, "Cenário" +  cenario.getName()+ "executado com sucesso!");
        extentReport.flush();
        //Fecha o browser depois que termina os cenarios
        driver.quit();

    }


}

