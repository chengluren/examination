package org.dreamer.examination.entity;

/**
 * Created by lcheng on 2014/4/1.
 */
public class Types {

    //题型
    public enum QuestionType {

        Choice("Choice"),MultipleChoice("MultipleChoice"), Completion("Completion"),
        TrueFalse("TrueFalse"), ShortAnswer("ShortAnswer");

        private String name;
        private QuestionType(String name){
            this.name= name;
        }

        public String getName(){
            return this.name;
        }

        public String getShortName(){
            switch (this){
                case Choice:
                    return "CH";
                case MultipleChoice:
                    return "MC";
                case Completion:
                    return "CO";
                case TrueFalse:
                    return "TF";
                case ShortAnswer:
                    return "SA";
                default:
                    return "";
            }
        }

        public static QuestionType getTypeFromShortName(String type){
             switch (type){
                 case "CH":
                     return QuestionType.Choice;
                 case "MC":
                     return QuestionType.MultipleChoice;
                 case "CO":
                     return QuestionType.Completion;
                 case "TF":
                     return QuestionType.TrueFalse;
                 case "SA":
                     return QuestionType.ShortAnswer;
                 default:
                     return null;
             }
        }
    }

    public enum DegreeType{
        Bachelor("Bachelor"),Master("Master");
        private String name;
        private DegreeType(String name){
            this.name = name;
        }
    }
}
