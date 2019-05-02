package com.rest.api.service.social;

import com.rest.api.model.social.KakaoProfile;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoServiceTest {

    @Autowired
    private KakaoService kakaoService;

    @Test @Ignore
    public void whenGetKakaoProfile_thenReturnProfile() {

        String accessToken = "xjsMzpQtIr4w13FIQvL3R7BW7X4yvm1KmzXCTwopyWAAAAFqMxEcwA";
        // given
        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        // then
        assertNotNull(profile);
        assertEquals(profile.getId(), Long.valueOf(1066788171));
    }
}