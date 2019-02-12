package CheckBook.DataAccess.Models;

public class MyEmail {


    private String content = "";

    private final String subject = "Feedback has been received by Checkbook";


    public MyEmail() {

    }

    public MyEmail(String text) {

        content = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSubject() {
        return subject;
    }
}
