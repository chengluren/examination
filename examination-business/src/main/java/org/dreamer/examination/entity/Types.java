package org.dreamer.examination.entity;

/**
 * Created by lcheng on 2014/4/1.
 */
public class Types {

    //题型
    enum QuestionType {

        Choice("choice"), Completion("completion"), TrueFalse("trueFalse"), ShortAnswer("shortAnswer");

        private String name;
        private QuestionType(String name){
            this.name= name;
        }

        public String getName(){
            return this.name;
        }
    }
}
