package com.example.casio;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.casio.Utils.SQLHelper;
import com.example.casio.fx.FxActivity;
import com.example.casio.login.LoginActivity;

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
public class FxActivityTest {
    TextView tvTitle, tvResult, tvX;
    Button btn_timX;
    EditText etA, etB, etC;
    FxActivity fxActivity;

    @Before
    public void setUp() throws Exception {
        fxActivity = new FxActivity();
        fxActivity = Robolectric.setupActivity(FxActivity.class);
        tvTitle = fxActivity.findViewById(R.id.tv_Title);
        tvResult = fxActivity.findViewById(R.id.tv_result);
        tvX = fxActivity.findViewById(R.id.tv_X);
        btn_timX = fxActivity.findViewById(R.id.btn_timX);
        etA = fxActivity.findViewById(R.id.et_A);
        etB = fxActivity.findViewById(R.id.et_B);
        etC = fxActivity.findViewById(R.id.et_C);
    }

    @Test
    public void testNotNull() throws Exception {
        assertNotNull(fxActivity);
        assertNotNull(tvTitle);
        assertNotNull(tvResult);
        assertNotNull(tvX);
        assertNotNull(btn_timX);
        assertNotNull(etA);
        assertNotNull(etB);
        assertNotNull(etC);
    }

    @Test
    public void testPtbac1() throws Exception {
        fxActivity.runOnUiThread(new Runnable() {
            public void run() {
                etA.setText("2");
                etB.setText("4");
                btn_timX.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(tvX.getText().toString(), is("x= -2.0"));
    }

    @Test
    public void testPtbac1_2() throws Exception {
        fxActivity.runOnUiThread(new Runnable() {
            public void run() {
                etA.setText("0");
                etB.setText("4");
                btn_timX.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(tvResult.getText().toString(), is("Phương trình có vô nghiệm"));
    }

    @Test
    public void testPtbac1_3() throws Exception {
        fxActivity.runOnUiThread(new Runnable() {
            public void run() {
                etA.setText("0");
                etB.setText("0");
                btn_timX.performClick();
            }
        });
        shadowOf(Looper.getMainLooper()).idle();
        assertThat(tvResult.getText().toString(), is("Phương trình có vô số nghiệm"));
    }

    @Test
    public void testPtbac2_1() throws Exception {
        fxActivity.ptbac2(0, 0, 1);
        assertThat(tvResult.getText().toString(), is("Phương trình có vô nghiệm"));
    }

    @Test
    public void testPtbac2_2() throws Exception {
        fxActivity.ptbac2(0, 2, 4);
        assertThat(tvResult.getText().toString(), is("Phương trình có 1 nghiệm duy nhất"));
        assertThat(tvX.getText().toString(), is("x= -2.0"));
    }

    @Test
    public void testPtbac2_3() throws Exception {
        fxActivity.ptbac2(1, 4, 1);
        assertThat(tvResult.getText().toString(), is("Phương trình có 2 nghiệm là"));
        assertThat(tvX.getText().toString(), is("x1= -0.2679491924311228          x2= -3.732050807568877"));
    }

    @Test
    public void testPtbac2_4() throws Exception {
        fxActivity.ptbac2(1, 2, 1);
        assertThat(tvResult.getText().toString(), is("Phương trình có nghiệm kép là"));
        assertThat(tvX.getText().toString(), is("x1=x2 = -1.0"));
    }

    @Test
    public void testPtbac2_5() throws Exception {
        fxActivity.ptbac2(8, 2, 5);
        assertThat(tvResult.getText().toString(), is("Phương trình có vô nghiệm"));
    }

}
