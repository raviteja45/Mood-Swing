package in.scheduling;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ravi on 19-05-2018.
 */

public class TaskHolder implements Serializable{
    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }


    public List<String> getAttachment() {
        return attachment;
    }

    public List<String> getReason() {
        return reason;
    }

    public void setReason(List<String> reason) {
        this.reason = reason;
    }

    public void setAttachment(List<String> attachment) {
        this.attachment = attachment;
    }

    public String getDate() {
        return date;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   String mood;
   List<String> reason;
   String date;
   String time;
   List<String> attachment;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    String comments;

}
