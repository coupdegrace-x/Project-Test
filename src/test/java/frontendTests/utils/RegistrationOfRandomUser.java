package frontendTests.utils;

import frontendTests.pages.RegisterPage;

public class RegistrationOfRandomUser {

    public void userRegistration(RegisterPage registerPage) {
        final String passwordUser = RandomUserData.getRandomPassword();

        registerPage.openRegisterPageChain()
                .enterFirstNameChain(RandomUserData.getRandomFirstName())
                .enterLastNameChain(RandomUserData.getRandomLastName())
                .enterEmailChain(RandomUserData.getRandomEmail())
                .enterPasswordChain(passwordUser)
                .enterConfirmPasswordChain(passwordUser)
                .clickRegisterButton();
    }
}
