package com.dorothy.security.social.qq.api.connect;

import com.dorothy.security.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        values.setDisplayName(api.getUserInfo().getNickname());
        values.setImageUrl(api.getUserInfo().getFigureurl_qq_1());
        values.setProfileUrl(null);
        values.setProviderUserId(api.getUserInfo().getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
    }
}
