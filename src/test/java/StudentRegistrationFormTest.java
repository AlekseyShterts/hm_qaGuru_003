import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void registrationFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Dolly");
        $("#lastName").setValue("Sheep");
        $("#userEmail").setValue("SheepDolly@example.com");
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").setValue("79995550199");
        //input dateOfBirth by string
        //$("#dateOfBirthInput").setValue("05 Feb 1996");
        //input dateOfBirth by datepick form
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(String.valueOf("February"));
        $(".react-datepicker__year-select").selectOption(String.valueOf("1996"));
        $(".react-datepicker__day--005").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("Edinburgh");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Uttar Pradesh")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Agra")).click();
        $("#submit").click();

        // Check result
        $("#example-modal-sizes-title-lg").shouldHave(exactText("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                Condition.text("Student Name Dolly Sheep"),
                Condition.text("Student Email SheepDolly@example.com"),
                Condition.text("Gender Other"),
                Condition.text("Mobile 7999555019"),
                Condition.text("Date of Birth 05 February,1996"),
                Condition.text("Subjects Computer Science"),
                Condition.text("Hobbies Sports"),
                Condition.text("Picture picture.jpg"),
                Condition.text("Address Edinburgh"),
                Condition.text("State and City Uttar Pradesh Agra")
        );
    }

}
