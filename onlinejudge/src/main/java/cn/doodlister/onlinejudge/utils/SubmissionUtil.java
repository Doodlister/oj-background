package cn.doodlister.onlinejudge.utils;

public class SubmissionUtil {
    public static boolean  isContainLanguage(String language){
        switch (language){
            case "Java":
            case "C":
            case "C++":
                return true;
            default:
                return false;
        }
    }
}
