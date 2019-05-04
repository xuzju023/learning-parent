package lambda.old;

public class Soft  implements Strategy {

    public String approach(String msg) {
        return msg.toLowerCase() + "?";
    }
}
