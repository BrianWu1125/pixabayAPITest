package com.brianwu.phototest;

import com.brianwu.utils.NetworkUtil;
import com.brianwu.network.APIRequst;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void apiTest() throws Exception {
        NetworkUtil.setNetWorkForICS();
        assertNull(APIRequst.getInstance().getPixbayData("cat"));
    }
}
