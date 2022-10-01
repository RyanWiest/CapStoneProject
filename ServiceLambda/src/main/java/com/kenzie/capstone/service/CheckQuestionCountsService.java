package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.QuestionCountsDao;
import com.kenzie.capstone.service.model.QuestionCountsRecord;
import com.kenzie.capstone.service.model.QuestionCountsRequest;
import com.kenzie.capstone.service.model.QuestionCountsResponse;

import javax.inject.Inject;
import java.util.HashMap;

public class CheckQuestionCountsService {
    private QuestionCountsDao questionCountsDao;

    @Inject
    public CheckQuestionCountsService(QuestionCountsDao questionCountsDao) {
        this.questionCountsDao = questionCountsDao;
    }

    public QuestionCountsResponse addQuestion(QuestionCountsRequest questionIdRequest) {

        if (questionIdRequest == null) {
            throw new IllegalArgumentException("Request must " +
                    "contain a valid question ID");
        }

        // Will need to keep track if the questionId has been used or not.
        // if it hasn't been used add that question in the map and set value to 1
        // if it has been used then just increase value by 1
        // check if recordId is in the database


        QuestionCountsRecord record = new QuestionCountsRecord();
        record.setQuestionId(questionIdRequest.getQuestionId());
        // need to increment here if new record
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer key = record.getQuestionId();
        Integer value = 0;
        if(!map.containsValue(value)){
            value = 1;
            map.put(key, value);
        }else {
            value += value;
            map.put(key, value);
        }




        questionCountsDao.storeQuestionCountsData(record);

        QuestionCountsResponse response =
                new QuestionCountsResponse();
        response.setQuestionId(record.getQuestionId());
        response.setCountsPicked(record.getPickedCounts());
        return response;
    }
}
