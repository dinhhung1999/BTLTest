package com.example.casio;

import com.example.casio.Utils.SQLHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import static junit.framework.Assert.assertNotNull;

public class DatabaseTest {
    public SQLHelper sqlHelper;

    @Before
    public void setUp(){
        sqlHelper = new SQLHelper(InstrumentationRegistry.getTargetContext());
        sqlHelper.open();
    }
    @After
    public void finish() {
        sqlHelper.close();
    }
    @Test
    public void testPreConditions() {
//        sqlHelper.onCreate();
    }
}
