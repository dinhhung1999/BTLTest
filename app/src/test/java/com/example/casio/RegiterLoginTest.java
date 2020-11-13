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
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(1));
    }
    @Test
    public void testRegister() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("hungdv72.com");
                pass.getEditText().setText("1mbyi2ioC*");
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(0));
        assertThat(user.getError().toString(), is("Bạn phải nhập email hoặc số điện thoại hợp lệ"));
    }
    @Test
    public void testRegister3() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("0982219082555");
                pass.getEditText().setText("1mbyi2ioC*");
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(0));
        assertThat(user.getError().toString(), is("Bạn phải nhập email hoặc số điện thoại hợp lệ"));
    }
    @Test
    public void testRegister4() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1mbyi");
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(0));
        assertThat(pass.getError(), is("Mật khẩu phải có trên 6 ký tự"));
    }
    @Test
    public void testRegister5() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1abcdefg*");
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(0));
        assertThat(pass.getError().toString(), is("Mật khẩu phải có 1 ký tự viết hoa"));
    }
    @Test
    public void testRegister6() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1aBcdefg");
//                loginActivity.onRegister(user,pass);
                btnSignUp.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(0));
        assertThat(pass.getError().toString(), is("Mật khẩu phải có 1 ký tự đặc biệt"));
    }
    @Test
        public void testRegister7() throws Exception {
            loginActivity.runOnUiThread(new Runnable() {
                public void run() {
                    user.getEditText().setText("hungdv72@wru.vn");
                    pass.getEditText().setText("1ABCDDDDD*");
//                loginActivity.onRegister(user,pass);
                    btnSignUp.performClick();
                }
            });
            shadowOf(Looper.getMainLooper()).idle();
            list = sqlHelper.getAllUser();
            assertThat(list.size(), is(0));
            assertThat(pass.getError().toString(), is("Mật khẩu phải có 1 ký tự viết thường"));
    }
    @Test
        public void testRegister8() throws Exception {
            loginActivity.runOnUiThread(new Runnable() {
                public void run() {
                    user.getEditText().setText("hungdv72@wru.vn");
                    pass.getEditText().setText("sgdsdsgT*");
                    btnSignUp.performClick();
                }
            });
            shadowOf(Looper.getMainLooper()).idle();
            list = sqlHelper.getAllUser();
            assertThat(list.size(), is(0));
            assertThat(pass.getError().toString(), is("Mật khẩu phải có 1 chữ số"));
    }
    @Test
        public void testRegister9() throws Exception {
            loginActivity.runOnUiThread(new Runnable() {
                public void run() {
                    user.getEditText().setText("hungdv72@wru.vn");
                    pass.getEditText().setText("1Agadgd  dgsg*");
                    btnSignUp.performClick();
                }
            });
            shadowOf(Looper.getMainLooper()).idle();
            list = sqlHelper.getAllUser();
            assertThat(list.size(), is(0));
            assertThat(pass.getError().toString(), is("Mật khẩu không được chứa khoảng trống"));
    }
    @Test
        public void testRegister10() throws Exception {
            loginActivity.runOnUiThread(new Runnable() {
                public void run() {
                    user.getEditText().setText("hungdv72@wru.vn");
                    pass.getEditText().setText("1Agadgddgsg*");
                    btnSignUp.performClick();
                    user.getEditText().setText("hungdv72@wru.vn");
                    pass.getEditText().setText("1Agadgddgsg*");
                    btnSignUp.performClick();
                }
            });
            shadowOf(Looper.getMainLooper()).idle();
            list = sqlHelper.getAllUser();
            assertThat(list.size(), is(1));
            assertThat(user.getError().toString(), is("Email đã tồn tại"));
    }
    @Test
    public void testLogin() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1Abggax*");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(list.size(), is(1));
        assertTrue(user.getError()==null);
        assertTrue(pass.getError()==null);
    }
    @Test
    public void testLogin2() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1Abgax*");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertThat(pass.getError().toString(), is("Mật khẩu không đúng"));
    }
    @Test
    public void testLogin3() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("hungdv72@wru.vn");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("hungd2@wru.vn");
                pass.getEditText().setText("1Abgax*");
                btnSignIn.performClick();;
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(user.getError().toString(), is("Tên đăng nhập không tồn tại"));
    }
    @Test
    public void testLogin4() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("0922190825");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("0922190825");
                pass.getEditText().setText("1Abggax*");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        list = sqlHelper.getAllUser();
        assertTrue(user.getError()==null);
        assertTrue(pass.getError()==null);
    }
    @Test
    public void testLogin5() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("0922190825");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("0922190827");
                pass.getEditText().setText("1Abggaxwere");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(user.getError().toString(), is("Tên đăng nhập không tồn tại"));
    }
    @Test
    public void testLogin6() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("0922190825");
                pass.getEditText().setText("1Abggax*");
                btnSignUp.performClick();
                user.getEditText().setText("0922190825");
                pass.getEditText().setText("1Abggax");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(pass.getError().toString(), is("Mật khẩu không đúng"));
    }
    @Test
    public void testLogin7() throws Exception {
        loginActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user.getEditText().setText("xf");
                pass.getEditText().setText("1Abggax");
                btnSignIn.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(user.getError().toString(), is("Bạn phải nhập email hoặc số điện thoại hợp lệ"));
    }
}
