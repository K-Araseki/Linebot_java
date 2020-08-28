package com.example.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@LineMessageHandler
    public class Callback {

        private static final Logger log = LoggerFactory.getLogger(Callback.class);

        // フォローイベントに対応する
        @EventMapping
        public TextMessage handleFollow(FollowEvent event) {
            // 実際の開発ではユーザIDを返信せず、フォロワーのユーザIDをデータベースに格納しておくなど
            String userId = event.getSource().getUserId();
            return replyFirst("あなたのユーザIDは " + userId);
        }

        // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
        @EventMapping
        public Message handleMessage(MessageEvent<TextMessageContent> event) throws Exception{
            TextMessageContent tmc = event.getMessage();
            String text = tmc.getText();
            return reply(text);
            //switchでメソッドを仕分ける。教科選択if(text.equals("英単語")||text.equals("社会")||text.equals("化学"))、解答、
            // それ以外はもう一度入力してくださいの出力
        }

        // 返答メッセージを作る（教科選択）
        @EventMapping
        private TextMessage reply(String text) throws Exception{
            String sql;

            if(text.equals("英単語")) {sql="select * from 英単語 where questionNumber = ?";}
            else if(text.equals("社会")){sql="select * from 社会 where questionNumber = ?";}
            else {sql="select * from 化学 where questionNumber = ?";}

            Question question = new Question(sql);

            int n = 1;
            //new java.util.Random().nextInt(100);
            List<PreExam> returning = question.selectPreExams(n);
            return new TextMessage(returning.get(0).getMonndai() + returning.get(0).getSentaku1() + returning.get(0).getSentaku2()
                    + returning.get(0).getSentaku3() + returning.get(0).getSentaku4());

        }
        //解答表示
        //private TextMessage answer(String text) throws Exception{}
    
        // 返答メッセージを作る
        private TextMessage replyFirst(String text) {
            return new TextMessage(text);
        }

        /*// 確認メッセージをpush
        @GetMapping("confirm")
        public String pushConfirm() {
            String text = "質問だよ";
            try {
                Message msg = new TemplateMessage(text,
                        new ConfirmTemplate("いいかんじ？",
                                new PostbackAction("おけまる", "CY"),
                                new PostbackAction("やばたん", "CN")));
                PushMessage pMsg = new PushMessage(userId, msg);
                BotApiResponse resp = client.pushMessage(pMsg).get();
                log.info("Sent messages: {}", resp);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            return text;
        }*/
    }


