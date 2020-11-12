package com.example.casio;

import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;

import com.example.casio.Utils.SQLHelper;
import com.example.casio.login.LoginActivity;
import com.example.casio.models.User;
import com.google.android.material.textfield.TextInputLayout;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import androidx.test.InstrumentationRegistry;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class RegiterLoginTest extends TestCase {
    String userErr1 = "Bạn phải nhập email hoặc số điện thoại hợp lệ";
    Button btnSignIn, btnSignUp;
    TextInputLayout user, pass;
    LoginActivity loginActivity;
    List<User> list;
    public SQLHelper sqlHelper;
    @Before
    public void setUp() throws Exception{
        loginActivity = new LoginActivity();
        sqlHelper = new SQLHelper(InstrumentationRegistry.getTargetContext());
        sqlHelper.open();
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        btnSignIn = loginActivity.findViewById(R.id.btnSignIn);
        btnSignUp = loginActivity.findViewById(R.id.btnSignUp);
        user = loginActivity.findViewById(R.id.user);
        pass = loginActivity.findViewById(R.id.pass);
        list = sqlHelper.getAllUser();
    }
    @Test
    public void testNotNull() throws Exception {
        assertNotNull(loginActivity);
        assertNotNull(btnSignIn);
        assertNotNull(btnSignUp);
        assertNotNull(user);
        assertNotNull(pass);
    }
    @Test
    public void testSignUpClick() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("hungdv72@wr.com");
                pass.getEditText().setText("1mbyi2ioC*");
//                loginActivity.login(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(list.size(), is(1));
//        Assert.assertThat(null,null,null);
    }
}
