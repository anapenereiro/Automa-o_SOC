package Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions( features = "C:\\Users\\anacl\\OneDrive\\Documentos\\GitHub\\TestSOC\\src\\test\\java\\Features",
        glue = "Steps")
public class Tests {

}
