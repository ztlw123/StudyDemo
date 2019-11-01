import io.rong.RongCloud;
import io.rong.methods.chatroom.Chatroom;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.chatroom.ChatroomMember;
import io.rong.models.chatroom.ChatroomModel;
import io.rong.models.response.ChatroomUserQueryResult;
import io.rong.models.response.CheckChatRoomUserResult;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

import java.io.Reader;

/**
 * @Author zjh
 * @Date 2019/01/17,11:38
 */
public class RongCloudDemo {

    final static String appKey = "25wehl3u2g8ew";
    final static String appSecret = "3bOGvylAKg9";

    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
//        Reader reader = null;
//        ChatroomModel[] chatrooms = new ChatroomModel[]{(new ChatroomModel()).setId("chatroomId1").setName("chatroomName1"), (new ChatroomModel()).setId("chatroomId2").setName("chatroomName2")};
//        ResponseResult result = chatroom.create(chatrooms);
//        System.out.println("create:  " + result.toString());
//        ChatroomModel chatroomModel = (new ChatroomModel()).setId("d7ec7a8b8d8546c98b0973417209a548");
//        chatroomModel = (new ChatroomModel()).setId("chatroomId1");
//        ChatroomUserQueryResult chatroomQueryUserResult = chatroom.get(chatroomModel);
//        System.out.println("queryUser:  " + chatroomQueryUserResult.toString());
//        ChatroomMember member = (new ChatroomMember()).setId("76894").setChatroomId("76891");
//        CheckChatRoomUserResult checkMemberResult = chatroom.isExist(member);
//        System.out.println("checkChatroomUserResult:  " + checkMemberResult.isInChrm);
        UserModel model = new UserModel("1", "hh", "pic.jpg");
        User user = rongCloud.user;
        Result result = user.register(model);
        System.out.println(result.toString());
    }
}
