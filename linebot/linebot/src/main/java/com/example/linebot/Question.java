package com.example.linebot;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.*;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question {
    private static final String URL = "jdbc:h2:file:~/h2db/test_demo;Mode=PostgreSQL;AUTO_SERVER=TRUE;;MV_STORE=false";
    private static final String USER_NAME = "b2190080";
    private static final String USER_PASS = "b2190080";
    public String sql;

    public Question(String sql) {
        this.sql = sql;
    }

    @EventMapping
    public List<PreExam> selectPreExams(int questionNumber) throws Exception{
        List<PreExam> returning = new ArrayList<PreExam>();
        //String sql = "select * from 英単語 where questionNumber = ?";

        try(Connection conn = DriverManager.getConnection(URL,USER_NAME,USER_PASS);
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,questionNumber);
            ResultSet results = stmt.executeQuery();
            while (results.next()){
                var col0 = results.getInt("questionNumber");
                var col1 = results.getString("mondai");
                var col2 = results.getString("sentaku1");
                var col3 = results.getString("sentaku2");
                var col4 = results.getString("sentaku3");
                var col5 = results.getString("sentaku4");
                var col6 = results.getInt("answer");
                var col7 = results.getString("kaisetu");
                var preExam = new PreExam(col0,col1,col2,col3,col4,col5,col6,col7);
                returning.add(preExam);
            }
        }
        return returning;
    }
}
