package afomic.com.camfood.ui.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import afomic.com.camfood.data.AuthManager;
import afomic.com.camfood.data.SharedPreferenceHelper;
import afomic.com.camfood.model.User;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {
    @Mock
    LoginView loginView;
    @Mock
    AuthManager authManager;
    @Mock
    SharedPreferenceHelper sharedPreferenceHelper;

    @Captor
    ArgumentCaptor<AuthManager.AuthCallback> authCallbackArgumentCaptor;

    private LoginPresenter loginPresenter;

    @Before
    public void setUp() {
        loginPresenter = new LoginPresenter(loginView, authManager, sharedPreferenceHelper);
    }

    @Test
    public void shouldShowErrorWhenLoginWithEmptyPassword() {
        loginPresenter.loginUser("afomic1@gmail.com", "");
        verify(loginView).showMessage(anyString());
    }

    @Test
    public void shouldShowErrorWhenLoginWithEmptyEmail() {
        loginPresenter.loginUser("", "assword");
        verify(loginView).showMessage(anyString());
        verify(loginView, never()).showProgressView();
    }

    @Test
    public void shouldShowErrorMessageWhenLoginWithWrongCredential() {
        String emailAddress = "afomic1@gmail.com";
        String password = "wrongpassword";
        String errorMessage = "Wrong email or password";
        loginPresenter.loginUser(emailAddress, password);
        verify(authManager).login(eq(emailAddress), eq(password), authCallbackArgumentCaptor.capture());
        AuthManager.AuthCallback callback = authCallbackArgumentCaptor.getValue();
        callback.onError(errorMessage);
        verify(loginView).showMessage(eq(errorMessage));
        verify(loginView).showProgressView();
    }

    @Test
    public void shouldShowHomeActivityWhenLoginWithRightCredential() {
        String emailAddress = "afomic1@gmail.com";
        String password = "wrongpassword";
        loginPresenter.loginUser(emailAddress, password);
        verify(authManager).login(eq(emailAddress), eq(password), authCallbackArgumentCaptor.capture());
        AuthManager.AuthCallback callback = authCallbackArgumentCaptor.getValue();
        callback.onSuccess(new User());
        verify(loginView).showProgressView();
        verify(loginView).showHomeView();
        verify(sharedPreferenceHelper).saveBooleanPref(SharedPreferenceHelper.PREF_USER_EXIST, true);

    }
}