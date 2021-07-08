package com.tea.myapplication.Common;

import com.tea.myapplication.Model.UserModel;

import java.util.Random;

public class Common {
    public static final String USER_REFERENCE = "People";
    public static final String CHAT_LIST_REFERENCE = "ChatList";
    public static final String CHAT_REFERENCE = "Chat";
    public static final String CHAT_DETAIL_REFERENCE = "Detail";

    public static UserModel currentUser = new UserModel();
    public static UserModel chatUser = new UserModel();

    public static String generateChatRoomId(String a, String b) {
        if (a.compareTo(b) > 0) {
            return a + b;
        } else if (a.compareTo(b) < 0) {
            return b + a;
        } else {
            return "Chat_Your_Self_Error" + new Random().nextInt();
        }
    }

    public static String getName(UserModel chatUser) {
        return chatUser.getFirstName() + " " + chatUser.getLastName();
    }
}
