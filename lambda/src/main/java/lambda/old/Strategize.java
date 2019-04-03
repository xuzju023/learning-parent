package lambda.old;

public class Strategize {
    Strategy strategy;
    String msg;
   public Strategize(String msg) {
        strategy = new Soft(); // [1]
        this.msg = msg;
    }
    void communicate() {
        System.out.println(strategy.approach(msg));
    }
    void changeStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public static void main(String[] args) {
        Strategy[] strategies = {
                new Strategy() { // [2]
                    public String approach(String msg) {
                        return msg.toUpperCase() + "!";
                    }
                },
                msg -> msg.substring(0, 5), // [3]
                Unrelated::twice // [4]
        };
        Strategize s = new Strategize("Hello there");
        s.communicate();
        for(Strategy newStrategy : strategies) {
            s.changeStrategy(newStrategy); // [5]
            s.communicate(); // [6]
        }
    }
}
