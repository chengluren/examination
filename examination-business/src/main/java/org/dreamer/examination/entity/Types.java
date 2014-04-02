package org.dreamer.examination.entity;

/**
 * Created by lcheng on 2014/4/1.
 */
public class Types {

    //题型
    enum QuestionType {

        Choice("Choice"), Completion("Completion"), TrueFalse("TrueFalse"), ShortAnswer("ShortAnswer");

        private String name;
        private QuestionType(String name){
            this.name= name;
        }

        public String getName(){
            return this.name;
        }
    }
}
