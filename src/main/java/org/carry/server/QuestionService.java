package org.carry.server;

import org.apache.ibatis.session.RowBounds;
import org.carry.dto.PaginationDTO;
import org.carry.dto.QuestionDTO;
import org.carry.mapper.QuestionMapper;
import org.carry.mapper.UserMapper;
import org.carry.model.Question;
import org.carry.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-20 13:08
 * @Description: ${Description}
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
